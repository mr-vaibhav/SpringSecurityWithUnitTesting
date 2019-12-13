<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="signupUser" method="post">
			<table style="with: 50%">
				<tr>
					<td>UserName</td>
					<td><input type="text" name="username" /></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Confirm Password</td>
					<td><input type="password" name="passwordConfirm" /></td>
				</tr>
				<tr>
					<td>Select Role</td>
					<td>
					<select name="role" >
						<option value="ROLE_ADMIN" > Admin </option>
						<option value="ROLE_USER"> User </option>
					</select>
					</td>
				</tr>
				<tr>
				<td>User Email</td>
					<td><input type="email" name="userMail" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" /></form>
</body>
</body>
</html>