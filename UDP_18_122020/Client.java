import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client{
    public static void main(String[] args){

        DatagramSocket sClient = null;

        try{            
            String hostname = "localhost";
            int port = 7000;
/*
            if(args.length != 2){
                throw new IllegalArgumentException("Num.parametri non corretto");
            }

            hostname = args[0];
            port = Integer.parseInt(args[1]);

            if(port <= 0){
                throw new IllegalArgumentException("porta non valida");
            }
*/
            sClient = new DatagramSocket();

            System.out.println("Indirizzo: "+sClient.getLocalAddress()
                +"; porta: "+ sClient.getLocalPort());

            InetSocketAddress isa = new InetSocketAddress(hostname, port);
            InputStreamReader keyboard = new InputStreamReader(System.in);

            BufferedReader br = new BufferedReader(keyboard);
            String phrase = br.readLine();
            byte[] buffer = phrase.getBytes();
            
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            dp.setSocketAddress(isa);
            sClient.send(dp);

        }catch (Exception e){
            e.printStackTrace();

        }finally{
            try{
                sClient.close();
            }catch (Exception e){
                System.err.println("Cliente error.");
                e.printStackTrace();
            }
        }
    }
}