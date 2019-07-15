<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,lector.LectorXML,org.jdom.Element"%>
<%
	

	String clave = "";
	String nombre = "";
	String fechaNacimiento = "";
	String nacionalidad = "";
	String anioPublicacion = "";
	String semblanza = "";
	String fotoAutor = "";
	String libros = "";
	String autores = "";
	
	if(request.getParameter("clave") != null && request.getParameter("nombre") != null && request.getParameter("fechaNacimiento") != null && 
			request.getParameter("nacionalidad") != null && request.getParameter("anioPublicacion") != null && request.getParameter("semblanza") != null && 
			request.getParameter("fotoAutor") != null && request.getParameter("libros") != null){

		clave = request.getParameter("clave");
		nombre = request.getParameter("nombre");
		fechaNacimiento = request.getParameter("fechaNacimiento");
		nacionalidad = request.getParameter("nacionalidad");
		anioPublicacion = request.getParameter("anioPublicacion");
		semblanza = request.getParameter("semblanza");
		fotoAutor = request.getParameter("fotoAutor");
		libros = request.getParameter("libros");
		autores = request.getParameter("autores");
		
		if(clave.equals("") || nombre.equals("") || fechaNacimiento.equals("") || nacionalidad.equals("") || anioPublicacion.equals("") || semblanza.equals("") || 
				fotoAutor.equals("") || libros.equals("")){
				
			%>
			<jsp:forward page="agregaLibro.jsp">
				<jsp:param name="missingFields" value="No has llenado todos los campos. Vuelve a intentarlo."/>
				<jsp:param name="autores" value="<%=autores%>"/>
			</jsp:forward>
			<%
		}else{
			//agregamos al autor en cuestion y devolvemos la lista de autores
			LectorXML lector = new LectorXML();
			lector.agregarAutor(clave, nombre, fechaNacimiento, nacionalidad, anioPublicacion, semblanza, fotoAutor, libros);  
			%>
			<jsp:forward page="agregaAutor.jsp">
				<jsp:param name="success" value="Se ha agregado el libro correctamente."/>
				<jsp:param name="autores" value="<%=autores%>"/>
			</jsp:forward>
			<%
		}
	}else{
		%>
		<jsp:forward page="agregaLibro.jsp">
			<jsp:param name="error" value="Hubo un error."/>
		</jsp:forward>
		<%
	}	
%>