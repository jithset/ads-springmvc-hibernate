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
<h1>Chat on Ads ${adsdetail }</h1>
			
			<c:forEach var="items" items="${currentchats}">
			
			<div>
			
				<p>${items.sender }</p>
				<p>${items.message }</p>
				<p>${items.messageTime }</p>
			
			</div>
			<hr>
			
			</c:forEach>

	<form:form action="chat" modelAttribute="chat" method="POST">
	<label>Enter your message: </label>
		<form:input path="message"/>
		<input type="hidden" name="adsid" value="${adsdetail.id }"/>
		<button type="submit">Send Message</button>
	</form:form>
</body>
</html>