<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="users.Admin,java.util.Map,java.util.Iterator,java.util.HashMap,java.util.TreeMap,bankingapplicationPojos.Statements"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
}

table {
    margin-top: 50px;
    margin-bottom:30%;
	border-radius: 10px;
	margin-left:20px;
	border-collapse: collapse;
	border: 3px solid #FFFFFF;
	width:95%;
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
    background: rgba(102, 153, 255, 1);
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


h2{           
              text-align:left;
              margin-top:15px;
              margin-left:10px;
			  font-size:2em;
			  background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
              -webkit-background-clip: text;
              -webkit-text-fill-color: transparent;
}

.accNo{
width:25%;
font-weight:bold;
font-size: 1.2em;
border-radius:5px;
height:35px;
border-color: rgba(255, 114, 111, .8);
box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
}

.save{
margin-left:50px;
width:80px;
color:#FFFFFF;
font-weight:bold;
border-radius:10px;
height:40px;
border-color: rgba(255, 114, 111, .8);
box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
background-color: rgba(102, 153, 255, .5);
}
</style>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2 >All Transaction Details</h2>
	<form action="bankingservlet" method="post" target="mainframe">
		<label style="font-size:1.5em">Account Number :</label> <input class= "accNo" type="number"
			name="accountNumber" /> <input class= "save" type="submit" name="submit" /> <input
			type="hidden" name="action" value="SPECIFIC STAEMENT" />
	</form>
	<table >
		<tr >
			<th>Transaction Id</th>
			<th>Customer Id</th>
			<th>Sender Account Number</th>
			<th>Receiver Account Number</th>
			<th>Transfer Amount</th>
			<th>Transaction Type</th>
			<th>Transaction Time</th>
			<th>Mode Of Transaction</th>
		</tr>
		<c:forEach items="${allStatements}" var="statements">
			<tr>
				<td>${statements.value.getTransactionId()}</td>
				<td>${statements.value.getCustomerId()}</td>
				<td>${statements.value.getSenderAccount()}</td>
				<td>${statements.value.getReceiverAccount()}</td>
				<td>${statements.value.getTransferAmount()}</td>
				<td>${statements.value.getTransactionType()}</td>
				<td>${statements.value.getTime()}</td>
				<td>${statements.value.getModeOfTransaction()}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>