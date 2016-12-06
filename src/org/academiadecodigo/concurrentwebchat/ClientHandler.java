package org.academiadecodigo.concurrentwebchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by codecadet on 11/11/16.
 */
public class ClientHandler implements Runnable {

    Socket clientSocket;
    Server server;
    PrintWriter out = null;
    BufferedReader in;


    public ClientHandler(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        String message = "";

        while (true) {
            try {
                message = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(message);
            server.sendAll(message);

        }
    }


    public void send(String message) {
        out.println("\n" + message);
        out.flush();
    }

}


//falta um metodo send()