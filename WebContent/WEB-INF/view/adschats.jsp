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
<h3><a href="/event-project">Home</a></h3>
<h1>Ads List </h1>
			
			<c:forEach var="chats" items="${adschats}">
			
			<div>
			
				<p>${chats.sender}</p>
				<p>${chats.message}</p>
				<p>Test ads is correct ${chats.ads.id }</p>
				<a href="addmychat"> Test Link</a>
				
			</div>
			<hr>
			
			</c:forEach>
			<form:form action="addmychat" modelAttribute="chat" method="POST">
				<label>Enter your message: </label>
				<form:input path="message"/>
				<input type="hidden" name="receiverId" value="${receiverId}"/>
				<input type="hidden" name="adsid" value="${adsId }"/>
				<button type="submit">Send Message</button>
			</form:form>
</body>
</html>