<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>

<h1>DashBoard</h1>
Current User LoggedIn ${sessionScope.currentuser}
<ul>
	<li><a href="account/register">Register</a></li>
	<li><a href="account/login">Login</a></li>
	<li><a href="ads/myads">My Ads</a></li>
	<li><a href="ads/list">All Ads</a></li>
	<li><a href="ads/mychats">Chats</a></li>
	
	<li><a href="account/logout">Logout</a></li>
</ul>
</body>
</html>