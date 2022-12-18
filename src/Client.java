import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String message){
        output.println(message);
    }

    public void stopConnection() throws IOException{
        socket.close();
        input.close();
        output.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Client client = new Client();
        client.startConnection("127.0.0.1", 5555);
        while (true) {
            System.out.print("Message: ");
            client.sendMessage(input.nextLine());
            System.out.println("> Message sent.");
        }
    }
}
