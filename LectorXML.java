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
            //si son del mismo año, lo agregamos de una vez
            if(anioEnTurno == anioPublicacion){
              listaOrdenada.add(i, libro);
              break;
            }else{
            //si el año del libro en turno es menor al año del libro que queremos acomodar
              if(anioEnTurno < anioPublicacion){
                //y si en la siguiente iteración termina el for
                if(i+1 == listaOrdenada.size()){
                  //lo agregamos al final
                  listaOrdenada.add(i+1, libro);
                  break;  
                }else{//sino, seguimos
                  continue;  
                }
              }else{
                //sin embargo, si el año del libro a acomodar es mayor al año del libro en turno
                listaOrdenada.add(i, libro);
                break;
              }
            }
          }
        }
      }

        return listaOrdenada;

    }catch(IOException e){e.printStackTrace();

    }catch(JDOMException e){e.printStackTrace();

    }

    return null;
  }

  public Element obtenerLibro(String isbn){

    try
    {
      SAXBuilder constructor = new SAXBuilder();
      Document doc = constructor.build(rutaLibros);

      Element raiz = doc.getRootElement();
      List libros = raiz.getChildren("libro");
      Iterator it = libros.iterator();

      while(it.hasNext()){
        Element libro = (Element)it.next();
        if(libro.getAttributeValue("isbn").equals(isbn)){
          return libro;
        }
      }

    }catch(IOException e){e.printStackTrace();

    }catch(JDOMException e){e.printStackTrace();

    }
    return null;
  }

  //public  extraeLibros();
  public static void main(String[] args) 
  {
    LectorXML test = new LectorXML();
    test.obtenerLibros();
    Element libro = test.obtenerLibro("9788478888566");
    System.out.println(libro.getChild("titulo").getText());
  }


}
