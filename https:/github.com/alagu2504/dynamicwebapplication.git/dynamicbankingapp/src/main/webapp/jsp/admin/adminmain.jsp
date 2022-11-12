<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
body {
	padding-top: 0px;
	margin: 0px;
}

.dropdown {
	display: inline-block;
	position: absolute;
	margin-left: 88%;
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
<link rel="stylesheet" href="css/CustomDesign.css">
<meta charset="UTF-8">
<title>ADMIN PAGE</title>
</head>
<body style="overflow: hidden;">
	<div style="padding-top:0px;">
		<header
			style="background: linear-gradient(to bottom, rgba(102, 153, 255, .8), rgba(102, 255, 255, .8)); height: 10vh;">
					<img alt="logo" src="images/starlinklogo.png" style="height:60px;width:60px;display:inline-block;paading-top:30px;">	
			<div class="dropdown">
				<img alt="" src="images/profile4.png"
					style="height: 50px; width: 60px; float: right; padding-top: 20px;">
				<div class="dropdown-content" style="margin-top: 105%;">

					<div>
						<form action="bankingservlet" method="post" target="mainframe">
							<input type="hidden" name="action" value="User Details" /> <input
								type="submit" name="profileInfo" value="Profile Info" />
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

			<h1
				style="text-align: left; font-size: 2em; padding-top: 40px; color: #FFFFFF; padding-left: 10px;display:inline">STARLINK
				BANK</h1>
		</header>
	</div>
	<iframe src="jsp/admin/adminmenu.jsp"
		style="height: 90vh; width: 14.8%; float: left; border-left: 0px; border-top: 0px; border-bottom: 0px"
		name="menuframe" title="menu"></iframe>
	<iframe src="jsp/homepage.jsp"
		style="height: 90vh; width: 85%; float: right; border-radius: 10px; border: 0;"
		name="mainframe"> </iframe>
</body>
</html>