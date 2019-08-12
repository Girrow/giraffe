<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login</title>
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

		.switch_box {
			display: -webkit-box;
			display: -ms-flexbox;
			display: flex;
			max-width: 100px;
			min-width: 100px;
			height: 50px;
			-webkit-box-pack: center;
			-ms-flex-pack: center;
			justify-content: center;
			-webkit-box-align: center;
			-ms-flex-align: center;
			align-items: center;
			-webkit-box-flex: 1;
			-ms-flex: 1;
			flex: 1;
		}

		/* Switch 1 Specific Styles Start */

		.box_1 {
			background: #eee;
		}

		input[type="checkbox"].switch_1 {
			font-size: 30px;
			-webkit-appearance: none;
			-moz-appearance: none;
			appearance: none;
			width: 3.5em;
			height: 1.5em;
			background: #ddd;
			border-radius: 3em;
			position: relative;
			cursor: pointer;
			outline: none;
			-webkit-transition: all .2s ease-in-out;
			transition: all .2s ease-in-out;
		}

		input[type="checkbox"].switch_1:checked {
			background: #0ebeff;
		}

		input[type="checkbox"].switch_1:after {
			position: absolute;
			content: "";
			width: 1.5em;
			height: 1.5em;
			border-radius: 50%;
			background: #fff;
			-webkit-box-shadow: 0 0 .25em rgba(0, 0, 0, .3);
			box-shadow: 0 0 .25em rgba(0, 0, 0, .3);
			-webkit-transform: scale(.7);
			transform: scale(.7);
			left: 0;
			-webkit-transition: all .2s ease-in-out;
			transition: all .2s ease-in-out;
		}

		input[type="checkbox"].switch_1:checked:after {
			left: calc(100% - 1.5em);
		}

		/* Switch 1 Specific Style End */
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:out value="${result}"/>
	<div class="container">
		<!-- Modal -->
		<div class="modal" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="padding:35px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
					</div>
					<div class="modal-body" style="padding:40px 50px;">
						<form method="GET" action="/login/add" id="login">
							<div class="switch_box box_1">
								<input type="checkbox" id="switchFlag" class="switch_1" name="switchFlag">
							</div>
							<div class="form-group">
								<label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
								<input type="text" class="form-control" name="id" id="usrname"
									placeholder="Enter email" required="required" autocomplete="off">
							</div>
							<div class="form-group">
								<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
								<input type="text" class="form-control" name="pw" id="psw"
									placeholder="Enter password" required="required" autocomplete="off">
							</div>
							<button type="submit" class="btn btn-success btn-block"><span
									class="glyphicon glyphicon-off"></span> Login</button>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>

</html>