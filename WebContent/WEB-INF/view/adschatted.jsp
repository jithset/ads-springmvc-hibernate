<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>
<body>
<h1>Ads List </h1>
			
			<c:forEach var="ads" items="${chattedads}">
			
			<div>
			
				<p><a href="chatsonads?userid=${param.userid}&adid=${ads.id}">${ads.add_title}</a></p>
				<p>${ads.add_description}</p>
				
				<p>${param.userid}</p>
				
			</div>
			<hr>
			
			</c:forEach>
</body>
</html>