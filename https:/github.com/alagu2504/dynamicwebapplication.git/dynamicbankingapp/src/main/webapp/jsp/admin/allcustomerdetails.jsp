<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Admin,java.util.Map,bankingapplicationPojos.Customer,java.util.TreeMap"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
}

table {
	border-radius: 10px;
	margin-left: 20px;
	border-collapse: collapse;
	border: 3px solid #FFFFFF;
	width: 95%;
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
}

td {
	text-align: center;
	border: 3px solid #FFFFFF;
	height: 30px;
	font-size: 20px;
}

th {
	text-align: center;
	border: 3px solid #FFFFFF;
	height: 45px;
	font-size: 20px;
	background-color: rgba(102, 153, 255, 1);
	position: sticky;
	top: 0;
}

tr:nth-child(even) {
	background-color: white;
}

tr:nth-child(odd) {
	background-color: rgba(102, 153, 255, .1);
}

tr:hover {
	background: linear-gradient(to bottom, #6699ff -3%, #66ffff 100%);
}

.save:hover {
	background-color: green;
}

h2 {
	text-align: left;
	margin-top: 15px;
	margin-left: 10px;
	font-size: 2em;
	background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

input {
	border: 0;
	width: 100%;
	background: transparent;
}

.save {
	color: #FFFFFF;
	font-weight: bold;
	border-radius: 5px;
	width: 90%;
	border-color: rgba(255, 114, 111, .8);
	box-shadow: 20px 20px 40px -6px rgba(0, 0, 0, 0.2);
	background-color: rgba(102, 153, 255, .5);
}
</style>
<head>

<meta charset="UTF-8">
<title>All Customer Details</title>
</head>
<body>


	<div>
		<h2>All Customer Details</h2>
		<table>
			<tr>
				<th style="width: 5%">Customer Id</th>
				<th style="width: 8%">Name</th>
				<th style="width: 5%">Date Of Birth</th>
				<th style="width: 8%">Mobile</th>
				<th style="width: 20%">Address</th>
				<th style="width: 9%">Email Id</th>
				<th style="width: 9%">Password</th>
				<th style="width: 5%">Role</th>
				<th style="width: 9%">Aadhaar Number</th>
				<th style="width: 8%">Pan Number</th>
				<th style="width: 5%">Status</th>
				<th style="width: 5%">Update Change</th>
			</tr>
			<c:forEach items="${customerDetail}" var="allCustomer">
				<form action="bankingservlet" method="post">
					<tr>
						<td><input type="number" name="customerId"
							value="${allCustomer.value.getCustomerId()}" /></td>
						<td><input type="text" name="userName"
							value="${allCustomer.value.getUserName()}" /></td>
						<td><input type="date" name="dateOfBirth"
							value="${allCustomer.value.getDataOfBirth()}" /></td>
						<td><input type="number" name="mobileNumber"
							value="${allCustomer.value.getMobileNumber()}" /></td>
						<td><input type="text" name="address"
							value="${allCustomer.value.getAddress()}" /></td>
						<td><input type="text" name="emailId"
							value="${allCustomer.value.getEmailId()}" /></td>
						<td><input type="text" name="password"
							value="${allCustomer.value.getPassword()}" /></td>
						<td><input type="text" name="role"
							value="${allCustomer.value.getRole()}" /></td>
						<td><input type="number" name="aadhaarNumber"
							value="${allCustomer.value.getAadhaarNumber()}" /></td>
						<td><input type="text" name="panCardNumber"
							value="${allCustomer.value.getPanNumber()}" /></td>
						<td><input type="text" name="status"
							value="${allCustomer.value.getStatus()}" /></td>
						<td><input class="save" type="submit" name="submit"
							value="save" /></td>
					</tr>
					<input type="hidden" name="action" value="SaveInfo">
				</form>
			</c:forEach>
		</table>
	</div>



</body>
</html>