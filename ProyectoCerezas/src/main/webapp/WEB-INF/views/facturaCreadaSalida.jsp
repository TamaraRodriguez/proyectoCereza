<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/WEB-INF/views/head.jsp" />
<div id="datos">
	<table>
		<!-- recoger los datos del cliente en la tabla personas para que aparezcan sus datos -->
		<c:forEach items="${personas}" var="cliente">
			<tr>
				<!-- comprobar que el controlodar que se encargue de recoger los datos del cliente relacionado con el nº cliente -->
				<th><spring:message code='nombre_razon_social'/></th>
					<td href="#?#=${cliente.#}">${cliente.nombreRazonSocial}</td>
				<th><spring:message code='apellido'/></th>
					<td href="#?#=${cliente.#}">${cliente.apellido}</td>
				<th><spring:message code='cif_nif'/></th>
					<td href="#?#=${cliente.#}">${cliente.cifNif}></td>
				<th><spring:message code='direccion'/></th>
					<td href="#?#=${cliente.#}">${cliente.direccion}></td>
				<th><spring:message code='telefono'/></th>
					<td href="#?#=${cliente.#}">${cliente.telefono}></td>
				<th><spring:message code='email'/></th>
					<td href="#?#=${cliente.#}">${cliente.email}></td>	
			</tr>
		</c:forEach>
	</table>
	<table>
		<th>Nombre de la empresa</th>
		<tr>
			<th>CIF:</th>
			<th>Dirección:</th>
			<th>Teléfonos:</th>
			<th>E-mail:</th>
		</tr>
	</table>
</div>
<div id="datos_factura">
	<table>
		<!-- Recuperar todos los datos de la nuevaFacturaEntrada.jsp-->
		<c:forEach items="${#}" var="cliente">
		<tr>
			<td><spring:message code='numeroFactura'/></td>
				<td href="#?#=${cliente.#}">${cliente.nFactura}</td>
			<td><spring:message code='numerocliente'/></td>
				<td href="#?#=${cliente.#}">${cliente.nCliente}</td>
			<td><label for="Fecha"><spring:message code='Fecha' /></label></td>
				<td><a href="#?#=${cliente.#}">${e.stringFecha}</a></td>	
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
				<th><spring:message code='numeroCajas' /></th>
					<td href="#?#=${albaran.#}">${albaran.numeroCajas}></td>
				<th></th>
				<th><spring:message code='pesoCaja' /></th>
					<td href="#?#=${albaran.#}">${albaran.pesoCaja}></td>
				<th></th>
				<th><spring:message code='precioCaja' /></th>
					<td href="#?#=${albaran.#}">${albaran.precioCaja}></td>
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