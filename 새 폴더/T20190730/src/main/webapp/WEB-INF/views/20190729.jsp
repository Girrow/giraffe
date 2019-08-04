<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>문제</title>
	<link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.png">
	<style>
		.modal-header,
		h4,
		.close {
			background-color: #5cb85c;
			color: white !important;
			text-align: center;
			font-size: 30px;
		}

		.modal-footer {
			background-color: #f9f9f9;
		}

		/* Switch 1 Specific Style End */

		@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

		body {
			font-family: 'Open Sans', sans-serif;
			font-weight: 300;
			line-height: 1.42em;
			color: #A7A1AE !important;
			background-color: #1F2739;
		}

		h1 {
			font-size: 3em;
			font-weight: 300;
			line-height: 1em;
			text-align: center;
			color: #4DC3FA;
		}

		h2 {
			font-size: 1em;
			font-weight: 300;
			text-align: center;
			display: block;
			line-height: 1em;
			padding-bottom: 2em;
			color: #FB667A;
		}

		h2 a {
			font-weight: 700;
			text-transform: uppercase;
			color: #FB667A;
			text-decoration: none;
		}

		.blue {
			color: #185875;
		}

		.yellow {
			color: #FFF842;
		}

		.container th h1 {
			font-weight: bold;
			font-size: 1em;
			text-align: left;
			color: #185875;
		}

		.container td {
			font-weight: normal;
			font-size: 1em;
			-webkit-box-shadow: 0 2px 2px -2px #0E1119;
			-moz-box-shadow: 0 2px 2px -2px #0E1119;
			box-shadow: 0 2px 2px -2px #0E1119;
		}

		.container {
			text-align: left;
			overflow: hidden;
			width: 80%;
			margin: 0 auto;
			display: table;
			padding: 0 0 8em 0;
		}

		.container td,
		.container th {
			padding-bottom: 2%;
			padding-top: 2%;
			padding-left: 2%;
		}

		/* Background-color of the odd rows */
		.container tr:nth-child(odd) {
			background-color: #323C50;
		}

		/* Background-color of the even rows */
		.container tr:nth-child(even) {
			background-color: #2C3446;
		}

		.container th {
			background-color: #1F2739;
		}

		.container td:first-child {
			color: #FB667A;
		}

		.container tr:hover {
			background-color: #464A52;
			/* -webkit-box-shadow: 0 6px 6px -6px #0E1119;
			-moz-box-shadow: 0 6px 6px -6px #0E1119; */
			box-shadow: 0 6px 6px -6px #0E1119;
		}

		.container td:hover {
			background-color: #FFF842;
			color: #403E10;
			font-weight: bold;

			box-shadow: #7F7C21 -1px 1px, #7F7C21 -2px 2px, #7F7C21 -3px 3px, #7F7C21 -4px 4px, #7F7C21 -5px 5px, #7F7C21 -6px 6px;
			transform: translate3d(6px, -6px, 0);

			transition-delay: 0s;
			transition-duration: 0.4s;
			transition-property: all;
			transition-timing-function: line;
		}

		@media (max-width: 800px) {

			.container td:nth-child(4),
			.container th:nth-child(4) {
				display: none;
			}
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script>
		$(function () {
			console.log("시작합니다");
			$("#button_check").click(function(e){
				e.preventDefault();
				getData();
			})


			function getData() {
				let page=1;
				$.ajax({
					url: "http://localhost:8080/getHtmlData",
					method: "GET",
					type: "json",
					data: {page},
					success: function (data) {
						$("tbody").empty();
						console.log(data);
						let row=data.resultList;
						let html="";
						for(let i=0;i<row.length;i++){
							console.log("data",(i+1),"=",row[i]);
							console.log(row[i].date);
							console.log(row[i].title);
							html=`<tr>
									<td>`+row[i].date+`</td>
									<td>`+row[i].title+`</td>
									<td>`+row[i].commant+`</td>
								</tr>`;
							$("tbody").prepend(html);
						}
					}
				});
			}
		})
	</script>
</head>

<body>
	<h1><span class="blue">&lt;</span>0801 Exam<span class="blue">&gt;</span>
	</h1>
	<button id="button_check">값 받아오기</button>
	<table class="container">
		<thead>
			<tr>
				<th>
					<h1>Sites</h1>
				</th>
				<th>
					<h1>Views</h1>
				</th>
				<th>
					<h1>Clicks</h1>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>7326</td>
				<td>10437</td>
				<td>00:51:22</td>
			</tr>
		</tbody>
	</table>
</body>

</html>