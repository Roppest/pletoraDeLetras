import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class LectorXML
{
  private String rutaLibros ="C:/xampp/tomcat/webapps/integracion2019/xml/catalogoLibros.xml";
  private String rutaAutores="C:/xampp/tomcat/webapps/integracion2019/xml/catalogoAutores.xml";


  /**
  *Este metodo muestra todos los libros de el XML de catalogoLibros.xml
  */
  public void obtenerLibros()
  {
    try
    {
      SAXBuilder constructor = new SAXBuilder();
      Document doc = constructor.build(rutaLibros);

      Element raiz = doc.getRootElement();
      List libros = raiz.getChildren("libro");
      Iterator it = libros.iterator();

      //lista que se regresará con todos los libros ordenados de min a max por fecha
      List<Element> listaOrdenada = new ArrayList<>();

      while(it.hasNext()){
        Element libro = (Element)it.next();
        String anio = libro.getChild("anioPublicacion").getText();
        int anioPublicacion = Integer.parseInt(anio);
    
        //si apenas es el primer libro en la lista    
        if(listaOrdenada.isEmpty()){
          listaOrdenada.add(0, libro);
        }else{
          //recorremos toda la lista
          for(int i = 0; i < listaOrdenada.size(); i++){
            //extraemos el libro en turno de la lista ordenada
            Element libroEnTurno = (Element)listaOrdenada.get(i);
            //obtenemos el año del libro en turno
            int anioEnTurno = Integer.parseInt(libroEnTurno.getChild("anioPublicacion").getText());
            //si el año del libro en turno es menor al año del libro que queremos acomodar
            //y si en la siguiente iteración no termina el for
            if(anioEnTurno < anioPublicacion && i+1 != listaOrdenada.size()){
              continue;
            }else{
              //sin embargo, si el año del libro a acomodar es mayor al año del libro en turno
              //o si ya llegamos al final de la lista, entonces lo agregamos
              listaOrdenada.add(i, libro);
              break;
            }
          }
        }
      }

      for(int i = 0; i < listaOrdenada.size(); i++){
        System.out.println(listaOrdenada.get(i).getChild("anioPublicacion").getText());
      }
      //return libros;

    }catch(IOException e){e.printStackTrace();
    }catch(JDOMException e){e.printStackTrace();
    }

    
  }

  //public  extraeLibros();
  public static void main(String[] args) 
  {
    LectorXML test = new LectorXML();
    test.obtenerLibros();

  }


}
