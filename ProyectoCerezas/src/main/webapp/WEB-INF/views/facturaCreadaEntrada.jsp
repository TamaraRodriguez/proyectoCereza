<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- creo que javi lo ha llamado cabecera, si no hay que cambiarlo -->
<c:import url="/WEB-INF/views/head.jsp" />
<div id="datos">
	<table>
		<th>Nombre de la empresa</th>
		<tr>
			<th>CIF:</th>
			<th>Dirección:</th>
			<th>Teléfonos:</th>
			<th>E-mail:</th>
		</tr>
	</table>
	<table>
		<!-- recoger los datos del agricultor en la tabla personas para que aparezcan sus datos -->
		<c:forEach items="${personas}" var="agricultor">
			<tr>
				<!-- comprobar que el controlodar que se encargue de recoger los datos del agricultor relacionado con el nº Agricultor -->
				<th><spring:message code='nombre_razon_social'/></th>
					<td href="#?#=${agricultor.#}">${agricultor.nombreRazonSocial}</td>
				<th><spring:message code='apellido'/></th>
					<td href="#?#=${agricultor.#}">${agricultor.apellido}</td>
				<th><spring:message code='cif_nif'/></th>
					<td href="#?#=${agricultor.#}">${agricultor.cifNif}></td>
				<th><spring:message code='direccion'/></th>
					<td href="#?#=${agricultor.#}">${agricultor.direccion}></td>
				<th><spring:message code='telefono'/></th>
					<td href="#?#=${agricultor.#}">${agricultor.telefono}></td>
				<th><spring:message code='email'/></th>
					<td href="#?#=${agricultor.#}">${agricultor.email}></td>	
			</tr>
		</c:forEach>
	</table>
</div>
<div id="datos_factura">
	<table>
		<!-- Recuperar todos los datos de la nuevaFacturaEntrada.jsp-->
		<c:forEach items="${#}" var="agricultor">
		<tr>
			<td><spring:message code='numeroFactura'/></td>
				<td href="#?#=${agricultor.#}">${agricultor.nFactura}</td>
			<td><spring:message code='numeroAgricultor'/></td>
				<td href="#?#=${agricultor.#}">${agricultor.nAgricultor}</td>
			<td><label for="Fecha"><spring:message code='Fecha' /></label></td>
				<td><a href="#?#=${agricultor.#}">${e.stringFecha}</a></td>	
		</tr>
	</table>
</div>
<br>
<br>
<div id="listado_albaranes">
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaranesGuardados}" var="albaran">
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<th><spring:message code='nAlbaran' /></th>
					<td href="#?#=${albaran.#}">${albaran.nAlbaran}</td>
				<th><spring:message code='fecha' /></th>
					<td href="#?#=${albaran.#}">${albaran.stringFecha}></td>
					<!-- <script>$("#fecha").datepicker({dateFormat: "${formatoFecha}",constrainInput:true});</script> -->
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.totalAlbaran}></td>
					<!-- comprobar el controlodar que se encargue de calcular el total del albaran -->
			</tr>
			<c:set var="i"  value="${i+1}"/>
		</c:forEach>
	</table>
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaranesGuardados}" var="albaran"> <!-- Habría que ver como cuadro el forEach con items. Tendriamos que pasarlo desde controlador -->
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<th>                </th>
				<th></th>
				<th><spring:message code='tipo' /></th>
					<td href="#?#=${albaran.#}">${albaran.tipo}></td>
				<th></th>	
				<th><spring:message code='peso' /></th>
					<td href="#?#=${albaran.#}">${albaran.peso}></td>
				<th></th>
				<th><spring:message code='precioKg' /></th>
					<td href="#?#=${albaran.#}">${albaran.precioKg}></td>
				<th></th>
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.total}</td>
					<!-- comprobar el controlodar que se encargue de calcular el total de lo que cuesta el producto por tipo, peso y precio-->
			</tr>
			<c:set var="i"  value="${i+1}"/>
		</c:forEach>
	</table>
</div>
<div id="totales">
	<table align="center">
		<tr class="fila">
			<th><spring:message code="totalNeto" /></th>
			<th><spring:message code="iva" /></th>
			<th><spring:message code="totalIva" /></th>
			<th></th>
		</tr>

		<c:set var="i"  value="0"/>
		<c:forEach items="${lista}" var="total">
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<!-- Calculos de los controladores -->
				<td href="#?#=${total.#}">${total.totalNeto}></td>
				<td href="#?#=${e.#}">${total.iva}></td>
				<td href="#?#=${e.#}">${total.totalIva}></td>				
			</tr>
			<c:set var="i"  value="${i+1}"/>
		</c:forEach>
	</table>
	<input type="submit" class="btn btn-lg btn-default"><spring:message code="confirmar"/><a href="listadoFacturaEntrada"><!--vuelve al listado principal de facturas listadoFactruaEntrada.jsp--></a>
	<input type="submit" class="btn btn-lg btn-default"><spring:message code="modificar"/><a href="nuevaFacturaEntrada"><!--debe volver a la factura que se esta utilizando nuevaFactruaEntrada.jsp deberá reconocer por controlador el número de factura--></a>
	<input type="submit" class="btn btn-lg btn-default"><spring:message code="descargar"/><a href="#"><!-- ¡¡¡¡comentado con el profesor!!!! se encarga de generar el pdf y se le descarga al usuario de la aplicación y ya que el se encarge de imprimirlo--></a>
</div>
<c:import url="/WEB-INF/views/end.jsp" />