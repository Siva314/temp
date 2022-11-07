<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
</head>
<body>
	<h1 style="margin-top:17%">Central Bank of India</h1>
	<div class=login>
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="get">
			<div>
				<label style="text-align: center">User id</label> <input
					type="number" name="username" required>
			</div>
			<div>
				<label style="text-align: center">Password</label> <input
					type="password" name="password" required>
			</div>
			<div>
				<p style="color: gold">${ errormessage }</p>
				<button name="action" value="login">Login</button>
			</div>
		</form>
	</div>
</body>
</html>