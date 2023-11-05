package dop11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client {
    private int port;
    private InetAddress ipAddress;
    private DatagramSocket clientSocket;

    private String username = "user";

    public Client(int port, InetAddress ipAddress, DatagramSocket datagramSocket) {
        this.port = port;
        this.ipAddress = ipAddress;
        this.clientSocket = datagramSocket;
    }

    public void sendMessage() throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String message = inFromUser.readLine();

            if (message.startsWith("@name")) {
                changeUsername(message);
                String[] tmp = message.split(" ");
                message = username + " change nickname to: ";
                try {
                    username = tmp[1];
                    message = message + username;
                    byte[] sendData = new byte[1024];
                    sendData = message.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                    clientSocket.send(sendPacket);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("ERROR: @name -name-");
                }
            } else if (message.startsWith("@quit")) {
                clientSocket.close();
                System.exit(-1);
            } else if (message.startsWith("@kill")) {
                sendServerKillCommand();
            }
            else {
                message = username + ": " + message;
                byte[] sendData = new byte[1024];
                sendData = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                clientSocket.send(sendPacket);
            }
        }
    }

    public void sendServerKillCommand() {
        System.out.println("Sending server kill command...");
        String killServerCommand = "@kill";

        byte[] sendData = killServerCommand.getBytes();

        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() throws IOException {
        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String message = new String(receivePacket.getData());
            System.out.println(message);

            if (message.trim().equals("@kill")) {
                System.out.println("Received kill command. Terminating client...");
                clientSocket.close();
                System.exit(0);
            }
        }
    }


    public void changeUsername(String message) {
        // Add logic to change the username here
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("java Client -IP- -port-");
        }
        System.out.println("Type Hello to Server\n@name command for changing nickname\n@quit command to shut down the program\n@kill command to kill the server");

        DatagramSocket clientSocket = new DatagramSocket();
        Client client = new Client(Integer.parseInt(args[1]), InetAddress.getByName(args[0]), clientSocket);

        Thread thread = new Thread(() -> {
            try {
                client.sendMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();
        client.receiveMessage();
    }
}
