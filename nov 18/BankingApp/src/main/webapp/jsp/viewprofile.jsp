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
				<label>Mobile <sup>*</sup></label><input type="tel" name="mobile"
					value="${customer.getMobile() }" maxlength="10">
			</div>
			<div>
				<label>Email <sup>*</sup></label><input type="email" name="email"
					value="${customer.getEmail() }">
			</div>
			<div>
				<label>Address </label>
				<textarea name="address" readonly>${customer.getAddress() }</textarea>
			</div>
			<div>
				<label>Pancard </label><input type="text"
					value="${customer.getPancard() }" readonly>
			</div>
			<div>
				<label>Aadhaar </label><input type="text"
					value="${customer.getAadhaar() }" readonly>
			</div>
			<br>
			<div>
				<button name="action" value="saveprofile">Save</button>
			</div>
		</form>
	</div>
</body>
</html>