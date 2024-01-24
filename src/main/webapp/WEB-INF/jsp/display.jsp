<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Inbox</title>
<style>
body {
	font-family: Arial, sans-serif;
	height: 95vh;
	margin: 5;
	background-color: #ffff0;
	border-style: double;
}

.input-container {
	margin: auto;
	width: 50%;
	height: 60%;
	border: 3px solid #808080; /* Add a grey border */
	font-size: 18px; /* Increase font-size */
	background-color: #f2e5dc;
	align-items: center;
	justify-content: center;
	text-align: center;
}

.buttonClass {
	margin: auto;
	width: 50%;
	position: relative;
	left: 250px;
}
</style>
</head>
<h1 class=buttonClass>
	<b> </b>
</h1>
<br>
<body>
	<div class="input-container">
		<h4>Winnner</h4>
		<table id="myTable"">
			<tr class="header">
				<th style="width: 30%;">Item</th>
				<th style="width: 30%;">Winner</th>
				<th style="width: 30%;">Winning price</th>
				<th style="width: 100%;">Winner</th>
			</tr>
			<c:forEach items="${itmLst}" var="itmVar">
				<tr>
					<td>${bidItm}</td>
					<td>${bidId}</td>
					<td>${bidPrice}</td>
					<td>${bidUsr}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
</body>
<br>
<form method="get">
	<div class=buttonClass>
		<br>
		<button type="submit" formaction="/">Home</button>
	</div>
</form>
</html>