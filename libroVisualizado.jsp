<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator, java.util.List, java.util.ArrayList, org.jdom.*, lector.LectorXML"%>
<%
  String titulo = "Titulo";
  String resumen = "Resumen";
  String tema = "Tema";
  String idioma = "Idioma";
  String anioPublicacion = "AnioPublicacion";
  String editorial = "Editorial";
  String ciudad = "Ciudad";
  String paisPublicacion = "Pais";
  String fotoPortada = "Foto";
  
 	//if(request.getParameter("isbn") != null)
 	//{
		String isbn = request.getParameter("isbn");
  	//}
  
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

	<h1>Pletora de Letras</h1>
    <hr>
    <h2>ISBN <%=isbn%></h2>
      
      <tr>
      	<td>TITULO: <%=libro.getChild("titulo").getText()%> </td>
      	<br><br>
      	<td>RESUMEN: <%=libro.getChild("resumen").getText()%></td>
      	<br><br>
      	<td>TEMA: <%=libro.getChild("tema").getText()%></td>
      	<br><br>
      	<td>IDIOMA: <%=libro.getChild("idioma").getText()%></td>
      	<br><br>
      	<td>AÑO DE PUBLICACIÓN: <%=libro.getChild("anioPublicacion").getText()%></td>
      	<br><br>
      	<td>EDITORIAL: <%=libro.getChild("editorial").getText()%></td>
      	<br><br>
      	<td>CIUDAD: <%=libro.getChild("ciudad").getText()%></td>
      	<br><br>
      	<td>PAIS DE PUBLICACIÓN: <%=libro.getChild("paisPublicacion").getText()%></td>
      	<br><br>
      	<td>PORTADA: <%=libro.getChild("fotoPortada").getText()%></td>
      	<br><br>
      	<br><br>
      </tr> 
   
    

</body> 
</html>
