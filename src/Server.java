import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;


public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;


    public void start(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String inputLine; // Считываем поток данных от клиента из in
        while ((inputLine = input.readLine()) != null) {
            if (".".equals(inputLine)) {
                output.println("good bye");
                System.out.println("good bye");
                break;
            } else {
                System.out.printf("Got a message from client: %s \n", inputLine);
            }
            output.println(inputLine);
        }
    }
//public void start(int port) throws IOException{
//
//    serverSocket = new ServerSocket(port);
//    socket = serverSocket.accept();
//    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//    output = new PrintWriter(socket.getOutputStream(), true);
//
//    String inputLine;
//        inputLine = input.readLine(); // считываем данные от клиента в переменную String inputLine
//        if (!Objects.equals(inputLine, "")) {
//            System.out.printf("Got a message from a client: %s", inputLine);
//        } else {
//            System.out.println("Got a null message from a client.");
//        }
//}

public void stop() throws IOException{
    serverSocket.close();
    socket.close();
    input.close();
    output.close();
}

    public static void main(String[] args) throws IOException{
        Server server = new Server();
        server.start(5555);
    }
}
