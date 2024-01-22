<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<title>Inbox</title>
</head>
<style>
#myInput {
	width: 30%; /* Full-width */
	font-size: 10px; /* Increase font-size */
	padding: 5px; /* Add padding */
	border: 2px solid #black; /* Add a grey border */
	float: right;
}

#myTable {
	border-collapse: collapse; /* Collapse borders */
	width: 50%; /* Full-width */
	border: 3px solid #808080; /* Add a grey border */
	font-size: 18px; /* Increase font-size */
}

#myTable th {
	text-align: center; /* Left-align text */
	padding: 9px; /* Add padding */
	border: 2px solid #808080;
}

#myTable td {
	text-align: center; /* Left-align text */
	padding: 4px; /* Add padding */
	border: 1px solid #808080;
}

#myTable tr {
	/* Add a bottom border to all table rows */
	border-bottom: 1px solid #808080;
}

#myTable tr.header, #myTable tr:hover {
	/* Add a grey background color to the table header and on hover */
	background-color: #f1f1f1;
}

#body {
	width: 50%; /* Full-width */
	float: right align-items: right;
	background-color: powderblue;
}

.buttonClass {
	margin: auto;
	width: 50%;
	position: relative;
}
</style>
<body>
	<div id="body">
		<input type="text" id="myInput" onkeyup="myFunction1()"
			placeholder="Search ">
	</div>
</body>
<br>
<br>
<table id="myTable">
	<tr class="header">
		<th style="width: 50%;" onclick="sortTable(0)">Name</th>
		<th style="width: 60%;" onclick="sortTable(1)">Message</th>
	</tr>
	<c:forEach items="${msgs}" var="msgVar">
		<tr>
			<td>${msgVar.name}</td>
			<td>${msgVar.msg}</td>
		</tr>
	</c:forEach>
</table>
<form method="get" modelAttribute="msgForm" action="getAllMsgs">
	<div class=buttonClass>
		<br>
		<button type="submit" formaction="getAllMsgs">See a Message</button>
		<button type="submit" formaction="/">Home</button>
	</div>
</form>
<script>
	function myFunction1() {
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			if (td) {
				txtValue = td.textContent || td.innerText;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}

	function sortTable(n) {
		var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		table = document.getElementById("myTable");
		switching = true;
		//Set the sorting direction to ascending:
		dir = "asc";
		/*Make a loop that will continue until
		no switching has been done:*/
		while (switching) {
			//start by saying: no switching is done:
			switching = false;
			rows = table.rows;
			/*Loop through all table rows (except the
			first, which contains table headers):*/
			for (i = 1; i < (rows.length - 1); i++) {
				//start by saying there should be no switching:
				shouldSwitch = false;
				/*Get the two elements you want to compare,
				one from current row and one from the next:*/
				x = rows[i].getElementsByTagName("TD")[n];
				y = rows[i + 1].getElementsByTagName("TD")[n];
				/*check if the two rows should switch place,
				based on the direction, asc or desc:*/
				if (dir == "asc") {
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						//if so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (dir == "desc") {
					if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
						//if so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				}
			}
			if (shouldSwitch) {
				/*If a switch has been marked, make the switch
				and mark that a switch has been done:*/
				rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
				switching = true;
				//Each time a switch is done, increase this count by 1:
				switchcount++;
			} else {
				/*If no switching has been done AND the direction is "asc",
				set the direction to "desc" and run the while loop again.*/
				if (switchcount == 0 && dir == "asc") {
					dir = "desc";
					switching = true;
				}
			}
		}
	}
</script>
</html>