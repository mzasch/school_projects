package knockknock.kkmultiserver;

import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java KKMultiServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;
        int threadCounter = 0;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
                    threadCounter++;
                    new KKMultiServerThread(serverSocket.accept()).start();
                    System.out.println("Thread " + threadCounter + " started!");
                }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
