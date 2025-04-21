<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>  
	<meta charset="UTF-8">
	<title>오늘어때?</title>
	<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
	<style>
		#headerContainer {
			width: 1300px;
			margin: 0 auto;
			height: auto;
			background-color: #f9f7ff;
		}
	
		main {
			width: 1300px;
			height: auto;
			margin: 0 auto;
			background-color: #F9F7FF;
			overflow: hidden;
			padding-top: 100px;
		}
		
		main > hr {
			border: 1.2px solid #1E62C8;
			margin: 0;
		}
		
		#topBox {
			height: 400px;
			display: flex;
		}
		
		#hostBox {
			width: 180px;
			height: 270px;
			margin: 50px 20px 0 20px;
			border: 5px solid #1E62C8;
			border-radius: 10px;
			box-sizing: border-box;
		}
		
		#hostBox > h1 {
			text-align: center;
			margin: 10px 0 20px 0;
		}
		
		#imgProfile {
			width: 100px;
			height: 100px;
			margin: auto auto 5px auto;
			display: block;
			cursor: pointer;
		}
		
		#imgIcon {
			width: 25px;
			height: 25px;
		}
		
		#hostInfoBox, #hostEvaluationBox {
			text-align: center;
		}
		
		#imgStar {
			width: 25px;
			height: 25px;
		}
		
	    #hostEvaluationBox > span {
        	vertical-align: middle;
		}

		#imgStar {
			vertical-align: bottom;
		}
		
		#imgMainBox {
			width: 950px;
			height: 300px;
			overflow: hidden;
			margin: 30px 0;
			text-align: center;
		}
		
		#imgMain {
			/* object-fit: cover; */
			/* object-fit: contain; */
			width: 100%;
            object-position: center;
            height: 100%;
            object-fit: cover;
		}
		
		#btnBox {
			margin-top: 30px;
			width: 150px;
			text-align: center;
		}
		
		#btnBackward {
			width: 100px;
			height: 30px;
			background-color: #1E62C8;
			color: white;
			border: none;
			border-radius: 5px;
		}
		
		#contentBox {
	        width: 950px;
	        margin-left: 220px; /* 수정 */
	        display: grid; /* 변경 */
	        grid-template-columns: 700px 250px; /* 추가 */
	        grid-template-rows: auto 50px; /* 추가 */
	        gap: 0px; /* Adjust gap between grid items if needed */
	    }

	    #titleAndCon {
	        width: 700px;
	        /* overflow: hidden; */
	        min-height: 400px;
	        box-sizing: border-box;
	    }

	    #infoBox {
	        width: 250px; /* 추가 */
	        overflow: hidden;
	        box-sizing: border-box;
	        min-height: 500px;
	    }
	    
	    #participationBox {
	        height: 50px;
	        width: 950px; /* 추가 */
	        border: 1px solid #1E62C8;
	        overflow: hidden;
	        grid-column: 1 / span 2; /* 추가 */
	        display: flex;
    		align-items: left;
	    }
		
		#title {
			font-size: 32px;
            font-weight: bold;
            color: #0D0143;
		}
		
		#locAndDateBox {
			margin-top: 5px;
			color: #505050;
		}
		
		#content {
			margin-top: 20px;
			margin-left: 20px;
		}
		
		#capacityBox {
			font-size: 20px;
			color: #616161;
			font-weight: bold;
			text-align: right;
			margin: 5px 5px 0 0;
		}
		
		#mapBox {
			margin-top: 50px;
			width: 250px;
			height: 250px;
			border: 5px solid #616161;
			box-sizing: border-box;
			text-align: center;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		#locationBox {
			padding: 3px 0 0 3px;
			font-size: 18px;
			color: #616161;
			font-weight: bold;
		}
		
		#locationBox > span {
			display: block;
		}
		
		.imgParticipants {
			width: 40px;
			height: 40px;
			margin: 5px;
		}
		.imgParticipants:hover {
			cursor: pointer;
		}
		
		#imgArrow {
			width: 40px;
			height: 40px;
			margin: 5px;
			margin-left: auto;
		}
		
		#bottomBox {
			height: 100px;
			display: flex;
		    justify-content: space-between; /* 아이템들을 양 끝으로 정렬 */
		    align-items: center; /* 아이템들을 수직 가운데 정렬 */
		    padding: 0 20px; /* 좌우 패딩 추가 */
		}
		
		#btnDelete {
			width: 120px;
			height: 35px;
			border: none;
			background-color: #1E62C8;
			color: white;
			border-radius: 5px;
			font-size: 18px;
		}
		
		#imgWish {
			width: 50px;
			height: 50px;
		}
		
		#imgWish:hover {
			cursor: pointer;
		}
		
		#btnShare {
			width: 60px;
			height: 30px;
			border: none;
			background-color: #99C2FE;
			color: white;
			border-radius: 5px;
			font-size: 16px;
		}
		
		#btnParticipate {
			width: 150px;
			height: 60px;
			border: none;
			background-color: #1E62C8;
			color: white;
			border-radius: 5px;
			font-size: 24px;
		}

        #rightBox {
            display: flex; /* 오른쪽 요소들을 묶어서 Flexbox로 정렬 */
            align-items: center; /* 수직 가운데 정렬 */
            gap: 10px; /* 요소들 사이의 간격 조절 (원하는 값으로 변경) */
        }
        
        #btnParticipate:hover, #btnShare:hover, #btnDelete:hover, #btnBackward:hover {
        	cursor: pointer;
        }

        #btnDisabled {
            width: 150px;
			height: 60px;
			border: none;
			background-color: #C81E1E;
			color: white;
			border-radius: 5px;
			font-size: 24px;
            display: none;
        }
        
        #btnFinish {
     	    width: 150px;
			height: 60px;
			border: none;
			background-color: #C81E1E;
			color: white;
			border-radius: 5px;
			font-size: 24px;
            display: none;
            cursor: pointer;
        }

	</style>
