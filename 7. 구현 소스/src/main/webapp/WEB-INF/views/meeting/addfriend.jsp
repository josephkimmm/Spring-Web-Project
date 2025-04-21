<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>친구 신청화면</title>
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
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
            display: flex;
            flex-direction: column;
            align-items: center; /* 수직 중앙 정렬 */
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
        
        #userName {
            text-align: center;
            margin: 0 auto;
            font-size: 16px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        #userName img {
            display: block;
            margin: 0 auto 5px; 
        }

        #userName a {
            display: block;
            color: gray;
            font-weight: bold;
        }
        
        #userName span {
            font-size: 20px;
            font-weight: bold;  
        }

        #logo {
            width: 150px;
            position: absolute; 
            top: 25px;         
            left: 25px;        
        }

        #profileImg {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 10px;
        }

        #grade {
            width: 30px;
            margin-right: 5px;
            margin-left: 10px;'
        }

        .btn {
            margin: 20px 0;
        }

        #friendApply {
            display: block; 
            padding: 10px 20px;
            margin: 0 10px;
            border: none;
            border-radius: 5px;
            background-color: #1e62c8;
            color: white;
            cursor: pointer;
            margin-bottom: 10px;
            width: 150px;
        }
        
        #friendBlock {
            display: block;
            padding: 10px 20px;
            margin: 0 auto;
            border: none;
            border-radius: 5px;
            background-color: red;
            color: white;
            cursor: pointer;
            width: 150px;

        }

        #textBox {
            width: 300px;
            height: 100px;
            padding: 5px;
            border: 1px solid black;
            border-radius: 5px;
            margin-bottom: 20px;
            resize: none; /* 크기 조정 방지 */
            text-align: center;
            background-color: #FFFFFF;
            overflow: auto;
        }
        

        .checkBtn button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #1e62c8;
            color: white;
            cursor: pointer;
            width: 75px;
        }
    </style>
