<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Admin,bankingapplicationPojos.Account,java.util.Map"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
	overflow: hidden;
}

table {
	border-radius: 10px;
	margin-top: 80px;
	margin-left: 250px;
	border-collapse: collapse;
	border: 3px solid #FFFFFF;
	width: 60%;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

td {
	text-align: center;
	border: 3px solid #FFFFFF;
	width: 60%;
	height: 40px;
	font-size: 20px;
}

th {
	text-align: center;
	border: 3px solid #FFFFFF;
	width: 60%;
	height: 45px;
	font-size: 25px;
  background: linear-gradient(to bottom, rgba(102, 153, 255, .7), rgba(102, 255, 255, .5));
}

tr:nth-child(even) {
	background-color: rgba(102, 153, 255, .1);
}

tr:nth-child(odd) {
	background-color: white;
}

tr:hover {
  background: linear-gradient(to bottom, rgba(102, 153, 255, .7), rgba(102, 255, 255, .5));
}

input {
	font-size: 20px;
	border-top-style: hidden;
	border-right-style: hidden;
	border-left-style: hidden;
	border-bottom-style: hidden;
	background-color: transparent;
	outline: none;
}

h2{           text-align:left;
              margin-top:15px;
              margin-left:10px;
			  font-size:2em;
			  background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
              -webkit-background-clip: text;
              -webkit-text-fill-color: transparent;
}
</style>
<head>
<meta charset="UTF-8">
<title>Account Details</title>
</head>
<body>
	<h2>Account Details</h2>
	<div>
		<table>
			<tr>
				<th>Account Number</th>
				<th>Status</th>
			</tr>
			<form action="bankingservlet" method="post">
				<c:forEach items="${accountDetails}" var="accountDetails">
					<tr>
						<td><input type="submit" name="accountNumber"
							value="${accountDetails.value.getAccountNumber()}"></td>
						<td>${accountDetails.value.getStatus()}</td>
					</tr>
				</c:forEach>
				<input type="hidden" name="action" value="ACCOUNT INFO">
			</form>
		</table>
	</div>

</body>
</html>