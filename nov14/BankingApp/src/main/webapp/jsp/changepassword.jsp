<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>change password</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body class="main">
	<h1>Change Password</h1>
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div class="card">
		<form action="<%=request.getContextPath() %>/ControllerServlet"
			method="post" target="${target }">
			<div>
				<div>
					<label>Old password<sup>*</sup></label> <input name="oldpassword"
						type="password" required>
				</div>
				<div>
					<label>New password<sup>*</sup></label> <input name="newpassword"
						type="password" required>
				</div>
				<div>
					<label style="padding-right:5px;">Re-enter<sup>*</sup></label><input name="reentry"
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