import java.net.*;
import java.io.*;
import java.util.Scanner;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;

/**
  *Autores: Rodrigo Vázquez & Javier Erazo
  *Es necesario compilar con jdom.jar, ya sea agregando a classpath o
  *a lib.
  */

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
  public String crearMensaje(int opcion)throws IOException
  {//crea el documento xml como cadena para el servidor, regresa la variable mensaje
    String mensaje;
    Scanner sc = new Scanner(System.in);
    Namespace xsNS = Namespace.getNamespace("xs","http://www.w3.org/2001/XMLSchema-instance");
    Element raiz = new Element("solicitud");
    raiz.addNamespaceDeclaration(xsNS);
    raiz.setAttribute("noNamespaceSchemaLocation","xsd/solicitud.xsd",xsNS);
    
    Element servicio = new Element("servicio");
    servicio.setText(Integer.toString(opcion));
    raiz.addContent(servicio);

    Element parametro = new Element("parametro");
    switch(opcion)
    {
      case 1://servicio de busqueda por anio
        System.out.println("Escribe el anio:");
        parametro.setAttribute("tipo","anioPublicacion");
        parametro.setText(sc.nextLine());
      break;
      case 2://servicio de busqueda por isbn
        System.out.println("Escribe el ISBN:");
        parametro.setAttribute("isbn",sc.nextLine());
      break;
      default: return null;
    }
    raiz.addContent(parametro);

    Document doc = new Document(raiz);
    XMLOutputter salida = new XMLOutputter();
    //salida.setFormat(Format.getPrettyFormat());
    salida.output(doc, System.out);
    mensaje=salida.outputString(doc);
    //System.out.println("Normal:"+mensaje);

    mensaje = mensaje.replace("\n","").replace("\r","");//elimina el line break
    //System.out.println("Mod:" + mensaje);
    return mensaje;
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
    out.print(mensaje);
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
      cliente.mandarMensaje(cliente.crearMensaje((cliente.elegirOpcion())));
    }
    else
      System.out.println("Cliente no pudo conectarse.");
  }
}
