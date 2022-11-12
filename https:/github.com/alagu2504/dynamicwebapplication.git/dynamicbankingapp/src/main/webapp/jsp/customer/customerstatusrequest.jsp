<!DOCTYPE html>
<%@ page
	import="users.Admin,bankingapplicationPojos.Account,java.util.Map"%>
<html>
<style>

body {
	background:white;
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
  top: 50%;
  left: 50%;
  width:40%;
  height:70%;
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
	text-align:center;
}

td{
height:50%;
}

table{
  border-collapse:separate;
  border-spacing: 30px;
}
</style>
<head>
<meta charset="UTF-8">
<title>change customer status</title>
</head>
<body>
	<div class="container">
		<h2>Request to change customer status</h2>
		<h4 style="text-align:center;">${error}</h4>

		<form action="bankingservlet" method="post">
			<table class="center">
				<tr>
					<td style="text-align:left;"><p>Customer Id 
						<p></td>
					<td><input class="textFeild" type="number" id="userId" name="userId"
						placeholder="Enter UserId" required></td>
				</tr>
				<tr>
					<td style="text-align:left;"><p>Password
						<p></td>
					<td><input class="textFeild" type="password" id="password" name="password"
						placeholder="Enter Password" required></td>
				</tr>
				<tr>
				<td style="text-align:left;"><label>Description </label>
				</td>
				<td style="padding-left:8px;"><textarea style="height: 90px; width: 50%; border-radius: 10px;" id="description" name="description" rows="4" cols="50"
				required></textarea></td>
				</tr>
			</table>
	
			

			<input type="hidden" name="action" value="Change Customer Status">

			<div>
				<input class="submitButton submitHover" style="margin-left:43%;" type="submit" value="SUBMIT">
			</div>

		</form>
	</div>
</body>
</html>