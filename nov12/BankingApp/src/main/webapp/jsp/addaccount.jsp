<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add account</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body>
	<h1>ADD ACCOUNT</h1>
	<div class="error" style="height:3vh;">
		<p>${ errormessage }</p>
	</div>
	<div class="card">
		<form action="<%=request.getContextPath()%>/ControllerServlet"
			method="post">
			<div>
				<div>
					<label>Customer Id<sup>*</sup></label><input type="number" name="userId"
						placeholder="${userId }" value="${userId } " required>
				</div>
				<div>
					<label>Opening balance<sup>*</sup></label><input name="balance" type="tel"
						min="500" value="500"required>
				</div>
				<div>
					<label>Account type<sup>*</sup></label><select name="accounttype" required>
						<option value="savings">Savings</option>
						<option value="current">Current</option>
					</select>

				</div>
				<div>
					<label>Branch Name<sup>*</sup></label><select name="branch" required>
						<option value="singampunari">Singampunari</option>
						<option value="ponnamaravathi">Ponnamaravathi</option>
						<option value="karaikudi">Karaikudi</option>
						<option value="sivagangai">Sivagangai</option>
					</select>
				</div>
			</div><br>
			<div>
				<button name="action" value="addnewaccount">Create</button>
			</div>
		</form>
	</div>
</body>
</html>