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
        
        #findPwBtn {
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

        #findPwBtn:hover {
            background-color: #1e4ca0;
        }
        
        p {
            font-size: 1.5rem;
        }
        
        span {
            font-weight: bold;
            color: #2765c7;
        }
        
        #move {
            color: gray;
            font-size: 0.8rem;
        }
        
        /* 모달창 스타일 */
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
            position: relative;
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 500px; 
            text-align: center; 
        }

        .close-btn {
            position: absolute;
            top: 10px;
            right: 15px;
            background: none;
            border: none;
            font-size: 1.5rem;
            cursor: pointer;
            font-weight: bold;
        }

    </style>
</head>
<body>

    <!-- findId.jsp 아이디 찾기 모달창 -->
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <button class="close-btn">&times;</button>
            <div>
                <img alt="로고" src="/lighting/images/logo_세로.png" id="logo2">
            </div>
            <div class="content">
            	<c:if test="${not empty userId}">
                <p>찾으신 아이디는 <strong>${userId}</strong> 입니다.</p>
            </c:if>
                <!-- <p>찾으신 아이디는 <span>hong123</span> 입니다.</p> -->
                <c:if test="${empty userId}">
                <p>입력하신 정보와 일치하는 아이디가 없습니다.</p>
            </c:if>
                
            </div>
            <button type="submit" id="findPwBtn">비밀번호 찾기</button>
            <p id="move">비밀번호 변경 페이지로 이동합니다.</p>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function(){
            // 페이지 로딩 시 모달창 표시
            $("#modal").css("display", "flex");

            // "비밀번호 찾기" 버튼 클릭 시 비밀번호 찾기 페이지로 이동
            $("#findPwBtn").click(function(){
                window.location.href = "/lighting/user/findpassword.do"; // 실제 비밀번호 찾기 페이지 URL로 변경 필요
            });

            // 닫기 버튼 클릭 시 모달창 닫기
            $(".close-btn").click(function(){
                $("#modal").fadeOut();
            });

            // 모달창 바깥 부분 클릭 시 닫기
            $(".modal-overlay").click(function(e){
                if ($(e.target).hasClass("modal-overlay")) {
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
