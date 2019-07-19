<%--
    Document   : pletoraDeLetras
    Created on : Jul 4, 2019, 11:02:31 AM
    Author     : Rodrigo Vázquez & Javier Erazo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator,java.util.List,java.util.ArrayList,lector.LectorXML, org.jdom.Element"%>
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
            <td> <a href="elminarLibro.jsp?isbn=<%=libro.getAttribute("isbn").getValue()%>">
                <img src="imgs/borrar.png"/>
                </a>
          </tr>
        <%
          }
        %>
        </table>
       </form>
      </div>
    </body>
</html>
