<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<h2>MENU</h2>
	<div class="menu">
	<form action="../ControllerServlet" method="get" target=siva>
		<div>
			<button name="action" value="customerhome">Home</button>
		</div>
		<div>
			<button name="action" value="viewprofile">View Profile</button>
		</div>
		<div>
			<button name="action" value="withdraw">Withdraw</button>
		</div>
		<div>
			<button name="action" value="deposit">deposit</button>
		</div>
		<div>
			<button name="action" value="transfer">Transfer</button>
		</div>
		<div>
			<button name="action" value="viewalltransaction">TransactionDetails</button>
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