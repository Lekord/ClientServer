import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Objects;
import java.util.Scanner;


public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

//    private PrintWriter output;
    private BufferedReader in;
    private PrintWriter out;

    private BufferedReader inpCurrency1;
    private BufferedReader inpCurrency2;
    private BufferedReader inpAmountOfMoney;
    double exchangedCurrency;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        inpCurrency1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inpCurrency2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inpAmountOfMoney = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String inpCurrency11 = inpCurrency1.readLine();
        String inpCurrency22 = inpCurrency2.readLine();

        double AmountOfMoney;
        while ((AmountOfMoney = Double.parseDouble(inpAmountOfMoney.readLine())) != 0) {
                System.out.println(inpCurrency11);
                System.out.println(inpCurrency22);
                System.out.println(AmountOfMoney);

                switch (inpCurrency11){
                    case "USD":
                        switch (inpCurrency22){
                            case "USD":
                                System.out.println(AmountOfMoney);
                                exchangedCurrency = AmountOfMoney;
                                break;
                            case "RUB":
                                System.out.println(AmountOfMoney * 61);
                                exchangedCurrency = AmountOfMoney * 61;
                                break;
                            case "YEN":
                                System.out.println(AmountOfMoney * 136.73);
                                exchangedCurrency = AmountOfMoney * 136.73;
                                break;
                            case "GBP":
                                System.out.println(AmountOfMoney * 0.82);
                                exchangedCurrency = AmountOfMoney * 0.82;
                                break;
                        }
                        break;
//                    case "RUB":
//                        switch(inpCurrency22){
//                            case "USD":
//                                break;
//                            case "YEN":
//                                break;
//                            case "GBP":
//                                break;
//                            case "RUB":
//                                break;
//                        }
//                        break;
//                    case "YEN":
//                        switch (inpCurrency22){
//                            case "USD":
//                                break;
//                            case "RUB":
//                                break;
//                            case "YEN":
//                                break;
//                            case "GBP":
//                                break;
//                        }
//                        break;
//                    case "GBP":
//                        switch (inpCurrency22){
//                            case "GBP":
//                                break;
//                            case "USD":
//                                break;
//                            case "RUB":
//                                break;
//                            case "YEN":
//                                break;
//                        }
//                        break;
//                }
                }
            break;
        }
    }

    public void sendMessage(double message){
        out.println(message);
    }

    public void stop() throws IOException{
        serverSocket.close();
        socket.close();
//        input.close();
//        output.close();
    }

    public static void main(String[] args) throws IOException{
        int port = 5555;
        Server server = new Server();
        System.out.printf("Server started on port: %d. Listening for client request.\n", port);
        server.start(port);
        if(server.exchangedCurrency != 0) {
            System.out.printf("Message: %f\n", server.exchangedCurrency);
            server.sendMessage(server.exchangedCurrency);
            System.out.println("> Message sent.");
        }
    }
}