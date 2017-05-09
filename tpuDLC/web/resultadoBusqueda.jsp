<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>  
        <title>Resultados</title>
        <style>
            body{
                background-color: #9acfea;
            }         
           
            p{
                 font-family: sans-serif;
            }
            a{
                font-family: sans-serif; 
            }
        </style>
    </head>
    <body>
               
        
        <% String t[] = (String [])request.getAttribute("titulos");%>
        <% String o[] = (String [])request.getAttribute("origenes");%>
        <ol>
        <%
        for(int i=0;i<t.length;i++){
            String nombre = t[i];
            String origen = o[i];
            System.out.println(new File(".").getAbsolutePath());
            out.write("<li>"+nombre+" <a href ="+origen+" download = \" "+nombre+ " \" > Descargar </a></li><br>"); 
        }    
        %>
        </ol>
        <a id="volver" href="index.html">Volver</a>
        <br>
        <footer>
        <p class="text-center" id="integrantes"> Antonellini, Gil, Lorenzo ©</p>
        </footer>
    </body>
</html>
