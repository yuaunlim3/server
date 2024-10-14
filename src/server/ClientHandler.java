package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientHandler implements Runnable{
        private final Socket sock;

    public ClientHandler(Socket s){
        sock = s;
    }

    @Override
    public void run(){
        try{
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            String cookieText = br.readLine();
            if (cookieText.equals("close")){
                is.close();
                sock.close();
                System.exit(0);
            }
            System.out.printf(">>> cookie-text: %s \n",cookieText);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
