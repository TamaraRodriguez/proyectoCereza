<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- creo que javi lo ha llamado cabecera, si no hay que cambiarlo -->
<c:import url="/WEB-INF/views/head.jsp" />

<div id="nuevo_cliente">
	<h1><spring:message code="nuevo_cliente" />Registrar un nuevo cliente</h1>
	<form action="########" method="POST" >
		<label for="nombre_razon_social"><spring:message code="nombre_razon_social"/>:</label> 
		<input type="text" id="nombre_razon_social" name="nombre_razon_social" placeholder="Nombre/Razón Social"/> 
		<br /> 
		<label for="apellido"><spring:message code="apellido" />:</label> 
		<input type="text" id="apellido" name="apellido" placeholder="Apellidos"/> 
		<br />
		<label for="cifNif"><spring:message code="cif_nif" />:</label> 
		<input type="text" id="cif_nif" name="cif_nif" placeholder="CIF/NIF"/> 
		<br />
		<label for="direccion"><spring:message code="direccion" />:</label> 
		<input type="text" id="direccion" name="direccion" placeholder="Dirección"/> 
		<br />
		<label for="telefono"><spring:message code="telefono" />:</label> 
		<input type="text" id="telefono" name="telefono" placeholder="Teléfono"/> 
		<br />
		<label for="email"><spring:message code="email" />:</label> 
		<input type="email" id="email" name="email" placeholder="E-mail"/> 
		<br />
		<label for="tipo"><spring:message code="tipo" />:</label>
		<input type="Radio" id="cliente" name="cliente" /><spring:message code="cliente" />
		<br/>
		<input type="Radio" id="agricultor" name="agricultor" value="agricultor"/><spring:message code="agricultor" />
		<br/>
	<!-- Desplegable
		<select id="tipo" name="tipo">
			<option value="cliente"/>
			<option value="agricultor"/>
		</select>  -->
		<!-- En este apartado haremos un controlador donde nos indique si en el tipo se ha seleccionado cliente, el input n_cliente estará habilitado y se autorellenará con el número nuevo de cliente -->
		<label for="nCliente"><spring:message code="numeroCliente" />:</label> 
		<input type="text" id="n_cliente" name="n_cliente" placeholder="Nº Cliente"/> 
		<br />
		<label for="nAgricultor"><spring:message code="numeroAgricultor" />:</label> 
		<input type="text" id="n_agricultor" name="nAgricultor" placeholder="Nº Agricultor"/> 
		<br />
		<button type="submit" class="btn btn-lg btn-default"><spring:message code="enviar" /></button>
	</form>
</div>
<c:import url="/WEB-INF/views/end.jsp" />