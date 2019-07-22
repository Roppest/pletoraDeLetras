package lector;
import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
public class XMLValidator {

	public Boolean validate(String xmlPath, String xsdPath){

		try{
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new File(xsdPath));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(new File(xmlPath)));
	    } catch (IOException | SAXException e) {
	        System.out.println("Exception: "+e.getMessage());
	        return false;
	    }
	        return true;
	    }


    /*public static void main(String[] args) {
        XMLValidator XMLValidator = new XMLValidator();
        String rutaLibros = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/pletora/WebContent/xml/catalogoLibros.xml";
        String rutaSchemaLibros = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/pletora/WebContent/xsd/catalogoLibrosTipo.xsd";

        boolean valid = XMLValidator.validate(rutaLibros, rutaSchemaLibros);

        System.out.printf("%s validation = %b.", rutaLibros, valid);
    }*/
}
