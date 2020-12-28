//import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.net.ServerSocket;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.UnknownHostException;

public class Server {
    public static void main(String[] args){
        ServerSocket sServer = null;
        Socket toClient;
        try{ 
            sServer = new ServerSocket(58828);           
            System.out.println("Indirizzo: "+ sServer.getInetAddress()+ "; Port: "+ sServer.getLocalPort());
            //Indirizzo: 0.0.0.0/0.0.0.0; Port: xxxx
            toClient = sServer.accept();
            System.out.println("Ind. client: "+ toClient.getInetAddress()+"; Port: "+toClient.getPort());
            
            int buffer_dim = 100;
            byte buffer[] = new byte[buffer_dim];
            InputStream fromClient = toClient.getInputStream();
            int read = fromClient.read(buffer);

            if(read>0){
                String output = new String(buffer,0,read);
                System.out.println("Server: Ricevuta string: " + output + " di " + read + " byte.");
                /*String splitted[] = output.split("---",0);

                for(int i=0;i<splitted.length;i++){
                    System.out.println(splitted[i]+ " ");
                }
                */

                StringTokenizer splitted = new StringTokenizer(output,"---");

                while(splitted.hasMoreTokens()){
                    System.out.println(splitted.nextToken());
                }
            }
            
            Thread.sleep(240*1000);            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                sServer.close();
            }catch (Exception e){
                System.err.println("Server error.");
                e.printStackTrace();
            }
        }
    }
}
