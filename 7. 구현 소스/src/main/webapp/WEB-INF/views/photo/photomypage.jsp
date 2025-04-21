<%@ page language="java" contentType="text/html; charset=UTF-8"  
	pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>  
<html lang="ko">  
<head>  
<meta charset="UTF-8">  
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>  
<title>오늘 어때? 이미지 갤러리 내 이미지 보기</title>  
<style>  
    /* CSS 스타일 영역 */  
    @font-face {  
        font-family: 'Pretendard-Regular';  
        src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');  
        font-weight: 400;  
        font-style: normal;  
    }  

    #body {  
        width: 1300px;  
        height: 800px;  
        background-color: #f9f7ff;  
        margin: 0;  
        display: flex;  
    }  

    .container {  
        max-width: 1300px;  
        margin: 0 auto;  
        display: flex;  
        flex-direction: column;  
    }  

    .tabs {  
        display: flex;  
        border-bottom: 2px solid #1e62c8;  
        padding-bottom: 10px;  
    }  

    .tab {  
        font-size: 22px;  
        margin: 10px;  
        font-weight: bold;  
        cursor: pointer;  
        color: #0d0143 !important;  
    }  

    .tab:hover {  
        color: #1e62c8 !important;   
    }  

    .dropdown {  
        float: right;  
        margin-left: 830px;  
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
        display: grid;  
        grid-template-columns: repeat(4, 1fr);  
        gap: 10px;  
        margin-top: 15px;  
        position: relative;  
        left: 30px;  
    }  

    .gallery div {  
        position: relative;  
        width: 240px;  
        height: 240px;  
        z-index: 10;  
    }  

    .gallery img {  
        width: 100%;  
        height: 100%;  
        border-radius: 8px;  
    }  

    .image-checkbox {  
        position: absolute;  
        top: 7px;  
        left: 215px;  
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
    }  

    #noImagesMessage {  
        color: #444;  
        margin: 0 auto;  
        font-size: 14px;  
        position: absolute;  
        top: 50%;  
        left: 45%;  
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
        display: none;   
        z-index: 9999;   
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
        z-index: 10000;   
    }  

    @keyframes fadeIn {  
        from { opacity: 0; transform: translateY(-20px); }  
        to { opacity: 1; transform: translateY(0); }  
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
</style>  
</head>  
<body>  
<div id="header">  
<%@ include file="/WEB-INF/views/inc/header.jsp" %>  
</div>  
    <div class="container">  
        <div class="tabs">  
            <a href="/lighting/photomain.do" class="tab" style="opacity: 0.6">오늘어때 이미지 갤러리</a>  
            <a href="/lighting/photomypage.do" class="tab">내 이미지 보기</a>  
            <select class="dropdown">  
                <option>최신순</option>  
                <option>오래된 순</option>  
                <option>조회 순</option>  
            </select>  
        </div>  
        <div class="buttons">  
        <button class="upload" onclick="openModal()">이미지 삭제하기</button>  
        </div>  

        <div class="gallery-container">  

            <div class="gallery">  
                <c:if test="${not empty postList}">  
                    <c:forEach var="post" items="${postList}"> 
                        <c:forEach var="photo" items="${post.attachedPhotos}">  
                            <div>  
                                <input type="checkbox" class="image-checkbox" id="photo_${photo.tblAttachedPhotoSeq}">  
                                <img src="${photo.photoFileName}" alt="이미지">  
                            </div>  
                        </c:forEach>  
                    </c:forEach>  
                </c:if>  
                <c:if test="${empty postList}">  
                <p id="noImagesMessage">등록한 이미지가 없습니다.</p>  
                </c:if>  
            </div>  
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
                    <button class="btn btn-primary" onclick="confirmDelete()">삭제하기</button>  
                </div>  
            </div>  
        </div>  
    </div>  

    <div id="footer">  
        <%@ include file="/WEB-INF/views/inc/footer.jsp" %>  
    </div>  
</body>  
<script>  
    // 페이지 로드 시 체크  
    document.addEventListener("DOMContentLoaded", updateNoImagesMessage);  

    function openModal() {  
        const checkboxes = document.querySelectorAll('.image-checkbox:checked');  

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
        const photoIds = [];  

        checkboxes.forEach(checkbox => {  
            photoIds.push(checkbox.id.split('_')[1]); // 체크된 체크박스의 id에서 사진 ID 추출  
            checkbox.parentElement.remove(); // 화면에서 삭제  
        });  

        if (photoIds.length === 0) {  
            alert("삭제할 이미지가 없습니다.");  
            closeModal();  
            return;  
        }  

        // AJAX 요청  
        const xhr = new XMLHttpRequest();  
        xhr.open("POST", "/lighting/deleteImages.do", true);  
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  
        xhr.onreadystatechange = function() {  
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {  
                alert("선택한 이미지가 삭제되었습니다.");  
                closeModal();  
                updateNoImagesMessage(); // 이미지 삭제 후 문구 업데이트  
            }  
        };  
        xhr.send("photoIds=" + JSON.stringify(photoIds)); // 삭제할 ID 배열 전송  
    }  

    // 모달 클릭하면 닫기  
    window.onclick = function(event) {  
        const modal = document.getElementById("modalOverlay");  
        if (event.target === modal) {  
            closeModal();   
        }  
    };  

    function updateNoImagesMessage() {  
        const gallery = document.querySelector('.gallery');  
        const images = gallery.querySelectorAll('div img');   
        const message = document.getElementById('noImagesMessage');  

        if (images.length === 0) {  
            message.style.display = "block";  
        } else {  
            message.style.display = "none";
        }  
    }  
</script>  
</html>  