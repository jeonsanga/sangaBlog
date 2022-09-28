<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="utf-8">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin 2 - Blank</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="../js/my_jquery.js"></script>
<style type="text/css">
	#result_card img{
		max-width: 100%;
	    height: auto;
	    display: block;
	    padding: 5px;
	    margin-top: 10px;
	    margin: auto;	
	}
	#result_card {
		position: relative;
	}
	.imgDeleteBtn{
	    position: absolute;
	    top: 0;
	    right: 5%;
	    background-color: #ef7d7d;
	    color: wheat;
	    font-weight: 900;
	    width: 30px;
	    height: 30px;
	    border-radius: 50%;
	    line-height: 26px;
	    text-align: center;
	    border: none;
	    display: block;
	    cursor: pointer;	
	}
	  .a {
        text-indent: 4em;
      }
	
</style>
<script>

$(document).ready(function(){
	
	showImageList();
	
	$.fn.serializeObject = function()
	{
	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};
	
	$("#modifyBtn").click(function(){
		//자바스크립트로 가져옴
		 var formData = $("#modifyForm").serializeObject();
		
		$.ajax({
			url: "/board/modify",
			data: JSON.stringify(formData), //전송 데이터
			type: "POST", //전송 타입
			dataType: "text", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)    			
			contentType : "application/json",
			success: function(data) {
				console.log(data);
				if(data == 1){
					alert("수정이 완료되었습니다",data);
					//getList();
				}
				    				
			},error:function(xhr){
				console.log(xhr);
			}
			    			
		
		})
	});
	
	$("#deleteBtn").click(function(){
		 var formData = $("#modifyForm").serializeObject();
			$.ajax({
				url: "/board/delete",
				data: JSON.stringify(formData), //전송 데이터
				type: "POST", //전송 타입
				dataType: "text", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)    			
				contentType : "application/json",
				success: function(data) {
					console.log(data);
					if(data == 1){
						alert("삭제가 완료되었습니다",data);
						getList();
					}
					    				
				},error:function(xhr){
					console.log(xhr);
				}
				    			
			
			})
	})
	//이미지 파일 삭제 
	//기존에 사용하던 제이쿼리 클릭 이벤트가동적 페이지로 변경되는 순간 동일한 id를 대상으로 이벤트가 적용되지않음 
	$("#imgDeleteBtn").on('click',function(){
		alert("삭제버튼");
		//deleteFile();
	});
	//밑의 방법처럼하면 동적페이지로 변경되도 실행된다.
	 $(document).on("click","#imgDeleteBtn",function(){
		alert("삭제버튼");
		deleteFile();
	}) 
	
	/* 리뷰쓰기 */
	//팝업창열기
	$(document).on("click",".reply_button_wrap",function(e){
		
		e.preventDefault();
		const memberId = '${boardInfo.bno}';
		const bookId = '${boardInfo.bookId}';

		let popUrl = "/reply/replyEnroll/" + memberId + "?bookId=" + bookId;
		console.log(popUrl);
		let popOption = "width = 490px, height=490px, top=300px, left=300px, scrollbars=yes";
		
		window.open(popUrl,"리뷰 쓰기",popOption);

	})
	
	//리뷰리스트 출력
	const bookId = '${boardInfo.bookId}';
	 $.getJSON("/reply/list", {bookId : bookId}, function(data){
		 makeReplyContent(data);
	});
	 
	 //답글달기 
	 //동적으로생성한 버튼 클릭방법
	 $(document).on("click", "#childInsert", function(){
			const memberId = '${boardInfo.bno}';
			const bookId = '${boardInfo.bookId}';
			//const content = $("#content").val();
			const content = document.getElementById('childContent').value;
			const rno = $(this).data("rno");
			
			var childData = {
					"bno": memberId,
					"bookId" : bookId,
					"content" :content,
					"rno" : rno
			}

			$.ajax({
				//url: "/reply/child/"+rno+"/",
				url: "/reply/child/",
				data: JSON.stringify(childData), //전송 데이터
				type: "POST", //전송 타입
				dataType: "json", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)    			
				contentType : "application/json",
				success: function(data) {
					console.log(data);
					makeReplyContent(data);
					    				
				},error:function(xhr){
					console.log(xhr);
				}
				    			
			
			})

		});

	
	
	
});

