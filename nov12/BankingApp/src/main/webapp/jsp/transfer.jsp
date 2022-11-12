<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body class="main">
	<h1>Money Transfer</h1>
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div class="card">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
				<label>From Account<sup>*</sup></label> <select name="accountNumber">
					<c:forEach var="account" items="${accountInfo }">
						<option value="${account.key }">${account.key }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label style="padding-right:5px;">To Account<sup>*</sup></label><input type="number" name="receiver"
					required>
			</div>
			<div>
				<label>Amount<sup>*</sup></label> <input type="number" name="amount"
					min="1" required>
			</div>
			<br>
			<div>
				<button name="action" value="maketransfer">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>