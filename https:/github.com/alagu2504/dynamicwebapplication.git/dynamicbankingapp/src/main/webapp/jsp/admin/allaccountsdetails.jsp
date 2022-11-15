<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Admin,java.util.Map,java.util.Iterator,bankingapplicationPojos.Account"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
}

table {
	border-radius: 10px;
	margin-left: 20px;
	border-collapse: collapse;
	border: 3px solid #FFFFFF;
	width: 95%;
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

h2 {
	text-align: left;
	margin-top: 15px;
	margin-left: 10px;
	font-size: 2em;
	background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

input {
	border: 0;
	width: 100%;
	background: transparent;
}

.save {
	width: 5%;
	color: #FFFFFF;
	font-weight: bold;
	border-radius: 5px;
	width: 90%;
	border-color: rgba(255, 114, 111, .8);
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background-color: rgba(102, 153, 255, .5);
}
</style>
<head>

<meta charset="UTF-8">
<title>All Account Details</title>
</head>
<body>
	<h2>All Accounts Details</h2>
	<table>
		<tr>
			<th>CUSTOMER ID</th>
			<th>ACCOUNT NUMBER</th>
			<th>ACCOUNT TYPE</th>
			<th>IFSC CODE</th>
			<th>BRANCH NAME</th>
			<th>BALANCE</th>
			<th>ACCOUNT STATUS</th>
		</tr>

		<c:forEach items="${allAccountDetails}" var="allAccount">
			<c:forEach items="${ allAccount.value}" var="account">
				<tr>
					<td>${account.value.getCustomerId() }</td>
					<td>${account.value.getAccountNumber() }</td>
					<td>${ account.value.getAccountType()}</td>
					<td>${ account.value.getIfscCode()}</td>
					<td>${ account.value.getBranchName() }</td>
					<td>${account.value.getBalance() }</td>
					<td>${ account.value.getStatus()}</td>
				</tr>
			</c:forEach>
		</c:forEach>

	</table>

</body>
</html>