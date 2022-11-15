<!DOCTYPE html>
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
	margin-top: 50px;
}

.container {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 50%;
	left: 50%;
	width: 35%;
	height: 250px;
	border-radius: 30px;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background: linear-gradient(to bottom, rgba(102, 153, 255, .3),
		rgba(102, 255, 255, .3));
}

.submitButton {
	color: #FFFFFF;
	font-weight: bold;
	background-color: rgb(0, 0, 255, 1);
	height: 40px;
	border-radius: 8px;
	width: 150px;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

.submitHover:hover {
	background-color: rgb(0, 0, 255, .8);
}
</style>
<head>
<meta charset="UTF-8">
<title>Add User</title>
</head>
<body>

	<div class="container">
		<h2>ADD NEW USER</h2>
		<form action="bankingservlet" method="post" target="mainframe">
			<table style="padding-left: 0px;">
				<tr>
					<td><input class=" submitButton submitHover"
						style="margin-left: 150px;" type="submit" name="action"
						value="ADD ADMIN" /></td>
					<td><input class=" submitButton submitHover"
						style="margin-right: 30px%;" type="submit" name="action"
						value="ADD CUSTOMER"></td>
				</tr>
			</table>

		</form>
	</div>

</body>
</html>