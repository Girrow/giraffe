<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
<style>
	.error {
		background-color: red;
	}
</style>
</head>
<body>
<%
	HashMap<String, Object> resultMap = (HashMap<String, Object>) request.getAttribute("result");
	String id ="", pw = "", nm = "", age = "";
	if(resultMap != null){ 
		if(!("").equals(resultMap.get("id"))) {
			id = resultMap.get("id").toString();
		}
		if(!("").equals(resultMap.get("pw"))) {
			pw = resultMap.get("pw").toString();
		}
	}
%>
	<h1>User</h1>	
	<!-- http://localhost:8080/User/Insert?id=1 -->
	<form action="/User/Insert" method="post">
		<input type="text" name="id" placeholder="아이디를 입력하세요." class="<%= ("").equals(id)?"error":""%>" value="<%=id%>">
		<input type="password" name="pw" placeholder="비밀번호를 입력하세요." class="<%= ("").equals(pw)?"error":""%>" value="<%= pw%>">
		<input type="text" name="nm" placeholder="이름을 입력하세요." class="<%= ("").equals(nm)?"error":""%>" value="<%= nm%>">
		<input type="number" name="age" placeholder="나이를 입력하세요." class="<%= ("").equals(age)?"error":""%>" value="<%= age%>">
		<select name="gd">
			<option value="M">남자</option>
			<option value="F">여자</option>
		</select>
		<input type="submit" value="등록">
	</form>
</body>
</html>