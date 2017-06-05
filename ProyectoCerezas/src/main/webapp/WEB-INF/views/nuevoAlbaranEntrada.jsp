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
				<td><label for="nAlbaran"><spring:message code='numeroAlbaran' /> </label></td><!--  Número de Albaran:-->
				<td><input id="nAlbaran" name="nAlbaran" placeholder="Recuperar número de Albaran de la BBDD" disabled /></td>

				<td><label for="nAgricultor"><spring:message code='numeroAgricultor'/> </label></td><!--  Número de Agricultor:-->
				<td><input type="text" id="nAgricultor" name="nAgricultor" placeholder="Recuperar número de agricultor de los albaranes" disabled /></td>

				<td><label for="Fecha"><spring:message code='Fecha' /></label></td><!--  Fecha:-->
				<td><input type="date" id="Fecha" name="Fecha" /></td>
			</tr>
			<tr>
				<td><input type = "submit" class="btn btn-lg btn-default"><spring:message code = "buscar"/></td><!--añadir imagen de la lupa en el boton / css-->
			</tr>
		</table>
	</form>
	
</div>



<div id="datosAlbaranes">
	<table>
		<c:set var="i"  value="0"/>
		<!-- confirmar el nombre del items -->
		<c:forEach items="${albaran}" var="albaran"> <!-- Habría que ver como cuadro el forEach con items. Tendriamos que pasarlo desde controlador -->
			<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
				<th><spring:message code='tipo' /></th>
						
		<td href="#?#=${albaran.#}">${albaran.tipo}></td>			
					<input type="text" id="tipo"  /> 
		<select name="tipoCereza">
			<c:forEach items="${tipos}" var="tipo">
				<option value="${tipo.name()}" ${equipo.tipo eq tipo ? "selected" : ""}><spring:message code="${tipo.name()}" /></option>
			</c:forEach>
		</select> 
		
				<th><spring:message code='peso' /></th>
					<td href="#?#=${albaran.#}">${albaran.peso}></td>
				<th><spring:message code='precioKg' /></th>
					<td href="#?#=${albaran.#}">${albaran.precioKg}></td>
				<th><spring:message code='total' /></th>
					<td href="#?#=${albaran.#}">${albaran.total}</td>
					<!-- comprobar el controlodar que se encargue de calcular el total de lo que cuesta el producto por tipo, peso y precio-->
				<input type="button" class="btn btn-lg btn-default"><a href="#?#=${albaran.#}"><spring:message code="borrar" /></a><!-- ver si se puede añadir una imagen en el boton de borrar-->
			 <input type = "submit" class="btn btn-lg btn-default"><a href="#?#=${albaran.#}"><spring:message code="confirmar" /> <!-- añadir imagen de confirmar -->
			 <input type = "button" class="btn btn-lg btn-default"><a href="#?#=${albaran.#} "><spring:message code="editar" /> <!--  añadir imagenes de editar -->
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
	<input type="submit" class="btn btn-lg btn-default"><spring:message code="enviar"/> <!-- añadir imagen de enviar -->
</div>


<c:import url="/WEB-INF/views/end.jsp" />