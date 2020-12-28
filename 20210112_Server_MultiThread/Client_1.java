import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
//import java.net.ServerSocket;
//import java.io.IOException;
import java.net.InetSocketAddress;
//import java.net.UnknownHostException;

public class Client_1{
    public static void main(String[] args){
        Socket clientSocket = null;
        InetAddress internetAddress = null;
        InetSocketAddress internetSocketAddress = null;

        try{
            clientSocket = new Socket();
            System.out.println("Client_1: OK: socket:"+clientSocket.getLocalPort());
            
            internetAddress = InetAddress.getLocalHost();
            internetSocketAddress = new InetSocketAddress(internetAddress, 58828); //58828 server socket
            
            System.out.println("Client_1: connecting...");
            clientSocket.connect(internetSocketAddress);
            System.out.println("Client_1: OK: connected to -> "+internetSocketAddress.getAddress()+":"+internetSocketAddress.getPort());
            
            java.io.InputStreamReader keyboard = new java.io.InputStreamReader(System.in);
            BufferedReader br  = new BufferedReader(keyboard);

            OutputStream toServer = clientSocket.getOutputStream();
            InputStream fromServer = clientSocket.getInputStream();
            String phrase = null;

            do{
                System.out.println("Insert: ");
                phrase = br.readLine();
                toServer.write(phrase.getBytes(),0,phrase.length());
                if(!phrase.equalsIgnoreCase("end")){
                    int buffer_dim = 100;
                    byte buffer[] = new byte[buffer_dim];
                    int readlength = fromServer.read();
                    String output = new String(buffer,0,readlength);
                    System.out.println("Server: received: " + output + " / " + readlength + " bytes.");
                }
            }while(!phrase.equalsIgnoreCase("end"));

            toServer.close();       

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                clientSocket.close();
            }catch (Exception e){
                System.err.println("Client_1: Error: Closing");
                e.printStackTrace();
            }
        }
        
    }
}