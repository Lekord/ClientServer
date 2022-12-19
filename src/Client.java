import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private BufferedReader in;
    private PrintWriter out;
    private PrintWriter outCurrency1;
    private PrintWriter outCurrency2;
    private PrintWriter outAmountOfMoney;

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // общение с сервером
        out = new PrintWriter(socket.getOutputStream(), true);
        outCurrency1 = new PrintWriter(socket.getOutputStream(), true);
        outCurrency2 = new PrintWriter(socket.getOutputStream(), true);
        outAmountOfMoney = new PrintWriter(socket.getOutputStream(), true);
    }

    public void getMessage() throws IOException{
        String inputLine; // Считываем поток данных от клиента из in
        while ((inputLine = input.readLine()) != null) {
            if (".".equals(inputLine)) {
                output.println("good bye");
                System.out.println("good bye");
                break;
            } else {
                System.out.printf("Got a message from the server: %s \n", inputLine);
            }
            output.println(inputLine);
        }
    }

    public void sendMessage(String message){
        output.println(message);
    }

    public void sendCurrency1(String message){
        outCurrency1.println(message);
    }

    public void sendCurrency2(String message){
        outCurrency2.println(message);
    }

    public void sendAmountOfMoney(String message){
        outAmountOfMoney.println(message);
    }

    public void stopConnection() throws IOException{
        socket.close();
        input.close();
        output.close();
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner value1 = new Scanner(System.in);
        String currency1;
        Scanner value2 = new Scanner(System.in);
        String currency2;
        Scanner value3 = new Scanner(System.in);
        String amountOfMoney;
        Client client = new Client();
        client.startConnection("127.0.0.1", 5555);
        while (true) {
            System.out.print("Введите курс валюты, какую хотите перевести: ");
            currency1 = value1.nextLine();
            client.sendCurrency1(currency1);
            System.out.print("Введите курс валюты, в какую хотите перевести: ");
            currency2 = value2.nextLine();
            client.sendCurrency2(currency2);
            System.out.printf("Введите сумму в %s, которую хотите перевести в %s: ", currency1, currency2);
            amountOfMoney = value3.nextLine();
            client.sendAmountOfMoney(amountOfMoney);
            System.out.println("> Message sent.");
//            client.getMessage();
        }
    }
}
