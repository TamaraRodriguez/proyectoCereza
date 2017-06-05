<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- creo que javi lo ha llamado cabecera, si no hay que cambiarlo -->
<c:import url="/WEB-INF/views/head.jsp" />
<div>
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
		<!-- recoger los datos del agricultor en la tabla personas -->
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
<div id="filtro">
	<form action="#" method="POST">
		<!-- Nos tendría que redireccionar a la vista en la que se muestran todos los datos, que tiene todos los datos y te permite modificar, imprimir y volver. NuevaFacturaCreando.jsp -->
		<table>
			<tr>
				<td><label for="nFactura"><spring:message code='numeroFactura' /> </label></td><!--  Número de Factura:-->
				<td><input id="nFactura" name="nFactura" placeholder="Recuperar número de Factura de la BBDD" disabled /></td>

				<td><label for="nAgricultor"><spring:message code='numeroAgricultor'/> </label></td><!--  Número de Agricultor:-->
				<td><input type="text" id="nAgricultor" name="nAgricultor" placeholder="Recuperar número de agricultor de los albaranes" disabled /></td>

				<td><label for="Fecha"><spring:message code='Fecha' /></label></td><!--  Fecha:-->
				<td><input type="date" id="Fecha" name="Fecha" /></td>
			</tr>
			<tr>
				<td><input type = "submit"><spring:message code = "buscar"/></input></td><!--añadir imagen de la lupa en el boton / css-->
			</tr>
		</table>
	</form>
	
</div>

<div id="listar_albaranes">
	<table>
		<tr>
			<td><label for = "buscar"><spring:message code = "buscar"/><!-- Buscar por cualquier campo: --></label></td>
			<td><input type = "text" id ="buscar" name="buscar" placeholder="Buscar Nº Albarán" />
			<tr>
				<td><input type = "submit"><spring:message code = "buscar"/></input></td><!--añadir imagen de la lupa en el boton / css-->
			</tr>
		</tr>
	</table>
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaranesGuardados}" var="albaran">
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<!-- <th><spring:message code='albaranes' /> Albaranes incluidos:</th> -->
					<!-- <td><a href="#?#=${albaran.#}">${albaran.tipo}</td> -->
				<th><spring:message code='nAlbaran' /></th>
					<td href="#?#=${albaran.#}">${albaran.tipo}</td>
				<th><spring:message code='fecha' /></th>
					<td href="#?#=${albaran.#}">${albaran.stringFecha}></td>
					<!-- <script>$("#fecha").datepicker({dateFormat: "${formatoFecha}",constrainInput:true});</script> -->
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.totalAlbaran}></td>
					<!-- comprobar el controlodar que se encargue de calcular el total del albaran -->
				<input type="submit" class="btn btn-lg btn-default"><a href="#?#=${albaran.#}" style="color: red;"><spring:message code="añadir"/></a></input>
			</tr>
			<c:set var="i"  value="${i+1}"/>
		</c:forEach>
	</table>
</div>

<div id="listado_albaranes">
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaranesGuardados}" var="albaran"> <!-- Habría que ver como cuadro el forEach con items. Tendriamos que pasarlo desde controlador -->
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<th><spring:message code='tipo' /></th>
					<td href="#?#=${albaran.#}">${albaran.tipo}></td>
				<th><spring:message code='peso' /></th>
					<td href="#?#=${albaran.#}">${albaran.peso}></td>
				<th><spring:message code='precioKg' /></th>
					<td href="#?#=${albaran.#}">${albaran.precioKg}></td>
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.total}</td>
					<!-- comprobar el controlodar que se encargue de calcular el total de lo que cuesta el producto por tipo, peso y precio-->
				<input type="submit" class="btn btn-lg btn-default"><a href="#?#=${albaran.#}" style="color: red;"><spring:message code="borrar" /></a></input><!-- ver si se puede añadir una imagen en el boton de borrar-->
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
	<button type="submit" class="btn btn-lg btn-default"><spring:message code="guardar"/></button>
</div>


<c:import url="/WEB-INF/views/end.jsp" />