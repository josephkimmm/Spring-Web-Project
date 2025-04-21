<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>오늘어때?</title>
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
        }

        #logo2 {
            width: 250px;
            margin: 30px 0;
            cursor: pointer;
        }

        .modal-overlay {
            width: 100%;
            height: 100%;
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            background: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }


        .modal-window {
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 500px; 
            text-align: center; 
            position: relative;

        }

        .close-btn {
            position: absolute;
            top: 10px;
            right: 15px;
            background: none;
            border: none;
            font-size: 1.5rem;
            cursor: pointer;
        }

        #loginBtn {
            width: 400px;
            background-color: #1e62c8;
            color: white;
            font-size: 1.2rem;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
            margin-top: 20px;
        }

        #loginBtn:hover {
            background-color: #1e4ca0;
        }

        p {
            font-size: 1.5rem;
        }

        span {
            font-weight: bold;
            color: #2765c7;
        }
    </style>
</head>
<body>

    <!-- findps.jsp 비밀번호 찾기모달 창 -->
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
		    <div>
		        <img alt="로고" src="/lighting/images/logo_세로.png" id="logo2">
		    </div>
            <button class="close-btn">&times;</button>
            <div class="content">
                <c:if test="${not empty userPassword}">
                    <p>찾으신 비밀번호는 <strong>${userPassword}</strong> 입니다.</p>
                </c:if>
                <c:if test="${empty userPassword}">
                    <p>입력하신 정보와 일치하는 비밀번호가 없습니다.</p>
                </c:if>
            </div>
            <button id="loginBtn">로그인</button>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function () {
        	// 페이지 로딩 시 모달창 표시
            $("#modal").css("display", "flex");

            // 로그인 버튼 클릭 시 로그인 페이지로 이동
            $("#loginBtn").click(function () {
                window.location.href = "/lighting/user/login.do"; // 로그인 페이지 URL로 변경
            });

            // 모달 닫기 기능 (닫기 버튼 또는 배경 클릭 시)
            $(".close-btn, .modal-overlay").click(function (e) {
                if ($(e.target).is(".modal-overlay") || $(e.target).is(".close-btn")) {
                    $("#modal").fadeOut();
                }
            });
        });
        
        $('#logo2').click(() => { 
            window.location.href =  '/lighting/main.do'; // 메인페이지로 이동
          });
    </script>

</body>
</html>
