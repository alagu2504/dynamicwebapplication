<!DOCTYPE html>
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
	width: 40%;
	height: 50%;
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
	width: 80%;
	text-align: center;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

.center {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}

table {
	border-collapse: separate;
	border-spacing: 40px;
	padding-left: 20px;
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
<title>Change Account Status</title>
</head>
<body>

	<div class="container">
		<h2>Request To Active Account</h2>
		<p class="error">${error }</p>
		<form action="bankingservlet" method="post">

			<table class="center">
				<tr>
					<td style="text-align: left;"><label
						style="padding-bottom: 10px; font-size: 20px;" for="accno">Account
							Number</label></td>
					<td><select class="textFeild" name="accountnumber">
							<c:forEach items="${inActiveAccounts}" var="accountNumber">
								<option value="${accountNumber.key}">${accountNumber.key}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td style="text-align: left;"><label
						style="margin-right: 80px; font-size: 20px; padding-bottom: 30px;"
						for="reason">Description </label></td>
					<td style="padding-left: 7%; padding-right: 10%;"><textarea
							style="height: 90px; width: 110%; border-radius: 10px;"
							id="describtion" name="describtion" rows="4" cols="50" required></textarea>
					</td>
				</tr>


			</table>

			<input type="hidden" name="action" value="Active Account Request">
			<input class="submitButton submitHover"
				style="margin-top: 30px; margin-left: 50%;" type="submit"
				name="submit" value="SUBMIT">
		</form>
	</div>

</body>
</html>