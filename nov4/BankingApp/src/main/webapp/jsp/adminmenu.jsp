<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin menu</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<h2>menu</h2>
	<div class="menu">
	<form action="<%= request.getContextPath() %>/ControllerServlet" method="get" target="admin" class=menu>
		<div>
			<button name="action" value="adminhome">Home</button>
		</div>
		<div>
			<button name="action" value="viewalluser">View All User</button>
		</div>
		<div>
			<button name="action" value="viewallaccount">View All Account</button>
		</div>
		<div>
			<button name="action" value="adduser">Add User</button>
		</div>
		<div>
			<button name="action" value="addaccount">Add Account</button>
		</div>
		<div>
			<button name="action" value="viewtransaction">View All Transactions</button>
		</div>
		<div>
			<button name="action" value="viewadminprofile">View profile</button>
		</div>
		<div>
			<button name="action" value="changepassword">Change Password</button>
		</div>
		</form>
		<form action="../ControllerServlet" method="get" target="_parent">
		<div>
			<button name="action" value="logout">Logout</button>
		</div>
		</form>
	</div>
</body>
</html>