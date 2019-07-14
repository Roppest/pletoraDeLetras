<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator, java.util.List, java.util.ArrayList, org.jdom.*, lector.LectorXML"%>
<%

String isbn = request.getParameter("isbn");
LectorXML lector = new LectorXML();
Element libro = lector.obtenerLibro(isbn);

	
%>


<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css"> <!-- href="../css/service.css"> -->
  <link href='https://fonts.googleapis.com/css?family=Nova Flat' rel='stylesheet'>
  <meta charset="utf-8">
  <script type="text/javascript" src="../js/pletora.js"></script>
  <title>NetSoporte</title>
</head>
<body>
 <table>
    	<tr>
    		<td class="datoLibro">TITULO: <%=libro.getChild("titulo").getText()%></td>
    		<td>RESUMEN: <%=libro.getChild("resumen").getText()%></td>
	      	<td>TEMA: <%=libro.getChild("tema").getText()%></td>	      	
	      	<td>IDIOMA: <%=libro.getChild("idioma").getText()%></td>	      	
	      	<td>AÑO DE PUBLICACIÓN: <%=libro.getChild("anioPublicacion").getText()%></td>	      	
	      	<td>EDITORIAL: <%=libro.getChild("editorial").getText()%></td>	      	
	      	<td>CIUDAD: <%=libro.getChild("ciudad").getText()%></td>
	      	<td>PAIS DE PUBLICACIÓN: <%=libro.getChild("paisPublicacion").getText()%></td>
	      	<td>PORTADA: <%=libro.getChild("fotoPortada").getText()%></td>
    	</tr>
   </table> 
</body> 
</html>
