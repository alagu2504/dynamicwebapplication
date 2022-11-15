<!DOCTYPE html>
<html>
<style>
body {
	background: linear-gradient(to bottom, rgba(102, 153, 255, .2),
		rgba(102, 255, 255, .1));
	height: 100vh;
	width: 100%;
	overflow: hidden;
}

div {
	margin-top: 10px;
	margin-bottom: 20px;
}

input {
	width: 80%;
	color: black;
	font-weight: bold;
	border-radius: 10px;
	border-color: #66ffff;
	height: 35px;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background-color: #FFFFFF;
	margin-left: 12px;
}

input:hover {
	background-color: rgba(102, 255, 255, 0);
}

h3 {
	margin-left: 5%;
	color: #FFFFFF;
	font-size: 20px;
	margin-top: 1px;
}
</style>
<head>
<meta charset="UTF-8">
<title>admin menu</title>
</head>
<body style="overflow: hidden;">
	<h3>Welcome ${userName}</h3>
	<div>
		<form action="../../bankingservlet" method="post" target="mainframe">

			<div>
				<input type="submit" name="action" value="View Users Details">
			</div>
			<div>
				<input type="submit" name="action" value="View Customer Details">
			</div>
			<div>
				<input type="submit" name="action" value="View Account Details">
			</div>
			<div>
				<input type="submit" name="action" value="View Transaction Details">
			</div>
			<div>
				<input type="submit" name="action"
					value="View Customer Status Request">
			</div>
			<div>
				<input type="submit" name="action"
					value="View Account Status Request">
			</div>
			<div>
				<input type="submit" name="action" value="View Withdraw Request">
			</div>
			<div>
				<input type="submit" name="action" value="Add New Users">
			</div>
			<div>
				<input type="submit" name="action" value="Add New Account">
			</div>

		</form>


	</div>
</body>
</html>