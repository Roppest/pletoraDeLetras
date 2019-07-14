<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator, java.util.List, java.util.ArrayList, org.jdom.*, lector.LectorXML"%>
<%
  String isbn = "";
  
 	if(request.getParameter("isbn") != null)
 	{
		isbn = request.getParameter("isbn");
  	}
  
	LectorXML lector = new LectorXML();
	//obtenemos el libro a visualizar, dado un isbn
	Element libro = lector.obtenerLibro(isbn);
	//obtenemos la lista de autores de dicho libro, pero solo como clave... necesitamos los nombres
	List<Element> autoresDeLibro = lector.obtenerAutoresDeLibro(libro);
	Iterator it = autoresDeLibro.iterator();
	
%>


<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../css/catalogo.css">
  <link href='https://fonts.googleapis.com/css?family=Nova Flat' rel='stylesheet'>
  <meta charset="utf-8">
  <script type="text/javascript" src="../js/pletora.js"></script>
  <title>NetSoporte</title>
</head>
<body>
	<h1>Pletora de Letras</h1>
    <hr>	
    <h2>ISBN <%=isbn%></h2>
    <table>
    	<tr>
    		<td>TITULO: <%=libro.getChild("titulo").getText()%></td>
    	</tr>
    	<tr>
    		<td>RESUMEN: <%=libro.getChild("resumen").getText()%></td>
	    </tr>
	    <tr>
	      	<td>TEMA: <%=libro.getChild("tema").getText()%></td>	      	
	    </tr>
	    <tr>  	
	      	<td>IDIOMA: <%=libro.getChild("idioma").getText()%></td>	      	
	    </tr>  	
	    <tr>  	
	      	<td>AÑO DE PUBLICACIÓN: <%=libro.getChild("anioPublicacion").getText()%></td>	      	
	    </tr>  	
	    <tr> 	
	      	<td>EDITORIAL: <%=libro.getChild("editorial").getText()%></td>	      	
	    </tr> 	
	    <tr> 	
	      	<td>CIUDAD: <%=libro.getChild("ciudad").getText()%></td>
	    </tr>
	    <tr> 	
	      	<td>PAIS DE PUBLICACIÓN: <%=libro.getChild("paisPublicacion").getText()%></td>
	    </tr> 	
	    <tr> 	
	      	<td>PORTADA: <%=libro.getChild("fotoPortada").getText()%></td>
    	</tr>
    	<%
    		while(it.hasNext()){
    			Element autorDeLibro = (Element)it.next();
    			Element autor = lector.obtenerAutor((String)autorDeLibro.getText());
    	%>
    	<tr>
	      	<td>AUTOR: <a href="autorVisualizado.jsp?clave=<%=autorDeLibro.getText()%>"> <%=autor.getChild("nombre").getText()%></a></td>
    	</tr>
    	<%
    		}
    	%>
   </table> 
</body> 
</html>
