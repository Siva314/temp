<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>change password</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body class="main">
	<h1>Change Password</h1>
	<h2>${message}</h2>
	<div class="card">
		<form action="<%=request.getContextPath() %>/ControllerServlet"
			method="post" target="${target }">
			<div>
				<div>
					<label>Old password</label> <input name="oldpassword"
						type="password" required>
				</div>
				<div>
					<label>New password</label> <input name="newpassword"
						type="password" required>
				</div>
				<div>
					<label style="padding-right:5px;">Re-enter password</label><input name="reentry"
						type="password" required>
				</div>
			</div><br>
			<div>
				<button name="action" value="savepassword">Save</button>
			</div>
		</form>
	</div>
</body>
</html>