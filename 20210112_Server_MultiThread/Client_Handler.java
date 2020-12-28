import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client_Handler extends Thread {
    private Socket clientSocket;

    public Client_Handler(Socket s){
        this.clientSocket = s;    
    }

    public void run(){
        int buffer_size = 100;
        byte[] buffer = new byte[buffer_size];

        while(true){
            try{
                InputStream fromClient = clientSocket.getInputStream();
                int readLength;
                OutputStream toClient = clientSocket.getOutputStream();                
                String clientInput;

                do{
                    System.out.println("-> Client-"+clientSocket.getInetAddress()+":"+clientSocket.getPort());
                    readLength = fromClient.read(buffer);
                    clientInput = new String(buffer,0,readLength);
                    System.out.println("-> -> received: "+clientInput);

                    if(!clientInput.equalsIgnoreCase("end")){
                        toClient.write(readLength);
                    }
                }while(!clientInput.equalsIgnoreCase("end"));

                toClient.close();
                fromClient.close();                
                clientSocket.close();
                return;
                

/*
                if(readLength>0){
                    String output = new String(buffer,0,readLength);
                    System.out.println("-> Client-"+clientSocket.getInetAddress()+":"+clientSocket.getPort());
                    System.out.println("--> "+output);
                }else{
                    clientSocket.close();
                    return;
                }
*/
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                try{
                    System.out.println("Server: Closing connection...");
                    clientSocket.close();
                }catch (Exception e){
                    System.err.println("Server error.");
                    e.printStackTrace();
                }
            }
        }
    }
}
