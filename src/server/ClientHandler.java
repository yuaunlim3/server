package server;

import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable{
        private final Socket sock;
        private final ServerSocket server;

    public ClientHandler(Socket s,ServerSocket server){
        sock = s;
        this.server = server;
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
                //System.exit(0);
            }
            System.out.printf(">>> cookie-text: %s \n",cookieText);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally{
            try {
                System.out.println("Server close");
                server.close();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
