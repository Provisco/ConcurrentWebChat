package org.academiadecodigo.concurrentwebchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by codecadet on 11/11/16.
 */
public class Server {


    private int portNum;
    private ArrayList<ClientHandler> clientHandlerList;
    private ServerSocket serverSocket;


    public Server(int portNum) throws IOException {
        this.portNum = portNum;
        serverSocket = new ServerSocket(portNum);
    }


    public void start() throws IOException {
        Socket clientSocket = serverSocket.accept();//is blocking and waits until a client connects
        clientHandlerList = new ArrayList<>();
        ClientHandler clientHandler = new ClientHandler(clientSocket, this);
        Thread thread = new Thread(clientHandler);
        thread.start();
        clientHandlerList.add(clientHandler);
        System.out.println("Connected to new client: " + clientSocket.getInetAddress());
    }


    public synchronized void sendAll(String message) {

            for (int i = 0; i < clientHandlerList.size(); i++) {
                clientHandlerList.get(i).send(message);
            }

    }

}
