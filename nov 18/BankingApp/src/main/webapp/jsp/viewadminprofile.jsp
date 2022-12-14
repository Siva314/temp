<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View profile</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body class="main">
	<h1>Profile</h1>
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div class="card">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
			<input type="hidden" name="oldMobile" value="${customer.getMobile() }">
			<input type="hidden" name="oldEmail" value="${customer.getEmail() }">
				<div>
					<label>Name </label><input type="text"
						value="${customer.getName() } " readonly>
				</div>
				<div>
					<jsp:useBean id="date" class="java.util.Date" />
					<c:set target="${date}" property="time"
						value="${customer.getDateOfBirth() }" />
					<label>Date of Birth </label><input
						value="<fmt:formatDate value="${date }" pattern="dd-MM-yyyy"/>"
						readonly>
				</div>
				<div>
					<label>Mobile</label><input type="tel" name="mobile"
						value="${customer.getMobile() }" pattern="^[6-9][0-9]{9}" maxlength="10">
				</div>
				<div>
					<label>Email </label><input type="email" name="email" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
						value="${customer.getEmail() }">
				</div>
				<div>
					<label>Address </label>
					<textarea name="address" readonly>${customer.getAddress() }</textarea>
				</div>
				<br>
			</div>
			<div>
				<button name="action" value="saveprofile">Save</button>
			</div>
		</form>
	</div>
</body>
</html>