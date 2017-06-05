<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/head.jsp" />

<!-- Navegador -->
	<div id="header">
		<ul class="navegador">
			<li><a>Personas</a>
				<ul>
					<li><a href="">Clientes</a>
						<ul>
							<li><a href="">Cliente Nuevo</a></li>
						</ul>
					</li>
					<li><a href="">Agricultores</a>
						<ul>
							<li><a href="">Agricultor Nuevo</a></li>
						</ul>
					</li>
				</ul>
			</li>

			<li><a>Entradas</a>
				<ul>
					<li><a href="ListarFacturasEntrada.jsp">Facturas</a>
						<ul>
							<li><a href="">Nueva Factura</a></li>
						</ul>
					</li>
					<li><a href="ListarAlbaranEntrada.jsp">Albaranes</a>
						<ul>
							<li><a href="">Nuevo Albaran</a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a>Salidas</a>
				<ul>
					<li><a href="ListarFacturasSalida.jsp">Facturas</a>
						<ul>
							<li><a href="">Nueva Factura</a></li>
						</ul>
					</li>
					<li><a href="ListarAlbaranSalida.jsp">Albaranes</a>
						<ul>
							<li><a href="">Nuevo Albaran</a></li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
	</div>
