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
<body>
	<h1>TRANSACTION DETAILS</h1>
	<p style="text-align:center;font-size:30px;">${message}</p>
	<div class="transaction">
		<div>
			<form action="<%=request.getContextPath()%>/ControllerServlet"
				method="post">
				<label>Id </label><input name="id" type="number"
					value="${cid}" min="1" required>
				<button name="action" value="showaccount">Show</button>
			</form>
		</div>
		<div class="right ${search}">
			<form action="<%=request.getContextPath()%>/ControllerServlet"
				method="post">
				<input type="hidden" name="id" value="${cid}"> <input
					type="hidden" name="account" value="${accountInfo}"> <label>Account</label> <select name="accountNumber" required>
					<option value="">--account--</option>
					<c:forEach var="account" items="${accountInfo }">
						<option value="${account.key }">${account.key }</option>
					</c:forEach>
				</select>
				<button name="action" value="showusertransaction">Show</button>
			</form>
		</div>
	</div>
	<div class="table ${visible}">
		<table>
			<tr>
				<th>Id</th>
				<th>From</th>
				<th>To</th>
				<th>Amount</th>
				<th>Mode</th>
				<th>Balance</th>
				<th>Date</th>
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