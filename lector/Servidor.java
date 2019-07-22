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
              //System.out.println(lista);
              if(lista.size() == 0)
              {
                //creamos XML con respuesta

                out = new PrintWriter(socket.getOutputStream(),true);
                out.println("No hay libros con ese anio");
                out.flush();

                //bw.write("No se encontraron libros con ese anio.");
                //bw.flush();
              }
              else
              {
                //creamos catalogo de libros para verificar y mandar
                String mensaje;
                Namespace xsNS = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
                Element root = new Element("catalogoLibros");
                //System.out.println("Root:" +root.getName());
                root.addNamespaceDeclaration(xsNS);
                root.setAttribute("noNamespaceSchemaLocation","catalogoLibrosTipo.xsd",xsNS);
                Iterator it = lista.iterator();
                while(it.hasNext())
                {
                  Element libro =(Element)it.next();
                  libro.detach();//porque ya tiene padre catalogoLibros
                  //System.out.println("libro: "+libro.getChild("titulo").getText());
                  root.addContent(libro);
                }
                Document docRespuesta = new Document(root);
                XMLOutputter salida = new XMLOutputter();
                //salida.setFormat(Format.getPrettyFormat());
                mensaje = salida.outputString(docRespuesta);
                fw = new java.io.FileWriter("respuesta.xml");
                mensaje = mensaje.replace("\n","").replace("\r","");//elimina el line break

                fw.write(mensaje);
                fw.close();
                //System.out.println("Mensaje a cliente: \n"+mensaje);

                //validar xml-------------------

                if(validador.validate("respuesta.xml","lector/xsd/catalogoLibrosTipo.xsd"))
                  System.out.println("Respuesta valida");
                else
                  System.out.println("Respuesta invalida");
                //mandamos mensaje a cliente
                out = new PrintWriter(socket.getOutputStream(),true);
                out.println(mensaje);
                out.flush();
                System.out.println("Respuesta Mandada");

              }

            break;
            case 2: //mostrar libro x isbn
              String isbn = raiz.getChild("parametro").getAttributeValue("isbn");
              Element libro = lector.obtenerLibro(isbn);
              //System.out.println(libro);
              if(libro == null)
              {
                //creamos XML con respuesta

                out = new PrintWriter(socket.getOutputStream());
                out.print("No hay libros con ese anio");
                out.flush();

              }
              else
              {
                //creamos catalogo de libros para verificar y mandar
                String mensaje;
                Namespace xsNS = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
                Element root = new Element("catalogoLibros");
                //System.out.println("Root:" +root.getName());
                root.addNamespaceDeclaration(xsNS);
                root.setAttribute("noNamespaceSchemaLocation","catalogoLibrosTipo.xsd",xsNS);

                libro.detach();//porque ya tiene padre catalogoLibros
                //System.out.println("libro: "+libro.getChild("titulo").getText());
                root.addContent(libro);

                Document docRespuesta = new Document(root);
                XMLOutputter salida = new XMLOutputter();
                //salida.setFormat(Format.getPrettyFormat());
                mensaje = salida.outputString(docRespuesta);
                mensaje = mensaje.replace("\n","").replace("\r","");//elimina el line break
                //System.out.println("Mensaje a cliente: \n"+mensaje);

                fw = new java.io.FileWriter("respuesta.xml");

                fw.write(mensaje);
                fw.close();
                if(validador.validate("respuesta.xml","lector/xsd/catalogoLibrosTipo.xsd"))
                  System.out.println("Respuesta valida");
                else
                  System.out.println("Respuesta invalida");
                //mandar mensaje
                out = new PrintWriter(socket.getOutputStream(),true);
                out.println(mensaje);
                out.flush();
                System.out.println("Respuesta Enviada");
              }
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
