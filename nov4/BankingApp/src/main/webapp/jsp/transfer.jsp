<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer</title>
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
		</select> 
		<label>Receiver Account Number</label><input type="number" name="receiver" required>
		<label>Enter the Amount</label> <input type="number" name="amount" min="0" required>
		<div>
			<button name="action" value="maketransfer">submit</button>
		</div>
	</form>
</body>
</html>