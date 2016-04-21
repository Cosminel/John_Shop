<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="eu.ubis.eshop.bfcl.ProductDTO"%>

<!-- Ca sa lucram cu HTML5 si functionalitatile lui, trebuie sa includem cel mai recent doctype, ca mai jos -->
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>John's shop</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-homepage.css" rel="stylesheet">
	<link href="css/animate.css" rel="stylesheet">
</head>

<body>
	<%
	if(session.getAttribute("user") != null)
	{
	    response.sendRedirect("home.jsp");
	    
	}
	
	%>
	
	<jsp:useBean id="index" class="eu.ubis.john.servlets.Index"></jsp:useBean>
	<jsp:useBean id="cart" class="eu.ubis.john.servlets.AddToCart"></jsp:useBean>
	
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
			          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> ${fn:length(products) }- Items<span class="caret"></span></a> 
		          		<ul id="cos" class="dropdown-menu dropdown-cart col-lg-12 " role="menu">	              		
		              		<c:forEach items="${products}" var="product">
		             			 <li class="col-lg-12">
		                 			 <span class="item">
										<span class="item-right">
		                        			<button class="btn btn-xs btn-danger pull-right" id="button-cos">x</button>
		                   				 </span>
		                    			<span class="item-left">
		                       				 <img src="<c:out value="${product.imagePath }"/>" class="img-rounded" height="50" max-width="50"  alt="" />
			                        		<span class="item-info">
			                            		<span><c:out value="${product.name}" /></span>
			                          	  		<span><c:out value="${product.price}"/>RON</span>
			                       			</span>
		                   				</span>
		               				 </span>
		             			 </li>
		             			 <li class="divider"></li>
		              		</c:forEach>
		              		
		              		<li><a class="text-center"  href="shopping-cart.jsp"><span class="glyphicon glyphicon-align-justify"></span>View Cart</a></li>
		        		</ul>
		        		
			        </li>
                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Register <span class="caret"></span></a>
                        <ul class="dropdown-menu dropdown-lr animated flipInX" role="menu">
                            <div class="col-lg-12">
                                <div class="text-center"><h3><b>Register</b></h3></div>
								<form id="ajax-register-form" action="<%=response.encodeURL("RegisterServlet") %>" method="post" role="form" autocomplete="off">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" required>
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="" required>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required">
									</div>
									<div class="form-group">
										<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password" required>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-info" value="Register Now">
											</div>
										</div>
									</div>
                                    <input type="hidden" class="hide" name="token" id="token" value="7c6f19960d63f53fcd05c3e0cbc434c0">
								</form>
                            </div>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Log In <span class="caret"></span></a>
                        <ul class="dropdown-menu dropdown-lr animated slideInRight" role="menu">
                            <div class="col-lg-12">
                                <div class="text-center"><h3><b>Log In</b></h3></div>
                                <form id="ajax-login-form" action=<%=response.encodeURL("LoginServlet?action=login") %> method="post" role="form" autocomplete="off">
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" autocomplete="off" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" autocomplete="off" required>
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-success" value="Log In">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="text-center">
                                                    <a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" class="hide" name="token" id="token" value="a465a2791ae0bae853cf4bf485dbe1b6">
                                </form>
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
    	
    		
    		<div class="col-md-3">
                <p class="lead">Noi avem</p>
                
         		<!-- Aducem subcategoriile in functie de ID-ul categoriei 
         			 pretty incurcat, pentru ca se alterneaza cod Java cu tag-uri HTML-->
    			 <%
    			 	for (String category : index.getAllCategories()) {
    			 %>
    			 	<div class="list-group">
    			 	<a href="#" class="list-group-item category"><strong> <%=category%> </strong></a>
    			 	
    			 	<%
    			 		for (String subcategory : index.getSubcategoriesByCategoryName(category)) {
    			 	%>
    			 		<a href="#" class="list-group-item subcategory"> <%=subcategory%> </a>
    			 	<%
    			 		}
    			 	%>
    			 	</div>
    			 <%
    			 	}
    			 %>
    		</div>
            
            <div class="col-md-9">
            	<!-- Un exemplu a ce va ofera Bootstrap - carusel cu imagini (in cazul meu, am luat imaginile de pe net :-)
            		 usor de configurat. Exemplu pe site-ul lor. -->
            	<div class="row carousel-holder">
                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="https://andyfood.com/wp-content/uploads/2015/07/Grilled-Fruit-Sangria-800x300.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://www.ebuyfsoft.com/photo/wp-content/uploads/2015/07/Red-Pomegranate-Fruit-Wallpaper-HD-800x300.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://www.paleoeffect.com/wp-content/uploads/2014/07/paleo_jam_strawberry_recipe-800x300@2x.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
                 
               	<div class="row">
                
                	<%
						for (ProductDTO prod : index.getAllProducts()) {
					%>
					<!-- clase responsive din Bootstrap -->
                	<div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
	                            <img name="img" src="<%=prod.getImagePath() %>"  style="width: 200px; height: 200px" class="img-circle" alt=""> <!-- prod.getImage(), daca aveti -->
	                            <div class="caption">
	                                <h4 name="price" class="pull-right"><%=prod.getPrice()%> RON</h4> <!-- prod.getPrice(), daca aveti implementat -->
	                                <h4 name="name"><%= prod.getName() %></h4>                   
	                                <p><strong>Categorie:</strong> <%= prod.getCategory() %></p>
	                                <p><strong>Subcategorie:</strong> <%= prod.getSubcategory() %></p>
	                                <p name="description"><%=prod.getDescription() %></p> <!-- prod.getDescription(), daca aveti implementat -->
	                            </div>
	                         	<input  type="submit" onClick="location.href='AddToCartServlet?action=add&name=<%=prod.getName() %>&price=<%=prod.getPrice() %>&img=<%=prod.getImagePath() %>&description=<%=prod.getDescription()%>&img=<%=prod.getImagePath() %>'"  class="btn btn-success btn-block" value='Adauga'>    
                        </div>
                    </div>
                    <%	
						}
                    %>
                    
                </div>        
            	
            </div>
    	</div>
    </div>
		
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1018181388255974',
      xfbml      : true,
      version    : 'v2.6'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<div
  class="fb-like"
  data-share="true"
  data-width="450"
  data-show-faces="true">
</div>
</body>
</html>