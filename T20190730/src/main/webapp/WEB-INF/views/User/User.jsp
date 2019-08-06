<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
</head>
<body>
	<h1>User</h1>
	<form action="/User/Insert" method="post">
		<input type="text" name="id" placeholder="아이디를 입력하세요.">
		<input type="password" name="pw" placeholder="비밀번호를 입력하세요.">
		<input type="text" name="nm" placeholder="이름을 입력하세요.">
		<input type="number" name="age" placeholder="나이를 입력하세요.">
		<select name="gd">
			<option value="M">남자</option>
			<option value="F">여자</option>
		</select>
		<input type="submit" value="등록">
	</form>
</body>
</html>