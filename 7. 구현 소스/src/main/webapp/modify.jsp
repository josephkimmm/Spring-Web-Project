<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>수정완료</title>
<link rel="stylesheet" href="css/lighting.css">
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.container {
	width: 1300px;
	margin: 0 auto;
	height: auto;
	padding: 20px;
	background-color: #f9f7ff;
}

.top-section {
	display: flex;
	justify-content: space-between;
	margin-bottom: 50px;
 	margin-right:5px;
	height:50px;
}

.left-section {
	 display: flex; /* Flexbox로 배치 */ 
	/* justify-content: space-between; */ /* 좌우 간격을 동일하게 */
	gap: 10px; /* 두 요소 사이에 간격을 설정 */
	width: 1300px; /* 부모 컨테이너의 너비에 맞게 설정 */
/* 	margin-left:50px; */
}

.left-section label {
	 display: block; 
	margin-bottom: 5px;
}

.left-section select, .left-section input {
/* 	width: 100px; */
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #1e62c8;
	border-radius: 4px;
}

.left-section textarea { 
	 width: 500px;
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #1e62c8;
	border-radius: 4px; 
	margin-right: 7px; 
} 

/* #category {
    margin-right: 0px;  /* 오른쪽 여백 추가 */
} */

.time-section {
	display: flex;
	gap: 10px;
	width: 150px;
	 margin-right: 10px; 
}

.time-section input {
	width: 150px;
	margin-right: 10px; 
}
#people {
    width: 100px; 
}

.middle-section {
	margin-top: 20px;
	 display: flex; 
	/* justify-content: space-between; */
	margin-right:10px;
	margin-left:30px;
}

.middle-section textarea {
	width: 900px;
	height: 500px;
	padding: 10px;
	border: 1px solid #1e62c8;
	border-radius: 4px;
	resize: vertical;
}

.middle-section .left-section {
	width: 60%;
	margin-right: 10px;
}

.middle-section .right-section {
	width: 400px;
}

.map {
	width: 440px; 
	height: 320px;
	background-color: white;
	border: 2px solid #6A6969;
	margin-left:10px;
}

.middle-section textarea {
	width: 900px;
	height: 550px;
	padding: 10px;
	border: 1px solid #1e62c8;
	border-radius: 4px;
	resize: vertical;
	margin-left:10px;
}

.buttons {
	margin-top: 95px; /* 지도와 버튼 사이의 간격 추가 */
	
	/* border: 1px solid black; */
	/* width: 500px; */
	margin-left:30px;
	
}

.buttons #loadPost {
    width: 230px;
    background-color: #4F8EEE;
    margin-right:7px;
    font-size: 14px;
}

.buttons #attend { /* 글 작성하기  */
    margin-top: 10px;
     width: 370px;
    background-color: #1E62C8;  
    height:45px;
    font-size: 18px;
}

.buttons #goBack {
	background-color: #C81E1E;
	width:130px;
	font-size: 14px;
	
}

.bottom-section {
	margin-top: 20px;
}

.bottom-section input {

	padding: 10px 20px;
	/* border: 1px solid #1e62c8; */
	border-radius: 4px;
}

.buttons button {
	padding: 10px 20px;
	/* margin-right: 10px; */
	background-color: #4CAF50;
	color: white;
	/* border: 1px solid #1e62c8; */
	 border-radius: 4px; 
	cursor: pointer;
	border: none;  /* 기존 border를 제거 */
}
.buttons button:nth-child(3) {
    
}

#category {
	width: 125px;
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #1e62c8; /* 기본 테두리 색상 */
	border-radius: 4px;
	/* margin-right: 0px; */
}

#subcategory {
	width: 125px;
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #1e62c8; /* 기본 테두리 색상 */
	border-radius: 4px;
	margin-right: 7px; 
	
}

#category:focus, #subcategory:focus {
	outline: none; /* 포커스를 클릭했을 때 생기는 기본 테두리 제거 */
	border: 1px solid #1e62c8; /* 포커스를 클릭했을 때도 원하는 테두리 색상 유지 */
}

/* 포커스 상태에서의 테두리 변경 */
textarea:focus, input:focus, button:focus {
	outline: none; /* 포커스를 클릭했을 때 생기는 기본 테두리 제거 */
	border: 1px solid #1e62c8; /* 테두리를 원하는 색상으로 유지 */
}

/* "장소" 텍스트 스타일 */
.location-text {
	font-size: 20px;
	font-weight: bold;
	margin-top: 10px;
	margin-left:25px;
	color: #6A6969;
	
}
</style>
</head>
<body>
	<div class="container">
		<!-- 상단 섹션 -->
		<div class="top-section">
			<div class="left-section">
				<label for="category"></label> <select id="category">
					<option value="" disabled selected>카테고리</option>
					<!-- 기본 텍스트 -->
					<option value="sports">스포츠</option>
					<option value="culture">문화</option>
					<option value="restaurant">맛집</option>
					<option value="game">게임</option>
					<option value="study">자기계발</option>
					<option value="healing">힐링</option>
					<!-- 다른 카테고리 추가 -->
				</select> <label for="subcategory"></label> <select id="subcategory">
					<option value="" disabled selected>분류</option>
					<!-- 기본 텍스트 -->
					<!-- 분류 옵션은 JavaScript로 동적으로 추가될 예정 -->
				</select>

				<textarea id="title" placeholder="제목은 20자 이내로 입력 가능합니다."></textarea>

				<div class="time-section">
					<input type="date" id="date" value="날짜"> <input type="time" id="time" value="시간">
					<input type="number" id="people" placeholder="인원수">
				</div>
			</div>
		</div>

		<!-- 중간 섹션: 글 내용과 지도 -->
		<div class="middle-section">
			<!-- 왼쪽 중간: 글 내용 텍스트 영역 -->
			<div class="left-section">
				<textarea id="content" placeholder="글 내용을 입력하세요."></textarea>
			</div>

			<div>
				<!-- 오른쪽 중간: 지도 섹션 -->
				<div class="right-section">
					<div class="map">
						<img src="map-placeholder.jpg" alt="지도"
							style="width: 100%; height: 100%;">
					</div>
					<div class="location-text">장소 : 역삼 남호식당 역삼 본점</div>
				</div>
				
				<!-- 하단 섹션 -->
				<div class="bottons-box">
	
					<div class="buttons">
						<button id="loadPost">내가 쓴 글 불러오기</button>
						<button id="goBack">돌아가기</button>
						<button id="attend">수정완료 </button>
					</div>
				</div>
			</div>
		</div>


	</div>
</body>
</html>