function getList(){
	location.href = "/board/tables";
}
/* 파일 삭제 메서드 */
function deleteFile(){
	
	let targetFile = $(".imgDeleteBtn").data("file");
	let uuid = $(".imgDeleteBtn").data("uuid");
	console.log("targetFile",targetFile);
	

	let targetDiv = $("#result_card");
	$.ajax({
		url: '/board/deleteFile',
		data : {filename : targetFile,
				uuid : uuid},
		dataType : 'text',
		type : 'POST',
		success : function(result){
			console.log(result);
			
			targetDiv.remove();
			$("input[type='file']").val("");
			
		},
		error : function(result){
			console.log(result);
			
			alert("파일을 삭제하지 못하였습니다.")
		}
	});
	
}

//이미지리스트 출력
function showImageList(){
	/* 이미지 정보 호출 */
	var n = '<c:out value="${boardInfo.bookId}"/>';
	var bookId = parseInt(n);  
	var uploadReslut = $("#uploadReslut");			
	console.log(typeof(bookId)); 
	$.getJSON("/board/getAttachList", {bookId : bookId}, function(arr){	
		
		let str = "";
		let obj = arr[0];	
		
		console.log("arr",arr);
		
		for(var i=0; i<arr.length; i++){
			let fileCallPath = encodeURIComponent(arr[i].uploadPath  +"/" +  arr[i].fileName);
			str += "<div id='result_card'";
			str += "data-path='" + arr[i].uploadPath + "' data-uuid='" + arr[i].uuid + "' data-filename='" + arr[i].fileName + "'";
			str += ">";
			str += "<div id='imgDeleteBtn' class='imgDeleteBtn' data-uuid='" + arr[i].uuid + "' data-file='" + fileCallPath + "'>x</div>";
			str += "<img src='/board/display?filename=" + fileCallPath +"'>";
			str += "</div>";		
		}
		
		//let fileCallPath = encodeURIComponent(obj.uploadPath  + obj.uuid  + obj.fileName);
	/* 	let fileCallPath = encodeURIComponent(obj.uploadPath  +"/" +  obj.fileName);
		str += "<div id='result_card'";
		str += "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "'";
		str += ">";
		str += "<div id='imgDeleteBtn' class='imgDeleteBtn' data-uuid='" + obj.uuid + "' data-file='" + fileCallPath + "'>x</div>";
		str += "<img src='/board/display?filename=" + fileCallPath +"'>";
		str += "</div>"; */	
		uploadReslut.html(str);	
		
	});	
}


/* 댓글(리뷰) 동적 생성 메서드 */
function makeReplyContent(data){
	 if(data.length === 0){
			$(".reply_not_div").html('<span>리뷰가 없습니다.</span>');
			$(".reply_content_ul").html('');
			$(".pageMaker").html('');
		}else{
			//댓글있는경우
			$(".reply_not_div").html('');
			//const list = obj.list;
			const userId = '${boardInfo.bno}';
			let reply_list = '';		
			
			$(data).each(function(i,obj){
				
				console.log("obj.bno",obj.bno);
				reply_list += '<li>';
				reply_list += '<div class="comment_wrap">';
				reply_list += '<div class="reply_top">';
				/* 아이디 */
				reply_list += '<span class="id_span">'+ obj.bno+'</span><br/>';
				/* 날짜 */
				reply_list += '<span class="date_span">'+ obj.createdate +'</span>';
				/* 평점 */
				reply_list += '<span class="rating_span">평점 : <span class="rating_value_span">'+ obj.rating +'</span>점</span><br/>';
				
				
				if(obj.bno == userId){
					reply_list += '<a class="update_reply_btn" href="'+ obj.rno +'">수정</a><a class="delete_reply_btn" href="'+ obj.rno +'">삭제</a>';
				}
				reply_list += '</div>'; //<div class="reply_top">
				reply_list += '<div class="reply_bottom">';
				reply_list += '<div class="reply_bottom_txt">'+ obj.content +'</div>';
		
				
				reply_list += ' <input type="text" id="childContent" name="content" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm">';
				reply_list += '<button type="button" id="childInsert" data-rno="' + obj.rno + '"  class="btn btn-outline-info">답글달기</button>';
				reply_list += '</div>';//<div class="reply_bottom">
				reply_list += '</div>';//<div class="comment_wrap">
				reply_list += '</li>';
			});
			
			$(".reply_content_ul").html(reply_list);	
		}

}


