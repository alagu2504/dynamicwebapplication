<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.dropdown {
	display: inline-block;
	position: absolute;
	margin-left: 90%;
}

.dropdown-content {
	display: none;
	overflow:visible;
	position: absolute;
	background-color: #f1f1f1;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index:1;
}

.dropdown-content input {
    padding:12px 10px;
	color: black;
	border: 0;
	width: 100%;
	overflow:visible;
}


.dropdown-content input:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
	overflow:visible;
}
</style>
<head>
<link rel="stylesheet" href="../css/CustomDesign.css">
<meta charset="UTF-8">
<title></title>
</head>
<body style="background-color: #FFDEAD;">


		<div class="dropdown" >
			<img alt="" src="../images/profile4.png"
				style="height: 50px; width: 60px; float: right;">
			<div class="dropdown-content" >
				<div>
					<form action="../bankingservlet" method="post" target="_parent">
						<input type="submit" name="action" value="Login" />
					</form>
				</div>
				<div>
					<form action="../bankingservlet" method="post" target="mainframe">
						<input type="submit" name="action" value="Personal Details" />
					</form>
				</div>
				<div>
					<form action="../bankingservlet" method="post" target="mainframe">
						<input type="submit" name="action" value="Change Password" />
					</form>
				</div>
			</div>
		</div>

		<h1
			style="text-align: left; font-size: 2em; margin-top: 5px; color: #FFFFFF">STARLINK
			BANK</h1>
</body>
</html>