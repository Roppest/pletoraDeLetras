import java.net.*;
import java.io.*;

public class Cliente
{
  public static void main(String[] args) throws IOException
  {
    Socket socket = new Socket("localhost", 8081);

    PrintWriter pr = new PrintWriter(socket.getOutputStream());
    pr.println("Hola soy Cliente");
    pr.flush();
  }
}
