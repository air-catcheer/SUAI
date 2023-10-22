package lab12;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Server is running.");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                String userName = in.readLine();
                System.out.println(userName + " connected.");

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("@senduser ")) {
                        String[] parts = message.split(" ");
                        String targetUser = parts[1];
                        String privateMessage = message.substring(11 + targetUser.length());
                        sendPrivateMessage(targetUser, privateMessage);
                    } else {
                        sendToAll(userName + ": " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
            }
        }

        private void sendToAll(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }

        private void sendPrivateMessage(String targetUser, String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    if (writer != out && writer.toString().contains(targetUser)) {
                        writer.println("[Private from " + socket.getInetAddress() + "]: " + message);
                        out.println("[Private to " + targetUser + "]: " + message);
                        return;
                    }
                }
                out.println("User " + targetUser + " not found.");
            }
        }
    }
}
