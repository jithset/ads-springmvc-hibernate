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
<h1>Add UserDetail</h1>
<table>
	<form:form action="userdetail" modelAttribute="userdetail" method="POST">
		<tr><td>Firstname</td></tr>
		<tr><td><form:input path="first_name"/></td></tr>
		<tr><td>Lastname</td></tr>
		<tr><td><form:input path="last_name"/></td></tr>
		<tr><td>Email</td></tr>
		<tr><td><form:input path="email"/></td></tr>
		<tr><td><button type ="submit">Update Detail</button></td></tr>
		<c:if test="${not empty error}">
		   ${error}
		</c:if>
	</form:form>
</table>
</body>
</html>