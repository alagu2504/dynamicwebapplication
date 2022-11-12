<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>

body{
background: linear-gradient(to bottom, rgba(102, 153, 255, .8), rgba(102, 255, 255, .8));
height: 100vh;
}
.subHeading{
              text-align:center;
			  font-size:2em;
			  background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
              -webkit-background-clip: text;
              -webkit-text-fill-color: transparent;
              margin-top:30px;
}

.submitButton{
      color:#FFFFFF;
      font-weight:bold;
	  background-color:rgb(0,0,255,1);
	  height:30px;
	  border-radius: 8px;
	  width:80px;
	  box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
}

.loginHover:hover{
	  background-color:rgb(0,0,255,.8);
}

input{
          border-top:0px;
          border-left: 0px;
          border-right: 0px;
		  border-radius: 5px;
	      height:30px;
	      text-align:center;
	      border-color: rgba(102, 255, 255, .8);
}

.container {
  position: absolute;
  transform: translate(-50%,-50%);
  top: 50%;
  left: 50%;
  width:35%;
  height:50%;
  background-color:#FFFFFF;
  border-radius: 20px;
  box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
}


.center{
	margin-left:auto;
	margin-right:auto;
	text-align:center;
}

table{
  border-collapse:separate;
  border-spacing: 30px;
  margin-top:5px;
}
td{
text-align:left;
}
</style>
<head>
<meta charset="UTF-8">
<title>Forget Password</title>
</head>
<body>
<div class="container">
<h2  class= "subHeading" style="margin-bottom: -10px;" >Forget Password</h2>

 <form action="bankingservlet" method="post" target="mainframe">
 <h4>${error}</h4>
 
 <table class="center">
 
 <tr>
 <td><label style="padding-top:30px;">User Id </label></td>
 <td>    <input class= "textFeild" type="number" name="userId" placeholder="Enter UserId" required />
 </td>
 </tr>
 
 <tr>
 <td >    <label>Mobile Number </label>
 </td>
 <td>    <input  type="number" name="mobileNumber" placeholder="Enter the mobile Number" required />
 </td>
 </tr>
 
 <tr>
 <td>   <label for="newpassword">New Password</label>
 </td>
 <td>    <input type="password" id="newpassword" name="newpassword" placeholder="Newpassword" required>
 </td>
 </tr>
 
 <tr>
 <td>   <label for="confirm_password">Confirm Password</label>
 </td>
 <td>
     <input type="password" id="confirmPassword" name="confirmPassword" placeholder="confirm password" required>
 </td>
 </tr>
 
 </table>


  <input class="submitButton submitHover " style="margin-top:5px;margin-left: 48%;width: 30%;" type="submit" name="submit" value="CHANGE PASSOWRD"/>
  <input type="hidden" name ="action" value="FORGET PASSWORD">
  
</form>
</div>
</body>
</html>