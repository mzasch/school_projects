package asta.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;



public class Server {
    double bestOffer = 0;
    static int auctionTime = 1;
    

    public static void main(String[] args) {
        ServerSocket portaAscolto;
        int activeThreads = 0;
        LocalTime startTime = LocalTime.now();
        try {
            portaAscolto = new ServerSocket(9090);
            System.out.println("MS: Main Server started, waiting for connections");
            
            do {
                Socket soketclient = portaAscolto.accept();
                ServerThread st = new ServerThread(soketclient, ++activeThreads);
                st.start();
            } while (startTime.plusMinutes(auctionTime).isBefore(LocalTime.now()));
            
            System.out.println("MS: Time's up!");
            portaAscolto.close();
        }catch(IOException e){
            e.printStackTrace(System.out);
        }
    }
}
