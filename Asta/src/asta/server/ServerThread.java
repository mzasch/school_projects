package asta.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket client = null;
    int tNumber;

    public ServerThread(Socket socket, int tNumber) {
        this.client = socket;
        this.tNumber = tNumber;
    }

    @Override
    public void run() {
        System.out.println("#" + tNumber + ": Thread started");
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            input.close();
            out.close();
            client.close();
            System.out.print("#" + tNumber + ": Thread ending...");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        System.out.println(" ended");
    }
}
