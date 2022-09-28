<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Jquery CDN 로드 : 항상 최신 버전 사용 -->    
    <script src="https://code.jquery.com/jquery-latest.min.js"></script> 



    <!-- 내부 JS 지정 -->
    <script>

    	/*
    	[JS 요약 설명]
    	1. window.onload : 브라우저 로드 완료 상태를 나타냅니다 
    	2. post body json : post body 쪽에 json 형식 데이터를 전송합니다
    	3. dataType: "JSON" / contentType: "application/json; charset=utf-8" 설정합니다
    	4. body json 데이터 : {"idx":1, "name":"투케이"} 형식입니다
    	5. 참고 : dataType: "JSON" 선언 시 서버쪽 api 에서도 반드시 json 형식으로 내려줘야합니다 
    	         (json 형식으로 내려주는 것이 아닐 경우 >> TEXT 형식으로 response 데이터를 받아야합니다)
    	*/

   	
    	
    	/* [html 최초 로드 및 이벤트 상시 대기 실시] */
    	window.onload = function() {
    		console.log("");
    		console.log("[window onload] : [start]");
    		console.log("");

    		// [이벤트 함수 호출]
    		requestPostBodyJson();
    	};



    	/* [이벤트 함수 정의] */    	
    	function requestPostBodyJson(){
    		console.log("");
    		console.log("[requestPostBodyJson] : [start]");    		
    		console.log("");

    		// [요청 url 선언]
    		var reqURL = "http://localhost:7200/testPostBodyJson"; // 요청 주소


    		// [요청 json 데이터 선언]
    		var jsonData = { // Body에 첨부할 json 데이터
    				"idx" : 1,
    				"name" : "투케이"
    			};  
    		
    		console.log("");
    		console.log("[requestPostBodyJson] : [request url] : " + reqURL);
    		console.log("[requestPostBodyJson] : [request data] : " + JSON.stringify(jsonData));
    		console.log("[requestPostBodyJson] : [request method] : " + "POST BODY JSON");
    		console.log("");
    		
    		$.ajax({
    			// [요청 시작 부분]
    			url: reqURL, //주소
    			data: JSON.stringify(jsonData), //전송 데이터
    			type: "POST", //전송 타입
    			async: true, //비동기 여부
    			timeout: 5000, //타임 아웃 설정
    			dataType: "JSON", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)    			
    			contentType: "application/json; charset=utf-8", //헤더의 Content-Type을 설정
    			    			
    			// [응답 확인 부분 - json 데이터를 받습니다]
    			success: function(response) {
    				console.log("");
    				console.log("[requestPostBodyJson] : [response] : " + JSON.stringify(response));    				
    				console.log("");    				
    			},
    			    			
    			// [에러 확인 부분]
    			error: function(xhr) {
    				console.log("");
    				console.log("[requestPostBodyJson] : [error] : " + JSON.stringify(xhr));
    				console.log("");    				
    			},
    			    			
    			// [완료 확인 부분]
    			complete:function(data,textStatus) {
    				console.log("");
    				console.log("[requestPostBodyJson] : [complete] : " + textStatus);
    				console.log("");    				
    			}
    		});									
    	};	   
    	
    </script>
</head>
<body>
<button type="button" id="test"></button>
</body>
</html>