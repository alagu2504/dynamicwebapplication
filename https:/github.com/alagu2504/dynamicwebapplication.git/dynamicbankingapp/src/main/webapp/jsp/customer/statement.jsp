
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Operations,bankingapplicationPojos.Statements,java.util.List"%>
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
	margin-right: 200px;
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
	background: linear-gradient(to bottom, rgba(102, 153, 255, .8),
		rgba(102, 255, 255, 0));
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
</style>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>Transaction Statements</h2>
	<table class="center">

		<tr>
			<th style="width: 5%;">Transaction Id</th>
			<th style="width: 5%;">Customer Id</th>
			<th style="width: 25%;">Sender Account Number</th>
			<th style="width: 25%;">Receiver Account Number</th>
			<th style="width: 10%;">Transfer Amount</th>
			<th style="width: 10%;">Transaction Type</th>
			<th style="width: 30%;">Transaction Time</th>
			<th style="width: 20%;">Mode Of Transaction</th>
		</tr>

		<c:forEach items="${statement}" var="account">

			<tr>
				<td>${account.value.getTransactionId()}</td>
				<td>${account.value.getCustomerId() }</td>
				<td>${account.value.getSenderAccount() }</td>
				<td>${account.value.getReceiverAccount() }</td>
				<td>${account.value.getTransferAmount() }</td>
				<td>${account.value.getTransactionType()}</td>
				<td>${account.value.getTime()}</td>
				<td>${account.value.getModeOfTransaction() }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>