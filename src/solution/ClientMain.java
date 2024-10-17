package solution;

import java.io.*;
import java.net.*;

public class ClientMain {
    public static void main(String[] args) {
        try{
            int portNum = 0;
            if(args.length > 0){
                portNum = Integer.parseInt(args[0]);
            }else{
                System.err.println("Invalid number of arguments expected");
                System.exit(0);
            }
            //Open port
            Socket sock = new Socket("localhost", portNum);
            try{

                InputStream is = sock.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);

                OutputStream os = sock.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                Console cons = System.console();
                String input = " ";
                String cookie = " ";
                while(!input.toLowerCase().equals("quit")){
                    input = cons.readLine("Enter '1' to request for a cookie or quit to terminate ");

                    dos.writeUTF(input);
                    dos.flush();

                    cookie = dis.readUTF();
                    System.out.println(cookie);
                }
            }
            catch(EOFException ex){
                System.err.println(ex.toString());
                sock.close();
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
