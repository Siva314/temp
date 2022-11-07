<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h1>Withdraw</h1>
	<h2>${message}</h2>
	<div class="align">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
				<label>Account Number</label> <select name="accountNumber">
					<c:forEach var="account" items="${accountInfo }">
						<option value="${account.key }">${account.key }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>Enter the Amount</label> <input type="text" name="amount" required>
			</div>
			<br>
			<div class="submitbutton">
				<button name="action" value="makewithdraw">submit</button>
			</div>
		</form>
	</div>
</body>
</html>
