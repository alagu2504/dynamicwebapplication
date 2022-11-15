<!DOCTYPE html>
<%@ page
	import="users.Admin,bankingapplicationPojos.Account,java.util.Map"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	width: 50%;
	height: 30%;
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
	margin-left: 30px;
}

.center {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
<head>
<meta charset="UTF-8">
<title>Transaction Statements</title>
</head>
<body>

	<div class="container">
		<h2 style="text-align: center;">Transaction Statements</h2>

		<form action="bankingservlet" method="post">
			<div style="margin-top: 8%;">
				<label style="margin-left: 15%; font-size: 20px; margin-top: 20px;">Account
					Number </label> <select class="textFeild" name="accountNumber"
					id="accountNumber">
					<c:forEach items="${activeAccount}" var="account">
						<option value="${account.value.getAccountNumber()}">${account.value.getAccountNumber() }</option>
					</c:forEach>
				</select> <input class="textFeild" style="display: inline;" type="number"
					name="days" placeholder="Enter number Of Days" />
			</div>
			<input class="submitButton submitHover"
				style="margin-left: 40%; margin-top: 5%;" type="submit"
				name="action" value="STATEMENTS">

		</form>
	</div>


</body>
</html>