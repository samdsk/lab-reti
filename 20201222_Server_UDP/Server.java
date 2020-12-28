import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args){
        DatagramSocket serverSocket = null;
        try{
            serverSocket = new DatagramSocket(7000);
            System.out.println("Server: ready at: "+serverSocket.getLocalAddress()+":"+serverSocket.getLocalPort());

            while(true){
                int buffer_dim = 100;
                byte buffer[] = new byte[buffer_dim];
                DatagramPacket dp = new DatagramPacket(buffer,buffer_dim);
                String clientInput;
                do{
                    serverSocket.receive(dp);
                    clientInput = new String(dp.getData(),0,dp.getLength());
                    System.out.println("Server: received from: "+dp.getAddress()+":"+dp.getPort());
                    System.out.println("-> " +clientInput);
                    if(!clientInput.equalsIgnoreCase("end")){
                        InetSocketAddress isa = new InetSocketAddress(dp.getAddress(), dp.getPort());
                        dp.setSocketAddress(isa);
                        serverSocket.send(dp);
                    }
                }while(!clientInput.equalsIgnoreCase("end"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                serverSocket.close();
            }catch (Exception e){
                System.err.println("Server error.");
                e.printStackTrace();
            }
        }
    }
}
