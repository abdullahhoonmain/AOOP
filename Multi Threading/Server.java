import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<PrintWriter> clientOutput = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started, waiting for clients...");

            // Thread to read server input and broadcast to clients
            Thread serverInputThread = new Thread(() -> {
                try (BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))) {
                    String message;
                    while ((message = serverInput.readLine()) != null) {
                        broadcastMessage("Server: " + message);
                    }
                } catch (IOException ex) {
                    System.out.println("Error in server input thread: " + ex.getMessage());
                }
            });
            serverInputThread.start();

            // Accept clients and handle them
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create PrintWriter to send messages to the client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clientOutput.add(writer);

                // Handle the client in a separate thread
                Thread clientHandler = new Thread(new ClientHandler(clientSocket, writer));
                clientHandler.start();
            }

        } catch (IOException ex) {
            System.out.println("Error in server: " + ex.getMessage());
        }
    }

    // Broadcast a message to all connected clients
    private static void broadcastMessage(String message) {
        for (PrintWriter writer : clientOutput) {
            writer.println(message);
        }
    }

    // Client handler to process messages from a client
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket, PrintWriter writer) {
            this.socket = socket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Client: " + message);
                    broadcastMessage("Client: " + message);
                }
            } catch (IOException ex) {
                System.out.println("Error in client handler: " + ex.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("Error closing client socket: " + ex.getMessage());
                }

                // Remove client from the list when disconnected
                clientOutput.remove(writer);
            }
        }
    }
}
