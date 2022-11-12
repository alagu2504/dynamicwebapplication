<!DOCTYPE html>
<html>
<style type="text/css">
body {
	background: linear-gradient(to bottom, rgba(102, 153, 255, .2), rgba(102, 255, 255, .1));
	height: 100vh;
	width: 100%;
	overflow: hidden;
}

div {
	margin-top: 10px;
	margin-bottom: 20px;
}

input {
	width: 100%;
	color: black;
	font-weight: bold;
	border-radius: 10px;
	border-color: #66ffff;
	height: 35px;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background-color:#FFFFFF;
	margin-left: 12px;
}

input:hover {
	background-color: rgba(102, 255, 255, 0	);
}

h3 {
	margin-left:5%;
	color: #FFFFFF;
	font-size: 20px;
}
</style>
<head>
<meta charset="UTF-8">
<title>Menu</title>
</head>
<body>
	<h3>  Welcome ${userName}</h3>
	<div style="width: 80%;">
		<form action="../../bankingservlet" method="post" target="mainframe">

			<div>
				<input type="submit" name="action" value="Home">
			</div>
			<div>
				<input type="submit" name="action" value="Deposit">
			</div>
			<div>
				<input type="submit" name="action" value="Withdraw">
			</div>
			<div>
				<input type="submit" name="action" value="Transfer">
			</div>
			<div>
				<input type="submit" name="action" value="Transaction Statements">
			</div>
			<div>
				<input type="submit" name="action" value="Active Account">
			</div>

		</form>

	</div>
</body>
</html>