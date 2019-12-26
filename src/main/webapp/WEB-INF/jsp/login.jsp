
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	</head>		

	<body>
		<form:form class="bg-light" action="doLogin" method="post">
		<div class="h2 text-center mt-1">User Login</div>
		<div class="form-row mt-3">
		
			<div class="form-group col-sm-4 col-sm-offset-2">
				<label>User Name:</label> 
				<input type="text" name="username" placeholder="UserName" required>
			</div>
			
			<div class="form-group col-sm-4">
				<label>Password:</label> 
				<input type="Password" name="password" placeholder="password" required>
			</div>
			
		</div>
		<div class="col-sm-12">
			<div  class="col-sm-1 col-sm-offset-5">
              <button class="btn  btn-outline-success waves-effect"  type="submit" >SUBMIT</button>
            </div>
            
            <div class="col-sm-2">
				<a href="signup">Sign Up</a>
			</div>
              
         </div>
        	
	</form:form>
	</body>
</html>
	
	
	
