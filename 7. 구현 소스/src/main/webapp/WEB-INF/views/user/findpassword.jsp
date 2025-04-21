<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<title>오늘어때?</title>
  <style>
        @charset "UTF-8";
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Pretendard-Regular';
            
        }

        body {
            background-color: #fff; 
            box-sizing: border-box;
        }

        /* 모달창 기본 스타일 */
        .modal {
            display: block; /* 기본적으로 모달창을 보이게 설정 */
            position: fixed;
            z-index: 1; /* 화면 맨 위에 */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* 반투명 배경 */
        }

        .modal-content {
            background-color: #fff;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 60%;
            max-width: 400px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            position: relative;
        	border-radius: 12px; /* ✅ 모달창 라운드 처리 */
            
        }

        /* 닫기 버튼 오른쪽 상단 고정 */
        .close-button {
        	position: absolute; /* ✅ 위치 고정 */
            top: 5px;
        	right: 15px;
            color: #aaa;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close-button:hover,
        .close-button:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        form label {
            display: block;
            margin-top: 10px;
            font-weight: bold; /* ✅ 글씨 굵게 */
        }

        form input {
            width: 100%;
            padding: 8px;
            margin-top: 3px;
            margin-bottom: 2px;
            border: 1px solid #1e62c8; /* ✅ 테두리 색 고정 */
            border-radius: 4px;
            outline: none; /* ✅ 클릭해도 파란 아웃라인 제거 */
        }
        
        /* focus(클릭)했을 때도 테두리 색 고정 */
        form input:focus {
            border: 1px solid #1e62c8;
        }

        form button, #openModalButton {
            width: 100%;
            padding: 10px;
            background-color: #1e62c8; /* ✅ 항상 이 색상으로 고정 */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 18px; /* ✅ 글씨 크기 키움 */
            margin-top: 10px; /* ✅ 버튼과 텍스트 박스 사이에 간격 추가 */
        }

        /* ✅ hover 시에도 색상 변화 없도록 동일하게 */
        form button:hover, #openModalButton:hover {
            background-color:#1e62c8;
        }
        
                  /* 로고에 마우스 포인터*/
        #logo {
            cursor: pointer;
        }
        
    </style>
</head>
<body>
    <!-- 모달창 내용 -->
    <div id="modal" class="modal">
        <div class="modal-content">
         <img alt="로고" src="/lighting/images/logo_세로.png" id="logo" style="display: block; margin: 0 auto 20px; max-width: 200px;">
        
            <span class="close-button">&times;</span>
            
            <!-- 에러 메시지 출력 부분 -->
    <c:if test="${not empty errorMessage}">
        <div class="error-message" style="color: red; font-size: 13px;">
            <p>${errorMessage}</p>
        </div>
    </c:if>
            
            
            <form action="/lighting/user/findpasswordok.do" method="POST">
                <label for="name">이름</label>
                <input type="text" id="name" name="name"  placeholder="예) 홍길동" required>
                <label for="id">아이디</label>
                <input type="text" id="id" name="id" placeholder="영문, 숫자, '_' 포함, 4~20자"
                 required>
                <label for="tel">연락처</label>
                <input type="text" id="tel" name="tel" placeholder="예) 010-1234-5678, 대시(-) 포함 입력" required>
                <button type="submit">비밀번호 찾기</button>
            </form>
        </div>
    </div>

    <script>
        // 모달창 닫기 기능
        var closeButton = document.getElementsByClassName("close-button")[0];

        // 모달창 닫기
        closeButton.onclick = function() {
            var modal = document.getElementById("modal");
            modal.style.display = "none";
        };

        // 모달창 바깥 클릭 시 닫기
        window.onclick = function(event) {
            var modal = document.getElementById("modal");
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        $('#logo').click(() => { 
            window.location.href =  '/lighting/main.do'; // 메인페이지로 이동
          });
        
    </script>
</body>
</html>
