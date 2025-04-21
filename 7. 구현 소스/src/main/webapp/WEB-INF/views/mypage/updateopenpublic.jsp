<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <meta charset="UTF-8" />
    <title>정보 공개 여부 설정</title>
    <style>
        /* 전체 화면 덮는 모달 배경 */
        .modalElement {
            position: fixed;
            z-index: 999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        /* 모달 내부 콘텐츠 */
        .modalContent {
            background-color: #f9f7ff;
            width : 500px;
            margin: 10% auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
            position: relative;
        }

        /* 닫기 버튼 (x) */
        .closeModalButton {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
        }
        
        #logo {
            width : 150px;
        }

        #infoTitle{
            width : 100%;
            text-align : center;
            font-weight:bold;
            margin : 30px auto 30px;
            font-size: 20px;
        }
        /* 왼쪽 상자 + 라디오 버튼 상자를 감싸는 컨테이너 */
        .infoContainer {
            display: flex;
            width: 80%;
            margin: 60px auto; /* 위아래 여백 */
            padding: 0;
        }

        /* 왼쪽 파란 상자 */
        .infoLabel {
            background-color: #1e62c8;
            color: #fff;
            padding: 10px;
            font-weight: bold;
            white-space: nowrap; /* 텍스트가 줄바꿈되지 않도록 */
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 8px 0px 0px 8px;
        }

        /* 오른쪽 흰색 배경+파란 테두리 상자 */
        .radioContainer {
            display: flex;
            align-items: center;
            background-color: #fff;
            border: 2px solid #1e62c8;
            padding: 10px;
            flex: 1; /* 남은 공간 전부 차지 */
            text-align : center;
            box-sizing: border-box;
            border-radius: 0px 8px 8px 0px;
        }

        .radioContainer label {
            margin-right: 20px;
            display: flex;
            align-items: center;
            gap: 5px;
            cursor: pointer;
        } 

        /* 제출 버튼 (공개 여부 설정 완료) */
        .submitButton {
            display: block;
            width: 60%;
            padding: 10px 0;
            margin: 100px auto 20px;
            background-color: #1e62c8;
            border: none;
            color: #fff;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            /* 왼쪽 아래 그림자 추가 (x축 음수, y축 양수) */
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);
        }
        .submitButton:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        window.addEventListener('DOMContentLoaded', () => {
            const closeModalButton = document.getElementById('closeModalButton');
            const modalElement = document.getElementById('modalElement');
            const submitButton = document.querySelector('.submitButton');


            // '공개 여부 설정 완료' 버튼 클릭 시
            submitButton.addEventListener('click', () => {
                const selectedOption = document.querySelector('input[name="publicOption"]:checked').value;
                alert(`선택된 옵션: ${selectedOption}`);
                // 선택된 옵션에 따라 서버 전송 등의 로직 추가 가능
                modalElement.style.display = 'none';
            });
        });
    </script>
</head>
<body>

    <!-- 모달 영역 -->
    <div id="modalElement" class="modalElement">
        <div class="modalContent">
            <!-- 닫기 버튼 -->
            <span id="closeModalButton" class="closeModalButton">&times;</span>
            
            <div>
            <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
            </div>
            <div id=infoTitle>회원 정보 공개 여부 설정</div>

            <!-- 공개 여부 라디오 영역 -->
            <form method="POST" action="/lighting/mypage/updateopenpublic.do">
            <div class="infoContainer">
                <!-- 왼쪽 파란 상자 -->
                <div class="infoLabel">공개 여부</div>
                <!-- 오른쪽 라디오 상자 -->
                <div class="radioContainer">
                    <label>
                        <input type="radio" name="status" value="0" checked />
                        공개
                    </label>
                    <label>
                        <input type="radio" name="status" value="1" />
                        비공개
                    </label>
                </div>
            </div>
            <div>
                <button class="submitButton"  type="submit">공개 여부 설정 완료</button>
            </div>
            </form>

        </div>
    </div>
</body>
</html>
