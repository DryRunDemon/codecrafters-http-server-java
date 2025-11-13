import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(4221);
    System.out.println("Server started on port 4221...");
    while (true) {
      Socket clientSocket = serverSocket.accept();
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
      String line = in.readLine();
      System.out.println("Received request: " + line);
      String[] parts = line.split(" ");
      String path = parts[1];
      if (path.equals("/")) {
        out.write("HTTP/1.1 200 OK\r\n\r\n");
      } else if (path.startsWith("/echo/")) {
        String message = path.substring(6);
        int length = message.length();
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: text/plain\r\n");
        out.write("Content-Length: " + length + "\r\n");
        out.write("\r\n");
        out.write(message);
      } else {
        out.write("HTTP/1.1 404 Not Found\r\n\r\n");
      }
      out.flush();
      clientSocket.close();
    }
  }
}
