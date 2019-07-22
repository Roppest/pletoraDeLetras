/**
  *Ejecucion: java -cp .:jdom.jar: lector.Servidor 
  */
package lector;
import java.net.*;
import java.io.*;
import java.io.FileWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.jdom.JDOMException;



public class Servidor
{
  public static void main(String[] args) throws IOException
  {
    int solicitudes = 0;
    ServerSocket serverSocket = new ServerSocket(8081);
    System.out.println("Servidor iniciado en: " + serverSocket.getInetAddress() +
    " puerto: " + serverSocket.getLocalPort() + "\nEscuchado...");
    String xml = "peticionCliente.xml";//ruta del xml que formara
    String xsd = "lector/xsd/solicitud.xsd";
    XMLValidator validador = new XMLValidator();
    LectorXML lector = new LectorXML();
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
      if(validador.validate(xml,xsd))
      {
        try
        {
          SAXBuilder constructor = new SAXBuilder();
          Document doc = constructor.build(xml);

          Element raiz = doc.getRootElement();
          int servicio = Integer.parseInt(raiz.getChild("servicio").getText());

          switch(servicio)
          {
            case 1: //mostrar libros x anio
              //List<Element> lista = lector.obtenerLibros(String anio);
              System.out.println("servicio1");
            break;
            case 2: //mostrar libro x isbn
            break;
            default:
            break;
          }
        }catch(JDOMException e){e.printStackTrace();}
      }
      else
      System.out.println("XML no valido. Cerrando comunicacion con cliente.");


      solicitudes++;
    }


    serverSocket.close();
  }
}
