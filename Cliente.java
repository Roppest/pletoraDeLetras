import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Cliente
{
  public Socket socket;
  public PrintWriter out;
  public BufferedReader in;
  private boolean conectado;
  public Cliente()
  {
    try
    {
      socket = new Socket("localhost", 8081);
      InputStreamReader input = new InputStreamReader(socket.getInputStream());
      in = new BufferedReader(input);
      out = new PrintWriter(socket.getOutputStream());
      conectado = true;
    }catch(IOException io){System.out.println(io);conectado = false;}

  }
  public String crearMensaje(int opcion)
  {//crea el documento xml con el mensaje para el servidor
    switch(opcion)
    {
      case 1:
      break;
      case 2:
      break;
      default: return null;
    }
    return "hola";
  }

  public int elegirOpcion()
  {
    int opcion=0;
    Scanner sc = new Scanner(System.in);
    opcion = sc.nextInt();
    if(opcion > 2 || opcion < 0)
      return 0;
    return opcion;
  }

  public boolean getConectado()
  {
    return conectado;
  }

  public void mandarMensaje(String mensaje)throws IOException
  {
    out.println(mensaje);
    out.flush();
  }

  public void mostrarMenu()
  {
    System.out.println(" ------------------------");
    System.out.println("|Escoge una operacion     |");
    System.out.println("|1) Buscar Libros por anio|");
    System.out.println("|2) Buscar Libro por ISBN |");
    System.out.println(" ------------------------");
  }

  public void mostrarMensaje()
  {

  }



  public static void main(String[] args) throws IOException
  {
    Cliente cliente = new Cliente();
    if(cliente.getConectado())
    {
      System.out.println("Cliente conectado: "+cliente.getConectado());
      cliente.mostrarMenu();
      cliente.mandarMensaje(Integer.toString(cliente.elegirOpcion()));
    }
    else
      System.out.println("Cliente no pudo conectarse.");
  }
}
