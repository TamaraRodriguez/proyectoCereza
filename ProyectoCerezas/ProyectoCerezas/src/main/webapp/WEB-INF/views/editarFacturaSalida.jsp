<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/WEB-INF/views/head.jsp" />
<!--==============================content=================================-->
 	<!-- FONDO GENERAL-->
	<script>
	    $(function(){
	      //  Initialize Backgound Stretcher
	      $('BODY').bgStretcher({
	        images: ['resources/images/fondoblanco.jpg'], 
			imageWidth: 1600, 
			imageHeight: 964, 
			resizeProportionally:true	
	       });	
	    });
	</script>
  	<section id="content">
      <div class="main-block">
      	<div class="main">
      		<label><h1><spring:message code="modificar_albaran_salida" /></h1></label><br>
			<div id="listado1">
				<table>
             		<caption><h5><spring:message code="datos_empresa" /></h5></caption>
					<thead>
						<tr id="texto">
			                <th><spring:message code = "nombre_empresa"/></th>
			                <th><spring:message code = "cif_nif"/></th>
			                <th><spring:message code = "direccion"/></th>
		                  	<th><spring:message code = "telefono"/></th>
			                <th><spring:message code = "email"/></th>
		             	</tr>
            		</thead>
            		<tbody>
						<tr>
							<td>Nombre</td>
							<td>cif o nif</td>
							<td>Dirección</td>
							<td>Teléfono</td>
							<td>ejemplo@ejemplo.com</td>
						</tr>
					</tbody>
            	</table><br><br>
				<table>
					<caption><h5><spring:message code="datos_cliente" /></h5></caption>
					<thead>
						<tr id="texto">
			                <th><spring:message code = "nombre_razon_social"/></th>
			                <th><spring:message code = "cif_nif"/></th>
			                <th><spring:message code = "apellido"/></th>
		                  	<th><spring:message code = "direccion"/></th>
			                <th><spring:message code = "telefono"/></th>
			                <th><spring:message code = "email"/></th> 
		             	</tr>
            		</thead>
            		<tbody>
						<tr>
							<td>${persona.nombreRazonSocial}</td>
							<td>${persona.apellidos}</td>
							<td>${persona.cifNif}</td>
							<td>${persona.direccion}</td>
							<td>${persona.telefono}</td>
							<td>${persona.email}></td>
						</tr>
					</tbody>
				</table>
			</div><br><br>
			<div id="listado1">
				<table>
					<caption><h5><spring:message code="modificar_albaran" /></h5></caption>
					<thead>
						<tr id="texto">
							<th><spring:message code='n_factura' /></th>
							<th><spring:message code='fecha' /></th>
						</tr>
					 </thead>
					<tbody>				
						<tr class="fila">
							<form action="updateFacturaSalidaFecha" method="POST">
							<td>${factura.nFactura}</td>
							<td><input type="date" id="fecha" name="fecha"
								value="${factura.stringFecha}" /></td>
							<input type="hidden" name="n_factura" value="${factura.nFactura}"/>
							<td><button type="submit" class="btn btn-default btn-sm"><spring:message code = "editar"/></button></td>	
							</form>
						</tr>
					</tbody>
				</table>				
			</div>
			<div id="listado1">
				<table>
					<caption><h5><spring:message code="listado_albaranes" /></h5></caption>
					<thead>
						<tr id="texto">
							<th><spring:message code="n_albaran" /></th>
							<th><spring:message code="fecha" /></th>
			                <th><spring:message code = "tipo"/></th>
			                <th><spring:message code = "n_cajas"/></th>
		                  	<th><spring:message code = "peso_caja"/></th>
			                <th><spring:message code = "precio_caja"/></th>
			                <th><spring:message code='precio_neto' /></th>
		             	</tr>
		            </thead>
		            <tbody>
						<c:forEach items="${factura.albaranes}" var="albaran">
						<tr>
							<td>${albaran.nAlbaran}</td>
							<td>${albaran.stringFecha}</td> 
							<td>${albaran.tipo}</td>
							<td>${albaran.nCajas}</td>
							<td>${albaran.pesoCaja}</td>
							<td>${albaran.precioCaja}</td>
							<td>${albaran.precioNeto}</td>
							<td title="<spring:message code='eliminar'/>">
								<form action="eliminarAculumador" method="POST">
									<input type="hidden" name="n_albaran" value="${linea.nAlbaran}" />
									<input type="hidden" name = "id_linea" value = "${linea.idLinea}"/>
									<button type="submit" class="btn btn-default btn-sm">
									<span  class="glyphicon glyphicon-remove" title="<spring:message code='eliminar'/>"></span>
									</button>
								</form>
							</td>
		                </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

						<!-- Borrar no borra el albaran, sÃ³lo lo quita, es decir pone a cero el valor nFactura del albaran -->
						<td><form action="FacturarAlbaranSalida" method="POST">
								<input type="hidden" name="n_factura" value="${factura.nFactura}"/>
								<input type="hidden" name="n_albaran" value="${albaran.nAlbaran}"/>
								<input type="hidden" name="borrar" value="true"/>
								<button type= "submit"  class="btn btn-default btn-sm">
								<spring:message code="borrar" /></button>
							</form></td>
					</tr>
				
					<c:forEach items="${albaran.lineas}" var="lineaAlbaran">
						<tr> <!-- Muestra las lineas que tiene cada albaran -->
							<td></td>
							<td></td>
							<td>${lineaAlbaran.tipo}</td>
							<td>${lineaAlbaran.nCajas}</td>
							<td>${lineaAlbaran.pesoCaja}</td>
							<td>${lineaAlbaran.precioCaja}</td>
							<td>${lineaAlbaran.precioTotal}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</table>
		</div>

		<!-- Muestra una lista con los albaranes de este cliente que no estÃ¡n facturados -->
		<div id="filtro">
			<h1>
				<spring:message code='pendientes' />
			</h1>
			<table>
				<!-- Cabecera tabla pendientes -->
				<tr>
					<th><spring:message code="n_albaran" /></th>
					<th><spring:message code="fecha" /></th>
					<th><spring:message code='precioNeto' /></th>
					<th></th>
					<!-- AÃ±ade este albaran a la factura en la que estamos, es decir pone el valor nFactura en el albaran -->
				</tr>
			
				<!-- Albaranes pendientes, muestra datos de los albaranes de este cliente, pendientes de facturar. Se podrian integrar mÃ¡s detalle a esta vista -->
				<c:set var="i" value="0" />
				<c:forEach items="${listaPendiente}" var="albaranPendiente">
					<tr class='fila fila_${i%2 eq 0 ? "par" : "impar"}'>
						<td>${albaranPendiente.nAlbaran}</td>
						<td>${albaranPendiente.stringFecha}</td>
						<td>${albaranPendiente.precioNeto}</td>
						<td><form action="FacturarAlbaranSalida" method="POST">
								<input type="hidden" name="n_factura" value="${factura.nFactura}"/>
								<input type="hidden" name="n_albaran" value="${albaranPendiente.nAlbaran}"/>
								<input type="hidden" name="borrar" value="false"/>
								<button type= "submit"  class="btn btn-default btn-sm">
								<spring:message code="incluir"/></button>
							</form></td>
					</tr>
				<c:set var="i" value="${i+1}" />
				</c:forEach>
			</table>
		</div>
	
	<form action="updateFacturaSalidaIVA" method="POST">
		<!-- Modificar IVA de la factura, por defecto aparece 21%-->
	
		<h1>
			<spring:message code='total_factura' />
		</h1>
		<div id="totales">
			<table>
				<tr class="fila">
					<th><spring:message code="precioNeto" /></th>
					<th><spring:message code="iva" /></th>
					<th><spring:message code="totalIva" /></th>
				</tr>

				<tr class="fila">
					<td><input type="text" id="precioNeto" name="precioNeto"
						value="${factura.precioNeto}" disabled /></td>
					<td><input type="number" id="iva" name="iva"
						value="${factura.iva}" />%</td>
					<td><input type="text" id="precioTotal" name="precioTotal"
						value="${factura.precioTotal}" disabled /></td>
					<td><input type="hidden" name="n_factura" value="${factura.nFactura}"/>
						<button type="submit" class="btn btn-default btn-sm">
						Cambiar IVA</button></td>	
				</tr>
			</table>
		</div>
	</form>
		

	
</div>	

<c:import url="/WEB-INF/views/end.jsp" />