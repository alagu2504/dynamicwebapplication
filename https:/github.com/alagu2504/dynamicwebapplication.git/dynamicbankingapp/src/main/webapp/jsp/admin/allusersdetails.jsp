<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Admin,bankingapplicationPojos.User,java.util.Map,java.util.TreeMap"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
}

table {
	border-radius: 10px;
	margin-left:200px;
	margin-right: 200px ;
	border-collapse: collapse;
	border: 3px solid #FFFFFF;
	width:80%;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

td {
	text-align: center;
	border: 3px solid #FFFFFF;
	height: 30px;
	font-size: 20px;
}

th {
	 text-align: center;
	border: 3px solid #FFFFFF;
	height: 45px;
	font-size: 20px;
    background-color: rgba(102, 153, 255, .8);
    position: sticky;
    top: 0;
}

tr:nth-child(even) {
	background-color: white;
}

tr:nth-child(odd) {
	background-color: rgba(102, 153, 255, .1);
}

tr:hover {
	background: linear-gradient(to bottom, #6699ff -3%, #66ffff 100%);
}


h2{           
              text-align:left;
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
<title>All User Details</title>
</head>
<body>
	<h2 >All User's Details</h2>
	<table>
		<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>Date Of Birth</th>
			<th>Mobile Number</th>
			<th>Address</th>
			<th>Email Id</th>
			<th>password</th>
			<th>Role</th>
		</tr>

		<c:forEach items="${usersDetails }" var="user">
			<tr>
				<td>${user.value.getUserId() }</td>
				<td>${user.value.getUserName() }</td>
				<td>${user.value.getDataOfBirth() }</td>
				<td>${user.value.getMobileNumber() }</td>
				<td>${user.value.getAddress() }</td>
				<td>${user.value.getEmailId() }</td>
				<td>${user.value.getPassword() }</td>
				<td>${user.value.getRole() }</td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>