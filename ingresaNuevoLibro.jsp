<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,lector.LectorXML,org.jdom.Element"%>
<%
	
	String isbn = "";
	String titulo = "";
	String tema = "";
	String idioma = "";
	String anioPublicacion = "";
	String editorial = "";
	String ciudad = "";
	String paisPublicacion = "";
	String fotoPortada = "";
	String autores = "";
	String resumen = "";

	if(request.getParameter("isbn") != null && request.getParameter("titulo") != null && request.getParameter("tema") != null && request.getParameter("idioma") != null
			&& request.getParameter("anio") != null && request.getParameter("editorial") != null && request.getParameter("ciudad") !=
			null && request.getParameter("pais") != null && request.getParameter("fotoPortada") != null && request.getParameter("autores") != null && 
			request.getParameter("resumen") != null){

		isbn = request.getParameter("isbn");
		titulo = request.getParameter("titulo");
		tema = request.getParameter("tema");
		idioma = request.getParameter("idioma");
		anioPublicacion = request.getParameter("anio");
		editorial = request.getParameter("editorial");
		ciudad = request.getParameter("ciudad");
		paisPublicacion = request.getParameter("pais");
		fotoPortada = request.getParameter("fotoPortada");
		autores = request.getParameter("autores");
		resumen = request.getParameter("resumen");		
		
		if(isbn.equals("") || titulo.equals("") || tema.equals("") || idioma.equals("") || anioPublicacion.equals("") || editorial.equals("") || ciudad.equals("") || 
				paisPublicacion.equals("") || fotoPortada.equals("") || autores.equals("") || resumen.equals("")){
			%>
			<jsp:forward page="index.jsp">
				<jsp:param name="missingFields" value="No has llenado todos los campos. Vuelve a intentarlo."/>
			</jsp:forward>
			<%
		}else{
			LectorXML lector = new LectorXML();
			lector.agregarLibro(isbn, titulo, resumen, tema, idioma, anioPublicacion, editorial, ciudad, paisPublicacion, fotoPortada, autores);
			%>
			<jsp:forward page="index.jsp">
				<jsp:param name="success" value="Se ha agregado el libro correctamente."/>
			</jsp:forward>
			<%
		}
	}else{
		%>
		<jsp:forward page="index.jsp">
			<jsp:param name="error" value="Hubo un error."/>
		</jsp:forward>
		<%
	}

			
%>