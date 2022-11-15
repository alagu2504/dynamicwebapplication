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
	margin-top: 30px;
	margin-bottom: 10px;
}

.container {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 40%;
	left: 50%;
	width: 40%;
	height: 80%;
	margin-top: 3%;
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
}

.center {
	margin-left: auto;
	margin-right: auto;
	text-align: left;
}

td {
	height: 50%;
}

table {
	border-collapse: separate;
	border-spacing: 30px;
	text-align: left;
	padding-left: 15%;
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
<title>Add User</title>
</head>
<body>

	<div class="container">
		<h2>ADD NEW customer</h2>
		<form action="bankingservlet" method="post" target="mainframe">
			<p class="error">${error }</p>
			<table>
				<tr>
					<th>Name</th>
					<td><input class="textFeild" type="text" id="name" name="name"
						placeholder="Enter Name" required></td>
				</tr>
				<tr>
					<th>Date of Birth</th>
					<td><input class="textFeild" type="date" id="dateofbirth"
						name="dateofbirth" required></td>
				</tr>
				<tr>
					<th>Mobile Number</th>
					<td><input class="textFeild" type="number" id="mobilenumber"
						name="mobilenumber" placeholder="Enter mobile Number" required></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><input class="textFeild" type="text" id="address"
						name="address" placeholder="Enter the Address" required></td>
				</tr>
				<tr>
					<th>Email Id</th>
					<td><input class="textFeild" type="email" id="emailid"
						name="emailid" placeholder="Enter Email Id" required></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input class="textFeild" type="password" id="password"
						name="password" placeholder="Enter password" required></td>
				</tr>
				<tr>
					<th>Aadhaar Card Number</th>
					<td><input class="textFeild" type="number" id="aadhaarNo"
						name="aadhaarNo" placeholder="Enter Aadhaar Number" required></td>
				</tr>

				<tr>
					<th>Pan Card Number</th>
					<td><input class="textFeild" type="text" id="panCardNo"
						name="panCardNo" placeholder="Enter Pan Card number" required></td>
				</tr>

				<tr>
					<td></td>
					<td><input class="submitButton submitHover" type="submit"
						name="submit" value="SUBMIT"></td>
				</tr>
			</table>
			<input type="hidden" id="status" name="status" value="Active"
				readonly> <input type="hidden" id="role" name="role"
				value="Customer" readonly> <input type="hidden"
				name="action" value="NEW CUSTOMER">
		</form>
	</div>


</body>
</html>