<%-- 
    Document   : calificaciones.form
    Created on : Oct 17, 2012, 4:15:51 AM
    Author     : scarafia
--%>

<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="calificaciones.form"/>

<div class="form-fields">
    <div id="form-msg"></div>
    <div class="form-field">
        <label>Fecha:</label>
        <input id="fecha" type="text" name="fecha" value="${fecha}">
    </div>
    <div class="form-field">
        <label>Nota:</label>
        <input id="nota" type="text" name="nota" value="${nota}">
    </div>
</div>
<div class="form-buttons">
    <div class="form-button">
        <button onclick="calificacionesFormSubmit('<c:url value="/calificaciones.form.post"/>', ${id}, ${curso}, ${alumno});">Guardar</button>
    </div>
</div>

