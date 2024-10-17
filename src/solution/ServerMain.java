package solution;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerMain {
    public static void main(String[] args) {
        try{
            String dirPath = " ";
            int portNum = 0;
            String fileName = " ";
            if(args.length > 0){
                portNum = Integer.parseInt(args[0]);
                dirPath = args[1];
                fileName = args[2];
            }else{
                System.err.println("Invalid number of arguments expected");
                System.exit(0);
            }
            File newDirectory = new File(dirPath);
    
            if (!newDirectory.exists()){
                System.out.println("New directory created");
                newDirectory.mkdir();
            }
            //read and print cookies
            Cookie c = new Cookie();
            c.readCookie(dirPath + File.separator +  fileName);
            c.printCookies();


            ServerSocket server = new ServerSocket(portNum);
            //Create thread pool - to store the workers
            ExecutorService thrPool = Executors.newFixedThreadPool(2);
            System.out.printf("Websocket server started on port: %s\n",portNum );


            while(true){
                Socket sock = server.accept();
                ClientHandler handler = new ClientHandler(sock,c);
                thrPool.submit(handler);
            }

        }
        catch(IOException ex){
            ex.printStackTrace();
        }



    }
}
