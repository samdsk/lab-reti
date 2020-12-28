import java.net.Socket;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket client = null;

        try{
            serverSocket = new ServerSocket(58828);
            System.out.println("Server: ready at: "+serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());

            while(true){
                client = serverSocket.accept();
                System.out.println("Server: serving: "+client.getInetAddress()+":"+client.getPort());
                int buffer_dim = 100;
                byte buffer[] = new byte[buffer_dim];
                InputStream fromClient = client.getInputStream();
                OutputStream toClient = client.getOutputStream();
                int readLength;
                String clientInput;
                do{
                    readLength = fromClient.read(buffer);
                    clientInput = new String(buffer,0,readLength);

                    System.out.println("Server: received: "+clientInput);
                    if(!clientInput.equalsIgnoreCase("end")){
                        toClient.write(readLength);
                    }
                }while(!clientInput.equalsIgnoreCase("end"));

                fromClient.close();
                client.close();
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