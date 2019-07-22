/**
  * Ejecucion: java -cp .:jdom.jar: lector.Cliente
  */
package lector;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.lang.InterruptedException;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;


import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;

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
  {//crea el documento xml como cadena con el mensaje para el servidor
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
        parametro.setAttribute("anioPublicacion",sc.nextLine());
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
    //salida.output(doc, System.out);
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

  public void recibirRespuesta()throws IOException
  {
  /*  String respuesta = in.readLine();
    System.out.println(respuesta);*/
    //lee archivo respuesta
    File respuesta = new File("respuesta.xml");
    in = new BufferedReader(new FileReader(respuesta));
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer;
    try
    {
      transformer = tf.newTransformer();
      StringWriter writer = new StringWriter();

      transformer.transform(new StreamSource("respuesta.xml"), new StreamResult(writer));

      String xmlString = writer.getBuffer().toString();
      System.out.println(xmlString);
    }catch (TransformerException e){e.printStackTrace();}
    catch (Exception e){e.printStackTrace();}
    //System.out.println(in.readLine());
  }


  public static void main(String[] args) throws IOException
  {
    Cliente cliente = new Cliente();
    if(cliente.getConectado())
    {
      System.out.println("Cliente conectado: "+cliente.getConectado());
      cliente.mostrarMenu();
      //mandamos mensaje a servidor
      cliente.mandarMensaje(cliente.crearMensaje((cliente.elegirOpcion())));
      //recibimos mensaje de servidor
      try
      {
        System.out.println("...");
        Thread.sleep(1000);
      }catch(InterruptedException ie){ie.printStackTrace();}

      cliente.recibirRespuesta();

    }
    else
      System.out.println("Cliente no pudo conectarse.");
  }
}
