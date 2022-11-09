<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users Details</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css"></link>
</head>
<body>
	
	<h2 style= color:gold>${message}</h2>
	<div>
		<table>
			<tr>
				<th>User id</th>
				<th>Name</th>
				<th>Mobile</th>
				<th>Date Of Birth</th>
				<th>Email</th>
				<th>Address</th>
				<th>User Type</th>
			</tr>
			<c:forEach var="users" items="${allUserDetails}">
				<tr>
					<td>${users.value.getUserId() }</td>
					<td>${users.value.getName() }</td>
					<td>${users.value.getMobile() }</td>
					<td><jsp:useBean id="date" class="java.util.Date"/>
					<c:set target="${date}" property="time" value="${users.value.getDateOfBirth() }"/>
					<fmt:formatDate value="${date }" pattern="dd-MM-yyyy"/></td>
					<td>${users.value.getEmail() }</td>
					<td>${users.value.getAddress() }</td>
					<td>${users.value.getType() }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>