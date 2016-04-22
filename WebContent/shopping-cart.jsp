<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
	<link href="css/font-awesome.min.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-homepage.css" rel="stylesheet">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Produs</th>
							<th style="width:10%">Pret</th>
							<th style="width:8%">Cantitate</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${shoppingcart}" var="product">
							<tr>
								<td data-th="Product">
									<div class="row">
										<div class="col-sm-2 hidden-xs"><img src="<c:out value="${product.imagePath}"/>" alt="..." class="img-responsive"/></div>
										<div class="col-sm-10">
											<h4 class="nomargin"><c:out value="${product.name }" /></h4>
											<p><c:out value="${product.description}"/></p>
										</div>
									</div>
								</td>
								<td data-th="Price"><c:out value="${product.price }"/></td>
								<td data-th="Quantity">
									<input type="number" class="form-control text-center" value="1">
								</td>
								<td data-th="Subtotal" class="text-center"><c:out value="${product.price }"/></td>
								<td class="actions" data-th="">
									<button class="btn btn-info btn-sm"'"><i class="fa fa-refresh"></i></button>
									<button class="btn btn-danger btn-sm"  onClick="location.href='AddToCartServlet?action=remove&name=<c:out value="${product.name }"/>'"><i class="fa fa-trash-o"></i>Sterge</button>								
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td><a href="index.jsp" class="btn btn-warning"><i class="fa fa-angle-left"></i> Inapoi</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center">
							<strong>
							<c:set var="total" value="0"/>
							<c:forEach var="product" items="${products}">
								<c:set var="total" value="${total+product.price }"/>
							</c:forEach>
							<fmt:formatNumber type="number"   value="${total}" pattern="####.######"/>
							<!--<c:out value="${total }"/>-->
							RON
							</strong>
							</td>
							<td><a href="#" class="btn btn-success btn-block">Continua <i class="fa fa-angle-right"></i></a></td>
						</tr>
					</tfoot>
				</table>
</div>
</body>
</html>