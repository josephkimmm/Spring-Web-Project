<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <meta charset="UTF-8">
    
    <title>비밀번호 변경</title>
    <style>
        /* 전체 화면 덮는 모달 배경 */
        .modal {
            position: fixed;
            z-index: 999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        /* 모달 내부 콘텐츠 */
        .modal-content {
            background-color: #f9f7ff;
            width: 500px;
            margin: 10% auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
            position: relative;
        }

        /* 닫기 버튼 (x) */
        .close-button {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
        }

        #logo {
            width: 150px;
        }

        #infoTitle {
            width: 100%;
            text-align: center;
            font-weight: bold;
            margin: 30px auto 30px;
            font-size: 20px;
            color: black; /* 글자색 검정색 */
        }

        /* 왼쪽 상자 + 입력 필드를 감싸는 컨테이너 */
        .infoContainer {
            display: flex;
            width: 80%;
            margin: 20px auto;
            padding: 0;
        }

        /* 왼쪽 파란 상자 */
        .infoLabel {
            background-color: #1e62c8;
            color: #fff;
            padding: 10px;
            font-weight: bold;
            white-space: nowrap;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 8px 0px 0px 8px;
            width: 120px; /* Label width */
        }

        /* 오른쪽 흰색 배경+파란 테두리 상자 */
        .inputContainer {
            display: flex;
            align-items: center;
            background-color: #fff;
            border: 2px solid #1e62c8;
            padding: 10px;
            flex: 1;
            box-sizing: border-box;
            border-radius: 0px 8px 8px 0px;
        }

        #infoBox {
            width: 100%;
            padding: 5px;
            border: none;
            outline: none;
        }

        /* 제출 버튼 (개인 정보 수정 완료) */
        .submitButton {
            display: block;
            width: 60%;
            padding: 10px 0;
            margin: 30px auto 20px;
            background-color: #1e62c8;
            border: none;
            color: #fff;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);
        }

        .submitButton:hover {
            background-color: #0056b3;
        }

        .margin-bottom-large {
            margin-bottom: 80px; /* 기존 마진의 4배 */
        }
        
        input[type="password"] {
            width: 100%;
            padding: 5px;
            border: none;
            outline: none;
        }
    </style>
</head>
<body>

    <!-- 모달 창 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close-button">&times;</span>
            <div>
                <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
            </div>
            <div id="infoTitle">비밀번호 변경</div>
            
            <form method="POST" action="/lighting/mypage/updatepasswordok.do" id="passwordForm">
            <div class="infoContainer">
                <div class="infoLabel">기존 비밀번호</div>
                <div class="inputContainer">
                    <input type="password" name="currentPassword" id="currentPassword" placeholder="기존 비밀번호" required>
                </div>
            </div>

            <div class="infoContainer">
                <div class="infoLabel">변경할 비밀번호</div>
                <div class="inputContainer">
                    <input type="password" name="newPassword" id="newPassword" placeholder="변경할 비밀번호" required>
                </div>
            </div>

            <div class="infoContainer margin-bottom-large">
                <div class="infoLabel">비밀번호 확인</div>
                <div class="inputContainer">
                    <input type="password" name="confirmPassword" id="confirmPassword" placeholder="비밀번호 확인" required>
                </div>
            </div>
            
            <button type="submit" class="submitButton">개인 정보 수정 완료</button>
            </form>
            
        </div>
    </div>

    <script>
    document.getElementById("passwordForm").addEventListener("submit", function(e) {
        const newPw = document.getElementById("newPassword").value;
        const confirmPw = document.getElementById("confirmPassword").value;

        if (newPw !== confirmPw) {
            e.preventDefault();
            alert("변경할 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    });
    </script>
</body>
</html>
