/**
  *Ejecucion: java -cp WEB-INF/classes/xalan.jar Servidor
  */
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
    String xml = "peticionCliente.xml";//ruta del xml que formara
    ValidadorXML validador = new ValidadorXML();
    while(solicitudes < 5)
    {
      Socket socket = serverSocket.accept();
      InputStreamReader in = new InputStreamReader(socket.getInputStream());
      BufferedReader buff = new BufferedReader(in);

      //lee la cadena del cliente
      String str = buff.readLine();
      //System.out.println("Recibido: " + str);

      //transformamos la cadena en un documento xml
      java.io.FileWriter fw = new java.io.FileWriter(xml);
      fw.write(str);
      fw.close();

      try
      {
        System.out.println("XML valido: "+ validador.validaXML(xml));

      }catch(Exception e){System.out.println(e);}

      solicitudes++;
    }


    serverSocket.close();
  }
}
