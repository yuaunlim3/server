package server;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.util.*;

public class ClientMain {
    public static void main(String[] args) {
        try{
            //Default port
            int port = 3000;
            if(args.length > 0){
                port = Integer.parseInt(args[0]);
            }

            ArrayList<String> cookies = new ArrayList<String>();
            //Save content of file into cookies
            File file = new File(args[1]);
            InputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            String line = "a";
            
            while (line != null) {
                line = dis.readLine();
                if(line == null){
                    break;
                }
                cookies.add(line);
            }
            dis.close();
            fis.close();

            Socket sock = null;
            OutputStream os = null;
            BufferedWriter bw = null;
            
            Console cons = System.console();

            while(true){
                String input = cons.readLine("CMD>>> ");

                if(constants.GET.equals(input)){
                    System.out.println("Connecting to the server");
                    sock = new Socket("localhost", port);
                    System.out.println("Connected!");

                     os = sock.getOutputStream();
                    Writer writer = new OutputStreamWriter(os);
                    bw = new BufferedWriter(writer);
                } 
                               
                else if(constants.TEXT.equals(input)){
                    Random rand = new SecureRandom();
                    int num = rand.nextInt(cookies.size());
                    String cookieText = cookies.get(num);

                    System.out.printf(">>> cookie-text: %s \n",cookieText);      
                    bw.write(cookieText);
                    bw.newLine();
                    bw.flush();
                    os.flush();

                }else if(constants.CLOSE.equals(input)){

                    bw.write("close");
                    bw.newLine();
                    bw.flush();
                    os.flush();
                    os.close();
                    sock.close();
                    System.exit(0);
                }
                
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
