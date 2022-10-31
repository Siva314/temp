<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"></link>
</head>
<body>
<h1>Chelliyampatty InterNational Bank</h1>
	<div class=login>
	<form action="../ControllerServlet" method="get">
		<div>
			<label>User id</label> <input type="number" name="username">
		</div>
		<div>
			<label>password</label> <input type="password" name="password">
		</div>
		<div>
			<button name="action" value="admin">Admin login</button>
			<button name="action" value="customer">customer login</button>
		</div>
		</form>
	</div>
</body>
</html>