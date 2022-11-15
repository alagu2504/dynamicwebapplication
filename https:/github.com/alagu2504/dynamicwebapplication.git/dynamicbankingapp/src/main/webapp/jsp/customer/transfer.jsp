<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Admin,bankingapplicationPojos.Account,java.util.Map,java.util.Set"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
	overflow: hidden;
}

h2 {
	opacity: 0.5;
	text-align: center;
	font-size: 2em;
	margin-top: 30px;
	margin-bottom: 10px;
}

.container {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 40%;
	left: 50%;
	width: 40%;
	height: 55%;
	border-radius: 30px;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background: linear-gradient(to bottom, rgba(102, 153, 255, .3),
		rgba(102, 255, 255, .3));
}

.submitButton {
	color: #FFFFFF;
	font-weight: bold;
	background-color: rgb(0, 0, 255, 1);
	height: 30px;
	border-radius: 8px;
	width: 150px;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

.submitHover:hover {
	background-color: rgb(0, 0, 255, .8);
}

.textFeild {
	font-size: 15px;
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
	border-radius: 8px;
	height: 30px;
	text-align: center;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

.center {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}

td {
	height: 50%;
}

table {
	border-collapse: separate;
	border-spacing: 30px;
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
<title>Transaction</title>
</head>
<body>
	<div class="container">
		<h2>Transfer</h2>
		<form action="bankingservlet" method="post">
			<p class="error">${error }</p>
			<table class="center">
				<tr>
					<td style="padding-top: 20px; text-align: left;"><label
						style="padding-right: 50px; font-size: 20px;" for="accno">Sender
							Account Number</label></td>
					<td><select class="textFeild"
						style="height: 34px; width: 100%; margin-left: 2px;"
						name="senderAccountnumber">
							<c:forEach items="${activeAccounts}" var="accountNumber">
								<option value="${accountNumber.key}">${accountNumber.key}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: left; padding-top: 20px;"><label
						style="padding-right: 50px; font-size: 20px;"
						for="receiveraccount">Receiver Account Number</label></td>
					<td><input class="textFeild" type="number" id="receiveracount"
						name="receiveraccount" placeholder="Receiver Account Number"
						required></td>
				</tr>

				<tr>
					<td style="text-align: left; padding-top: 20px;"><label
						style="padding-right: 50px; font-size: 20px;" for="transferamount">Transfer
							Amount</label></td>
					<td><input class="textFeild" type="number" id="transferamount"
						name="transferamount" placeholder="Enter Amount" required>
					</td>
				</tr>

				<tr>
					<td style="text-align: left; padding-top: 20px;"><label
						style="padding-right: 50px; font-size: 20px;" for="password">Password
					</label></td>
					<td><input class="textFeild" type="password" name="password"
						placeholder="Enter Password" required></td>
				</tr>

			</table>
			<input class="submitButton submitHover" style="margin-left: 60%;"
				type="submit" name="action" value="TRANSFER" />
		</form>
	</div>
</body>
</html>