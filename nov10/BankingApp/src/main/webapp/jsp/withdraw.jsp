<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body class="main">
	<h1>Withdraw</h1>
	<h2>${message}</h2>
	<div class="card">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
				<label>Form Account</label> <select name="accountNumber">
					<c:forEach var="account" items="${accountInfo }">
						<option value="${account.key }">${account.key }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>Amount</label> <input type="text" name="amount" required>
			</div>
			<br>
			<div>
				<button name="action" value="makewithdraw">submit</button>
			</div>
		</form>
	</div>
</body>
</html>
