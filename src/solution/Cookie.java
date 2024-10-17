package solution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    List<String> cookies  = new ArrayList<>();

    public void readCookie(String dirPathFileName) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(dirPathFileName);
        BufferedReader br = new BufferedReader(fr);

        String cookie = ""; 
        while ((cookie = br.readLine()) != null) {
            cookies.add(cookie);            
        }
        br.close();
        fr.close();
    }

    public void printCookies(){
        if(cookies.size() > 0){
            cookies.forEach(System.out:: println);
        }
    }

    public String getCookie(){
        Random rand = new Random();
        if(cookies != null){
            if(cookies.size() >0){
                return cookies.get(rand.nextInt(cookies.size()));
            }else{
                return "No cookies found";
            }
        }
        return "No cookies found";
    }

}
