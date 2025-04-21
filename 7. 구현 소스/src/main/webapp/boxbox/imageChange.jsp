<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>프로필 이미지 변경</title>
    <style>
        .modalElement {
            display: none;
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

        /* 모달 창 타이틀 */
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

        /* 이미지 업로드 버튼 */
        .imageUploadButton {
            display: block;
            width: 60%;
            margin-top: 50px;    
		    margin-bottom: 80px; 
		    margin-left: auto;
		    margin-right: auto;
            padding: 15px 0;
            background-color: #e7f1ff;
            border: 2px dashed #1e62c8;
            color: #1e62c8;
            font-size: 16px;
            border-radius: 8px;
            cursor: pointer;
        }
        .imageUploadButton:hover {
            background-color: #d0e3ff;
        }

        /* 이미지 변경 완료 버튼 */
        .submitProfileImageButton {
            display: block;
            width: 60%;
            margin: 0 auto 20px;
            padding: 15px 0;
            background-color: #1e62c8;
            border: none;
            color: #fff;
            font-size: 16px;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: -2px 2px 5px rgba(0, 0, 0, 0.3);
        }
        .submitProfileImageButton:hover {
            background-color: #0056b3;
        }

        /* 모달 열기 버튼 */
        #openModalButton {
            margin: 30px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
    <script>
    window.addEventListener('DOMContentLoaded', () => {
        const openModalButton = document.getElementById('openModalButton');
        const closeModalButton = document.getElementById('closeModalButton');
        const modalElement = document.getElementById('modalElement');

        // 실제 버튼 요소를 가져와 변수에 할당
        const imageUploadButton = document.querySelector('.imageUploadButton');
        const submitProfileImageButton = document.querySelector('.submitProfileImageButton');

        // 모달 열기
        openModalButton.addEventListener('click', () => {
            modalElement.style.display = 'block';
        });

        // 모달 닫기
        closeModalButton.addEventListener('click', () => {
            modalElement.style.display = 'none';
        });

        // 이미지 가져오기 버튼 클릭
        imageUploadButton.addEventListener('click', () => {
            alert('이미지 가져오기 기능 구현 부분');
        });

        // 이미지 변경 완료 버튼 클릭
        submitProfileImageButton.addEventListener('click', () => {
            alert('이미지 변경 완료!');
            modalElement.style.display = 'none';
        });
    });
    </script>
</head>
<body>
    <!-- 모달 열기 버튼 -->
    <button id="openModalButton">프로필 이미지 변경 모달 열기</button>

    <!-- 모달 영역 -->
    <div id="modalElement" class="modalElement">
        <div class="modalContent">
            <!-- 닫기 버튼 -->
            <span id="closeModalButton" class="closeModalButton">&times;</span>
            
            
            <div>
            <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
            </div>
            
            
            <div id=infoTitle>프로필 이미지 변경</div>
            

            <button class="imageUploadButton">이미지 가져오기 +</button>
            <button class="submitProfileImageButton">이미지 변경 완료</button>
        </div>
    </div>
</body>
</html>
