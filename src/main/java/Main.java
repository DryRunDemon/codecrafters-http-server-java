import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) throws Exeception {
    ServerSocket serverSocket = new ServerSocket(4221);
    System.out.println("Server started on port 4221...");
    while (true) {
      Socket clientSocket = serverSocket.accept();
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
      String line = in.readLine();
      System.out.println("Received request: " + line);
      out.write("HTTP/1.1 200 OK\r\n\r\n");
      out.flush();

      clientSocket.close();
    }
  }
}
