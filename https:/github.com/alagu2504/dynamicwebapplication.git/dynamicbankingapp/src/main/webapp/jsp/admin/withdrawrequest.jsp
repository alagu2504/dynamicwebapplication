<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.Map,bankingapplicationPojos.TransactionRequest,java.util.TreeMap"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
}

table {
	margin-top: 50px;
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
	height: 50px;
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

tr:nth-child(odd) {
	background-color: white;
}

tr:nth-child(even) {
	background-color: rgba(102, 153, 255, .1);
}

tr:hover {
	background: linear-gradient(to bottom, rgba(102, 153, 255, .8),
		rgba(102, 255, 255, .5));
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

.response {
	width: 70%;
	font-size: .8em;
	border-radius: 5px;
	height: 30px;
	border-color: rgba(255, 114, 111, .8);
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background-color: rgba(102, 153, 255, .5);
}

.save {
	width: 80px;
	color: #FFFFFF;
	font-weight: bold;
	border-radius: 10px;
	height: 40px;
	border-color: rgba(255, 114, 111, .8);
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background-color: rgba(102, 153, 255, .5);
}

input {
	background: transparent;
	text-align: center;
	font-size: 15px;
	border: 0;
}

.error {
	margin-top: 5px;
	text-align: center;
	font-size: 1em;
	color: red;
}
</style>
<head>
<meta charset="UTF-8">
<title>withdraw request</title>
</head>
<body>
	<h2>Withdraw Request Table</h2>
	<p class="error">${error }</p>

	<table>

		<tr>
			<th>Request Id</th>
			<th>Account Number</th>
			<th>Withdraw Amount</th>
			<th>Status</th>
			<th>Action</th>
			<th>Submit</th>
		</tr>
		<c:forEach items="${withdrawRequest}" var="withdrawRequests">
			<form action="bankingservlet" method="post" target="mainframe">
				<tr>
					<td><input type="number" name="requestId"
						value="${withdrawRequests.value.getRequestId() }" readonly
						required></td>
					<td><input type="number" name="accountNumber"
						value="${withdrawRequests.value.getSenderAccountNumber() }"
						readonly required></td>
					<td><input type="number" name="withdrawAmount"
						value="${withdrawRequests.value.getTransferAmount() }" readonly
						required></td>
					<td><input type="text" name="status"
						value="${withdrawRequests.value.getRequestStatus() }" readonly
						required></td>
					<td><select class="response" name="state">
							<option value="Accepted">Accepted</option>
							<option value="Rejected">Rejected</option>
					</select></td>
					<td><input class="save" type="submit" name="submit"
						value="SUBMIT"></td>

				</tr>
				<input type="hidden" name="action" value="Update Request">
			</form>
		</c:forEach>

	</table>
	<p id="error">${error}</p>
</body>
</html>