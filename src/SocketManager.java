import java.io.IOException;
import java.util.Scanner;
public class SocketManager {
    public static void main(String[] args) {
        System.out.println("Enter port for this server to listen on: ");
        int Port = new Scanner(System.in).nextInt();

        System.out.println("Enter IP address of server to connect to: ");
        String ipAdd = new Scanner(System.in).nextLine();

        System.out.println("Enter port of other server");
        int otherPort = new Scanner(System.in).nextInt();

        SocketServer mServer = new SocketServer(Port);
        Thread mServerThread = new Thread(mServer);
        mServerThread.start();

        SocketClient mClient = new SocketClient();

        while(true){

                System.out.println("Enter 3 #s separated by commas");
                String sepNumbers = new Scanner(System.in).nextLine();
                String mReplyFromServer = mClient.connectForOneMessage(ipAdd, otherPort,sepNumbers);

                System.out.println("[client] Server reply: " + mReplyFromServer);

        }
    }
    
}
