package solution;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientHandler implements Runnable{
    private final Socket sock;
    private final Cookie cookie;

    public ClientHandler(Socket s,Cookie c){
        this.sock = s;
        this.cookie = c;
    }


    @Override
    public void run(){
        try{
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String messageReceived = " ";


            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            while (!messageReceived.toLowerCase().equals("quit")) {
                System.out.println("Writing for client input");
                messageReceived = dis.readUTF(dis);
                String toSend = cookie.getCookie();
                dos.writeUTF(toSend);
                dos.flush();

            }

            dos.close();
            bos.close();
            os.close();

            dis.close();
            bis.close();
            is.close();
            sock.close();


        }catch(IOException ex){
            System.err.println(ex.toString());
        }
    }


}
