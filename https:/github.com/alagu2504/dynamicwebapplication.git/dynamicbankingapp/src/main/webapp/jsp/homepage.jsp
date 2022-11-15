<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bankingapplicationPojos.User,users.Operations"%>
<!DOCTYPE html>
<html>
<style>
body {
	background: linear-gradient(to bottom, rgba(102, 153, 255, -100),
		rgba(102, 255, 255, -100));
	height: 100vh;
	width: 100%;
	overflow: hidden;
}

h1 {
	text-align: center;
	margin-top: 10%;
	color: #FFFFFF;
	font-size: 3em;
}

h2 {
	text-align: center;
	color: #FFFFFF;
	font-size: 2.5em;
}
</style>
<head>
<meta charset="UTF-8">
<title>Customer Home</title>
</head>
<body>
	<h1>Welcome to Our StarLink</h1>
	<h2>${userName}</h2>
</body>
</html>