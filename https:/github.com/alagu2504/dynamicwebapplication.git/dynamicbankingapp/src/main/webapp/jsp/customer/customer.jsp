<!DOCTYPE html>
<html>
<style>
body {
	background: linear-gradient(to bottom, rgba(102, 153, 255, .8),
		rgba(102, 255, 255, .8));
	height: 100vh;
	width: 100%;
	overflow: hidden;
	margin: 0;
}

.dropdown {
	display: inline-block;
	position: absolute;
	margin-left: 89%;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content input {
	padding: 12px 10px;
	color: black;
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
	border-color: rgba(102, 153, 255, .8);
	width: 100%;
}

.dropdown-content input:hover {
	background-color: rgba(102, 153, 255, .5);
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
<head>
<meta charset="UTF-8">
<title>customer</title>
</head>
<body>
	<div style="padding-top: 0px;">

		<header
			style="background: linear-gradient(to bottom, rgba(102, 153, 255, .8), rgba(102, 255, 255, .8)); height: 8vh;">

			<div class="dropdown">
				<img alt="" src="images/profile4.png"
					style="height: 50px; width: 60px; float: right; padding-top: 10px;">
				<div class="dropdown-content"
					style="margin-top: 95%; margin-right: 30%;">

					<div>
						<form action="bankingservlet" method="post" target="mainframe">
							<input type="submit" name="action" value="Personal Details" />
						</form>
					</div>
					<div>
						<form action="bankingservlet" method="post" target="mainframe">
							<input type="submit" name="action" value="Change Password" />
						</form>
					</div>
					<div>
						<form action="bankingservlet" method="post" target="_parent">
							<input type="submit" name="logout" value="Logout" /> <input
								type="hidden" name="action" value="Login" />
						</form>
					</div>
				</div>
			</div>

			<img alt="logo" src="images/starlinklogo.png"
				style="height: 50px; width: 50px; float: left; padding-top: 10px; padding-left: 20px;">
			<h1
				style="font-size: 2em; color: #FFFFFF; margin-top: 0px; padding-top: 19px;">
				STARLINK BANK</h1>
		</header>
		<iframe src="jsp/customer/customermenu.jsp"
			style="height: 92vh; width: 14.8%; float: left; border-left: 0px; border-top: 0px; border-bottom: 0px"
			name="menuframe" title="menu"></iframe>
		<iframe src="jsp/homepage.jsp"
			style="height: 92vh; width: 85%; float: right; border-radius: 10px; border: 0;"
			name="mainframe"></iframe>
	</div>

</body>
</html>