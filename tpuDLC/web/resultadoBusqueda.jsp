<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Ranking</title>
        <link href="https://fonts.googleapis.com/css?family=Fjalla+One" rel="stylesheet"> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script>
            $(document).ready(function()
            {
                $("#pagina1-2").click(function()
                {
                    $(".listado").hide();
                    $("#pagina2").show();
                });
                $("#pagina2-1").click(function()
                {
                    $(".listado").hide();
                    $("#pagina1").show();
                });
                $("#pagina2-3").click(function()
                {
                    $(".listado").hide();
                    $("#pagina3").show();
                });
                $("#pagina3-2").click(function()
                {
                    $(".listado").hide();
                    $("#pagina2").show();
                });
            });
        </script>
        <style>
            body
            {
                background-color:#d7fac4;
            }
            .titulo, .listado, .subtitulo, .volver
            {
                font-family: Fjalla One;
                width: 50%;
                margin: auto;
                text-align: center;                                
            }
            .listado
            {
                display: none;
            }
            .titulo
            {
                font-size: 30px; 
                line-height: 100%;
                color: #6fdeab;
                text-shadow: 1.5px 1.5px green, -1.5px 1.5px green, 1.5px -1.5px green, -1.5px -1.5px green;
            }
            .subtitulo
            {
                font-size: 20px; 
                line-height: 20%;
            }
            .boton
            {
                border: 2px solid green;
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
                background-color: #4CAF50;
                color: white;
                padding: 15px 32px;
                font-family: Fjalla One;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
            }
        </style>           
        <title>Resultado</title>
    </head>
    <body>
        <div class="titulo">
            <h1>Documentos encontrados</h1>
        </div>
        <div id="pagina1" class="listado" style="display: block">
            <br>
            <h2 class="subtitulo">Página 1</h2>
            <ol>
                <c:forEach items="${listaDocumentos}" var="d" begin="0" end="9" varStatus="i">
                    <li value="${i.index + 1}">Documento: <c:out value="${d.nombre}"></c:out></li>
                    <br>
                </c:forEach>
            </ol>
            <input type="button" id="pagina1-2" class="boton" value="Siguiente">
        </div>
        <div id="pagina2" class="listado">
            <br>
            <h2 class="subtitulo">Página 2</h2>
            <ol>
                <c:forEach items="${listaDocumentos}" var="d" begin="10" end="19" varStatus="i">
                    <li value="${i.index + 1}">Documento: <c:out value="${d.nombre}"></c:out></li>
                    <br>
                </c:forEach>
            </ol>
            <input type="button" id="pagina2-1" class="boton" value="Atrás">
            <input type="button" id="pagina2-3" class="boton" value="Siguiente">
        </div>
        <div id="pagina3" class="listado">
            <br>
            <h2 class="subtitulo">Página 3</h2>
            <ol>
                <c:forEach items="${listaDocumentos}" var="d" begin="20" end="29" varStatus="i">
                    <li value="${i.index + 1}">Documento: <c:out value="${d.nombre}"></c:out></li>
                    <br>
                </c:forEach>
            </ol>
            <input type="button" id="pagina3-2" class="boton" value="Atrás">  
        </div>
        <br>
        <div class="volver">
            <form action="index.html">
                <input type="submit" value="Volver al inicio" class="boton"/>
            </form> 
        </div>
    </body>
</html>
