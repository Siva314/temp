<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add account</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h1>${message}</h1>
	<form action="<%=request.getContextPath()%>/ControllerServlet"
		method="post">
		<div>
			<div>
				<label>User id</label><input type="number"  name="userId" placeholder="${userId }" value="${userId } " >
				<button name="action" value="checkprofile">check</button>
			</div>
			<div>
				<label>Pancard</label><input name="pancard" value="${customer.getPancard() }"type="text">
			</div>
			<div>
				<label>Aadhaar</label><input name="aadhaar" value="${customer.getAadhaar() }" type="number">
			</div>
			<div>
				<label>Opening balance</label><input name="balance" type="number" min="0">
			</div>
			<div>
				<label>Account type</label><select name="accounttype">
				<option value="savings">Savings</option>
				<option value="current">Current</option>
				</select>
				
			</div>
			<div>
				<label>Ifsc code</label><input name="ifsccode" type="text">
			</div>
			<div>
				<label>Branch Name</label><input name="branch" type="text">
			</div>
		</div>
		<div>
			<button name="action" value="addnewaccount">Add</button>
		</div>
	</form>
</body>
</html>