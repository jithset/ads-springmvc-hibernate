<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
<table>
	<form:form action="login" modelAttribute="user" method="POST">
		<tr><td>Username</td></tr>
		<tr><td><form:input path="username" value="jithin"/></td></tr>
		<tr><td>Password</td></tr>
		<tr><td><form:input path="password" value="1234"/></td></tr>
		<tr><td><button type ="submit">Login</button></td></tr>
		<c:if test="${not empty error}">
		   <p style="color:red;">${error}</p>
		</c:if>
	</form:form>
	<tr>
	<td>
	<a href="register">Register</a>
	</td>
	</tr>
</table>
</body>
</html>