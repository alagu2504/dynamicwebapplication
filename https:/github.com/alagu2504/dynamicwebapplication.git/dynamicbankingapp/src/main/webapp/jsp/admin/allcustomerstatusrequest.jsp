<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>
body {
	background-color: rgba(245, 245, 245, 1);
	height: 100vh;
	width: 100%;
}

table {
    margin-top: 50px;
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
	height: 60px;
	font-size: 20px;
}

th {
	 text-align: center;
	border: 3px solid #FFFFFF;
	height: 45px;
	font-size: 20px;
    background-color: rgba(102, 153, 255, .8);
    position: sticky;
    top: 0;
}

tr:nth-child(odd) {
	background-color: white;
}

tr:nth-child(even) {
	background-color: rgba(102, 153, 255, .1);
}



tr:hover {
    background: linear-gradient(to bottom, rgba(102, 153, 255, .8), rgba(102, 255, 255, .5));
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

.response{
width:70%;
font-size: .8em;
border-radius:5px;
height:30px;
border-color: rgba(255, 114, 111, .8);
box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
background-color: rgba(102, 153, 255, .5);
}

.save{
width:80px;
color:#FFFFFF;
font-weight:bold;
border-radius:10px;
height:40px;
border-color: rgba(255, 114, 111, .8);
box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
background-color: rgba(102, 153, 255, .5);
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
<title>customer status request</title>
</head>
<body>
	<h2 >Customer Status Request</h2>
			<p class="error">${error }</p>
		<table>
			<tr>
				<th style="width:9%">Customer Id</th>
				<th style="width:50%;">Description</th>
				<th style="width:9%">Status</th>
				<th style="width:9%">Accepted</th>
				<th style="width:9%">Submit</th>
			</tr>
			<c:forEach items="${statusRequest }" var="statusRequest">
								<form action="bankingservlet" method="post" target="mainframe">
									<input type="hidden" name="customerId1"
					value="${statusRequest.value.getCustomerId()}">
				
				<tr>
					<td><input style="background: transparent;text-align:center;font-size:15px;border: 0;" type="text" name="customerId"
						value="${statusRequest.value.getCustomerId()} " readonly ></td>
					<td><input style="background: transparent;text-align:center;font-size:15px;border: 0;width:98%;height:60px;" type="text" name="describtion"
						value="${statusRequest.value.getDescription()}" readonly ></td>
					<td><input style="background: transparent;text-align:center;font-size:15px;border: 0;" type="text" name="status"
						value="${statusRequest.value.getStatus()}" readonly ></td>
					<td><select class="response" name="response">
							<option value="true">True</option>
							<option value="false">False</option>
					</select></td>
					<td><input class ="save" type="submit" name="submit" value="SUBMIT"></td>
						
				</tr>
			<input type="hidden" name="action"
			value="Update Customer Status Request">
					</form>
			</c:forEach>

		</table>
</body>
</html>