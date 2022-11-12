<!DOCTYPE html>
<html>
<style>

body{
background: linear-gradient(to bottom, rgba(102, 153, 255, .8), rgba(102, 255, 255, .8));
}
.subHeading{
			  font-size:3em;
			  background: -webkit-linear-gradient(#6699ff -3%, #66ffff 100%);
              -webkit-background-clip: text;
              -webkit-text-fill-color: transparent;
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

.textFeild{
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
  background-color:#FFFFFF;
  border-radius: 20px;
}

.login{
  padding: 3em;
  height: 320px;
  border-radius: 20px;
  border-left: 1px solid #F8F8FF;
  border-top: 1px solid #F8F8FF;
  box-shadow: 20px 20px 40px -6px rgba(0,0,0,0.2);
  text-align: center;
}

.request{
  background-color: transparent;
font-weight:bold;
color:rgba(255, 0, 0,.8);
border:0;
}

table{
  border-collapse:separate;
  border-spacing: 30px;
}

</style>
<head>
<link rel="stylesheet" href="css/CustomDesign.css">
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<div class="container">
<form class="login" action="bankingservlet" method="post" >
    <p id="error"> ${error}</p>
  <h2 class="subHeading" style="margin-top:5px;margin-bottom:2px;">User Login </h2>
  <table class="center">

  <tr >
        <td  Style="padding-right:36px;padding-top:23px;font-size:20px;"><label>User Id </label></td>
        <td >  <input  class ="textFeild " type="number" id="userId" name="userId" placeholder="Enter UserId" ></td>
  </tr>
  <tr>
        <td  Style="padding-right:50px;padding-top:23px;font-size:20px;"><label>Password    </label></td>
        <td >  <input  class ="textFeild " type="password" id="password" name="password" placeholder="Enter Password" ></td>
  </tr>
</table>
<div style="margin-top:10px;margin-left:50px;">
<input class="submitButton loginHover" type="submit" name ="action" value="Login">
</div>
<input class="request" style="width:150px;margin-left:90px;margin-top:20px;" type="submit" name="action" value="Forgot Password ?"/>
</form> 
</div>
</body>
</html>
