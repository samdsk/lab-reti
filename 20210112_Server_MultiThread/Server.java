import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;


public class Server {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try{
            serverSocket = new ServerSocket(58828);
            System.out.println("Server: serving at "+serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());

            while(true){
                clientSocket = null;
                clientSocket = serverSocket.accept();
                System.out.println("-> Connected-Client: "+clientSocket.getInetAddress()+":"+clientSocket.getPort());

                Thread t = new Client_Handler(clientSocket);
                t.start();
                
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                serverSocket.close();
            }catch (Exception e){
                System.err.println("Server error.");
                e.printStackTrace();
            }
        }
    }
}
