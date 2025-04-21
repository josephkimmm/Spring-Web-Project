<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<img alt="로고" src="images/logo_가로.png" id="logo">
			<h1>3월 24일 역삼역에서
			삼겹살 드실 3분 구합니다!!</h1>
			<h2 id="subtitle">장소 : 서울/강남구 &ensp;&ensp; 일시 : 3월 25일 19:00</h2>
			<div id="main_user">
				<div class="userbox">
					<img src="images/마스터.png" class="icon">
					<h2>
						홍길동<small>(모임장)</small>
					</h2>
				</div>
				<div class="userbox">
					<img src="images/다이아.png" class="icon">
					<h2>
						치킨중독자<small>(참석자)</small>
					</h2>
				</div>
			</div>

			<button id="delete">모임 삭제하기</button>
			<p>모임 참석자가 없을 때만 모임 삭제가 가능합니다.</p>

			<div id="modalConfirmDelete" style="display: none; height: 300px;">
				<div class="modal-content">
					<h2>모임 삭제 확인</h2>
					<p>정말로 이 모임을 삭제하시겠습니까?</p>
					<button id="confirmDelete">삭제하기</button>
					<button id="cancelDelete">취소</button>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		const modal = document.getElementById("myModal");
		const modalConfirmDelete = document
				.getElementById("modalConfirmDelete");
		const span = document.getElementsByClassName("close")[0];

		document.getElementById("delete").onclick = function() {
			modalConfirmDelete.style.display = "block"; // 삭제 확인 모달 열기  
		}

		document.getElementById("confirmDelete").onclick = function() {
			alert("모임에 참여자가 있어 모임을 삭제할 수 없습니다.");
			modalConfirmDelete.style.display = "none"; // 모달 닫기  
		}

		document.getElementById("cancelDelete").onclick = function() {
			modalConfirmDelete.style.display = "none"; // 모달 닫기  
		}
	</script>
</body>
</html>
