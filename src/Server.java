import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Objects;
import java.util.Scanner;


public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
//    private BufferedReader input;
    private PrintWriter output;
    private BufferedReader in;
    private PrintWriter out;

    private BufferedReader inpCurrency1;
    private BufferedReader inpCurrency2;
    private BufferedReader inpAmountOfMoney;


    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        output = new PrintWriter(socket.getOutputStream(), true);
//        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        inpCurrency1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inpCurrency2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inpAmountOfMoney = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String inputLine; // Считываем поток данных от клиента из in
        String inpCurrency11 = inpCurrency1.readLine();
        String inpCurrency22 = inpCurrency2.readLine();
        String inpAmountOfMoney1 = inpAmountOfMoney.readLine();
//        while ((inputLine = input.readLine()) != null) {
//            if (".".equals(inputLine)) {
//                output.println("good bye");
//                System.out.println("good bye");
//                break;
//            }
//            } else {
//                System.out.printf("Got a message from client: %s \n", inputLine);
//
//
//            }
//            output.println(inputLine);
            System.out.println(inpCurrency11);
            System.out.println(inpCurrency22);
            System.out.println(inpAmountOfMoney1);
        }


//    }

    public void sendMessage(String message){
        out.println(message);
    }

    public void stop() throws IOException{
        serverSocket.close();
        socket.close();
//        input.close();
        output.close();
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        server.start(5555);
        while (true) {
            System.out.print("Message: ");
            server.sendMessage(scanner.nextLine());
            System.out.println("> Message sent.");
        }
    }
}
