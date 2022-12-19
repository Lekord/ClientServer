import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Objects;
import java.util.Scanner;


public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

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
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        inpCurrency1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inpCurrency2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inpAmountOfMoney = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String inpCurrency11 = inpCurrency1.readLine();
        String inpCurrency22 = inpCurrency2.readLine();

        double AmountOfMoney;
        while ((AmountOfMoney = Double.parseDouble(inpAmountOfMoney.readLine())) != 0) {
            if (".".equals(inpCurrency11)) {
                output.println("good bye");
                System.out.println("good bye");
            } else {
                System.out.println(inpCurrency11);
                System.out.println(inpCurrency22);
                System.out.println(AmountOfMoney);

                switch (inpCurrency11){
                    case "USD":
                        switch (inpCurrency22){
                            case "USD":
                                System.out.println(AmountOfMoney);
                                break;
                            case "RUB":
                                System.out.println(AmountOfMoney * 61);
                                break;
                        }
                        break;
                    case "RUB":
                    case "YEN":
                    case "GBP":
                }
            }
        }
    }

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
        int port = 5555;
        Server server = new Server();
        System.out.printf("Server started on port: %d. Listening for client request.", port);
        server.start(port);
        while (true) {
            System.out.print("Message: ");
            server.sendMessage(scanner.nextLine());
            System.out.println("> Message sent.");
        }
    }
}