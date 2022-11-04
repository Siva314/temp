<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add user</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h2 style="color: gold">${message}</h2>
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
			<div>
				<label>type</label><select name="type" required>
					<option value="admin">Admin</option>
					<option value="customer">User</option>
				</select>
			</div>
		</div>
		<div>
		<button style="justify-content: center" name="action"
			value="addnewuser">add</button>
			</div>
	</form>
</body>
</html>