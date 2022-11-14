<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Central Bank of India</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	border: none;
}
</style>
</head>
<body style="display: grid;">
	<div class="logout">
		<header>
			<div>
				<h1>Central Bank Of India</h1>
			</div>
			<div class="headerMenu">
				<button class="dropMenu">
					<i class="fa fa-bars fa-3x"></i>
				</button>
				<div class="dropField">
					<form action="<%=request.getContextPath()%>/ControllerServlet"
						method="get" target=customer>
						<div>
							<button name="action" value="viewprofile">
								<i class="fa fa-fw fa-user"></i>Profile
							</button>
						</div>
						<div>
							<button name="action" value="changepassword">
								<i class="fa fa-fw fa-key"></i>Change Password
							</button>
						</div>
					</form>
					<form action="<%=request.getContextPath()%>/ControllerServlet"
						method="get" target="_parent">
						<div>
							<button class="logoutbutton" name="action" value="logout">
								<i class="fa fa-fw fa-sign-out"></i>Logout
							</button>
						</div>
					</form>
				</div>
			</div>
		</header>
	</div>
	<div style="display: flex;">
		<iframe src="jsp/usermenu.jsp" width=15%;></iframe>
		<iframe src="jsp/customerhome.jsp" width=85%; name=customer></iframe>
	</div>
	<%response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("pragma", "no-cache");
	response.setHeader("Expires","0");
	
	if(session.getAttribute("id").equals(null)){
		response.sendRedirect("jsp/login.jsp");
	}
	
	%>
</body>
</html>