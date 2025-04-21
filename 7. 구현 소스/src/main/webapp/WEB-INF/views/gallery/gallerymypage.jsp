<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>오늘 어때? 이미지 갤러리 내 이미지 보기</title>
<style>
   @font-face {  
      font-family: 'Pretendard-Regular';  
      src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');  
      font-weight: 400;  
      font-style: normal;
    }  

    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Pretendard-Regular';
    }
    
    body{
    background-color: #fff;
    position: relative;
	right: 0px;
    
    }

#body {
	width: 1300px;
	height: 100%;
	margin: 0 auto;
	display: flex;
	font-family: 'Pretendard-Regular';
/* 	position: relative;
	right: 10px; */
}

.container {
	width: 1300px;
	margin: auto;
	background-color: #f9f7ff;
}


.tabs {
	display: flex;
	border-bottom: 2px solid #1e62c8;
	padding-bottom: 10px;
}

.tab {
	width: auto;
	color: #0d0143 !important;
	font-size: 20px;
	margin: 10px;
	font-weight: bold;
	cursor: pointer;
	text-decoration: none; /* 기본 밑줄 제거 */
	white-space: nowrap;
}

.tab:hover {
	color: #1e62c8 !important;
}

.dropdown {
	float: right;
	margin-left: 800px;
	width: 100px;
	height: 30px;
	margin-top: 10px;
	border: 1px solid #0d0143;
	background-color: white;
	color: #0d0143;
	border-radius: 5px;
	outline: none;
}

.gallery {
	display: grid; /* Use grid for gallery layout */
	grid-template-columns: repeat(4, 1fr); /* Create 4 equal columns */
	gap: 40px; /* Space between images */
	margin-top: 15px; /* Top margin for gallery */
	margin-left: 30px; /* Adjust the left margin to move gallery */
	position: relative; /* Keep the relative positioning */
}

.gallery div {
	position: relative;
	width: 259px;
	height: 259px;
	z-index: 10;
}

.gallery img {
	width: 259px; /* 이미지가 div를 100% 차지하도록 설정 */
	height: 259px; /* 비율에 맞게 세로 길이 자동 조정 */
	border-radius: 8px; /* 테두리 둥글게 */
}

.image-checkbox {
	position: absolute;
	top: 5px;
	left: 232px;
	width: 20px;
	height: 20px;
}

.buttons {
	margin-top: 20px;
	display: flex;
	justify-content: flex-end;
}

.buttons button {
	margin-left: 10px;
	padding: 10px 15px;
	border: none;
	cursor: pointer;
	border-radius: 5px;
}

.buttons a {
	text-decoration: none;
}

.upload {
	width: 180px;
	height: 40px;
	font-size: 20px;
	background-color: #1e62c8;
	border-radius: 5px;
	border: none;
	color: #fff;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-left: 10px;
	text-decoration: none;
	position: relative;
	right: 30px;
}

#noImagesMessage {
	color: #444;
	margin: 0 auto;
	font-size: 14px;
	position: absolute;
	top: 50%;
	left: 45%;
}

.share {
	width: 60px;
	height: 40px;
	background-color: #1e62c8;
	border-radius: 5px;
	border: none;
	color: #fff;
	display: flex;
	align-items: center;
	justify-content: center;
	text-decoration: none;
}

.modal-overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
	display: flex;
	justify-content: center;
	align-items: center;
	display: none; /* 기본적으로 숨김 */
	z-index: 9999; /* 이미지 갤러리보다 위로 올리기 */
}