</head>
<body>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close-button">&times;</span>
            
            <div>
                <img src="/lighting/images/logo_가로.png" id="logo">
            </div>
            <div>
                <img src="/lighting/images/${dto.photoFileName}" id="profileImg">
            </div>
            
            <div id="userName">
            	
                <img src="/lighting/images/${scoreimage }" id="grade"><span>${dto.nickname}</span><br> 
                <a>거주 지역: ${dto.sido} / ${dto.gugun}</a>
            </div>
            <div class="btn">
                <input type="button" value="친구 신청" id="friendApply">
                <input type="button" value="차단 등록" id="friendBlock">        
            </div>
            
            <div class="checkBtn">
                <button type="submit" id="btn1">확인</button>
            </div>
        </div>
    </div>
    
    <script>
    	let requestedMemberSeq = ${dto.tblMemberSeq};
    	let requestingMemberSeq = ${requestingMemberSeq};
    	let auth = "${auth}";
    	
    	if (auth == null || auth == "") {
			console.log('통과');
    		auth = 0;
    	}
    	
    	if (requestedMemberSeq == requestingMemberSeq) {
			$('#friendApply').css('visibility', 'hidden');
			$('#friendBlock').css('visibility', 'hidden');
    	}
    
    	$('.close-button').click(function() {
			window.close();
    	});
    	
    	$('#btn1').click(function() {
			window.close();
    	});
    	
    	if (auth != 0) {

	    	$.ajax({//친구신청 테이블 조회
	    		url: '/lighting/meeting/getfriendrequest.do',
	            type: 'GET',
	            data: {
	            	requestingMemberSeq: requestingMemberSeq,
	            	requestedMemberSeq: requestedMemberSeq
	            },
	            dataType: 'json',
	            success: function(result) {
	            	// 1 상대가 나에게 신청
	                // 2 내가 상대에게 신청(미응답)
	                // 3 이미 친구
	                // 4 거절 기록
	                // 5 최초 신청
	                
	                if (result.result == 1) {
						$('#friendApply').attr('value', '수락하기');
						$('#friendApply').css('cursor', 'pointer');
						$('#friendApply').on('click', acceptFriendRequest);
						
	                } else if (result.result == 2) {
	                	$('#friendApply').attr('value', '신청취소');
	                	$('#friendApply').css('cursor', 'pointer');
	                	$('#friendApply').on('click', deleteFriendRequest);
	                	
	                } else if (result.result == 3) {
	                	$('#friendApply').attr('value', '친구삭제');
	                	$('#friendApply').css('cursor', 'pointer');
	                	$('#friendApply').on('click', deleteFriendList);
	                	
	                } else if (result.result == 4) {
	                	$('#friendApply').attr('value', '상대가 거절');
	                	$('#friendApply').css('cursor', 'default');
	                	$('#friendApply').css('background-color', 'red');
	                	
	                } else if (result.result == 5) {
	                	$('#friendApply').on('click', addFriendRequest);
	                }
	                
	            },
	    		error: function(a, b, c) {
	                console.error(a,b,c);
	            }
	    	});
	    	
	    	$.ajax({//블록리스트 조회
	    		url: '/lighting/meeting/getblocklist.do',
	            type: 'GET',
	            data: {
	            	blockerMemberSeq: requestingMemberSeq,
	            	blockedMemberSeq: requestedMemberSeq
	            },
	            dataType: 'json',
	            success: function(result) {
	            	// 0 차단 등록으로 전환(레코드 없음)
	                // 1 차단 해제로 전환(레코드 있음)
	                console.log(result.result);
	                if (result.result == 0) {
	                	$('#friendBlock').attr('value', '차단 등록');
	                	$('#friendBlock').on('click', addBlock);
	                } else if (result.result == 1) {
	                	$('#friendBlock').attr('value', '차단 해제');
	                	$('#friendBlock').on('click', deleteBlock);
	                } 
	            },
	    		error: function(a, b, c) {
	                console.error(a,b,c);
	            }
	    	});
    	} else {
    		$('#friendApply').on('click', login);
    		$('#friendBlock').on('click', login);
    	}
    	
    	function login() {
			opener.location.href='/lighting/user/login.do';
			window.close();
			return;
    	}
    	
    	function acceptFriendRequest() {
			//친구신청 수락
    		$.ajax({
        		url: '/lighting/meeting/acceptfriendrequest.do',
                type: 'GET',
                data: {
                	requestingMemberSeq: requestingMemberSeq,
                	requestedMemberSeq: requestedMemberSeq
                },
                //dataType: 'json',
                success: function() {
                	location.reload();                
                },
        		error: function(a, b, c) {
                    console.error(a,b,c);
                }
        	});
    	}
    	
    	function deleteFriendRequest() {
    		//친구신청 취소
    		$.ajax({
        		url: '/lighting/meeting/deletefriendrequest.do',
                type: 'GET',
                data: {
                	requestingMemberSeq: requestingMemberSeq,
                	requestedMemberSeq: requestedMemberSeq
                },
                //dataType: 'json',
                success: function() {
                	location.reload();                
                },
        		error: function(a, b, c) {
                    console.error(a,b,c);
                }
        	});
    	}
    	
    	function deleteFriendList() {
    		//친구삭제
    		$.ajax({
        		url: '/lighting/meeting/deletefriendlist.do',
                type: 'GET',
                data: {
                	mainMemberSeq: requestingMemberSeq,
                	subMemberSeq: requestedMemberSeq
                },
                //dataType: 'json',
                success: function() {
                	location.reload();                
                },
        		error: function(a, b, c) {
                    console.error(a,b,c);
                }
        	});
    	}
    	
    	function addFriendRequest() {
    		//친구신청
    		$.ajax({
        		url: '/lighting/meeting/addfriendrequest.do',
                type: 'GET',
                data: {
                	requestingMemberSeq: requestingMemberSeq,
                	requestedMemberSeq: requestedMemberSeq
                },
                //dataType: 'json',
                success: function() {
                	location.reload();                
                },
        		error: function(a, b, c) {
                    console.error(a,b,c);
                }
        	});
    	}
    	
    	function addBlock() {
    		$.ajax({//차단신청
        		url: '/lighting/meeting/addblocklist.do',
                type: 'GET',
                data: {
                	blockerMemberSeq: requestingMemberSeq,
                	blockedMemberSeq: requestedMemberSeq
                },
                success: function() {
                	$('#friendBlock').off('click');
                	$('#friendBlock').attr('value', '차단 해제');
                	$('#friendBlock').on('click', deleteBlock);
                },
        		error: function(a, b, c) {
                    console.error(a,b,c);
                }
        	});
    	}
    	
    	function deleteBlock() {
    		$.ajax({//차단해제
        		url: '/lighting/meeting/deleteblocklist.do',
                type: 'GET',
                data: {
                	blockerMemberSeq: requestingMemberSeq,
                	blockedMemberSeq: requestedMemberSeq
                },
                success: function() {
                	$('#friendBlock').off('click');
                	$('#friendBlock').attr('value', '차단 등록');
                	$('#friendBlock').on('click', addBlock);
                },
        		error: function(a, b, c) {
                    console.error(a,b,c);
                }
        	});
    	}
    
    </script>
</body>
</html>
