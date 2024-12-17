import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader inputC = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server");

            // Thread to listen for server messages
            Thread serverListener = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException ex) {
                    System.out.println("Error reading server message: " + ex.getMessage());
                }
            });
            serverListener.start();

            // Read user input and send it to the server
            String userInput;
            while ((userInput = inputC.readLine()) != null) {
                writer.println(userInput);
            }

        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Connection error", e);

        }
    }
}
