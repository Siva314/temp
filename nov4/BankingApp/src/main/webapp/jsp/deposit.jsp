<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<h2 style= color:gold>${message}</h2>
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<label>account number</label> <select name="accountNumber" >
			<c:forEach var="account" items="${accountInfo }">
				<option value="${account.key }">${account.key }</option>
			</c:forEach>
		</select> <label>Enter the Amount</label> <input type="text" name="amount">
		<div>
			<button name="action" value="makedeposit">submit</button>
		</div>
	</form>
</body>
</html>