.modal {
	background-color: #f9f7ff;
	border-radius: 12px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.25);
	width: 400px;
	text-align: center;
	padding: 30px;
	position: relative;
	animation: fadeIn 0.3s ease-out;
	z-index: 10000; /* 모달을 다른 요소들보다 더 위로 배치 */
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(-20px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
.icon {
	background-color: #1e62c8;
	border-radius: 50%;
	width: 80px;
	height: 80px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0 auto 20px;
}

.icon img {
	width: 36px;
	height: 36px;
	filter: invert(100%);
}

.modal h2 {
	color: #333;
	margin: 10px 0 15px;
	font-size: 22px;
	font-weight: 600;
}

.modal p {
	margin-bottom: 25px;
	font-size: 16px;
	color: #555;
	line-height: 1.5;
}

.btn-group {
	display: flex;
	gap: 10px;
	margin-top: 15px;
}

.btn {
	flex: 1;
	padding: 12px 0;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	font-weight: 600;
	font-size: 16px;
	transition: all 0.2s ease;
}

.btn-primary {
	background-color: #1e62c8;
	color: white;
	box-shadow: 0 2px 5px rgba(30, 98, 200, 0.3);
}

.btn-primary:hover {
	background-color: #174b9e;
}

.btn-secondary {
	background-color: #fff;
	color: #1e62c8;
	border: 1px solid #1e62c8;
}

.btn-secondary:hover {
	background-color: #f0f5ff;
}

.close-btn {
	position: absolute;
	top: 15px;
	right: 15px;
	background: none;
	border: none;
	cursor: pointer;
	padding: 5px;
}

.close-btn img {
	width: 18px;
	height: 18px;
	opacity: 0.6;
	transition: opacity 0.2s;
}

.close-btn:hover img {
	opacity: 1;
}

.modal-overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
	display: flex;
	justify-content: center;
	align-items: center;
	display: none; /* 기본적으로 숨김 */
}
</style>
</head>
<body>
<body>  
    <div id="header">  
        <%@ include file="/WEB-INF/views/inc/header.jsp"%>  
    </div>  
    <div class="container">  
        <div class="tabs">  
            <a href="/lighting/gallery/gallerymain.do" class="tab" style="opacity: 0.6">오늘어때 포토 갤러리</a>  
            <a href="/lighting/gallery/gallerymypage.do" class="tab">내 이미지 보기</a>  
            <select class="dropdown" id="sort" name="sort">  
                <option value="latest">최신순</option>  
                <option value="oldest">오래된 순</option>  
            </select>  
        </div>  
        <div class="buttons">  
            <button type="button" class="upload" onclick="openModal()">이미지 삭제하기</button>   
        </div>  

        <form id="deleteForm" action="/lighting/gallery/gallerymypage.do" method="post">
    <div class="gallery-container">  
        <div class="gallery">  
            <c:forEach var="photoFilename" items="${photoFilenames}">
                <div class="image-container">  
                    <input type="checkbox" class="image-checkbox" name="filenames" value="${photoFilename}" />   
                    <img src="${pageContext.request.contextPath}/images/${photoFilename}" data-tblPhotoPostseq="${tblPhotoPostseq}"/>
                </div>  
            </c:forEach>  
        </div>  
    </div>  
 
</form>
    </div>  
    <div id="modalOverlay" class="modal-overlay">  
        <div class="modal">  
            <button class="close-btn" onclick="closeModal()">  
                <img src="https://cdn-icons-png.flaticon.com/512/2961/2961937.png" alt="Close">  
            </button>  
            <div class="icon">  
                <img src="https://cdn-icons-png.flaticon.com/512/484/484662.png" alt="Trash Icon">  
            </div>  
            <h2>이미지를 삭제하시겠어요?</h2>  
            <p>삭제된 이미지는<br>복구가 불가능합니다.</p>  
            <div class="btn-group">  
                <button class="btn btn-secondary" onclick="closeModal()">취소</button>  
                <button class="btn btn-primary" type="button" onclick="confirmDelete()">삭제하기</button>  
            </div>  
        </div>  
    </div>  
    <div id="footer">  
        <%@ include file="/WEB-INF/views/inc/footer.jsp"%>  
    </div>
</body>
<script>  
document.addEventListener("DOMContentLoaded", updateNoImagesMessage);  

function openModal() {  
    const checkboxes = document.querySelectorAll('.image-checkbox:checked'); // 체크된 항목 가져오기  
    
    if (checkboxes.length === 0) {  
        alert("삭제할 이미지를 선택하세요!");  
        return;  
    }  
    document.getElementById("modalOverlay").style.display = "flex";  
}  

function closeModal() {  
    document.getElementById("modalOverlay").style.display = "none";  
}  

function confirmDelete() {  
    const checkboxes = document.querySelectorAll('.image-checkbox:checked');  

    checkboxes.forEach(checkbox => {  
        checkbox.parentElement.remove(); // 체크된 이미지 삭제  
    });  

    alert("선택한 이미지가 삭제되었습니다.");  
    closeModal();  
    updateNoImagesMessage();  
}  

window.onclick = function(event) {  
    const modal = document.getElementById("modalOverlay");  
    if (event.target === modal) {  
        closeModal();   
    }  
};  

function updateNoImagesMessage() {  
    const gallery = document.querySelector('.gallery');  
    const images = gallery.querySelectorAll('div img'); // div 내부의 img 태그 개수 확인   
    const message = document.getElementById('noImagesMessage');  

    if (images.length === 0) {  
        message.style.display = "block";  
    } else {  
        message.style.display = "none";  
    }  
}  
</script>  
</html>