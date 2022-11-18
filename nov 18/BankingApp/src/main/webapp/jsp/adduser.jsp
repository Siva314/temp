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
			const inputPan=document.getElementById('pan');
			const inputAadhaar=document.getElementById('aadhaar');
			inputPan.required=true;
			inputAadhaar.required=true;
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
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div class="card">
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<div>
			<div>
				<label>Name<sup>*</sup></label><input name="username" type="text" required>
			</div>
			<div>
				<label>Email Id<sup>*</sup></label><input name="email" type="email" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
			</div>
			<div>
				<label> Date of Birth<sup>*</sup></label><input name="dateofbirth" type="date" required>
			</div>
			<div>
				<label>Mobile<sup>*</sup></label><input name="mobile" type="tel" pattern="^[6-9][0-9]{9}" maxlength="10" required>
			</div>
			<div>
				<label>Address<sup>*</sup></label><input name="address" type="text" required>
			</div>
			<div class="selectmenu">
				<label>User Type<sup>*</sup></label><select name="type" onchange="showlabel(this)" required>
					<option value="admin">Admin</option>
					<option value="customer">Customer</option>
				</select>
			</div>
			<div id="show" style="display:none;">a
			<div><label>Pancard<sup>*</sup></label><input id="pan" name="pancard" type="text" maxlength="10"></div>
			<div><label>Aadhaar<sup>*</sup></label><input id="aadhaar" name="aadhaar" type="tel" pattern="^[1-9][0-9]{11}" maxlength="12"></div>
			</div>
		</div><br>
		<div>
		<button name="action" value="addnewuser">Add</button>
			</div>
	</form>
	</div>
</body>
</html>