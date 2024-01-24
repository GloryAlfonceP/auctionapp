<!DOCTYPE html>
<html>
<head>
<title>Users</title>
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<style>
.body {
	background: #f5f5ed;
}

h1 {
	position: absolute;
	margin: 0;
	font-size: 20px;
	color: #fff;
	z-index: 2;
	line-height: 80px;
}

.testbox {
	display: flex;
	justify-content: center;
	align-items: center;
	height: inherit;
	padding: 20px;
	width: calc(100% - 50px);
}

form {
	width: 60%;
	height: 100%;
	padding: 20px;
	border-radius: 6px;
	background: #fff;
	box-shadow: 0 0 8px #cc7a00;
	padding: 20px;
}

.banner {
	position: relative;
	height: 50px;
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
}

.banner::after {
	content: "";
	background-color: rgba(0, 0, 0, 0.2);
	position: absolute;
	width: 100%;
	height: 100%;
}

input, select, textarea {
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

input {
	width: calc(100% - 5px);
	padding: 5px;
}

.item:hover p, .item:hover i, .question:hover p, .question label:hover,
	input:hover::placeholder {
	color: #cc7a00;
}

.item input:hover, .item select:hover, .item textarea:hover {
	border: 1px solid transparent;
	box-shadow: 0 0 3px 0 #cc7a00;
	color: #cc7a00;
}

.item {
	position: relative;
	margin: 10px 0;
}

.item span {
	color: red;
}

input[type="date"]::-webkit-inner-spin-button {
	display: none;
}

.item i, input[type="date"]::-webkit-calendar-picker-indicator {
	position: absolute;
	font-size: 11px;
	color: #cc7a00;
}

.item i {
	right: 1%;
	top: 30px;
	z-index: 1;
}

[type="date"]::-webkit-calendar-picker-indicator {
	right: 1%;
	z-index: 2;
	opacity: 0;
	cursor: pointer;
}

input[type=radio], input[type=checkbox] {
	display: none;
}

label.radio {
	position: relative;
	display: inline-block;
	margin: 5px 20px 15px 0;
	cursor: pointer;
}

.question span {
	margin-left: 30px;
}

.question-answer label {
	display: block;
}

label.radio:before {
	content: "";
	position: absolute;
	left: 0;
	width: 17px;
	height: 17px;
	border-radius: 50%;
	border: 2px solid #ccc;
}

input[type=radio]:checked+label:before, label.radio:hover:before {
	border: 2px solid #cc7a00;
}

label.radio:after {
	content: "";
	position: absolute;
	top: 6px;
	left: 5px;
	width: 8px;
	height: 4px;
	border: 3px solid #cc7a00;
	border-top: none;
	border-right: none;
	transform: rotate(-45deg);
	opacity: 0;
}

input[type=radio]:checked+label:after {
	opacity: 1;
}

.btn-block {
	margin-top: 10px;
	text-align: center;
}

button {
	width: 60px;
	padding: 5px;
	border: none;
	border-radius: 5px;
	background: #cc7a00;
	font-size: 8px;
	color: #fff;
	cursor: pointer;
	position: relative;
}

button:hover {
	background: #ff9800;
}

.Miniitem:hover p, .Miniitem:hover i, .question:hover p, .question label:hover,
	input:hover::placeholder {
	color: #cc7a00;
}

.Miniitem input:hover, .Miniitem select:hover, .Miniitem textarea:hover
	{
	border: 1px solid transparent;
	box-shadow: 0 0 3px 0 #cc7a00;
	color: #cc7a00;
}

.Miniitem {
	position: relative;
	margin: 10px 0;
}

.Miniitem span {
	color: red;
}

input[type="date"]::-webkit-inner-spin-button {
	display: none;
}

.Miniitem i, input[type="date"]::-webkit-calendar-picker-indicator {
	position: absolute;
	font-size: 6px;
	color: #cc7a00;
}

.Miniitem i {
	right: 1%;
	top: 30px;
	z-index: 1;
}

@media ( min-width : 568px) {
	.name-item, .city-item {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}
	.name-item input, .name-item div {
		width: calc(50% - 20px);
	}
	.name-item div input {
		width: 97%;
	}
	.name-item div label {
		display: block;
		padding-bottom: 5px;
	}
}

.collapsible {
	background-color: #cc7a00;
	color: white;
	cursor: pointer;
	padding: 6px;
	width: 10%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 10px;
}

.active, .collapsible:hover {
	background-color: #555;
}

.content {
	width: 90%;
	height: 50% padding: 0 18px;
	display: none;
	overflow: hidden;
	background-color: #f1f1f1;
}
</style>
</head>
<script type="text/javascript">
	
</script>

<body class="body">
	<div class="testbox">
		<form method="post" modelAttribute="loginForm" action="login">
			<div class="banner">
				<h2>Login</h2>
			</div>
			<div class="item">
				<label>User Id<span>*</span></label> <input id="usrId" type="text"
					name="usrId" required />
				<%
				request.getSession().setAttribute("usrId", request.getParameter("usrId"));
				%>
			</div>
			<div class="item">
				<label>pw<span>*</span></label> <input id="pw" type="text" name="pw"
					required />
			</div>
			<div class="item">
				<label>Role <span>*</span></label> <input class="btn-block"
					name="usrRole" type="submit" value="seller" /> <input
					class="btn-block" name="usrRole" type="submit" value="buyer" />
				<%
				request.getSession().setAttribute("usrRole", request.getParameter("usrRole"));
				%>
			</div>
		</form>
	</div>
</body>
</html>