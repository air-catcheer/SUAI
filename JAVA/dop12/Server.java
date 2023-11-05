package dop12;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static Map<String, ClientHandler> activeClients = new ConcurrentHashMap<>();
    private static Map<String, Long> userAlarms = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Server is running.");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept(), scheduler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private ScheduledExecutorService scheduler;
        private String userName;

        public ClientHandler(Socket socket, ScheduledExecutorService scheduler) {
            this.socket = socket;
            this.scheduler = scheduler;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                userName = in.readLine();

                if (activeClients.containsKey(userName)) {
                    out.println("Welcome back, " + userName + "!");
                } else {
                    System.out.println(userName + " connected.");
                    activeClients.put(userName, this);
                }

                long currentTime = System.currentTimeMillis();
                Long userAlarmTime = userAlarms.get(userName);
                if (userAlarmTime != null && userAlarmTime <= currentTime) {
                    System.out.println("User " + userName + "'s alarm has gone off");
                    out.println("Wake up!");
                    userAlarms.remove(userName);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equals("@quit")) {
                        out.println("Goodbye!");
                        clientDisconnect(userName);
                        break;
                    } else if (message.startsWith("@setalarm ")) {
                        long timeToWakeUp = Long.parseLong(message.substring(10)) * 1000;
                        long alarmTime = currentTime + timeToWakeUp;

                        userAlarms.put(userName, alarmTime);
                        scheduler.schedule(() -> {
                            out.println("Wake up!");
                            userAlarms.remove(userName);

                            System.out.println("User " + userName + "'s alarm has gone off");
                        }, timeToWakeUp, TimeUnit.MILLISECONDS);
                    } else if ("Wake up".equals(message)) {

                        out.println("Wake up!");
                    } else if (message.startsWith("@senduser ")) {
                        String[] parts = message.split(" ");
                        String targetUser = parts[1];
                        String privateMessage = message.substring(11 + targetUser.length());
                        sendPrivateMessage(userName, targetUser, privateMessage);
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
                if (activeClients.get(userName) == this) {
                    activeClients.remove(userName);
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

        private void sendPrivateMessage(String sender, String targetUser, String message) {
            ClientHandler targetHandler = activeClients.get(targetUser);
            if (targetHandler != null) {
                targetHandler.out.println("[Private from " + sender + "]: " + message);
                out.println("[Private to " + targetUser + "]: " + message);
            } else {
                out.println("User " + targetUser + " not found.");
            }
        }

        private void clientDisconnect(String userName) {
            synchronized (clientWriters) {
                clientWriters.remove(out);
            }
            activeClients.remove(userName);
            System.out.println(userName + " disconnected.");
        }
    }
}
