<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View profile</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h2 style="color: gold">${message}</h2>
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<div>
			<table>
				<tr>
					<td>Name :</td>
					<td><input type="text" value="${customer.getName() } "
						readonly></td>
				</tr><jsp:useBean id="date" class="java.util.Date" />
				<c:set target="${date}" property="time"
					value="${customer.getDateOfBirth() }" />

				<tr>
					<td>Date of birth :</td>
					<td><input
						value="<fmt:formatDate value="${date }" pattern="dd-MM-yyyy"/>"
						readonly></td>
				</tr>
				<tr>
					<td>Mobile :</td>
					<td><input type="tel" name="mobile"
						value=${customer.getMobile() } maxlength="10"></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><input type="email" name="email"
						value=${customer.getEmail() }></td>
				</tr>
				<tr>
					<td>Address :</td>
					<td><textarea name="address" readonly>${customer.getAddress() }</textarea></td>
				</tr>
			</table>
		</div>
		<div class="center">
			<button name="action" value="saveprofile">Save</button>
		</div>
	</form>
</body>
</html>