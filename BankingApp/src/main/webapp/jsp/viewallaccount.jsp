<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>All Account Details</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h2 style= color:gold>${message}</h2>
	<div>
		<table>
			<tr>
				<th>User id</th>
				<th>Account Number</th>
				<th>Account Type</th>
				<th>Ifsc code</th>
				<th>Account Balance</th>
				<th>Account Status</th>
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