<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer frame</title>
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
		<iframe src="jsp/usermenu.jsp" width=15%; height=850px;></iframe>
		<iframe src="jsp/customerhome.jsp" width=85%; height=850px; name=siva></iframe>
	</div>

</body>
</html>