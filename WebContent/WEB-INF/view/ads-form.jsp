<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h3>Ads</h3>
	<form:form action="add" modelAttribute="ads" method="POST">
		<tr><td>Title</td></tr>
		<tr><td><form:input path="add_title"/></td></tr>
		<tr><td>Description</td></tr>
		<tr><td><form:input path="add_description"/></td></tr>
		<tr><td><button type ="submit">Add</button></td></tr>
		<c:if test="${not empty error}">
		   ${error}
		</c:if>
	</form:form>
</body>
</html>