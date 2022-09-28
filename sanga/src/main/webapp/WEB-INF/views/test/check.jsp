<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<title>Web Form</title> 
</head> 
<body> 

<h3>폼 데이터 테스트</h3> 

<form method=post > 

    <ol> 
    <li> 이름을 입력하세요 <br> 
    이름 : <input type=text name="name"> 
    <p> 
    <li> 사용중인 소프트웨어를 선택하세요 <br> 
    <input type=checkbox name=sw value="jdk1.5">JDK1.5<br> 
    <input type=checkbox name=sw value="JBuilder">JBuilder<br> 
    <input type=checkbox name=sw value="Eclipse">Eclipse<br> 
    <input type=checkbox name=sw value="Visual age">Visual age<br> 
    <input type=checkbox name=sw value="NetBean">NetBean<br> 
    <p> 
    <li> 사용중인 운영체제는 ? <br> 
    <input type=radio name=os value="win32"> 윈도우 <br> 
    <input type=radio name=os value="linux"> 리눅스 <br> 
    <input type=radio name=os value="solaris"> 솔라리스 <br> 
    <input type=radio name=os value="misc"> 기타 <br> 
    <p>     
    <li> 사용중인 컴퓨터 하드웨어는 ?<br> 
    <input type=radio name=hw value="pentium"> 펜티엄 <br> 
    <input type=radio name=hw value="misc"> 기타<br> 
    <p> 

    <li> 즐겨 먹는 커피는 ? <br> 
    <select name=coffee size=5 multiple> 
        <option value="아메리카노"> 아메리카노 
        <option value="카페라떼"> 카페라떼 
        <option value="카푸치노"> 카푸치노 
        <option value="에스프레소"> 에스프레소 
        <option value="자판기"> 자판기 
    </select> 
    <p> 
    <input type=submit value=전송> 
    <input type=reset  value=취소> 

    </ol> 
</form> 
</body> 

<script type="text/javascript">
$(document).ready(function(){	
	$("input[name=sw]:checked").each(function() 
			{ var test = $(this).val(); 
			console.log("test"+test);

			})
	

	
});
</script>



</html>