<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
</head>
<body style="display: grid;">
	<div class="logout">
		<header>
			<form action="<%=request.getContextPath()%>/ControllerServlet"
				method="get" target="_parent">
				<div>
					<button name="action" value="logout">Logout</button>
					<h1>Central Bank Of India</h1>
				</div>
			</form>
		</header>
	</div>
	<div style="display: flex;">
		<iframe style="height: 950px; width: 15%;" src="jsp/adminmenu.jsp"></iframe>
		<iframe style="height: 950px; width: 85%;" src="jsp/adminhome.jsp"
			name="admin"></iframe>
	</div>
</body>
</html>