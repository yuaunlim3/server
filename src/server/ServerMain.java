package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    public static void main(String[] args) {
        try{
            //Default port
            int port = 3000;
            if(args.length > 0){
                port = Integer.parseInt(args[0]);
            }

            //Create server port
            ServerSocket server = new ServerSocket(port);
            //Create thread pool - to store the workers
            ExecutorService thrPool = Executors.newFixedThreadPool(2);

            while(true){
                System.out.printf("Waiting for connection on port %d\n", port);
                Socket sock = server.accept();
                System.out.println("Got a new connection");
                ClientHandler handler = new ClientHandler(sock);
                thrPool.submit(handler);
            }


        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
