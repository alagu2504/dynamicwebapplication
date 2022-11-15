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
}

.container {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 40%;
	left: 50%;
	width: 40%;
	height: 43%;
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
<title>Change Password</title>
</head>
<body>
	<div class="container">
		<h2 style="margin-top: 30px;">Change Password</h2>

		<form action="bankingservlet" method="post" target="mainframe">
			<p class="error">${error }</p>

			<table class="center">

				<tr>
					<td style="padding-right: 20px; padding-top: 5px; font-size: 20px;"><label>New
							Password</label></td>
					<td><input class="textFeild"
						style="height: 30px; margin-left: 2px;" type="password"
						id="newpassword" name="newpassword" placeholder="Newpassword"
						required></td>
				</tr>

				<tr>
					<td
						style="padding-right: 50px; padding-top: 20px; font-size: 20px;">
						<label for="confirm_password">Confirm Password</label>
					</td>
					<td><input class="textFeild" type="password"
						id="confirm_password" name="confirm_password"
						placeholder="confirmpassword" required></td>
				</tr>
			</table>


			<input style="margin-left: 53.5%; margin-top: 10px;"
				class="submitButton submitHover" type="submit" name="action"
				value="CHANGE PASSWORD">


		</form>
	</div>
</body>
</html>