import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.DatagramPacket;
//import java.net.ServerSocket;
//import java.io.IOException;
import java.net.InetSocketAddress;
//import java.net.UnknownHostException;

public class Client_2{
    public static void main(String[] args){
        DatagramSocket serverSocket = null;
        InetAddress internetAddress = null;
        InetSocketAddress internetSocketAddress = null;

        try{
            serverSocket = new DatagramSocket();
            System.out.println("Client_2: OK: socket:"+serverSocket.getLocalPort());
            
            internetAddress = InetAddress.getLocalHost();
            internetSocketAddress = new InetSocketAddress(internetAddress, 7000); //7000 UDP server socket
                    
            java.io.InputStreamReader keyboard = new java.io.InputStreamReader(System.in);
            BufferedReader br  = new BufferedReader(keyboard);

            String phrase = null;
            do{
                System.out.println("Insert: ");                
                phrase = br.readLine();
                byte[] buffer = phrase.getBytes();                
                

                DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
                dp.setSocketAddress(internetSocketAddress);
                serverSocket.send(dp);

                if(!phrase.equalsIgnoreCase("end")){                                    
                    serverSocket.receive(dp);
                    String output = new String(dp.getData(),0,buffer.length);
                    System.out.println("-> Server: received: " + output);
                }

            }while(!phrase.equalsIgnoreCase("end"));
      

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
               serverSocket.close();
            }catch (Exception e){
                System.err.println("Client_2: Error: Closing");
                e.printStackTrace();
            }
        }
        
    }
}