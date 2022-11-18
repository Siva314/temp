<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
</head>
<body class="main" style="margin-top: 14%;">
	<h1 class="leftalign">Central Bank of India</h1>
	<div class="error" style="height:5vh;">
		<p>${ errormessage }</p>
	</div>
	<div class=login>
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
			
				<label>User id</label> <input type="number" name="username" autocomplete="off" required>
			</div>
			<div>
				<label>Password</label> <input type="password" name="password"
					required>
			</div>
			<div>
				<button name="action" value="login">Login</button>
			</div>
		</form>
	</div>
</body>
</html>