import java.net.*;
import java.io.*;

public class Servidor
{
  public static void main(String[] args) throws IOException
  {
    int solicitudes = 0;
    ServerSocket serverSocket = new ServerSocket(8081);
    System.out.println("Servidor iniciado en: " + serverSocket.getInetAddress() +
    " puerto: " + serverSocket.getLocalPort() + "\nEscuchado...");
    while(solicitudes < 5)
    {
      Socket socket = serverSocket.accept();
      InputStreamReader in = new InputStreamReader(socket.getInputStream());
      BufferedReader buff = new BufferedReader(in);

      String str = buff.readLine();
      System.out.println("Recibido: " + str);
      solicitudes++;
    }


    serverSocket.close();
  }
}
