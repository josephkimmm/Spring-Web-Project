<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.lighting.meeting.model.MeetingPostDTO, com.lighting.meeting.model.MemberDTO, java.util.List" %>

<%
	MeetingPostDTO meetingPostdto = (MeetingPostDTO)request.getAttribute("meetingPostdto");
	MemberDTO memberdto = (MemberDTO)request.getAttribute("memberdto");
	List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("list");
	String tblMeetingPostSeq = (String)request.getAttribute("tblMeetingPostSeq");
	
	String starttime = meetingPostdto.getStartTime();
	
    String month = starttime.substring(5, 7); // 5~7번째 글자: 월
    String day = starttime.substring(8, 10); // 8~10번째 글자: 일
    String hour = starttime.substring(11, 13); // 11~13번째 글자: 시간
    String minute = starttime.substring(14, 16); // 14~16번째 글자: 분
    
    request.setAttribute("month", month);
    request.setAttribute("day", day);
    request.setAttribute("hour", hour);
    request.setAttribute("minute", minute);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	@font-face {
		font-family: 'Pretendard-Regular';
		src:
			url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff')
			format('woff');
		font-weight: 400;
		font-style: normal;
	}
	
	body {
		background-color: #f9f7ff;
		display: flex;
		justify-content: center;
		align-items: center;
		height: 100vh;
		margin: 0;
		color: #0d0143;
		text-align: center;
		
	}
	
	#logo {
		width: 200px;
		height: auto;
		margin-right: 450px;
	}
	
	h1 {
		width: 440px;
		margin: 15px;
		white-space: pre-line;
	}
	
	h2 {
		margin-top: 3px;
	}
	
	#subtitle{
		color: #616161;
		margin-bottom: 3px;
	}
	
	.icon {
		width: 30px;
		margin-right: 10px;
	}
	
	#main_user {
		width: 400px;
		height: 300px;
		border: 2px solid #1e62c8;
		border-radius: 15px;
		margin: 15px;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: flex-start;
		background-color: #fff;
	}
	
	.userbox {
		display: flex;
		justify-content: center;
		margin: 10px;
	}
	
	button {
		width: 200px;
		height: 50px;
		font-size: 22px;
		align-items: center;
		cursor: pointer;
		margin: 10px;
		color: #fff;
		background-color:#1e62c8;
		border: none;
		border-radius: 10px;
	}
	
	p {
		color: #616161;
		font-size: 18px;
		margin: 5px 0; /* 위아래 마진 추가 */
	}
	
	.modal {
		display: block;
		width: 100%; /* 전체 너비 */
		height: 100%; /* 전체 높이 */
		overflow: auto;
		background-color: rgba(0, 0, 0, 0.4);
		padding-top: 60px;
	}
	
	.modal-content {
		background-color: #f9f7ff;
		margin: 5% auto;
		padding: 20px;
		width: 700px; /* 모달 너비 */
		height: auto;
		display: flex;
		flex-direction: column;
		align-items: center;
		border-radius: 15px;
	}
	
</style>
</head>
<body>
	<!-- 모달 창 -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
			<h1>${meetingPostdto.title}</h1>
			<h2 id="subtitle">장소 : ${memberdto.sido}/${memberdto.gugun} &ensp;&ensp; 일시 : ${month}월 ${day}일 ${hour}:${minute}</h2>
			<div id="main_user">
				<div class="userbox">
					<img src="/lighting/images/${memberdto.scoreImage}" class="icon">
					<h2>
						${memberdto.nickname}<small>(모임장)</small>
					</h2>
				</div>
				
				<c:forEach var="item" items="${list}">
				<div class="userbox">
					<img src="/lighting/images/${item.scoreImage}" class="icon">
					<h2>
						${item.nickname }<small>(참석자)</small>
					</h2>
				</div>
				</c:forEach>
			</div>

			<button id="delete">모임 삭제하기</button>
			<p id="info">모임 참석자가 없을 때만 모임 삭제가 가능합니다.</p>

		</div>
	</div>

	<script>
        const isListEmpty = ${list == null || list.isEmpty()};
		
        if (${checkMeeting} == 1) {//모임이 성사 되었을 때
        	$('#delete').css('visibility', 'hidden');
        	$('#info').css('color', 'red');
			$('#info').css('font-weight', 'bold');
			$('#info').text('이미 성사된 모임은 삭제가 불가능합니다.');
        }
        
		if (isListEmpty) {//참석자가 없을 때
		    $('#delete').on('click', deleteMeetingPost);
		} else {//참석자가 있을 때
			$('#info').css('color', 'red');
			$('#info').css('font-weight', 'bold');
			$('#delete').css('visibility', 'hidden');
		}
		
		function deleteMeetingPost() {
			$.ajax({//게시글 삭제
				url: '/lighting/meeting/deleteok.do',
		        type: 'GET',
		        data: 'tblMeetingPostSeq=' + ${tblMeetingPostSeq},
		        success: function() {
		            // 부모 페이지 이동
		            if (window.opener && !window.opener.closed) {
		                window.opener.location.href = "/lighting/main.do";
		            }
		            // 자식 창 닫기
		            window.close();
		        },
				error: function(a, b, c) {
		            console.error(a,b,c);
		         	// 자식 창 닫기
		         	alert('error');
		            window.close();
		        }
			});
		}
	
	</script>
</body>
</html>
