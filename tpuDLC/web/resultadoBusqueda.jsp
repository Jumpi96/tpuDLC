<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>  
        <title>Resltados</title>
        <style>
            body{
                background-color: cornsilk;
            }
            #volver{
                position: relative; 
                top: 30px;
            }
            #integrantes{
                position: absolute; 
                right: 30px; 
                bottom: 10px; 
               
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
        <img>
        <form action="Conexion" method="POST" onsubmit="showSpinner()">
                    <input class="w3-input" type="text" placeholder="Ingrese aquí su búsqueda..." name="campoBusqueda" autofocus required>
                    <!--<input class="w3-button w3-hover-blue-grey w3-block w3-xlarge" type="submit" value="Buscar">-->
        </form>
        
        <% String t[] = (String [])request.getAttribute("titulos");%>
        <% String o[] = (String [])request.getAttribute("origenes");%>
        <ol>
        <%
        for(int i=0;i<t.length;i++){
            String nombre=t[i];
            out.write("<li>"+nombre+" <a href =\"./DocumentosTP1/"+nombre+" \" download = \" "+nombre+ " \" > Descargar </a></li><br>");            
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
