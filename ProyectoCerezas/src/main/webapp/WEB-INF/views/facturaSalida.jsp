<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="/WEB-INF/views/head.jsp" />
<h1>DATOS DE FACTURA</h1>

	<table >

<tr>
					<td>${nº factura}</td>
					<td href="XXX?n_factura=${nº factura}">${nº factura}</td>
					
				</tr>


</table>

<h2>ALBARANES</h2>

	<table >
	<tr><th>nº albaran</th>
		<th>fecha</th>
		<th> total</th></tr>
	
<tr>
					
					<td href="XXX?n_albaran=${nº albaran}">${nº albaran}</td>
					
					<td href="XXX?fecha=${fecha}">${fecha}</td>
					
					<td href="XXX?total=${total}">${total}</td>
					
				</tr>

<c:forEach items="${lista_albaranes}" var="c">
<tr><th>tipo</th>
		<th>peso</th>
		<th> precio</th></tr>
<tr>
                   
					<td href="XXX?tipo=${tipo}">${tipo}</td>
					
					<td href="XXX?peso=${peso}">${peso}</td>
					
					<td href="XXX?precio=${precio}">${precio}</td> <td> <input type = "button" class="btn btn-lg btn-default"><spring:message code = "eleminar"/></td>
					
</tr>
<tr>
<td></td>

</tr>
</c:forEach>


</table>
 <input type = "button" class="btn btn-lg btn-default"><spring:message code = "añadir"/>
		

<c:import url="/WEB-INF/views/end.jsp" />