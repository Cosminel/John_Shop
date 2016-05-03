<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="java.util.Date" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <jsp:useBean id="current" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete Order</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-purchaseOrder.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<%
	if(session.getAttribute("comanda") == null)
	{
	    response.sendRedirect("index.jsp");
	    
	}
	%>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
    		<div class="invoice-title">
    			<h2>Factura</h2>
    		</div>
    		<hr>
    		<div class="row">
    			<div class="col-xs-6">
    				<address>
    				<strong>Client:</strong><br>
    					<c:out value="${user.firstName} ${user.name}"></c:out><br>
						<c:out value="${user.address}"></c:out>
    				</address>
    			</div>
    			<div class="col-xs-6 text-right">
    				<address>
        			<strong>Livrare catre:</strong><br>
						<c:out value="${user.firstName} ${user.name}"></c:out><br>
						<c:out value="${user.address}"></c:out>
    				</address>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-xs-6">
    				<address>
    					<strong>Email:</strong><br>
    					<c:out value="${user.email }"></c:out>
    				</address>
    			</div>
    			<div class="col-xs-6 text-right">
    				<address>
    					<strong>Data comenzii:</strong><br>
    					<fmt:formatDate type="date" value="${current}" /><br><br>
    				</address>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Detalii Comanda</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Produs</strong></td>
        							<td class="text-center"><strong>Pret</strong></td>
        							<td class="text-center"><strong>Cantitate</strong></td>
        							<td class="text-right"><strong>Subtotal</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<c:forEach items="${shoppingcart}" var="product">
    							<tr>
    								<td><c:out value="${product.key.name }"></c:out></td>
    								<td class="text-center"><c:out value="${product.key.price }"></c:out></td>
    								<td class="text-center"><c:out value="${product.value }"></c:out></td>
    								<td class="text-right"><fmt:formatNumber type="number" value="${product.key.price*product.value}"  pattern="####.######" />RON</td>
    							</tr>
    							</c:forEach>
	    							
	    						<c:set var="total" value="0"/>
								<c:forEach var="product" items="${shoppingcart}">
									<c:set var="total" value="${total+product.key.price*product.value }"/>
								</c:forEach>

    							<tr>
    								<td class="thick-line"></td>
    								<td class="thick-line"></td>
    								<td class="thick-line text-center"><strong>Total</strong></td>
    								<td class="thick-line text-right"><fmt:formatNumber type="number" value="${total}" pattern="####.######"/> RON</td>
    								
    							</tr>
    							<tr>
    							<td>
    							<td class="text-center"></td>
    							<td class="text-center"></td>
    							<td><a href="#" class="btn btn-success btn-block" onClick="location.href='PurchaseOrderServlet'"><i class="fa fa-angle-left"></i> Continua</a></td>
    							</tr>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
</div>
</body>
</html>