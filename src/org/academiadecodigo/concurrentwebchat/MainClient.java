package org.academiadecodigo.concurrentwebchat;

import java.io.IOException;

/**
 * Created by codecadet on 11/11/16.
 */
public class MainClient {

    public static void main(String[] args) throws IOException {

        Client client = new Client("localhost", 5000);
        client.start();

    }
}
