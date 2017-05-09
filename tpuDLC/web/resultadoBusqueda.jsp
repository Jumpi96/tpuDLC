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
                 text-align: center;
            }
            a{
                font-family: sans-serif; 
                text-align: center;
            }
            .titulo
            {
                font-size: xx-large; 
                line-height: 20%;
                color: #ffffff;
                text-align: center;  
                
            }
            .container
            {
                text-align: center;
            }
            .div
            {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="titulo">
        <h1>Resultados de la búsqueda</h1>     
        </div>
        
        <div class="container">               
        
        <% String t[] = (String [])request.getAttribute("titulos");%>
        <% String o[] = (String [])request.getAttribute("origenes");%>
        <ol>
        <%  
        if(t.length ==0){
        out.write("La búsqueda no arojó resultados");
        }
        else{
            for(int i=0;i<t.length;i++){
                String nombre = t[i];
                String origen = o[i];              
                out.write("<li>"+nombre+" <a href =\"origen\" download = \" "+nombre+ " \" > Descargar </a></li><br>"); 
            }
        }
        %>
        </ol>
        
        </div>
        <div>
        <a id="volver" href="index.html">Volver</a>
        </div>
        <br>
        <footer>
        <p class="text-center" id="integrantes"> Antonellini, Gil, Lorenzo ©</p>
        </footer>
    </body>
</html>
