<%--
    Document   : pletoraDeLetras
    Created on : Jul 4, 2019, 11:02:31 AM
    Author     : Rodrigo Vázquez & Javier Erazo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema WEB</title>
    </head>
    <body>
      <h1>Pletora de Letras</h1>
      <hr>
      <form action="ingresaNuevoLibro.jsp" method="post">
        <table>
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
            <td>Resumen:</td>
            <td><textarea rows="5" cols="20" name="resumen"></textarea></td>
          </tr>
          <tr>
            <td></td>
            <td><input type="submit" value="agregar"/></td>
          </tr>
        </table>
      </form>
    </body>
</html>
