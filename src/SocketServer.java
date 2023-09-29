import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {

    private int thisServerPort;
    public SocketServer(int Port){
        thisServerPort = Port;
    }
    public void run() {

        try (ServerSocket mServerSocket = new ServerSocket(thisServerPort)) {

            System.out.println("Server is listening on port " + thisServerPort);

            while (true) {

                Socket mSocket = mServerSocket.accept(); // waiting/listening for call.
                System.out.println("[server]: New client connected: " + mSocket.getRemoteSocketAddress());

                // Handling the input.
                InputStream input = mSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                // Preparing for output.
                OutputStream output = mSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String sReceivedMessage = reader.readLine();

                System.out.println("[server]: Server received message: " + sReceivedMessage);
                //putting our received message (which is 3#s) into an array and splitting them into three
                String [] numbers = sReceivedMessage.split(",");
                //sets an initial value for our sum.
                int sum = 0;
               try{
                   //if our array length == 3 (because of 3 values, we will take each array element and use parseInt to convert them to integers)
                   //we use the trim method to rid of any whitespace that may be prevalent.
                   if(numbers.length == 3){
                       int num1 = Integer.parseInt(numbers[0].trim());
                       int num2 = Integer.parseInt(numbers[1].trim());
                       int num3 = Integer.parseInt(numbers[2].trim());
                       //after we parse, we then add all the int nums to our sum so that when a user enters 3 numbers which were originally strings, they will get an int sum as a result.
                       sum = num1 + num2 + num3;
                       //Prints out the sum of the received numbers.
                       System.out.println("Sum of received numbers: " + sum);
                       //puts an else statement to clarify to the user if they input something wrong.
                   }else {
                       System.out.println("[server]: Invalid input.");
                   }
                   //catch statement to go with our try block to give an exception in case of any errors in our code.
               } catch (NumberFormatException e) {
                   throw new RuntimeException(e);
               }

                writer.println("Server received your message.");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println("[server]: Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
