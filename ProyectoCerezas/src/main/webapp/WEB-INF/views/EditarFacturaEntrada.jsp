<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/WEB-INF/views/head.jsp" />
<h1>DATOS DE FACTURA</h1>

	<table>

		<tr>
			<th>Nº albaran</th>
			<th>Nº cliente</th>
			<th>Fecha</th>
			<th>Lugar de recogida</th>
			<th>Nº factura</th>
				<th>Eliminar</th>
			<th>Añadir</th>
		

		</tr>


		<c:forEach items="${lista_albaranes}" var="c">
			<tr> 
				<td>${c.n_albaran}</td>
				<td>${c.n_cliente}</td>
				<td>${c.fecha}</td>
				<td>${c.lugar_recogida}</td>
				<td>${c.n_factura}</td>
				<td> <a href="">Eliminar</a></td>
				<td> <a href="">Añadir</a></td>
			</tr>
		</c:forEach>
	</table>

<c:import url="/WEB-INF/views/end.jsp" />