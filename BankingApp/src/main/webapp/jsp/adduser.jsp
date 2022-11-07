<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add user</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"></link>
</head>
<script type="text/javascript">
	function showlabel(that){
		if(that.value=="customer"){
			document.getElementById("show").style.display="block";
		}
		else{
			document.getElementById("show").style.display="none";
		}
	}
</script>
<body>
	<h1>
	ADD NEW USER
	</h1>
	<div class="align">
	<h2>${message}</h2>
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<div>
			<div>
				<label>Name</label><input name="username" type="text" required>
			</div>
			<div>
				<label>Email</label><input name="email" type="email" required>
			</div>
			<div>
				<label> Date of birth</label><input name="dateofbirth" type="date" required>
			</div>
			<div>
				<label>Mobile</label><input name="mobile" type="tel" pattern="[0-9]{10}" maxlength="10" required>
			</div>
			<div>
				<label>Address</label><input name="address" type="text" required>
			</div>
			<div class="selectmenu">
				<label>User Type</label><select name="type" onchange="showlabel(this)" required>
					<option value="admin">Admin</option>
					<option value="customer">Customer</option>
				</select>
			</div>
			<div id="show" style="display:none;">
			<div><label>Pancard</label><input name="pancard" type="text" maxlength="10" required></div>
			<div><label>Aadhaar</label><input name="aadhaar" type="tel" pattern="[0-9]{12}" maxlength="16"></div>
			</div>
		</div><br>
		<div class="submitbutton">
		<button name="action" value="addnewuser">Add</button>
			</div>
	</form>
	</div>
</body>
</html>