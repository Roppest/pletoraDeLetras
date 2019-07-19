import java.net.*;
import java.io.*;

public class Servidor
{
  public static void main(String[] args) throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(8081);
    System.out.println("Servidor iniciado en espera de peticion...")
    Socket socket = serverSocket.accept();

    System.out.println("Cliente conectado");

    InputStreamReader in = new InputStreamReader(socket.getInputStream());
    BufferedReader buff = new BufferedReader(in);

    String str = buff.readLine();
    System.out.println("Recibido: " + str);
  }
}
