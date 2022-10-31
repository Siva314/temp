<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin menu</title>
<style type="text/css">
button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  width:100%;
}
</style>
</head>
<body>
	<h2>menu</h2>
	<div>
	<form action="../ControllerServlet" method="get" target="admin">
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
			<button name="action" value="viewalltransaction">View All Transactions</button>
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