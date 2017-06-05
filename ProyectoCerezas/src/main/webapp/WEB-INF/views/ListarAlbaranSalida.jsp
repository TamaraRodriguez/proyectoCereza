<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/WEB-INF/views/head.jsp" />

<form>
	 <label for="n_albaran"><spring:message code="n_albaran" />Nº Albaran:</label> 
	<input type="text" id="n_albaran" value="" /> 
	<label for="cif_nif"><spring:message code="cif_nif" />CIF/NIF:</label> 
	<input type="text" id="cif_nif" value="" /> 
	<label for="fecha"><spring:message code="fecha" />Fecha:</label> 
	<input type="text" id="fecha" value="" /> 
<input type = "button" class="btn btn-lg btn-default"><spring:message code = "buscar"/><!--añadir imagen de la lupa en el boton / css-->
	<table>

		<tr>
			<th>Nº albaran</th>
			<th>Nº cliente</th>
			<th>Fecha</th>
			<th>Nº factura</th>
			<th>Editar</th>
	        <th>Imprimir</th>

		</tr>


		<c:forEach items="${lista}" var="c">
			<tr>
				<td>${c.}</td>
				<td>${c.n_cliente}</td>
				<td>${c.fecha}</td>
				<td>${c.n_albaran}</td>
				<td> <input type = "button" class="btn btn-lg btn-default"><spring:message code = "editar"/></td>
				<td> <input type = "button" class="btn btn-lg btn-default"><spring:message code = "descargar"/></td>
			</tr>
		</c:forEach>
	</table>

</form>

<c:import url="/WEB-INF/views/end.jsp" />
