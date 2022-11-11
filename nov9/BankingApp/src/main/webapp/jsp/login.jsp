<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
</head>
<body class="main">
	<h1 style="margin-top: 15%;margin-left:38%;">Central Bank of India</h1>
	<div class=login>
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="get">
			<div>
				<label>User id</label> <input type="number" name="username" required>
			</div>
			<div>
				<label>Password</label> <input type="password" name="password"
					required>
			</div>
			<div>
				<button name="action" value="login">Login</button>
			</div>
			<p>${ errormessage }</p>
		</form>
	</div>
</body>
</html>