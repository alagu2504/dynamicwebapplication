<!DOCTYPE html>
<%@ page import="users.Operations,bankingapplicationPojos.Customer"%>

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
	margin-bottom: 5px;
}

.container {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 50%;
	width: 40%;
	height: 75%;
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
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
	border-radius: 8px;
	height: 30px;
	text-align: center;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	width: 60%;
	font-size: 1em;
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
	width: 100%;
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
<title>Customer Details</title>
</head>
<body>

	<div class="container">
		<h2>Profile Info</h2>
		<p class="error">${error }</p>
		<form action="bankingservlet" method="post" target="mainframe">
			<table class="center">
				<tr>
					<td
						style="text-align: left; padding-top: 20px; padding-left: 80px;">
						<label style="padding-right: 50px; font-size: 20px;">Name</label>
					</td>
					<td><input class="textFeild" type="text" id="userName"
						name="userName" value="${user.getUserName() }" required></td>
				</tr>

				<tr>
					<td
						style="text-align: left; padding-top: 20px; padding-left: 80px;">
						<label style="padding-right: 50px; font-size: 20px;">Date
							of Birth </label>
					</td>
					<td><input class="textFeild" type="text" id="dateOfBirth"
						name="dateOfBirth" value="${user.getDataOfBirth() }" readonly
						required></td>
				</tr>

				<tr>
					<td
						style="text-align: left; padding-top: 20px; padding-left: 80px;"><label
						style="padding-right: 50px; font-size: 20px;">Mobile
							Number </label></td>
					<td><input class="textFeild" type="number" id="mobileNumber"
						name="mobileNumber" value="${user.getMobileNumber() }" required></td>
				</tr>

				<tr>
					<td
						style="text-align: left; padding-top: 20px; padding-left: 80px;"><label
						style="padding-right: 50px; font-size: 20px;">Address </label></td>
					<td><input class="textFeild" type="text" id="address"
						name="address" value="${user.getAddress() }" required></td>
				</tr>

				<tr>
					<td
						style="text-align: left; padding-top: 20px; padding-left: 80px;"><label
						style="padding-right: 50px; font-size: 20px;">Email Id </label></td>
					<td><input class="textFeild" type="text" id="emailId"
						name="emailId" value="${user.getEmailId() }" required></td>
				</tr>


				<tr>
					<td></td>
					<td style="padding-left: 1px;"><input
						class="submitButton submitHover" type="submit" name="save"
						value="Save" style="border: 3px solid #555;"></td>
				</tr>
			</table>
			<input type="hidden" name="userId" value="${user.getUserId()}" /> <input
				type="hidden" name="password" value="${user.getPassword()}" /> <input
				type="hidden" name="role" value="${user.getRole()}" /> <input
				type="hidden" name="action" value="Update UserInfo" />
		</form>
	</div>

</body>
</html>