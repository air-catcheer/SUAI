package dop12;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;
        String userName;

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.print("Enter your username: ");
            userName = scanner.nextLine();
            out.println(userName);

            Thread messageReceiver = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageReceiver.start();

            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                out.println(userInput);
                if (userInput.equals("@quit")) {
                    messageReceiver.interrupt();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


