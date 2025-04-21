<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<meta charset="UTF-8">
<title>회원탈퇴 모달</title>
<style>
    body {
        background-color: rgba(0, 0, 0, 0.4);
        display: flex;
        height: 100vh;
        justify-content: center;
        align-items: center;
        margin: 0;
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
    }
</style>
</head>
<body>
    <div id="modalOverlay" class="modal-overlay">
	    <div class="modal">
	        <button class="close-btn" onclick="closeModal()">
	            <img src="https://cdn-icons-png.flaticon.com/512/2961/2961937.png" alt="Close">
	        </button>
	        <div class="icon">
	            <img src="https://cdn-icons-png.flaticon.com/512/484/484662.png" alt="Trash Icon">
	        </div>
	        <h2>정말 탈퇴하시겠어요?</h2>
	        <p>탈퇴 시 모든 개인정보와 데이터는 즉시 삭제되며,<br>복구가 불가능합니다.</p>
	        <div class="btn-group">
	            <button class="btn btn-secondary" onclick="closeModal()">취소</button>
	            <button class="btn btn-primary" onclick="confirmDelete()">탈퇴하기</button>
	        </div>
	    </div>
    </div>

<script>
    // 모달 닫기 기능
    function closeModal() {
    	document.getElementById('modalOverlay').style.display = 'none';
        // 또는 모달을 숨기는 방식: document.querySelector('.modal').style.display = 'none';
    }
    
    // 탈퇴 확인 기능
    function confirmDelete() {
        if(confirm("정말로 탈퇴하시겠습니까?\n이 작업은 되돌릴 수 없습니다.")) {
            // 실제 탈퇴 처리 로직 (AJAX 호출 등)
            performDelete();
        }
    }
    
    // 실제 탈퇴 처리 함수
    function performDelete() {
        // 여기에 서버로 탈퇴 요청을 보내는 코드 작성
        // 예: fetch API 사용 또는 form submit
        
        // 완료 후 리디렉션
        window.location.href = "/lighting/mypage/unregisterok.do"; // 홈페이지로 이동
    }
    
 // 모달 외부 클릭 시 닫기
    document.getElementById('modalOverlay').addEventListener('click', function(e) {
        if (e.target === this) {
            closeModal();
        }
    });
</script>
</body>
</html>
