<%-- 
    Document   : calificaciones.cursos
    Created on : Oct 16, 2012, 7:38:05 PM
    Author     : scarafia
--%>

<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="calificaciones.cursos"/>

<c:choose>
    <c:when test="${cursos != null}">
        <c:forEach items="${cursos}" var="curso">
            <div class="tab">
                <a href="#" onclick="refreshCalificacionesNotas('<c:url value="/calificaciones.notas.jsp?curso=${curso.id}&alumno=${alumno}"/>');">
                    ${curso.nombre}
                </a>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        ${msg}
    </c:otherwise>
</c:choose>

