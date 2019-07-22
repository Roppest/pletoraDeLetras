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

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
    PrintWriter out;




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
          //System.out.println("Servicio "+servicio);
          switch(servicio)
          {
            case 1: //mostrar libros x anio
              String anio =  raiz.getChild("parametro").getAttributeValue("anioPublicacion");
              //System.out.println("buscando anio" + anio);
              List<Element> lista = lector.obtenerLibros(anio);
              System.out.println(lista);
              if(lista.size() == 0)
              {
                //creamos XML con respuesta

                out = new PrintWriter(socket.getOutputStream());
                out.print("No hay libros con ese anio");
                out.flush();

                //bw.write("No se encontraron libros con ese anio.");
                //bw.flush();
              }
              else
              {
                //creamos catalogo de libros para verificar y mandar
                String mensaje;
                Scanner sc = new Scanner(System.in);
                Namespace xsNS = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
                Element raiz = new Element("catalogoLibros");
                raiz.addNamespaceDeclaration(xsNS);
                raiz.setAttribute("noNamespaceSchemaLocation","xsd/catalogoLibrosTipo.xsd",xsNS);
                Iterator it = lista.iterator();
                while(it.hasNext())
                {
                  Element autor = new Element("libro");
                  autor.setAttribute("isbn",it.)
                }
              }

            break;
            case 2: //mostrar libro x isbn
              String isbn = raiz.getChild("parametro").getAttributeValue("isbn");
              Element libro = lector.obtenerLibro(isbn);
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