</script>
    <!-- Custom fonts for this template-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Components</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Components:</h6>
                        <a class="collapse-item" href="buttons.html">Buttons</a>
                        <a class="collapse-item" href="cards.html">Cards</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Utilities</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Utilities:</h6>
                        <a class="collapse-item" href="utilities-color.html">Colors</a>
                        <a class="collapse-item" href="utilities-border.html">Borders</a>
                        <a class="collapse-item" href="utilities-animation.html">Animations</a>
                        <a class="collapse-item" href="utilities-other.html">Other</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Addons
            </div>

         <%@ include file="/WEB-INF/views/include/navi.jsp" %>
            
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="../img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="../img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="../img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

${fileIfno }
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
                    <form id="modifyForm"  method="post">
					   <div class="mb-3">
						  <label for="exampleFormControlInput1" class="form-label">Title</label>
						  <input type="text" name="title" value="${boardInfo.title}" class="form-control" id="exampleFormControlInput1">
						  <input type="hidden" name="bno" value="${boardInfo.bno}">
						</div>
						<div class="mb-3">
						  <label for="exampleFormControlTextarea1" class="form-label">Contents</label>
						  <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3">${boardInfo.content}</textarea>
						</div>
						
						<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 이미지</label>
                    				<button type="button" id=""></button>
                    			</div>
                    			<div class="form_section_content">

									<div id="uploadReslut">
																		
									</div>
                    			</div>
                    		</div>
					</form>
					<button type="button" id="modifyBtn" class="btn btn-success">수정</button>
					<button type="button" id="deleteBtn" class="btn btn-secondary">삭제</button>
					 <a href="/board/tables" class="btn btn-info btn-icon-split">
	                                        <span class="icon text-white-50">
	                                            <i class="fas fa-info-circle"></i>
	                                        </span>
	                                        <span class="text">목록으로 이동</span>
	                 </a>
	                 
                </div>
                
                
            <div class="reply_subject">
					<h2>리뷰</h2>
				</div>
			<div class="reply_button_wrap">
						<button>리뷰 쓰기</button>
			</div>
			<div class="reply_not_div">
					
			</div>
				<ul class="reply_content_ul">
					<li>
						<div class="comment_wrap">
							<div class="reply_top">
								<div class="childTest">
									<span class="id_span">sjinjin7</span>
									<span class="date_span">2021-10-11</span>
									<span class="rating_span">평점 : <span class="rating_value_span">4</span>점</span>
									<a class="update_reply_btn">수정</a><a class="delete_reply_btn">삭제</a>
								</div>
								
							</div>
							<div class="reply_bottom">
								<div class="reply_bottom_txt">
									사실 기대를 많이하고 읽기시작했는데 읽으면서 가가 쓴것이 맞는지 의심들게합니다 문체도그렇고 간결하지 않네요 제가 기대가 크던 작았던간에 책장이 사실 안넘겨집니다.
								</div>
							</div>
						</div>
					</li>
				</ul>
			<div class="repy_pageInfo_div">

			</div>
				
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin-2.min.js"></script>

</body>

</html>