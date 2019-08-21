<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>Users chat with me!</title>
</head>

<body>
	<h3>Users chat with Me!</h3>
	<div id="container">
		<div id="content">
			<c:forEach var="user" items="${userchatted}">
				<a href="chatted?userid=${user.id}">${user.username}</a>
			</c:forEach>				
		</div>
	</div>
</body>
</html>









