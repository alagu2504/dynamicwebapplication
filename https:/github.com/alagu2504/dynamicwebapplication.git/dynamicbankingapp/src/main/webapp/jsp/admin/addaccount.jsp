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
  transform: translate(-50%,-50%);
  top: 40%;
  left: 50%;
  width:40%;
  height:80%;
  margin-top:3%;
  border-radius: 30px;
    box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
  background: linear-gradient(to bottom, rgba(102, 153, 255, .3), rgba(102, 255, 255, .3));
		}

.submitButton{
      color:#FFFFFF;
      font-weight:bold;
	  background-color:rgb(0,0,255,1);
	  height:30px;
	  border-radius: 8px;
	  width:150px;
	  box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
}

.submitHover:hover{
	  background-color:rgb(0,0,255,.8);
}

.textFeild{
          font-size:15px;
          border-top:0px;
          border-left: 0px;
          border-right: 0px;
		  border-radius: 8px;
	      height:30px;
	      text-align:center;
	      box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
}


.center{
	margin-left:auto;
	margin-right:auto;
	text-align:left;
}

td{
height:50%;
}

table{
  border-collapse:separate;
  border-spacing: 30px;
  text-align:left;
  padding-left:15%;
}

.error{
	    margin-top:5px;
	    text-align:center;
		font-size:1em;
		color:red;
}
</style>
<head>
<meta charset="UTF-8">
<title>Add New Account</title>
</head>
<body>

	<div class="container">
		<h2 >ADD NEW ACCOUNT</h2>
	<form action="bankingservlet" method="post">
		<p class="error">${error }</p>
		<table>
			<tr>
				<th>Account Number </th>
				<td><input class="textFeild" type="number" id="accno" name="accno"
					placeholder="Enter Account Number" required></td>
			</tr>
			<tr>
				<th>Account Type </th>
				<td><input class="textFeild" type="text" id="acctype" name="acctype"
					placeholder="Enter Account Type" required></td>
			</tr>
			<tr>
				<th>Ifsc Code </th>
				<td><input class="textFeild" type="text" id="ifsccode" name="ifsccode"
					placeholder="Enter Ifsc Code" required></td>
			</tr>
			<tr>
				<th>Branch Name </th>
				<td><input class="textFeild" type="text" id="branchname" name="branchname"
					placeholder="Enter Branch Name" required></td>
			</tr>
			<tr>
				<th>Balance </th>
				<td><input class="textFeild" type="number" id="balance" name="balance"
					placeholder="Balance" required></td>
			</tr>
			<tr>
				<th>Status </th>
				<td><input class="textFeild" type="text" id="status" name="status" value="Active"
					readonly required></td>
			</tr>
			<tr>
				<th>Customer Id </th>
				<td><select class="textFeild" name="customerId">
						<c:forEach items="${activeCustomer}" var="customer">
							<option value="${customer.value.getCustomerId()}">${customer.value.getCustomerId()}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="submitButton loginHover" type="submit" name="action" value="CREATE ACCOUNT"></td>
			</tr>
		</table>
	</form>
	</div>
	


</body>
</html>