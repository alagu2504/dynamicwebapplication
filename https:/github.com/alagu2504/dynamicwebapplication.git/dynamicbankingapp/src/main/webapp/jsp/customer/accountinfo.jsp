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
	overflow: hidden;
}

table {
	border-radius: 50px;
	margin-top: 5%;
	margin-left: 20%;
	border-collapse: collapse;
	border: 3px solid #FFFFFF;
	height:50vh;
	width: 60%;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

td {
	text-align: center;
	border: 3px solid #FFFFFF;
	height: 30px;
	font-size: 20px;
}


tr:nth-child(even) {
	background-color: white;
}

tr:nth-child(odd) {
	background-color: rgba(102, 153, 255, .1);
}

tr:hover {
  background: linear-gradient(to bottom, rgba(102, 153, 255, .7), rgba(102, 255, 255, .5));
}

h2{           text-align:left;
              margin-top:15	px;
              margin-left:10px;
			  font-size:2em;
			  background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
              -webkit-background-clip: text;
              -webkit-text-fill-color: transparent;
}

</style>
<head>
<meta charset="UTF-8">
<title>Accounts Info</title>
</head>
<body>

	<div>
	<h2>Accounts Info</h2>
	<table >

		<tr>
			<td style="opacity:.7">CUSTOMER ID </td>
			<td style="width:60%;">${account.getCustomerId()}</td>
		</tr>

		<tr>
			<td style="opacity:.7">ACCOUNT NUMBER </td>
			<td style="padding-top:20px;">${account.getAccountNumber()}</td>
		</tr>

		<tr>
			<td style="opacity:.7">ACCOUNT TYPE </td>
			<td style="padding-top:20px;">${account.getAccountType()}</td>
		</tr>

		<tr>
			<td style="opacity:.7">IFSC CODE </td>
			<td style="padding-top:20px;">${account.getIfscCode()}</td>
		</tr>

		<tr>
			<td style="opacity:.7">BRANCH NAME </td>
			<td style="padding-top:20px;">${account.getBranchName()}</td>
		</tr>

		<tr>
			<td style="opacity:.7">BALANCE </td>
			<td style="padding-top:20px;">${account.getBalance()}</td>
		</tr>

		<tr>
			<td style="opacity:.7">ACCOUNT STATUS </td>
			<td style="padding-top:20px;">${account.getStatus()}</td>
		</tr>


	</table>
	</div>

</body>
</html>