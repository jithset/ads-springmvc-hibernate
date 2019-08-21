<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
<h1>Register</h1>
<table>
	<form:form action="register" modelAttribute="user" method="POST">
		<tr><td>Username</td></tr>
		<tr><td><form:input path="username"/></td></tr>
		<tr><td>Password</td></tr>
		<tr><td><form:input path="password"/></td></tr>
		<c:if test="${not empty error}">
		   <p style="color:red;">${error}</p>
		</c:if>
		<tr><td><button type ="submit">Register</button></td></tr>
	</form:form>
	<tr>
	<td>
	<a href="login">Login</a>
	</td>
	</tr>
</table>
</body>
</html>