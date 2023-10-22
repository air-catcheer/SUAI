package lab11;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    private int port = -1 ;
    private InetAddress ipAddress;
    private DatagramSocket serverSocket;
    private String username = "Server";

    public Server(int port, DatagramSocket datagramSocket){
        this.port = port;
        this.serverSocket = datagramSocket;

    }

    public void sendMessage() throws IOException {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {

                String message = inFromUser.readLine();
                if (message.startsWith("@name")){
                    String[] tmp = message.split(" ");
                    message = username+" change nickname to: ";
                    if(tmp[1]!=null) {
                        username = tmp[1];
                        message = message + username;
                        byte[] sendData = new byte[1024];
                        sendData = message.getBytes();

                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                        serverSocket.send(sendPacket);
                    }
                    else{
                        System.out.println("ERROR: @name -name-");
                    }
                }
                else if (message.startsWith("@quit")) {
                    System.exit(-1);
                }
                else {
                    message = username + ": " + message;
                    byte[] sendData = new byte[1024];
                    sendData = message.getBytes();

                    byte[] receiveData = new byte[1024];
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                    serverSocket.send(sendPacket);
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("!WAIT FOR ANY USER CONNECT!");
            }
        }
    }
    public void receiveMessage() throws IOException {

        while(true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            port = receivePacket.getPort();
            ipAddress = receivePacket.getAddress();

            String sentence = new String( receivePacket.getData());
            System.out.println(sentence);

        }
    }

    static public void main(String[] args) throws IOException {

        if(args.length!=1){
            System.out.println("java Server -port-");
        }
        DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(args[0]));

        Server server = new Server(Integer.parseInt(args[0]),serverSocket);

        Thread thread = new Thread(() -> {
            try {
                server.sendMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();

        server.receiveMessage();
    }

}