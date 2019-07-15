<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,lector.LectorXML,org.jdom.Element"%>
<%
	
/*

TENEMOS QUE RECIBIR LOS DATOS DEL LIBRO
ASI COMO CLAVE EN UN GRAN STRING SEPARADA POR COMAS
		SE AGREGA EL LIBRO
		Y SE BUSCA ESA CLAVE EN EL ARCHVO XML DE AUTORES
		SI NO LA ENCUENTRA
		NOS REDIRECCIONA A LA PANTALLA DE AGREGAAUTOR
		TIENE QUE MANDAR A LLAMAR EL METODO AGREGARAUTOR

*/
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
			<jsp:forward page="agregaLibro.jsp">
				<jsp:param name="missingFields" value="No has llenado todos los campos. Vuelve a intentarlo."/>
			</jsp:forward>
			<%
		}else{
			LectorXML lector = new LectorXML();
			lector.agregarLibro(isbn, titulo, resumen, tema, idioma, anioPublicacion, editorial, ciudad, paisPublicacion, fotoPortada, autores);
			//separamos todos los autores
			String[] autorSplit = autores.split(",");
		    
			//en caso de que haya autores nuevos, se guardaran aqui
			String autoresNuevos = "";
		    String autoresViejos = "";
		    //recorremos todos los autores, buscando los nuevos y viejos
			for(String autor: autorSplit){
				//si ya esta en el archivo seguimos al siguiente autor
		    	if(lector.estaAutorEnArchivo(autor) == false){
		    		//de lo contrario, lo guardamos en autoresNuevos
		    		autoresNuevos = autoresNuevos + autor + ",";
		    	}else{
		    		autoresViejos = autoresViejos + autor + ",";
		    	}
		    }
		    //si hay viejos autores que se tengan que actualizar en el archivo de autores
		    String[] autorViejosSplit = autoresViejos.split(",");
		    if(!autoresViejos.equals("")){
		    	for(String autorActualizado: autorViejosSplit){
					lector.agregarLibroDeAutor(autorActualizado, isbn);
				}	
		    }
			
		    //si no hay autores nuevos, entonces
			if(autoresNuevos.equals("")){
				
				
				%>
				<jsp:forward page="agregaLibro.jsp">
					<jsp:param name="success" value="Se ha agregado el libro correctamente."/>
				</jsp:forward>
				<%
			}else{//si hay autores nuevos
				%>
				<jsp:forward page="agregaAutor.jsp">
					<jsp:param name="autores" value="<%=autoresNuevos%>"/>
				</jsp:forward>
				<%
			}
		} 
	}else{
			%>
			<jsp:forward page="agregaLibro.jsp">
				<jsp:param name="error" value="Hubo un error."/>
			</jsp:forward>
			<%
	}
%>