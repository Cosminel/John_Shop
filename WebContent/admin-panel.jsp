<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<c:if test="${user.role != 1}">
	<%
	{
	    response.sendRedirect("index.jsp");
	}
	%>
	</c:if>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>John's shop - Admin panel</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-homepage.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">John's shop</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">Despre John</a>
                    </li>
                    <li>
                        <a href="#">Ferme</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>                 
                </ul>
               
                <ul class="nav navbar-nav navbar-right">
	               	<li class="dropdown">
           				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><c:out value="${user.user}"></c:out><span class="caret"></span></a>
	               		<ul class="dropdown-menu dropdown-cart" role="menu">
	               			<li><a class="text-center" href="userSettings.jsp">Settings</a></li>
	               			<c:if test="${user.role==1 }">
		               			<li>
		               				<a class="text-center" href="admin-panel.jsp">Admin Panel</a>
		               			 </li>
	               			 </c:if>
	               			<li>
	                			<form action="<%=response.encodeURL("LogoutServlet") %>"  method="post">
                    				<button type="submit" class="btn btn-default center-block">LogOut</button>
                				</form>
	               			 </li>

	               		</ul>	
	                </li>
	                
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> ${fn:length(shoppingcart) }- Items<span class="caret"></span></a>
			          <ul class="dropdown-menu dropdown-cart" role="menu">
			           <div class="col-lg-12 ">
			              <c:forEach items="${shoppingcart}" var="product">
			              <li>
			                  <span class="item">
			                    <span class="item-left">
			                        <img src="<c:out value="${product.key.imagePath }"/>" class="img-responsive"  width="50" height="50" alt="" />
			                        <span class="item-info">
			                            <span><c:out value="${product.key.name}" /></span>
			                            <span><c:out value="${product.key.price}"/>RON</span>
			                        </span>
			                    </span>
			                    <span class="item-right">
			                        <button class="btn btn-xs btn-danger pull-right">x</button>
			                    </span>
			                </span>
			              </li>
			              </c:forEach>
			              <li class="divider"></li>
			              <li><a class="text-center" href="shopping-cart.jsp">View Cart</a></li>
			            </div>
			          </ul>
			        </li>
      			</ul>
                
            </div>
            <!-- /.navbar-collapse -->

        </div>
        <!-- /.container -->
        
    </nav>

	<div class="container">
	
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				
				<form action="ProductsCRUDServlet?action=addProduct" method="post">
				
					<div class="form-group">
						<label for="name">Product name</label>
						<input type="text" class="form-control" name="name" />
					</div>
					
					<div class="form-group">
						<label for="description">Description</label>
						<input type="text" class="form-control" name="description" />
					</div>
					
					<div class="form-group">
						<label for="category">Category</label>
						<select class="form-control" name="category">
							<c:forEach items="${categories}" var="category">
								<option value="${category}" selected><c:out
										value="${category}" /></option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="subcategory">Subcategory</label>
						<select class="form-control" name="subcategory">
							<c:forEach items="${subcategories}" var="subcategory">
								<option value="${subcategory}" selected><c:out
										value="${subcategory}" /></option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="price">Price</label>
						<input type="number" min="0" class="form-control" name="price" />
					</div>
									
					<input class="btn btn-primary" type="submit" value="Submit">
				</form>

			</div>
		</div>
		
		<br/><br/>
		
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
			
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover">
		           		<thead>
							<tr>
								<th>Produs</th>
								<th>Categorie</th>								
								<th>Subcategorie</th>
							</tr>
						</thead>
							
						<tbody>
							<c:forEach items="${products}" var="product">
								<tr>
									<td><c:out value="${product.name}" /></td>
									<td><c:out value="${product.category}" /></td>									
									<td><c:out value="${product.subcategory}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
	</div>

	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	
</body>
</html>