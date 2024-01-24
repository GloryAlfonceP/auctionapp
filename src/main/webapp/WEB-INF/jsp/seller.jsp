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
	font-size: 40px;
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
}

form {
	width: 40%;
	padding: 20px;
	border-radius: 6px;
	background: #fff;
	box-shadow: 0 0 8px #cc7a00;
}

.banner {
	position: relative;
	height: 80px;
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
	width: calc(100% - 10px);
	padding: 5px;
}

input[type="date"] {
	padding: 4px 5px;
}

textarea {
	width: calc(100% - 12px);
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
	font-size: 20px;
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
	width: 150px;
	padding: 10px;
	border: none;
	border-radius: 5px;
	background: #cc7a00;
	font-size: 16px;
	color: #fff;
	cursor: pointer;
}

button:hover {
	background: #ff9800;
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
</style>
</head>
<body class="body">
	<div class="testbox">
		<form method="post" modelAttribute="sellerForm" action="postItm">
			<div class="banner">
				<h2>Item to sell</h2>
			</div>
			<div class="item">
				<label>Item to sell<span>*</span></label> <input id="itmId"
					type="text" name="itmId" required />
			</div>
			<div class="item">
				<label>Item Name<span>*</span></label> <input id="itmName"
					type="text" name="itmName" required />
			</div>
			<div class="item">
				<label>Min price<span>*</span></label> <input id="minPrice"
					type="text" name="minPrice" required />
			</div>
			<div class="btn-block">
				<button type="submit">SUBMIT</button>
			</div>
		</form>
	</div>
	<form method="get" action="seeWinner">
		<button type="submit" formaction="/seeWinner">Auction Winner</button>
	</form>
	<form method="post" action="/closeBid">
		<div class="item">
			<label>Item to close<span>*</span></label> <input id="itmId"
				type="text" name="itmId" required />
		</div>
		<button type="submit" onclick="myFunction()">Close Bid</button>
	</form>
	<form method="get"">
		<button type="submit" formaction="/">Home</button>
	</form>

</body>
</html>