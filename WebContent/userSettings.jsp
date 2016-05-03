<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Settings</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-homepage.css" rel="stylesheet">
</head>
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
    
    <div class="container">
    <h1>Edit Profile</h1>
  	<hr>
	<div class="row">
      <!-- edit form column -->
      <div class="col-md-6 personal-info">
        <h3>Personal info</h3>
        
        <form class="form-horizontal" role="form" action="UserSettingsServlet?action=saveProfile" method="post">
          <div class="form-group">
            <label class="col-lg-3 control-label">First name:</label>
            <div class="col-lg-8">
              <input class="form-control" name="firstname" type="text" value="<c:out value="${user.firstName}"/>">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Last name:</label>
            <div class="col-lg-8">
              <input class="form-control" name="lastname" type="text" value="<c:out value="${user.name}"/>">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Address:</label>
            <div class="col-lg-8">
              <input class="form-control" name="address" type="text" value="<c:out value="${user.address }"/>">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
              <input class="form-control" name="email" type="email" value="<c:out value="${user.email }"/>">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Username:</label>
            <div class="col-md-8">
              <input class="form-control" name="username" type="text" value="<c:out value="${user.user }"/>" readonly>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <input type="submit"  class="btn btn-primary" value="Save Changes">
              <span></span>
              <input type="reset" onClick="location.href='home.jsp'" class="btn btn-default" value="Cancel">
            </div>
          </div>
        </form>
      </div>
                 <div class="col-md-6">
         <h3>Reset your password</h3>
      		 <form class="form-horizontal" role="form" action="UserSettingsServlet?action=resetPassword" method="post">
      		  <div class="form-group">
            <label class="col-md-3 control-label">Old password:</label>
            <div class="col-md-8">
              <input class="form-control" name ="oldpassword" type="password" value="" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">New password:</label>
            <div class="col-md-8">
              <input class="form-control" name="newpassword" type="password" value="" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Confirm new password:</label>
            <div class="col-md-8">
              <input class="form-control" name="confirmnewpassword" type="password" value="" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <input type="submit" class="btn btn-primary" value="Reset password">
            </div>
          </div>
          </form>
        </div>
  </div>
</div>
    
    
</body>
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>