<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h1>Transfer To Money</h1>
	<h2>${message}</h2>
	<div class="align">
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<div>
		<label>Account number</label> <select name="accountNumber" >
			<c:forEach var="account" items="${accountInfo }">
				<option value="${account.key }">${account.key }</option>
			</c:forEach>
		</select> 
		<label>Receiver Account</label><input type="number" name="receiver" required>
		<label>Enter the Amount</label> <input type="number" name="amount" min="0" required>
		</div><br>
		<div class="submitbutton">
			<button name="action" value="maketransfer">submit</button>
		</div>
	</form>
	</div>
</body>
</html>