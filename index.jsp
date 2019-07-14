<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,lector.LectorXML, org.jdom.Element"%>
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
      <h2>Acervo de Libros</h2>
      <div id="listaLibros">
        <!--Busca los libros en el XML y los muestra-->
        <table id="tablaLibros">
          <tr>
            <td>Titulo</td>
            <td>Año de Publicacion</td>
            <td></td>
          </tr>
        <%
          LectorXML lector = new LectorXML();
          List<Element> lista = lector.obtenerLibros();
          Iterator it = lista.iterator();
          while(it.hasNext())
          {
            Element libro = (Element)it.next();
        %>
          <tr>
            <td class="tituloLibro"><a href="libroVisualizado.jsp?isbn=<%=libro.getAttribute("isbn").getValue()%>"> <%=libro.getChild("titulo").getText()%></a></td>
            <td class="anioLibro"> <%=libro.getChild("anioPublicacion").getText()%></td>
            <td> <a href="eliminarLibro.jsp?isbn=<%=libro.getAttribute("isbn").getValue()%>">
                <img src="imgs/borrar.png"/>
                </a>
          </tr>
        <%
          }
        %>
        </table>
      </div>
      
	
      <div id="formIngresaLibro">
        <form action="ingresaNuevoLibro.jsp" method="post">
          <table>
          	<tr>
              <td>ISBN:</td>
              <td><input type="text" name="isbn"/></td>
            </tr>
            <tr>
              <td>Titulo:</td>
              <td><input type="text" name="titulo"/></td>
            </tr>
            <tr>
              <td>Tema:</td>
              <td><input type="text" name="tema"/></td>
            </tr>
            <tr>
              <td>Idioma:</td>
              <td><input type="text" name="idioma"></td>
            </tr>
            <tr>
              <td>Año de Publicación:</td>
              <td><input type="text" name="anio"></td>
            </tr>
            <tr>
              <td>Editorial:</td>
              <td><input type="text" name="editorial"></td>
            </tr>
            <tr>
              <td>Ciudad:</td>
              <td> <input type="text" name="ciudad"> </td>
            </tr>
            <tr>
              <td>Pais:</td>
              <td> <input type="text" name="pais"> </td>
            </tr>
            <tr>
              <td>Foto de Portada:</td>
              <td> <input type="text" name="fotoPortada"> </td>
            </tr>
            <tr>
            <tr>
              <td>Autores:</td>
              <td> <input type="text" name="autores"> </td>
            </tr>
            <tr>
              <td>Resumen:</td>
              <td><textarea rows="5" cols="20" name="resumen"></textarea></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="submit" value="agregar"/></td>
            </tr>
          </table>
          
        </form>
      </div>
    </body>
</html>