</head>
<body>
	<div id="headerContainer">
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	</div>
	<main>
		<hr>
		<div id="topBox">
			<div id="hostBox">
				<h1>모임장</h1>
				<img alt="프로필이미지" src="" id="imgProfile">
				<div id="hostInfoBox">
					<img alt="등급" src="" id="imgIcon">
					<span>
					<!-- 닉네임 -->
					</span>
				</div>
				<div id="hostEvaluationBox">
					<img alt="등급" src="/lighting/images/별점.png" id="imgStar">
					<span>
						<!-- 4.2점 -->
					</span>
				</div>
			</div>
			
			<div id="imgMainBox">
				<img alt="메인이미지" src="" id="imgMain">
			</div>
			<div id="btnBox">
				<button type="button" id="btnBackward">
					<b>⇐ 이전 화면</b>
				</button>
			</div>
		</div><!-- topBox -->
		
		<div id="contentBox">
			<div id="titleAndCon">
				<div id="title">
					<!-- 4월 3일 역삼에서 삼겹살 드실 3명 구해봅니다! -->
				</div> 
				<div id="locAndDateBox">
					<span>장소 : 
						<span id="sido"><!-- 서울특별시 --></span> /
						<span id="gugun"><!-- 강남구 --></span>
					</span>
					<span>
					&ensp;&ensp;&ensp;시간 :
						<span id="month"><!-- 4 --></span>월
						<span id="day"><!-- 3 --></span>일
						<span id="hour"><!-- 19 --></span> :
						<span id="minute"><!-- 20 --></span>
					</span>
				</div>
				
				<div id="content">
                    <!-- 내용 -->
				</div> 
			</div>
			
			<div id="infoBox">
				<div id="capacityBox">
					<span>모집인원 : </span>
					<span><!-- 3 -->></span>
					<span>명</span>
				</div>
				<div id="mapBox">
				</div>
				<div id="locationBox">
					<div>
						<!--장소명 : 역삼 홍콩 반점인-->
					</div>
				</div>
			</div>
			
			<div id="participationBox">
				<!--<img alt="프로필이미지" src="/lighting/images/icon.png" class="imgParticipants">-->
				<img alt="화살표" src="/lighting/images/arrow.png" id="imgArrow">
			</div>
		</div><!-- contentBox -->
		
		<div id="bottomBox">
			<button type="button" id="btnDelete">모임 삭제 🗑️</button>
			<div id="rightBox">
                <img alt="찜" src="/lighting/images/찜하기전버튼.png" id="imgWish" onclick="toggleImage();">
                <button type="button" id="btnShare">공유</button>
                <button type="button" id="btnParticipate">참석하기</button>
                <button type="button" id="btnDisabled">종료된 모임</button>
                <button type="button" id="btnFinish">모임종료</button>
            </div>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c1697336f6cbeae05fbfbf1920de091c"></script>
	<script>
		let tblMeetingPostSeq = ${tblMeetingPostSeq};
		let loginedtblMemberSeq = "";
		
		// auth가 null이거나 빈 문자열인지 확인
		if ("${auth}" == "" || "${auth}" == "null") {
			console.log('auth확인 = 비회원');
			loginedtblMemberSeq = 0;
		} else {
			// auth가 숫자인지 확인
			if (!isNaN("${auth}")) {
				console.log('auth확인 = 회원/세션할당');
				loginedtblMemberSeq = parseInt("${auth}"); // 숫자로 변환하여 할당
			} else {
				console.log('비상비상');
				loginedtblMemberSeq = 0; // 숫자가 아니면 0으로 처리하거나, 다른 로직을 추가
			}
		}
		
		
	function toggleImage() {
        const img = document.getElementById("imgWish");
        // getAttribute로 실제 지정한 src 값을 가져옴
        if (img.getAttribute("src") === "/lighting/images/찜하기전버튼.png") {
            //찜하기
            if (loginedtblMemberSeq == 0) {
				location.href='/lighting/user/login.do';
				return;
            } else {
            	img.setAttribute("src", "/lighting/images/찜하기버튼.png");
            	addWish();
            }
        } else {
			//취소
            img.setAttribute("src", "/lighting/images/찜하기전버튼.png");
			deleteWish();
        }
    }

	function addWish() {
		$.ajax({ //AddWish.java
			url: '/lighting/meeting/addwish.do',
	        type: 'GET',
	        data: {
	        	tblMeetingPostSeq: tblMeetingPostSeq,
	        	loginedtblMemberSeq: loginedtblMemberSeq
	        },
			error: function(a, b, c) {
	            console.error(a,b,c);
	        }
		});
	}

	function deleteWish() {
		$.ajax({ //DeleteWish.java
			url: '/lighting/meeting/deletewish.do',
	        type: 'GET',
	        data: {
	        	tblMeetingPostSeq: tblMeetingPostSeq,
	        	loginedtblMemberSeq: loginedtblMemberSeq
	        },
			error: function(a, b, c) {
	            console.error(a,b,c);
	        }
		});
	}
	
	function createMap(Latitude, Longitude) { //map 생성 함수

		const container = document.getElementById('mapBox');
		const options = {

			center: new kakao.maps.LatLng(Latitude, Longitude),
			level: 4
		};
		        	
		const map = new kakao.maps.Map(container, options); //map 만들기
		map.setDraggable(false);
		map.setZoomable(false);
		
		var markerImage = new kakao.maps.MarkerImage(
			    '/lighting/images/search.png', // 이미지 경로
			    new kakao.maps.Size(50, 50) // 이미지 크기
		);
		
		var marker = new kakao.maps.Marker({
		    position: new kakao.maps.LatLng(Latitude, Longitude),
		    image: markerImage
		});
		
		marker.setMap(map);
		
	}
	
	function writerClick() {
		const seq = $(this).data('tblmemberseq');

	    const url = '/lighting/meeting/addfriend.do?requestedMemberSeq=' + seq + '&requestingMemberSeq=' + loginedtblMemberSeq;
	    
	    window.open(url, '_blank', 'width=600,height=650,resizable=no,menubar=no,toolbar=no,location=no,status=no');
	}
	
	//1. 글 번호 넘겨서 작성자 정보 가져오기
	//2. 글 번호로 글 내용 가져오기
	//2. 글 번호로 맵 정보 가져오기
	//3. 글 번호로 참가자 가져오기
	let tblMemberSeq = "";
	let gender = "";
	
	$.ajax({//1.
        url: '/lighting/meeting/getmemberinfo.do',
        type: 'GET',
        data: 'tblMeetingPostSeq=' + tblMeetingPostSeq,
        dataType: 'json',
        success: function(result) {
            $('#imgProfile').attr('src', '/lighting/images/' + result.photoFileName);
            tblMemberSeq = result.tblMemberSeq;
            $('#imgProfile').attr('data-tblmemberseq', tblMemberSeq);
            $('#imgProfile').on('click', writerClick);
            $('#hostInfoBox > span').text(result.nickname);
            gender = result.gender;
            
            const score = parseFloat(result.score);
            
            if (score == 0) {
            	$('#hostEvaluationBox > span').text('점수없음');
            } else {
	            $('#hostEvaluationBox > span').text(score + '점');
            }
            
            let icon = "";
            
            if (score < 2) {
				//src="/lighting/images/마스터.png" id="imgIcon"
            	icon = '실버.png';
            } else if (score < 3) {
            	icon = '골드.png';
            } else if (score < 4) {
            	icon = '다이아.png';
            } else {
            	icon = '마스터.png';
            }
            
            $('#imgIcon').attr('src', '/lighting/images/' + icon);
            $('#sido').text(result.sido);
            $('#gugun').text(result.gugun);
            
            hiddenBox();
            
        },
        error: function(a, b, c) {
            console.error(a,b,c);
        }
    });
	
	let PostDate = "";
	let Latitude = "";
	let Longitude = "";
	
	$.ajax({//2.
		url: '/lighting/meeting/getpostinfo.do',
        type: 'GET',
        data: 'tblMeetingPostSeq=' + tblMeetingPostSeq,
        dataType: 'json',
        success: function(result) {
			//console.log(result.StartTime);
        	$('#title').text(result.Title);
        	const content = result.Content.replaceAll("\n", "<br>");
        	$('#content').html(content);
        	PostDate = result.PostDate;
        	$('#locationBox').text('장소명 : ' + result.Location);
        	$('#capacityBox :nth-child(2)').text(result.Capacity);
        	$('#imgMain').attr('src', '/lighting/images/' + result.PhotoFileName);
        	Latitude = result.Latitude;
        	Longitude = result.Longitude;
        	
        	EndTime = result.EndTime;
        	StartTime = result.StartTime;
        	
        	const dateStr = result.StartTime;

			// 날짜와 시간을 분리
			const [datePart, timePart] = dateStr.split(' ');
			
			// 날짜와 시간 각각을 나눔
			const [year, month, day] = datePart.split('-');
			const [hour, minute, second] = timePart.split(':');
			
			// 0을 제거하는 함수
			const removeLeadingZero = (str) => str.startsWith('0') ? str.slice(1) : str;
			
			// 결과값 처리
			const parsedMonth = removeLeadingZero(month);
			const parsedDay = removeLeadingZero(day);
			const parsedHour = hour;
			const parsedMinute = minute;
			
			$('#month').text(parsedMonth);
			$('#day').text(parsedDay);
			$('#hour').text(parsedHour);
			$('#minute').text(parsedMinute);
			
			const currentTime = new Date(); // 현재 시간

			// 문자열을 Date 객체로 변환
			const parsedTime = new Date(EndTime.replace(' ', 'T'));

			// 비교
			if (parsedTime > currentTime) {
				
			} else if (parsedTime < currentTime) {
			 	// btnDisabled 요소의 display: none 제거
			    $('#btnDisabled').css('display', 'block');
			    $('#btnParticipate').css('display', 'none');
			    $('#btnFinish').css('display', 'none');
			    //$('#btnDelete').css('visibility', 'hidden');
			} else {

			}
			if (Latitude == null || Latitude == "") {
				$('#mapBox').html('작성자가 위치를 정하지 않았습니다!');
			} else {
				createMap(Latitude, Longitude);
			}
        },
		error: function(a, b, c) {
            console.error(a,b,c);
        }
	});
	
	$.ajax({//3.
		url: '/lighting/meeting/getparticipantinfo.do',
        type: 'GET',
        data: 'tblMeetingPostSeq=' + tblMeetingPostSeq,
        dataType: 'json',
        success: function(result) {
			//console.log(result);
			
			result.forEach(function(member){
				// img 태그 생성
				let img = $('<img />')
				    .attr('src', '/lighting/images/' + member.PhotoFileName)
				    .attr('class', 'imgParticipants')
				    .attr('data-TblMemberSeq', member.TblMemberSeq);

				$('#participationBox').prepend(img);
				
			});
        },
		error: function(a, b, c) {
            console.error(a,b,c);
        }
	});
	
	$('#btnBackward').click(()=>{
		history.back();
	});
	
	$('#btnDelete').click(()=>{
		window.open('/lighting/meeting/delete.do?tblMeetingPostSeq=' + tblMeetingPostSeq, '_blank', 'width=780,height=800,resizable=no,menubar=no,toolbar=no,location=no,status=no');
	});
	
	$('#btnFinish').click(()=>{
		$.ajax({//모임 종료하기
			url: '/lighting/meeting/finish.do',
	        type: 'GET',
	        data: {
	        	tblMeetingPostSeq: tblMeetingPostSeq
	        },
	        success: function(result) {
				location.reload();
	        },
			error: function(a, b, c) {
	            console.error(a,b,c);
	        }
		});
	});
	
	function openServletInNewWindow(servletUrl) {
        window.open(servletUrl, "_blank", "width=780,height=800,scrollbars=no");
    }
	
	function hiddenBox() {
		//tblMemberSeq = 글 작성자 번호
		//loginedtblMemberSeq = 로그인한 회원 번호, 비로그인 = 0
		if (loginedtblMemberSeq == tblMemberSeq) {//로그인 == 작성자
		    $('#imgWish').css('visibility', 'hidden');
		    $('#btnShare').css('visibility', 'hidden');
		    $('#btnParticipate').css('visibility', 'hidden');
		    $('#btnFinish').css('display', 'block');
		} else {
			if (loginedtblMemberSeq == 0) {//비로그인
				$('#btnDelete').css('visibility', 'hidden');
			} else {//로그인 != 작성자
				$('#btnDelete').css('visibility', 'hidden');
			}
		}
	}
	
	$('#participationBox').on('click', '.imgParticipants', function() {
	    const seq = $(this).data('tblmemberseq');

	    const url = '/lighting/meeting/addfriend.do?requestedMemberSeq=' + seq + '&requestingMemberSeq=' + loginedtblMemberSeq;
	    
	    window.open(url, '_blank', 'width=600,height=650,resizable=no,menubar=no,toolbar=no,location=no,status=no');
		    
	});
	
    $('#btnShare').click(() => {
        const currentUrl = window.location.href;

        // 클립보드에 복사
        navigator.clipboard.writeText(currentUrl)
            .then(() => {
                // 복사 성공 시 알림
                alert('현재 주소가 클립보드에 복사되었습니다.');
            })
            .catch(err => {
                // 복사 실패 시 에러 처리
                console.error('클립보드 복사에 실패했습니다.', err);
                alert('클립보드 복사에 실패했습니다. 수동으로 복사해주세요.');
            });
    });

	$.ajax({//찜하기 버튼 결정
		url: '/lighting/meeting/getwish.do',
        type: 'GET',
        data: {
        	tblMeetingPostSeq: tblMeetingPostSeq,
        	loginedtblMemberSeq: loginedtblMemberSeq
        },
        dataType: 'json',
        success: function(result) {
			//imgWish > src 변경
			//console.log(result.result);
			if (result.result == 0) {
				$('#imgWish').attr('src', '/lighting/images/찜하기전버튼.png');
			} else {
				$('#imgWish').attr('src', '/lighting/images/찜하기버튼.png');
			}
        	
        },
		error: function(a, b, c) {
            console.error(a,b,c);
        }
	});
    
	function addParticipationRequest() {
		
		if (loginedtblMemberSeq == 0) {//비로그인 참가신청 >> 로그인으로
			location.href='/lighting/user/login.do';
			return;
        } 
		
		$.ajax({//참가신청 insert하기 tblParticipationRequest
			url: '/lighting/meeting/addparticipationrequest.do',
	        type: 'GET',
	        data: {
	        	tblMeetingPostSeq: tblMeetingPostSeq,
	        	loginedtblMemberSeq: loginedtblMemberSeq
	        },
	        dataType: 'json',
	        success: function(result) {
				if (result.result == 1) {
					alert('참가 신청 완료!');
					$('#btnParticipate').off('click');
					$('#btnParticipate').text('신청취소');
					$('#btnParticipate').on('click', deleteParticipationRequest);
				} else {
					alert('참가 신청 실패!');
				}
	        },
			error: function(a, b, c) {
	            console.error(a,b,c);
	        }
		});
	}
	
	function deleteParticipationRequest() {
		
		if (loginedtblMemberSeq == 0) {//비로그인 참가신청 >> 로그인으로
			location.href='/lighting/user/login.do';
			return;
        } 
		
		$.ajax({//참가신청 delete하기 tblParticipationRequest
			url: '/lighting/meeting/deleteparticipationrequest.do',
	        type: 'GET',
	        data: {
	        	tblMeetingPostSeq: tblMeetingPostSeq,
	        	loginedtblMemberSeq: loginedtblMemberSeq
	        },
	        dataType: 'json',
	        success: function(result) {
				if (result.result == 1) {
					alert('취소 완료!');
					$('#btnParticipate').off('click');
					$('#btnParticipate').text('참석하기');
					$('#btnParticipate').on('click', addParticipationRequest);
				} else {
					alert('취소 실패!');
				}
	        },
			error: function(a, b, c) {
	            console.error(a,b,c);
	        }
		});
	}	
	
	$.ajax({//참가신청 했는지 안했는지 확인
		url: '/lighting/meeting/getparticipationrequest.do',
        type: 'GET',
        data: {
        	tblMeetingPostSeq: tblMeetingPostSeq,
        	loginedtblMemberSeq: loginedtblMemberSeq
        },
        dataType: 'json',
        success: function(result) {
			//결과에 따라 add, delete, finish
        	//1 >> 이미 신청함 NULL >> 취소 이벤트 추가
        	//2 >> 이미 신청함 'Y' >> 취소 불가
        	//3 >> 이미 신청함 'N' >> 거절당함
        	//0 >> 최초 신청
        	console.log(result.result);
        	
			if(result.result == 0) {
				$('#btnParticipate').text('참석하기');
				$('#btnParticipate').on('click', addParticipationRequest);
			} else if(result.result == 1) {
				$('#btnParticipate').text('신청취소');
				$('#btnParticipate').on('click', deleteParticipationRequest);
			} else if(result.result == 2) {
				$('#btnParticipate').text('승인완료');
				$('#btnParticipate').css('cursor', 'default');
			} else if(result.result == 3) {
				$('#btnParticipate').text('거절(사유)');
				$('#btnParticipate').css('cursor', 'default');
			}
        	
        },
		error: function(a, b, c) {
            console.error(a,b,c);
        }
	});
	
	</script>
	
</body>
</html>
