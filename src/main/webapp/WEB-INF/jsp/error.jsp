<!DOCTYPE html>
<html>
<style>
.buttonClass {
	margin: auto;
	width: 50%;
	position: relative;
	left: 250px;
}
</style>
<head>
<title>Glorys Message Box</title>
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"
	rel="stylesheet">
</head>
<body>
	<form method="get" modelAttribute="msgForm" action="getAllMsgs">
		<div class=buttonClass>
			<h4 style="font-size: 30px;">
				<b>Invalid Request</b>
			</h4>
			<br>
			<button type="submit" formaction="getAllMsgs">See a Message</button>
			<button type="submit" formaction="/">Home</button>
		</div>
	</form>
</body>
</html>