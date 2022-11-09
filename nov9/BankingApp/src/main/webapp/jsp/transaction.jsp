<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction details</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
<style type="text/css">
.show {
	visibility: hidden;
}
</style>
</head>
<body class="main">
	<h1>Transaction Details</h1>
	<h2>${message}</h2>
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<div class="sameline verticalAlign">
			<div>
				<label>Account number</label> <select name="accountNumber" required>
					<c:forEach var="account" items="${accountInfo }">
						<option value="${account.key }">${account.key }</option>
					</c:forEach>
				</select>
			</div>
			<div ><select name="days" required>
					<option value="">Select days</option>
					<option value="7">7</option>
					<option value="15">15</option>
				</select>
			</div>

			<button name="action" value="showusertransaction" class="showbutton"
				>Show</button>
		</div>
	</form>
	<div class="table ${visible }">
		<table>
			<tr>
				<th>Transaction Id</th>
				<th>From account</th>
				<th>To account</th>
				<th>Transaction amount</th>
				<th>Mode of Transaction</th>
				<th>Closing Balance</th>
				<th>Transaction Date</th>
			</tr>
			<c:forEach var="transaction" items="${transactionDetails }">
				<tr>
					<td>${transaction.getTransactionId() }</td>
					<td>${transaction.getSenderAccountNumber() }</td>
					<td>${transaction.getReceiverAccountNumber() }</td>
					<td>${transaction.getTransactionAmount() }</td>
					<td>${transaction.getTransactionType() }</td>
					<td>${transaction.getCurrentBalance() }</td>
					<td><jsp:useBean id="date" class="java.util.Date" /> <c:set
							target="${date}" property="time"
							value="${transaction.getTransactionDate() }" /> <fmt:formatDate
							value="${date }" pattern="dd-MM-yyyy HH:mm" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>