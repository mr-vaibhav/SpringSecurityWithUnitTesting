
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>User Login</title>
	</head>		

	<body>
		<h1><strong>User Login</strong></h1>
		<form:form action="doLogin" method="post">
			<label>User Name:</label> <input type="text" name="username" />
			<label>Password:</label> <input type="Password" name="password" />
			<input type="submit"/>
			<a href="signup" value="signup">Sign Up</a>
		</form:form>
	</body>
</html>
	
	
	
