<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,lector.LectorXML,org.jdom.Element"%>
<%
	
	String isbn = "";
	

	if(request.getParameter("isbn") != null){

		isbn = request.getParameter("isbn");	
		
		if(isbn.equals("")){
			%>
			<jsp:forward page="index.jsp">
				<jsp:param name="errorISBN" value="Hubo un error en el ISBN."/>
			</jsp:forward>
			<%
		}else{
			LectorXML lector = new LectorXML();
			lector.eliminarLibro(isbn);
			lector.eliminarLibroDeAutor(isbn);
			%>
			<jsp:forward page="index.jsp">
				<jsp:param name="successEliminar" value="Se ha eliminado el libro correctamente."/>
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