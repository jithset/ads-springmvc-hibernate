<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Ads</title>
</head>

<body>
	<c:if test="${!ischat}">
		<a href="add">Add Ads</a>
	</c:if>
	
	<div id="container">
		<div id="content">
			<!-- loop over and print our customers -->
			<c:forEach var="ads" items="${ads}">
			
				<h3>${ads.add_title}</h3>
				<p>${ads.add_description}</p>
				<p>${ads.user}</p>
				<c:if test="${ischat}">
					<p><a href="chat?id=${ads.id }">start chat with seller</a></p>
				</c:if>
			</c:forEach>				
		</div>
	</div>
</body>
</html>









