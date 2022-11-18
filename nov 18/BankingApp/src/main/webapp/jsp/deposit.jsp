<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body class="main">
	<h1>Deposit</h1>
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div class="card">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
				<label>To Account<sup>*</sup></label> <select name="accountNumber">
					<c:forEach var="account" items="${accountInfo }">
						<option value="${account.key }">${account.key }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>Amount<sup>*</sup></label> <input type="number" name="amount" min="1" max="10000" required>
			</div>
			<br>
			<div>
				<button name="action" value="makedeposit">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>