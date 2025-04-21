<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="css/lighting.css">
 <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<title>오늘어때?</title>
	<style>
	 /* 전체 모달 스타일 */
	        .modal {
	            display: flex; /* 모달 창을 페이지 로드 시 바로 표시 */
	            justify-content: center; /* 가로 중앙 정렬 */
	            align-items: center; /* 세로 중앙 정렬 */
	            position: fixed;
	            top: 0;
	            left: 0;
	            width: 100%;
	            height: 100%;
	            background-color: rgba(0, 0, 0, 0.5); /* 배경 어두운 색 */
	            z-index: 1000; /* 다른 요소들이 모달 위에 오지 않게 함 */
	        }
	
	        .modal-content {
	            background-color: rgb(249, 247, 255); /* 모달 배경 색상 */
	            border-radius: 10px;
	            padding: 20px;
	            width: 500px;
	            height: 500px;
	            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
	            margin: auto;
	            
	            position: relative;
	        }
	
	        .modal-header {
	            font-size: 26px;
	            font-weight: bold;
	            color: black;
	            
	            text-align: center;
	
	            position: relative;
	        }
	            /* 로고 이미지 위치 조정 */
	        #logo {
	            width: 150px; /* 로고 이미지 크기 */
	            /* height: 50px; */
	            /* position: absolute; */ /* 상단 왼쪽에 배치 */
	            /* top: -40px;
	            left: -10px; */ /* 모달 왼쪽 */
	
	            display: block;
	         
	        }
	        
	        .modal-header-text {
	            margin-top: 10px; /* 텍스트만 아래로 내리기 위한 간격 추가 */
	        }
	        
	        /* 닫기 버튼 스타일 */
	        #close-btn {
	            position: absolute;
	            top:10px;
	            right: 10px; /* 오른쪽 끝 */
	            width: 30px; /* 이미지 크기 조정 */
	            height: 30px; /* 이미지 크기 조정 */
	            cursor: pointer; /* 클릭할 수 있음을 나타내는 커서 */
	            /* margin-right: 690px;
	            margin-top:180px; */
	        }
	            /* 강남 스터디 모임과 날짜 텍스트 위치 및 색상 변경 */
	        .modal-header div {
	            color: black; /* 텍스트 색상 변경 */
	            /* margin-top: 30px; */ /* 텍스트를 아래로 내리기 위한 간격 */
	        }
	          /* 2025년 텍스트 크기 조정 */
	        .modal-header .date {
	            font-size: 16px; /* 크기 작게 */
	            font-weight: normal; /* 볼드체 제거 */
	             color: rgb(97, 96, 103);
	              margin-bottom: 20px; /* 간격을 넓히기 위해 여백 추가 */
	             display: block; /* 필요한 경우 텍스트를 블록 요소로 설정 */
	             
	             
	        }
	        /* 별 스타일 */
	        .stars {
	            color: #ccc; /* 기본 회색 별 */
	            font-size: 30px;
	            margin-left: auto;
	            transition: color 0.3s ease; /* 색상 변경에 트랜지션 효과 추가 */
	            cursor: pointer;
	        }
	        
	        /* 별점 컨테이너 스타일 */
	        .rating {
	            display: flex;
	            flex-direction: row-reverse; /* 별을 오른쪽부터 왼쪽으로 배치 */
	            margin-left: auto;
	        }
	        
	        /* 별점 스타일 */
	        .rating input {
			    position: absolute;
			    opacity: 0;
			    pointer-events: none;
			}
	        
	        .rating label {
	            color: #ccc;
	            font-size: 30px;
	            padding: 0 2px;
	            cursor: pointer;
	            transition: all 0.3s ease;
	        }
	        
	        /* 호버 효과: 현재 별과 그 왼쪽의 모든 별 */
	        .rating label:hover,
	        .rating label:hover ~ label,
	        .rating input:checked ~ label {
	            color: #ffcc00;
	        }
	
	        .user-img {
	            width: 50px;
	            height: 50px;
	            border-radius: 50%;
	            right: 10px;
	            
	        }
	
	        .user-info {
	            display: flex;
	            align-items: center;
	            margin-bottom: 10px;
	             border: 2px solid #1e62c8; /* 사용자 정보 박스 테두리 추가 */
	            padding: 10px; /* 여백 추가 */
	            border-radius: 15px; /* 둥근 테두리 */
	            margin-bottom:20px;
	        }
	
	        .user-name {
	            font-size: 18px;
	            font-weight: bold;
	        }
	
	        .modal-footer {
	            display: flex;
	            justify-content: space-between;
	            margin-top: 20px;
	            align-items: center;
	        }
	
	        #btnSubmit {
	            background-color: #1e62c8;
	            color: white;
	            border: none;
	            padding: 10px 20px;
	            border-radius: 5px;
	            cursor: pointer;
	            margin-left:200px;
	            font-size: 15px;
	            margin-top: 10px;
	        }
	
	        #btnSubmit:hover {
	            background-color: #154a96;
	        }
	        
	        .badge-icon {
	            width: 40px;
	            height: 40px;
	            margin-left: 20px;
	        }
	</style>
	<script>
	    //닫기 버튼 클릭 시 모달 창 닫기
	    function closeModal() {
	        document.querySelector('.modal').style.display = 'none';
	    }
	</script>
	</head>
	<body>
	<!-- 모달창 -->
	    <div class="modal">
	        <div class="modal-content">
	            <div class="modal-header">
	                <img src="/lighting/images/logo_가로.png" alt="로고" id="logo">
	                <div class="modal-header-text">
	                
	                ${meetingInfo.title}
	                <br>
	                <span class="date">${meetingInfo.startTime}</span>
	            
	            	</div>
	            </div>
	            
	            <img src="/lighting/images/닫기.png" alt="닫기" id="close-btn" onclick="closeModal()">
	
	            <form method="POST" action="/lighting/mypage/evaluationok.do" id="evalForm">
	            <!-- 평가할 사람이 없을 때 메시지 출력 -->
			  <c:if test="${empty attendees}">
			      <p style="text-align:center; font-size:16px; color:gray; margin-top:20px;">평가할 사용자가 없습니다.</p>
			  </c:if>
				  <c:forEach var="attendee" items="${attendees}" varStatus="status">
		  <div class="user-info">
		      <img src="/lighting/images/${attendee.photoFileName}" class="user-img">
		      <img src="/lighting/images/${attendee.grade}.png" class="badge-icon">
		      <span class="user-name">${attendee.nickname} (${attendee.id})</span>
		      <div class="rating">
		         <input type="radio" id="star5_${status.index}" name="rating_${status.index}" value="5" />
		         <label for="star5_${status.index}">★</label>
		         <input type="radio" id="star4_${status.index}" name="rating_${status.index}" value="4" />
		         <label for="star4_${status.index}">★</label>
		         <input type="radio" id="star3_${status.index}" name="rating_${status.index}" value="3" />
		         <label for="star3_${status.index}">★</label>
		         <input type="radio" id="star2_${status.index}" name="rating_${status.index}" value="2" />
		         <label for="star2_${status.index}">★</label>
		         <input type="radio" id="star1_${status.index}" name="rating_${status.index}" value="1" />
		         <label for="star1_${status.index}">★</label>
		         <input type="hidden" name="userId_${status.index}" value="${attendee.id}" />
		      </div>
		  </div>
		</c:forEach>
		<c:forEach var="attendee" items="${attendees}" varStatus="status">
    		<c:out value="인덱스: ${status.index}, 닉네임: ${attendee.nickname}, ID: ${attendee.id}" /><br/>
		</c:forEach>
				  <p>디버그 - 평가 수: ${evaluationCount}</p>
				  <input type="hidden" name="meetingSeq" value="${meetingSeq}" />
				  <input type="hidden" name="evaluationCount" value="${fn:length(attendees)}" />
				  <button type="submit" id="btnSubmit">평가 완료</button>
				</form>
	        </div>
	    </div>
	    <script>
	 	// 모달 닫기 함수
	    function closeModal() {
	    window.close(); // 팝업창 닫기
		}	
	
	    // 평가 제출 처리
	    document.addEventListener("DOMContentLoaded", function () {
	        const form = document.getElementById('evalForm');

	        form.addEventListener('submit', function (e) {
	        	//e.preventDefault();
	
	            const userCount = parseInt(document.querySelector('input[name="evaluationCount"]').value);
	            console.log("사용자 수:", userCount);
	
	            let allRated = true;
	
	            for (let i = 0; i < userCount; i++) {
	            	const selected = document.querySelector(`input[name="rating_${i}"]:checked`);
	                const name = `rating_${i}`;
	                console.log(`radio name: ${name}, 선택된 값:`, selected ? selected.value : "선택 안됨");
	
	                
	
	                if (!selected) {
	                    allRated = false;
	                    break;
	                }
	            }
	
	            const formData = new FormData(this);
	
	            fetch('/lighting/mypage/evaluationok.do', {
	                method: 'POST',
	                body: formData
	            })
	            .then(response => {
	                console.log("raw response", response);
	                return response.json();
	            })
	            .then(data => {
	                console.log("서버 응답:", data);

	                if (data.result === 'success') {
	                    alert('평가가 완료되었습니다.');
	                    if (window.opener && !window.opener.closed) {
	                        window.opener.location.href = '/lighting/mypage/mypage.do?status=joined';
	                    }
	                    window.close();
	                } else {
	                    alert('평가 제출 중 오류가 발생했습니다.');
	                }
	            })
	            .catch(error => {
	                console.error("Error:", error);
	                alert('평가 제출 중 오류가 발생했습니다.');
	            });

	        });
	    });
	
	    </script>
	</body>
	</html>