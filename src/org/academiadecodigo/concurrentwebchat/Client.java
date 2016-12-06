package org.academiadecodigo.concurrentwebchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by codecadet on 11/11/16.
 */
public class Client {

    private Socket clientSocket;
    PrintWriter outS = null;     //para o servidor
    //BufferedReader inS = null; //vem do servidor  ---> Posso substituir por um Scanner keyboardInput
    BufferedReader inT = null;  //para o terminal
    private int portNum;
    private String hostName;


    public Client(String hostName, int portNum) {
        this.portNum = portNum;
        this.hostName = hostName;
        inT = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {

        String message = "";

        clientSocket = new Socket(hostName, portNum);
        outS = new PrintWriter(clientSocket.getOutputStream());
        Thread thread = new Thread(new MessageReader());
        thread.start();

        while (true) {
            message = inT.readLine();
            outS.write(message);
            outS.flush();
        }


    }


    private class MessageReader implements Runnable {

        private String message;
        private BufferedReader inS;


        public MessageReader() throws IOException {
            inS = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }


        @Override
        public void run() {
            while (true) { //aqui fazer while (isConnected == true)
                try {
                    message = inS.readLine();
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
