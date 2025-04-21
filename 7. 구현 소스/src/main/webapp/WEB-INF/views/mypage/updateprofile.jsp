<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <meta charset="UTF-8" />
    <title>프로필 이미지 변경</title>
    <style>
        .modalElement {
            position: fixed;
            z-index: 999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modalContent {
            background-color: #f9f7ff;
            width : 500px;
            margin: 10% auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
            position: relative;
        }

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
        
        .previewWrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
            margin-bottom: 30px;
        }

        #preview {
            display: none;
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            border: 2px solid #1e62c8;
            box-shadow: 0 2px 6px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body>

<div id="modalElement" class="modalElement">
    <div class="modalContent">
        <span id="closeModalButton" class="closeModalButton">&times;</span>

        <div><img alt="로고" src="/lighting/images/logo_가로.png" id="logo"></div>

        <div id="infoTitle">프로필 이미지 변경</div>

        <!-- 파일 선택 input -->
        <input type="file" name="profileImage" id="profileImage" style="display: none;" accept="image/*" />
        
        <!-- 미리보기 -->
        <div class="previewWrapper">
            <img id="preview" src="#" alt="선택된 이미지 없음" />
        </div>

        <!-- 버튼 -->
        <button type="button" class="imageUploadButton" onclick="document.getElementById('profileImage').click();">이미지 가져오기 +</button>
        <button type="button" class="submitProfileImageButton">이미지 변경 완료</button>

        <!-- 에러 메시지 -->
        <p id="errorMsg" style="color:red; text-align:center; margin-top:10px;"></p>
    </div>
</div>

<script>
window.addEventListener('DOMContentLoaded', () => {
    const fileInput = document.getElementById('profileImage');
    const preview = document.getElementById('preview');
    const errorMsg = document.getElementById('errorMsg');
    const MAX_SIZE = 5 * 1024 * 1024;

    fileInput.addEventListener('change', () => {
        const file = fileInput.files[0];
        if (!file) return;

        const validTypes = ['image/jpeg', 'image/png', 'image/jpg', 'image/gif'];
        if (!validTypes.includes(file.type)) {
            errorMsg.textContent = "이미지 파일(jpg, png, gif)만 업로드 가능합니다.";
            fileInput.value = "";
            preview.style.display = "none";
            return;
        }

        if (file.size > MAX_SIZE) {
            errorMsg.textContent = "파일 용량은 5MB 이하만 가능합니다.";
            fileInput.value = "";
            preview.style.display = "none";
            return;
        }

        errorMsg.textContent = "";
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = "block";
        };
        reader.readAsDataURL(file);
    });

    document.querySelector('.submitProfileImageButton').addEventListener('click', function(e) {
        const file = fileInput.files[0];
        if (!file) {
            alert("이미지를 먼저 선택해주세요.");
            return;
        }

        const formData = new FormData();
        formData.append("profileImage", file);

        fetch("/lighting/mypage/updateprofileok.do", {
            method: "POST",
            body: formData
        })
        .then(res => res.json())
        .then(data => {
            if (data.status === "success") {
                alert("프로필 이미지가 변경되었습니다.");

                // 부모창 반영 (캐시 방지로 ?t 붙임)
                if (window.opener && window.opener.document.getElementById("profile")) {
                    window.opener.document.getElementById("profile").src = "/lighting/images/" + data.fileName + "?t=" + new Date().getTime();
                }

                window.close();
            } else {
                alert("변경 실패");
            }
        })
        .catch(err => {
            console.error("오류:", err);
            alert("서버와 통신 중 오류가 발생했습니다.");
        });
    });

    document.getElementById("closeModalButton").addEventListener("click", () => {
        window.close();
    });
});
</script>

</body>
</html>
