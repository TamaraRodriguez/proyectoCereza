<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- creo que javi lo ha llamado cabecera, si no hay que cambiarlo -->
<c:import url="/WEB-INF/views/head.jsp" />
<div id="datos">
	<table>
		<!-- recoger los datos del cliente en la tabla personas para que aparezcan sus datos -->
		<c:forEach items="${personas}" var="c">
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
	<form action="#" method="POST">
		<!-- Nos tendría que redireccionar a la vista en la que se muestran todos los datos, que tiene todos los datos y te permite modificar, imprimir y volver. NuevaFacturaCreando.jsp -->
		<table>
			<tr>
				<td><label for="nFactura"><spring:message code='numeroFactura' /> </label></td><!--  Recuperar número de Factura de la BBDD-->
				<td><input id="nFactura" name="nFactura" placeholder="Nº Factura" disabled /></td>

				<td><label for="nCliente"><spring:message code='numerocliente'/> </label></td><!--  Recuperar número de cliente de los albaranes-->
				<td><input type="text" id="nCliente" name="nCliente" placeholder="Nº Cliente" disabled /></td>

				<td><label for="Fecha"><spring:message code='Fecha' /></label></td><!--  Fecha:-->
				<td><input type="date" id="Fecha" name="Fecha" /></td>
			</tr>
		</table>
	</form>
	
</div>

<div id="buscar_albaranes">
	<table>
		<tr>
			<td><label for = "buscar"><spring:message code = "buscar"/><!-- Buscar por cualquier campo: --></label></td>
			<td><input type = "text" id ="buscar" name="buscar" placeholder="Buscar Nº Albarán" />
			<tr>
				<td><input type="button"><spring:message code = "buscar"/>buscar</input></td><!--añadir imagen de la lupa en el boton / css-->
			</tr>
		</tr>
	</table>
</div>

<div id="listado_albaranes_busqueda">
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaranesGuardados}" var="albaran">
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<!-- <th><spring:message code='albaranes' /> Albaranes incluidos:</th> -->
					<!-- <td><a href="#?#=${albaran.#}">${albaran.tipo}</td> -->
				<th><spring:message code='nAlbaran' /></th>
					<td href="#?#=${albaran.#}">${albaran.nAlbaran}</td>
				<th><spring:message code='fecha' /></th>
					<td href="#?#=${albaran.#}">${albaran.stringFecha}></td>
					<!-- <script>$("#fecha").datepicker({dateFormat: "${formatoFecha}",constrainInput:true});</script> -->
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.totalAlbaran}></td>
					<!-- comprobar el controlodar que se encargue de calcular el total del albaran -->
				<input type="button" class="btn btn-lg btn-default"><a href="#?#=${albaran.#}" style="color: red;"><spring:message code="añadir"/>añadir</a></input>
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
				<th><spring:message code='numero_cajas' /></th>
					<td href="#?#=${albaran.#}">${albaran.numeroCajas}></td>
				<th></th>
				<th><spring:message code='peso_caja' /></th>
					<td href="#?#=${albaran.#}">${albaran.pesoCaja}></td>
				<th></th>
				<th><spring:message code='precio_caja' /></th>
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
<br>
<br>
<div id="listado_albaranes">
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaranesGuardados}" var="albaran">
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<!-- <th><spring:message code='albaranes' /> Albaranes incluidos:</th> -->
					<!-- <td><a href="#?#=${albaran.#}">${albaran.tipo}</td> -->
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
				<th><spring:message code='numero_cajas' /></th>
					<td href="#?#=${albaran.#}">${albaran.numeroCajas}></td>
				<th></th>
				<th><spring:message code='peso_caja' /></th>
					<td href="#?#=${albaran.#}">${albaran.pesoCaja}></td>
				<th></th>
				<th><spring:message code='precio_caja' /></th>
					<td href="#?#=${albaran.#}">${albaran.precioCaja}></td>
				<th></th>
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.total}</td>
					<!-- comprobar el controlodar que se encargue de calcular el total de lo que cuesta el producto por tipo, peso y precio-->
				<input type="button" class="btn btn-lg btn-default"><a href="#?#=${albaran.#}" style="color: red;"><spring:message code="borrar" />borrar</a></input><!-- ver si se puede añadir una imagen en el boton de borrar-->
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
	<button type="submit" class="btn btn-lg btn-default"><spring:message code="guardar"/><a href="facturaCreada"><!--llama a facturaCreada.jsp--></a></button>
</div>
<c:import url="/WEB-INF/views/end.jsp" />