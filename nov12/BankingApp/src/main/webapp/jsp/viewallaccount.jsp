<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>All Account Details</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h1>All Account Details</h1>
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div>
		<table style="margin-top:6%;">
			<tr>
				<th>Id</th>
				<th>Account Number</th>
				<th>Type</th>
				<th>Ifsc code</th>
				<th>Balance</th>
				<th>Status</th>
			</tr>
			<c:forEach var="accountList1" items="${allaccountdetails }">
				<c:forEach var="accountList" items="${accountList1.value }">
					<tr>
						<td>${accountList.value.getUserId() }</td>
						<td>${accountList.value.getAccountNumber() }</td>
						<td>${accountList.value.getAccountType() }</td>
						<td>${accountList.value.getIfscCode() }</td>
						<td>${accountList.value.getAccountBalance() }</td>
						<td>${accountList.value.getAccountStatus() }</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
</body>
</html>