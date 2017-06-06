<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/head.jsp" />

<!-- Navegador -->
	<div id="header">
		<ul class="navegador">
			<li><a><spring:message code = "registro"/></a>
				<ul>
					<li><a href=""><spring:message code = "listarClientes"/></a>
						<ul>
							<li><a href=""><spring:message code = "altaCliente"/></a></li>
						</ul>
					</li>
					<li><a href=""><spring:message code = "listarAgricultores"/></a>
						<ul>
							<li><a href=""><spring:message code = "altaAgricultor"/></a></li>
						</ul>
					</li>
				</ul>
			</li>

			<li><a><spring:message code = "recolecta"/></a>
				<ul>
					<li><a href="ListarFacturasEntrada.jsp"><spring:message code = "listarFacturasEntrada"/></a>
						<ul>
							<li><a href=""><spring:message code = "nuevaFacturaEntrada"/></a></li>
						</ul>
					</li>
					<li><a href="ListarAlbaranEntrada.jsp"><spring:message code = "listadoAlbaranesEntrada"/></a>
						<ul>
							<li><a href=""><spring:message code = "nuevoAlbaranEntrada"/></a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a><spring:message code = "ventas"/></a>
				<ul>
					<li><a href="ListarFacturasSalida.jsp"><spring:message code = "listadoFacturasSalidas"/></a>
						<ul>
							<li><a href=""><spring:message code = "nuevaFacturaSalida"/></a></li>
						</ul>
					</li>
					<li><a href="ListarAlbaranSalida.jsp"><spring:message code = "listadoAlbaranesSalida"/></a>
						<ul>
							<li><a href=""><spring:message code = "nuevoAlbaranSalida"/></a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a><spring:message code = "administracion"/></a>
				<ul>
					<li><a href=""><spring:message code = "listadoVariedades"/></a>
						<ul>
							<li><a href=""><spring:message code = "nuevaVariedad"/></a></li>
							<li><a href=""><spring:message code = "modificarVariedad"/></a></li>
						</ul>
					</li>
				</ul>			
			</li>
		</ul>
	</div>
