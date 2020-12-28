import java.io.BufferedReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
//import java.net.ServerSocket;
//import java.io.IOException;
import java.net.InetSocketAddress;
//import java.net.UnknownHostException;

public class Client{
    public static void main(String[] args){
        
        Socket sClient = null;
        InetAddress ia;
        InetSocketAddress isa;

        try {
            sClient = new Socket();
            ia = InetAddress.getLocalHost();
            isa = new InetSocketAddress(ia,58828);
            sClient.connect(isa);
            System.out.println("Porta allocata: "+ sClient.getLocalPort());
            System.out.println("Indirizzo: "+ sClient.getInetAddress() + ":" + sClient.getPort());
            
            java.io.InputStreamReader keyboard = new java.io.InputStreamReader(System.in);
            BufferedReader br  = new BufferedReader(keyboard);

            System.out.println("Inserisci frase: ");
            String phrase = br.readLine();
            System.out.println("Insersci float: ");
            Double num = Double.parseDouble(br.readLine());
            String total = phrase + "---" + Double.toString(num);

            System.out.println("Msg: "+total);
            //total += "\r\n"; 
            OutputStream toServer = sClient.getOutputStream();
            toServer.write(total.getBytes(),0,total.length());
            
            Thread.sleep(120*1000);           
        }catch (Exception e){
            e.printStackTrace();            
        }finally {
            try{
                sClient.close();
            }catch (Exception e){
                System.err.println("Cliente error.");
                e.printStackTrace();
            }
        }
    }
}