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

   public void agregarLibro(String isbn, String titulo, String resumen, String tema, String idioma, String anioPublicacion, String editorial,
		  String ciudad, String paisPublicacion, String fotoPortada, String autores) {
	  
	  try{
	  
		  SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaLibros);
	      Element raiz = doc.getRootElement();
	      
	      Element libro = new Element("libro");
	      libro.setAttribute("isbn", isbn);
	      raiz.addContent(libro);
	      
	      Element hijo = new Element("titulo");
	      hijo.setText(titulo);
	      libro.addContent(hijo);
	      
	      hijo = new Element("resumen");
	      hijo.setText(resumen);
	      libro.addContent(hijo);
	      
	      hijo = new Element("tema");
	      hijo.setText(tema);
	      libro.addContent(hijo);
	      
	      hijo = new Element("idioma");
	      hijo.setText(idioma);
	      libro.addContent(hijo);
	      
	      hijo = new Element("anioPublicacion");
	      hijo.setText(anioPublicacion);
	      libro.addContent(hijo);
	      
	      hijo = new Element("editorial");
	      hijo.setText(editorial);
	      libro.addContent(hijo);
	      
	      hijo = new Element("ciudad");
	      hijo.setText(ciudad);
	      libro.addContent(hijo);
	      
	      hijo = new Element("paisPublicacion");
	      hijo.setText(paisPublicacion);
	      libro.addContent(hijo);
	      
	      hijo = new Element("fotoPortada");
	      hijo.setText(fotoPortada);
	      libro.addContent(hijo);
	      
	      hijo = new Element("autores");
	      hijo.setText(autores);
	      libro.addContent(hijo);
	      
	    //escribimos nuevamente el documento ya modificado (con el auto agregado)
        this.escribeDocumentoXML(doc);
	      
		  
	  }catch (IOException ioe){
          ioe.printStackTrace();
      }catch(JDOMException e){
          e.printStackTrace();
      }
  
  }

  public void eliminarLibro(String isbn) {
	  
	  Element libroEliminado = null;
	  
	  try
	    {
	      SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaLibros);

	      Element raiz = doc.getRootElement();
	      List libros = raiz.getChildren("libro");
	      Iterator it = libros.iterator();
	      List eliminado;
	      
	      while(it.hasNext()){
	        Element libro = (Element)it.next();
	        
	        if(libro.getAttributeValue("isbn").equals(isbn)){
	          libroEliminado = libro;
	          break;
	        }
	      }
	      
	      libroEliminado.detach();
	      
	      this.escribeDocumentoXML(doc, rutaLibros);

	    }catch(IOException e){e.printStackTrace();

	    }catch(JDOMException e){e.printStackTrace();
	    
	    }
  }
  
  public void eliminarLibroDeAutor(String isbn) {
	  
	  Element libroEliminado = null;
	  trypackage lector;

import org.jdom.Document;
import org.jdom.Element;

import java.io.FileWriter;
import java.io.IOException;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class LectorXML
{
  //private String rutaLibros ="C:/xampp/tomcat/webapps/integracion2019/xml/catalogoLibros.xml";
  private String rutaLibros ="C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/pletora/WebContent/xml/catalogoLibros.xml";
 // private String rutaAutores="C:/xampp/tomcat/webapps/integracion2019/xml/catalogoAutores.xml";
  private String rutaAutores ="C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/pletora/WebContent/xml/catalogoAutores.xml";

  public LectorXML(){

  }
  /**
  *Este metodo muestra todos los libros de el XML de catalogoLibros.xml
  */
  public List<Element> obtenerLibros()
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

  public List<Element> obtenerAutoresDeLibro(Element libro){
	  
	List<Element> autores = libro.getChildren("autor");
	
	return autores;
  }
  
  public Element obtenerAutor(String autorBuscado) {
	  try
	    {
	      SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaAutores);

	      Element raiz = doc.getRootElement();
	      List autores = raiz.getChildren("autor");
	      Iterator it = autores.iterator();

	      while(it.hasNext()){
	        Element autor = (Element)it.next();
	        if(autor.getAttributeValue("clave").equals(autorBuscado)){
	          return autor;
	        }
	      }

	    }catch(IOException e){e.printStackTrace();

	    }catch(JDOMException e){e.printStackTrace();

	    }
	
	  return null;
  }
  
  
  public void agregarLibro(String isbn, String titulo, String resumen, String tema, String idioma, String anioPublicacion, String editorial,
		  String ciudad, String paisPublicacion, String fotoPortada, String autores) {
	  
	  try{
	  
		  SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaLibros);
	      Element raiz = doc.getRootElement();
	      
	      Element libro = new Element("libro");
	      libro.setAttribute("isbn", isbn);
	      raiz.addContent(libro);
	      
	      Element hijo = new Element("titulo");
	      hijo.setText(titulo);
	      libro.addContent(hijo);
	      
	      hijo = new Element("resumen");
	      hijo.setText(resumen);
	      libro.addContent(hijo);
	      
	      hijo = new Element("tema");
	      hijo.setText(tema);
	      libro.addContent(hijo);
	      
	      hijo = new Element("idioma");
	      hijo.setText(idioma);
	      libro.addContent(hijo);
	      
	      hijo = new Element("anioPublicacion");
	      hijo.setText(anioPublicacion);
	      libro.addContent(hijo);
	      
	      hijo = new Element("editorial");
	      hijo.setText(editorial);
	      libro.addContent(hijo);
	      
	      hijo = new Element("ciudad");
	      hijo.setText(ciudad);
	      libro.addContent(hijo);
	      
	      hijo = new Element("paisPublicacion");
	      hijo.setText(paisPublicacion);
	      libro.addContent(hijo);
	      
	      hijo = new Element("fotoPortada");
	      hijo.setText(fotoPortada);
	      libro.addContent(hijo);
	      
	      hijo = new Element("autor");
	      hijo.setText(autores);
	      libro.addContent(hijo);
	      
	    //escribimos nuevamente el documento ya modificado (con el auto agregado)
        this.escribeDocumentoXML(doc, rutaLibros);
	      
		  
	  }catch (IOException ioe){
          ioe.printStackTrace();
      }catch(JDOMException e){
          e.printStackTrace();
      }
  
  }
  
  public void eliminarLibro(String isbn) {
	  
	  Element libroEliminado = null;
	  
	  try
	    {
	      SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaLibros);

	      Element raiz = doc.getRootElement();
	      List libros = raiz.getChildren("libro");
	      Iterator it = libros.iterator();
	      List eliminado;
	      
	      while(it.hasNext()){
	        Element libro = (Element)it.next();
	        
	        if(libro.getAttributeValue("isbn").equals(isbn)){
	          libroEliminado = libro;
	          break;
	        }
	      }
	      
	      libroEliminado.detach();
	      
	      this.escribeDocumentoXML(doc, rutaLibros);

	    }catch(IOException e){e.printStackTrace();

	    }catch(JDOMException e){e.printStackTrace();
	    
	    }
  }
  
  public void eliminarLibroDeAutor(String isbn) {
	  
	  Element libroEliminado = null;
	  try
	    {
	      SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaAutores);

	      Element raiz = doc.getRootElement();
	      List autores = raiz.getChildren("autor");
	      Iterator it = autores.iterator();
	      
	      while(it.hasNext()){
	    	Element autor = (Element)it.next();
	        List libros = autor.getChildren("libro");
	        
	        Iterator itLibros = libros.iterator();
	        
	        while(itLibros.hasNext()) {
	        	Element libro = (Element)itLibros.next();
	        	if(libro.getText().equals(isbn)) {
	        		libroEliminado = libro;	
	    	        libroEliminado.detach();
	        		break;	
	        	}
	        }
	        
    		this.escribeDocumentoXML(doc, rutaAutores);

	        /*
	         * aqui lo que se tiene que hacer es en el autor del turno, extraer todos sus libros...
	         * de tal manera que se implemente una lista, donde cada tag es un index de la lista
	         * despues se itera la lista, y se compara el isbn recibido con el contenido de ese index
	         * o tal vez exista una funcion que busque el valor en la lista y lo elimine...
	         * despues se pasaria con el siguiente autor hasta terminar.
	         */
	        
	      }
	      
	    }catch(IOException e){;

	    }catch(JDOMException e){;
	    
	    }
  }
  
  public void escribeDocumentoXML(Element raiz, String ruta){
      System.out.println("Escribiendo el documento XML...");
      try{
          //creamos un objeto Document con el elemento raiz
          Document doc = new Document(raiz);
          XMLOutputter salida = new XMLOutputter();
          salida.output(doc, System.out);
          salida.setFormat(Format.getPrettyFormat());
          salida.output(doc, new FileWriter(ruta));
      }
      catch(IOException io){
          io.printStackTrace();
      }
  }
  
  public void escribeDocumentoXML(Document doc, String ruta){
      System.out.println("Escribiendo el documento XML a partir de Document...");
      try{
          //creamos un objeto Document con el elemento raiz
          //Document doc = new Document(raiz);
          XMLOutputter salida = new XMLOutputter();
          salida.output(doc, System.out);
          salida.setFormat(Format.getPrettyFormat());
          salida.output(doc, new FileWriter(ruta));
      }
      catch(IOException io){
          io.printStackTrace();
      }
  }
  
  public static void main(String[] args) 
  {
    LectorXML test = new LectorXML();
    test.obtenerLibros();
    Element libro = test.obtenerLibro("9788478888566");
    System.out.println(libro.getChild("titulo").getText());
  }


}


	    {
	      SAXBuilder constructor = new SAXBuilder();
	      Document doc = constructor.build(rutaAutores);

	      Element raiz = doc.getRootElement();
	      List autores = raiz.getChildren("autor");
	      Iterator it = autores.iterator();
	      
	      while(it.hasNext()){
	    	Element autor = (Element)it.next();
	        List libros = autor.getChildren("libro");
	        
	        Iterator itLibros = libros.iterator();
	        
	        while(itLibros.hasNext()) {
	        	Element libro = (Element)itLibros.next();
	        	if(libro.getText().equals(isbn)) {
	        		libroEliminado = libro;	
	    	        libroEliminado.detach();
	        		break;	
	        	}
	        }
	        
    		this.escribeDocumentoXML(doc, rutaAutores);

	        /*
	         * aqui lo que se tiene que hacer es en el autor del turno, extraer todos sus libros...
	         * de tal manera que se implemente una lista, donde cada tag es un index de la lista
	         * despues se itera la lista, y se compara el isbn recibido con el contenido de ese index
	         * o tal vez exista una funcion que busque el valor en la lista y lo elimine...
	         * despues se pasaria con el siguiente autor hasta terminar.
	         */
	        
	      }
	      
	    }catch(IOException e){;

	    }catch(JDOMException e){;
	    
	    }
  }
  
  public void escribeDocumentoXML(Element raiz){
      System.out.println("Escribiendo el documento XML...");
      try{
          //creamos un objeto Document con el elemento raiz
          Document doc = new Document(raiz);
          XMLOutputter salida = new XMLOutputter();
          salida.output(doc, System.out);
          salida.setFormat(Format.getPrettyFormat());
          salida.output(doc, new FileWriter(rutaLibros));
      }
      catch(IOException io){
          io.printStackTrace();
      }
  }
  
  public void escribeDocumentoXML(Document doc){
      System.out.println("Escribiendo el documento XML a partir de Document...");
      try{
          //creamos un objeto Document con el elemento raiz
          //Document doc = new Document(raiz);
          XMLOutputter salida = new XMLOutputter();
          salida.output(doc, System.out);
          salida.setFormat(Format.getPrettyFormat());
          salida.output(doc, new FileWriter(rutaLibros));
      }
      catch(IOException io){
          io.printStackTrace();
      }
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
