<%-- 
    Document   : calificaciones.notas
    Created on : Oct 17, 2012, 2:24:08 AM
    Author     : scarafia
--%>

<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="calificaciones.notas"/>

<c:choose>
    <c:when test="${calificaciones != null}">
        <table border="1">
            <thead>
                <tr>
                    <th>Curso</th>
                    <th>Fecha</th>
                    <th>Nota</th>
                    <th colspan="2">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${calificaciones}" var="calificacion">
                    <tr>
                        <td>${calificacion.curso}</td>
                        <td align="right">${calificacion["fecha"]}</td>
                        <td align="right">${calificacion["nota"]}</td>
                        <td align="center">
                            <button onclick="refreshCalificacionesForm('<c:url value="/calificaciones.form.jsp?id=${calificacion.id}&curso=${curso}&alumno=${alumno}"/>');">...</button>
                        </td>
                        <td align="center">
                            <button onclick="calificacionesFormSubmit('<c:url value="/calificaciones.form.post"/>', ${id}, ${curso}, ${alumno});">X</button>
                        </td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td colspan="5" align="center">
                            <button onclick="refreshCalificacionesForm('<c:url value="/calificaciones.form.jsp?id=0&curso=${curso}&alumno=${alumno}"/>');">Agregar Calificación</button>
                        </td>
                    </tr>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        ${msg}
    </c:otherwise>
</c:choose>

<div id="calificaciones-form"></div>

