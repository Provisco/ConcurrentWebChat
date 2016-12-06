package org.academiadecodigo.concurrentwebchat;

import java.io.IOException;

/**
 * Created by codecadet on 11/11/16.
 */
public class MainServer {

    public static void main(String[] args) {

        try {
            Server s1 = new Server(5000);
            s1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
