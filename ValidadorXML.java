/**
 * Autor: Carlos Roberto Jaimez González
 * U.E.A.: Integración de Sistemas
 * Universidad Autónoma Metropolitana
 * carlos.jaimez@gmail.com
 * Requiere xalan.jar
 */

import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
public class ValidadorXML implements ErrorHandler
{
  /**
    *(ruta del archivo xml) retorna booleano si es valido
    */
  public boolean valido = true;
  public boolean validaXML(String xml)throws Exception
  {
    DOMParser parser = new DOMParser();
    //Colocamos validación en "on"
    try {
        parser.setFeature
                ("http://xml.org/sax/features/validation", true);
        parser.setFeature
                ("http://apache.org/xml/features/validation/schema", true);
        parser.setFeature
                ("http://apache.org/xml/features/validation/schema-full-checking", true);
    } catch (Exception e) {
        System.out.println(e);
    }
    //Colocamos el ErrorHandler a nuestra clase ValidadorXML
    parser.setErrorHandler( new ValidadorXML() );

    //Hacemos que el parser recorra el documento XML y verifique
    //que cumple con las reglas establecidas en el Schema XML
    parser.parse(xml);
    return valido;
}

//Event Handler de "Warning"
  public void warning(SAXParseException e)throws SAXException
  {
    System.err.println("Warning:  " + e);
    valido = false;
  }

  //Event Handler de "Error"
  public void error(SAXParseException e)throws SAXException
  {
    System.err.println("Error:  " + e);
    valido = false;
  }

  //Event Handler de "Fatal Error"
  public void fatalError(SAXParseException e)throws SAXException
  {
    System.err.println("Fatal Error:  " + e);
    valido = false;
  }

}
