<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<title>오늘 어때? 이미지 갤러리</title>
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
    }

#body {
	width: 1300px;
	height: auto;
	margin: 0 auto;
	display: flex;
	font-family: 'Pretendard-Regular';
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

.gallery-container {
	display: flex; /* Use flexbox for alignment */
	justify-content: center; /* Center items horizontally */
	align-items: center; /* Center items vertically, if needed */
	flex-direction: column; /* Stack items vertically */
	width: 1300px; /* Take full width */
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

.buttons {
	position: relative;
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

/* Modal styles */
.modal-overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: none; /* Initially hidden */
	justify-content: center;
	align-items: center;
}

.modal {
	background-color: white;
	border-radius: 12px;
	padding: 20px;
	width: 400px;
	text-align: center;
	align-items: center;
	justify-content: center;
}

.close-btn {
	cursor: pointer;
	background: none;
	border: none;
	float: right;
	font-size: 20px;
}

.modal h2 {
	margin-bottom: 15px;
}

.modal input[type="file"] {
	margin-bottom: 15px;
	border: 1px solid #0d0143;
}

.modal .btn {
	margin-top: 10px;
	padding: 10px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn-primary {
	background-color: #1e62c8;
	color: white;
}

#logo {
	width: 150px;
}

#closebtn {
	width: 25px;
	position: relative;
	left: 13px;
	top: -5px;
}

#imageModal .modal {
	max-width: 90%;
	max-height: 90%;
	display: flex;
	align-items: center;
	justify-content: center;
	background: none;
}

#modalImage {
	max-width: 100%;
	max-height: 90vh;
	border-radius: 8px;
}
</style>
</head>
<body>
	<div id="header">
		<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	</div>
	<div class="container">
		<div class="tabs">
			<a href="/lighting/gallery/gallerymain.do" class="tab">오늘어때 포토 갤러리</a>
			<a href="/lighting/gallery/gallerymypage.do" class="tab" style="opacity: 0.6">내 이미지 보기</a>
				
			     <form action="/lighting/gallery/gallerymain.do" method="GET" onsubmit="return false;">
        <select class="dropdown" id="sort" name="sort">  
            <option value="latest">최신순</option>  
            <option value="oldest">오래된 순</option>  
        </select>  
    </form> 
			
		</div>

		<div class="buttons">
			<button class="upload" onclick="openUploadModal()">이미지 올리기</button>
		</div>

		<div class="gallery" id="imageGallery">
		  <c:forEach var="photoFilename" items="${photoFilenames}">  
           <img src="${pageContext.request.contextPath}/images/${photoFilename}" alt="Photo"/>
        </c:forEach>   
		</div>
	</div>

	<!-- 이미지 업로드 모달 -->
	<form action="/lighting/gallery/galleryok.do" method="post" enctype="multipart/form-data">
		<div class="modal-overlay" id="uploadModal">
			<div class="modal">
				<h2>이미지 업로드</h2>
				<input type="file" accept="image/*" id="imageUpload" name="imageUpload" required
					onchange="previewImage()" /> <img id="imagePreview" alt="미리보기"
					style="display: none; width: 100%; margin-bottom: 15px; border-radius: 8px;" />
				<button class="btn btn-primary" onclick="uploadImage()"
					type="submit">이미지 업로드</button>
			</div>
		</div>
	</form>

	<!-- 이미지 모달 -->
	<div class="modal-overlay" id="imageModal" onclick="closeImageModal()">
		<div class="modal">
			<img id="modalImage"
				style="width: 100%; height: auto; border-radius: 8px;">
		</div>
	</div>
	<div id="footer">
		<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	</div>
</body>
	<script>
		function openUploadModal() {
			document.getElementById("uploadModal").style.display = "flex";
			document.getElementById("imagePreview").style.display = "none";
		}

		function closeUploadModal() {
			document.getElementById("uploadModal").style.display = "none";
			document.getElementById("imageUpload").value = "";
			document.getElementById("imagePreview").style.display = "none";
		}

		function previewImage() {
			const fileInput = document.getElementById("imageUpload");
			const imagePreview = document.getElementById("imagePreview");

			if (fileInput.files.length > 0) {
				const file = fileInput.files[0];
				const reader = new FileReader();

				// 파일이 로드되면 미리보기 이미지를 설정  
				reader.onload = function(e) {
					imagePreview.src = e.target.result;
					imagePreview.style.display = "block";
				}

				reader.readAsDataURL(file);
			}
		}

		function uploadImage() {
			const fileInput = document.getElementById("imageUpload");

			if (fileInput.files.length === 0) {
				alert("업로드할 이미지를 선택하세요!");
				return;
			}

			const file = fileInput.files[0];

			// 새로운 이미지를 갤러리에 추가  
			/* const gallery = document.getElementById("imageGallery");
			const newImageDiv = document.createElement("div");
			const imgElement = document.createElement("img");
			imgElement.src = URL.createObjectURL(file);
			imgElement.alt = "업로드된 이미지";
			imgElement.style.width = "100%";

			newImageDiv.appendChild(imgElement);
			gallery.appendChild(newImageDiv);

			// 모달 닫기 및 폼 초기화  
			closeUploadModal(); */
			this.form.submit();
		}

		// 모달 외부 클릭 시 닫기  
		window.onclick = function(event) {
			const modal = document.getElementById("uploadModal");
			if (event.target === modal) {
				closeUploadModal();
			}
		};

		function showImageModal(src) {
			const modalImage = document.getElementById("modalImage");
			modalImage.src = src;
			document.getElementById("imageModal").style.display = "flex";
		}

		// 모달 닫기
		function closeImageModal() {
			document.getElementById("imageModal").style.display = "none";
		}

		// 이미지 클릭 이벤트 추가 (이미 업로드된 이미지에도 적용)
		document.addEventListener("click", function(event) {
			if (event.target.tagName === "IMG"
					&& event.target.closest(".gallery")) {
				showImageModal(event.target.src);
			}
		});

		// 모달 바깥 클릭 시 닫기
		document.getElementById("imageModal").addEventListener("click",
				function(event) {
					if (event.target === this) {
						closeImageModal();
					}
		});
		
		$(document).ready(function() {  
            // 셀렉트 박스의 선택 값이 변경될 때마다 AJAX 요청  
            $('#sort').change(function() {  
                var selectedSort = $(this).val(); // 선택된 정렬 기준 가져오기  

                $.ajax({  
                    url: '/lighting/gallery/gallerymain.do', // 서블릿 URL  
                    type: 'GET',  
                    data: { sort: selectedSort }, // 선택된 값 전송  
                    success: function(response) {  
                        $('#photoGallery').html(response); // 응답으로 받은 HTML을 갤러리 DIV에 추가  
                    },  
                    error: function(xhr, status, error) {  
                        alert('오류가 발생했습니다: ' + error);  
                    }  
                });  
            });  
        });  
	</script>
</html>
