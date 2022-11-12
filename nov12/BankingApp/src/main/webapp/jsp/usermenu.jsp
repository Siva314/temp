<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body style="background: #2b6777">
	<div class="menu">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="get" target=customer>
			<div>
				<button name="action" value="customerhome">Home</button>
			</div>
			<div>
				<button name="action" value="viewalltransaction">
					Statement</button>
			</div>
			<div class="menuDrop">
				<button type="button">Transaction</button>
				<div class="menuDropField">
					<div>
						<button name="action" value="withdraw">Withdraw</button>
					</div>
					<div>
						<button name="action" value="deposit">Deposit</button>
					</div>
					<div>
						<button name="action" value="transfer">Transfer</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>