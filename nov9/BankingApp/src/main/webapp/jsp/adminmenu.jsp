<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin menu</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body style="background:#2d545e">
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
		</form>
	</div>
</body>
</html>