<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JQuery 함수 정리</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h3>10월 12일 JQuery 함수 정리</h3>
	<p>
		1) JQuery : $ 표현식을 사용한다.<br>
		2) 선택자     : () 인자값으로 문자열를 넣는다.<br> 
		 -> 예) tag, id, class 등등 사용이 가능하다.<br><br>
		 
		3) 함수        : $(선택자).함수 로 사용 할 수 있다.<br> 
		 -> 예) .을 이용하여 함수를 연속적으로 추가도 가능하다.<br><br>
		 
		4) 지금까지 사용한 함수들은?<br>
		 -> ready()      : JQuery의 선택자를 이용하여 이벤트를 주입 하기 위해서<br> 
		                   document가 모두 화면에 출력 된 후를 감지하기 위해서 사용한다.<br>
		 -> click()      : 마우스를 이용하여 클릭이 발생 했을때 이벤트를 실행하기 위한 함수 이다.<br>
		 -> on() & off() : 이벤트를 사용 하거나 종료를 하기 위해서 사용하는 함수이다.<br>
		                   on() 사용한다  & off() 종료한다.<br>
		 -> text()       : 선택자의 내용을 모두 문자열로 받아 오거나 넣기 위해서 사용하는 함수 이다.<br>
		 -> val()        : input의 value를 가져오거나 넣기 위해서 사용하는 함수 이다.<br>
		 -> html()       : 선택자의 내용을 특정 tag로 넣기 위해서 사용하는 함수 이다.<br>
		 -> append()     : 선택자의 자식으로 tag나 문자열를 추가 하기 위해서 사용한다.<br>
		                   LIFO(last in, first out, 후입 선출) 방식으로 화면에 표현된다.<br>
		 -> prepned()    : 선택자의 자식으로 tag나 문자열를 추가 하기 위해서 사용한다.<br>
		                   FIFO(first in, first out, 선입 선출) 방식으로 화면에 표현된다.<br>
		 -> remove()     : 선택자의 전체 내용을 삭제 하기 위하여 사용하는 함수 이다.<br>
		 -> empty()      : 선택자의 자식을 모두 비우기 위하여 사용하는 함수 이다.<br>
		 -> eq()         : 배열의 특정 인덱스를 선택하기위하여 사용하는 함수 이다.<br>
		 -> index()      : 현재 인덱스를 구하기 위하여 사용하는 함수 이다.<br>
		 -> find()       : 선택자가 가지고 있는 내용을 찾기 위하여 사용하는 함수 이다.<br>
		 -> prop()       : input의 타입인 라디오, 체크박스의 현재 상태를 수정하거나 가져오기 위하여 사용하는 함수 이다.<br>
		                   prop("checked") | prop("checked",true) & prop("checked",false) 사용가능<br>
	</p>
</div>
</body>
</html>