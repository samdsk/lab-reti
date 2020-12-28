import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.InetAddress;

public class Server{
    public static void main(String[] args){
        DatagramSocket sServer = null;
        try{
            sServer = new DatagramSocket();
            System.out.println("Indirizzo: "+sServer.getLocalAddress()+ "; porta: "+ sServer.getLocalPort());

            int buffer_dim = 100;
            byte[] buffer = new byte[buffer_dim];
            DatagramPacket dpin = new DatagramPacket(buffer,buffer_dim);
            sServer.receive(dpin);
            String output = new String(buffer, 0, dpin.getLength());
            System.out.println("Ricevuto: "+ output);
            InetAddress ia = dpin.getAddress();
            int port = dpin.getPort();
            System.out.println("Indirizzo "+ ia.getHostAddress()+ "; porta: "+ port);
            sServer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
