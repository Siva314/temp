<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add account</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h1>ADD ACCOUNT</h1>
	<h2>${message}</h2>
	<div class="align">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
				<div>
					<label>Customer Id</label><input type="number" name="userId"
						placeholder="${userId }" value="${userId } " required>
				</div>
				<div>
					<label>Opening balance</label><input name="balance" type="number"
						min="0" required>
				</div>
				<div>
					<label>Account type</label><select name="accounttype" required>
						<option value="savings">Savings</option>
						<option value="current">Current</option>
					</select>

				</div>
				<div>
					<label>Ifsc code</label><input name="ifsccode" type="text" required>
				</div>
				<div>
					<label>Branch Name</label><select name="branch" required>
						<option value="singampunari">Singampunari</option>
						<option value="ponnamaravathi">Ponnamaravathi</option>
						<option value="karaikudi">Karaikudi</option>
						<option value="sivagangai">Sivagangai</option>
					</select>
				</div>
			</div><br>
			<div class="submitbutton">
				<button name="action" value="addnewaccount">Add</button>
			</div>
		</form>
	</div>
</body>
</html>