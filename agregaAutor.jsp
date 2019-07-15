<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,java.lang.String,lector.LectorXML, org.jdom.Element"%>
<%
	//si no llenamos algun campo..
	if(request.getParameter("missingFields") != null){
		String Msj = (String)request.getParameter("missingFields");
	%>
		<script>
			alert('<%=Msj%>');
		</script>	
	<%		
	}

	//si se agrego el libro
	if(request.getParameter("success") != null){
		String Msj = (String)request.getParameter("success");
	%>
		<script>
			alert('<%=Msj%>');
		</script>	
	<%		
	}
	
	//si hubo un error
	if(request.getParameter("error") != null){
		String Msj = (String)request.getParameter("error");
	%>
		<script>
			alert('<%=Msj%>');
		</script>	
	<%		
	}
	
	//clave del autor
	String clave = "";
	//autores que seran creados
	String autores = request.getParameter("autores");
	//si ya no quedan autores, es decir, ya todos fueron agregados...
	if(autores.equals("")){
		%>
			<jsp:forward page="agregaLibro.jsp">
				<jsp:param name="successAutor" value="Se ha agregado el autor correctamente."/>
			</jsp:forward>
		<%
	}else{
		//si queda un solo autor...
		if(autores.length() == 5){
			clave = autores.replace(",", "");
			autores = "";
		}else{
			//si queda mas de un autor
			String[] autoresSplit = autores.split(",");
			autores = autores.substring(5);
			clave = autoresSplit[0];	
		}
	}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/pletora.css">
        <title>Sistema WEB</title>
    </head>
    <body>
      <h1>Pletora de Letras</h1>
      <hr>
      <h2>Agregar Autor</h2>
      
	
      <div id="formIngresaAutor">
        <form action="agregaAutorProceso.jsp" method="post">
          <table>
          	<tr>
              <td>Clave</td>
              <td><input type="text" name="clave" value="<%=clave%>" readonly/></td>
            </tr>
            <tr>
              <td>Nombre</td>
              <td><input type="text" name="nombre"/></td>
            </tr>
            <tr>
              <td>Fecha de Nacimiento: </td>
              <td><input type="text" name="fechaNacimiento"/></td>
            </tr>
            <tr>
              <td>Nacionalidad: </td>
              <td><input type="text" name="nacionalidad"></td>
            </tr>
            <tr>
              <td>Año de Publicación:</td>
              <td><input type="text" name="anioPublicacion"></td>
            </tr>
            <tr>
              <td>Semblanza :</td>
              <td><input type="text" name="semblanza"></td>
            </tr>
            <tr>
              <td>Foto de Autor: </td>
              <td> <input type="text" name="fotoAutor"> </td>
            </tr>
            <tr>
              <td>Libros: </td>
              <td> <input type="text" name="libros"> </td>
            </tr>
            <tr>
              <td></td>
              <td><input type="submit" value="agregar"/>
              	  <input type="hidden" name="autores" value="<%=autores%>"/>
              </td>
              
            </tr>
          </table>
          
        </form>
      </div>
    </body>
</html>