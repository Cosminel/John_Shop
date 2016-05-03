<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ page import="eu.ubis.eshop.bfcl.ProductDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>John's shop</title>
	<script  src="js/product_filter.js"></script>	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-homepage.css" rel="stylesheet">
	<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
</head>
<jsp:useBean id="index" class="eu.ubis.john.servlets.Index"></jsp:useBean>
<body>
	<%
	if(session.getAttribute("user") == null)
	{
	    response.sendRedirect("index.jsp");
	    
	}
	%>
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
		               				<a class="text-center" href="AdminServlet?action=getProducts">Admin Panel</a>
		               			 </li>
	               			 </c:if>
	               			 <li><a class="text-center" href="userOrders.jsp">My Orders</a>
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
<!-- ------------------------------------------------------------------------ --> 
	<div class="container">
    	<div class="row">
    		<div class="col-md-3">
                <p class="lead">Noi avem</p>
                
         		<c:forEach items="${index.getAllCategories()}" var="category">
    			 		<div class="list-group">
    			 		<a href="#" class="list-group-item category"><strong> <c:out value="${category}"/> </strong></a>
    			 			<c:forEach items="${index.getSubcategoriesByCategoryName(category)}" var="subcategory">
    			 					<a href="#" class="list-group-item category"> <c:out value="${subcategory}"/></a>		 				
    			 			</c:forEach>
    			 		</div>		
					</c:forEach>	
				<!--  Meniu pentru filtrare produse -->	
					<div id="filter-panel" class="collapse filter-panel">
			            <div class="panel panel-default">
			                <div class="panel-body">
			                    <form class="form-inline" role="form">
			                        <div class="form-group">
			                            <label class="filter-col" style="margin-right:0;" for="pref-perpage">Rows per page:</label>
			                            <select id="pref-perpage" class="form-control">
			                                <option selected="selected" value="10">10</option>
			                                <option value="15">30</option>
			                                <option value="50">50</option>
			                            </select>                                
			                        </div> <!-- form group [rows] -->
			                       
			                        <div class="form-group">
			                            <label class="filter-col" style="margin-right:0;" for="pref-orderby">Sort:</label>
			                            <select id="pref-orderby" class="form-control">
			                                <option>Please Select </option>
			                                <option>Price (Low to High)</option>
			                                <option>Price (High to Low)</option>
			                            </select>                                
			                        </div> <!-- form group [order by] --> 
			                         <div class="form-group" id="filters">
			                        	<label class="filter-col" style="margin-right:0;">Categories</label>   
			                            <div class="filter-attributes" style="margin-left:10px; margin-right:10px;">
											<c:forEach items="${index.getAllCategories()}" var="category">
											<p> <label><input type="checkbox" name="category" id='<c:out value="${category}"/>' value ='<c:out value="${category}"/>'> <c:out value="${category}"/></label></p>
											</c:forEach>
			                            </div>   
			                              <label class="filter-col" style="margin-right:0;">Subcategories</label>   
			                            <div class="filter-attributes" style="margin-left:10px; margin-right:10px;">
											<c:forEach items="${index.getAllSubcategories()}" var="subcategory">
											<p> <label><input type="checkbox" name="subcategory" id='<c:out value="${subcategory}"/>' value ='<c:out value="${subcategory}"/>'> <c:out value="${subcategory}"/></label></p>
											</c:forEach>
			                            </div>           
			                        </div> 
			                    </form>
			                </div>
			            </div>
			        </div>    
			        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#filter-panel">
			            <span class="glyphicon glyphicon-cog"></span> Advanced Search
			        </button>
    		</div>
    		 
    		 <div class="col-md-9">
    				 <!--  Grid de produse -->
               	<div class="row">
                	<%
						for (ProductDTO prod : index.getAllProducts()) {
					%>
					<!-- clase responsive din Bootstrap -->
					<div class="products" data-category='<%=prod.getCategory()%>' data-subcategory='<%=prod.getSubcategory()%>'>
                	<div class="col-sm-4 col-lg-4 col-md-4" >
                	<ul>
                        <li class="thumbnail" >
	                            <img name="img" src="<%=prod.getImagePath() %>"  style="width: 200px; height: 200px" class="img-circle" alt=""> <!-- prod.getImage(), daca aveti -->
	                            <div class="caption">
	                            	<ul >
	                                <h4 class="pull-right" ><%=prod.getPrice()%> RON</h4> <!-- prod.getPrice(), daca aveti implementat -->
	                                <li  ><%= prod.getName() %></li>                   
	                                <li ><strong>Categorie:</strong> <%= prod.getCategory() %></li>
	                                <li ><strong>Subcategorie:</strong> <%= prod.getSubcategory() %></li>                                
	                                <li ><%=prod.getDescription() %> </li>
	                                <li ><strong>Cantitate:</strong><%=prod.getQuantity() %></li>
	                                </ul>
	                               
	                            </div>
	                         	<input  type="submit"  onClick="location.href='ShoppingCart?action=add&id=<%=prod.getProductId()%>'"  class="btn btn-success btn-block" value='Adauga'>    
                    	</li>
                    </ul>
                    </div>
                       </div> 
                    <%	
						}
                    %> 
                 </div>  
               </div>
        </div>
     </div>
           
	
</body>
	
</html>