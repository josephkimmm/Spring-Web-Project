<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <meta charset="UTF-8">
    <title>개인 정보 수정</title>
    <style>
        .modal {
            position: fixed;
            z-index: 999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #f9f7ff;
            width: 500px;
            margin: 10% auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
            position: relative;
        }

        .close-button {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
        }

        #logo {
            width: 150px;
        }

        #infoTitle {
            width: 100%;
            text-align: center;
            font-weight: bold;
            margin: 30px auto 30px;
            font-size: 20px;
            color: black;
        }

        .infoContainer {
            display: flex;
            width: 80%;
            margin: 20px auto;
            padding: 0;
        }

        .infoLabel {
            background-color: #1e62c8;
            color: #fff;
            padding: 10px;
            font-weight: bold;
            white-space: nowrap;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 8px 0px 0px 8px;
            width: 80px;
        }

        .inputContainer input,
        .inputContainer select {
            width: 100%;
            padding: 5px;
            border: none;
            outline: none;
        }

        .inputContainer {
            display: flex;
            align-items: center;
            background-color: #fff;
            border: 2px solid #1e62c8;
            padding: 10px;
            flex: 1;
            box-sizing: border-box;
            border-radius: 0px 8px 8px 0px;
        }

        .submitButton {
            display: block;
            width: 60%;
            padding: 10px 0;
            margin: 30px auto 20px;
            background-color: #1e62c8;
            border: none;
            color: #fff;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);
        }

        .submitButton:hover {
            background-color: #0056b3;
        }

        #sido, #gugun {
            padding: 8px;
            border: none;
            outline: none;
            width: 100%;
        }

        .locationContainer {
            width: 80%;
            margin: 20px auto;
        }

        .selectWrapper {
            display: flex;
        }

        .locationContainer p {
            text-align: center;
            color: black;
            font-size: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close-button">&times;</span>
            <div>
                <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
            </div>
            <div id="infoTitle">개인정보 수정</div>
            <form method="POST" action="/lighting/mypage/updateinfook.do" onsubmit="return handleSubmit();">
                <div class="infoContainer">
                    <div class="infoLabel">전화번호</div>
                    <div class="inputContainer">
                        <input type="tel" name="tel" placeholder="전화번호" class="infoInput" value="${member.tel}">
                    </div>
                </div>
                <div class="infoContainer">
                    <div class="infoLabel">닉네임</div>
                    <div class="inputContainer">
                        <input type="text" name="nickname" placeholder="닉네임" class="infoInput" value="${member.nickname}">
                    </div>
                </div>

                <div class="locationContainer">
                    <p>활동 지역 변경</p>
                    <div class="infoContainer">
                        <div class="infoLabel">시/도 변경</div>
                        <div class="inputContainer">
						<!-- 시/도 -->
						<select id="sido" name="sido">
						    <option ${sido == '서울특별시' ? 'selected' : ''}>서울특별시</option>
						    <option ${sido == '인천광역시' ? 'selected' : ''}>인천광역시</option>
						    <option ${sido == '경기도' ? 'selected' : ''}>경기도</option>
						    <option ${sido == '경상남도' ? 'selected' : ''}>경상남도</option>
						    <option ${sido == '경상북도' ? 'selected' : ''}>경상북도</option>
						    <option ${sido == '대구광역시' ? 'selected' : ''}>대구광역시</option>
						    <option ${sido == '전라남도' ? 'selected' : ''}>전라남도</option>
						    <option ${sido == '전라북도' ? 'selected' : ''}>전라북도</option>
						    <option ${sido == '광주광역시' ? 'selected' : ''}>광주광역시</option>
						    <option ${sido == '충청남도' ? 'selected' : ''}>충청남도</option>
						    <option ${sido == '충청북도' ? 'selected' : ''}>충청북도</option>
						    <option ${sido == '대전광역시' ? 'selected' : ''}>대전광역시</option>
						    <option ${sido == '강원도' ? 'selected' : ''}>강원도</option>
						    <option ${sido == '제주도' ? 'selected' : ''}>제주도</option>
						</select>
						
						<!-- 구/군 -->
						<select id="gugun" name="gugun" 
						    <c:if test="${sido ne '서울특별시'}">disabled</c:if>>
						    <option ${gugun == '강남구' ? 'selected' : ''}>강남구</option>
						    <option ${gugun == '강동구' ? 'selected' : ''}>강동구</option>
						    <option ${gugun == '강북구' ? 'selected' : ''}>강북구</option>
						    <option ${gugun == '강서구' ? 'selected' : ''}>강서구</option>
						    <option ${gugun == '관악구' ? 'selected' : ''}>관악구</option>
						    <option ${gugun == '광진구' ? 'selected' : ''}>광진구</option>
						    <option ${gugun == '구로구' ? 'selected' : ''}>구로구</option>
						    <option ${gugun == '금천구' ? 'selected' : ''}>금천구</option>
						    <option ${gugun == '노원구' ? 'selected' : ''}>노원구</option>
						    <option ${gugun == '도봉구' ? 'selected' : ''}>도봉구</option>
						    <option ${gugun == '동대문구' ? 'selected' : ''}>동대문구</option>
						    <option ${gugun == '동작구' ? 'selected' : ''}>동작구</option>
						    <option ${gugun == '마포구' ? 'selected' : ''}>마포구</option>
						    <option ${gugun == '서대문구' ? 'selected' : ''}>서대문구</option>
						    <option ${gugun == '서초구' ? 'selected' : ''}>서초구</option>
						    <option ${gugun == '성동구' ? 'selected' : ''}>성동구</option>
						    <option ${gugun == '성북구' ? 'selected' : ''}>성북구</option>
						    <option ${gugun == '송파구' ? 'selected' : ''}>송파구</option>
						    <option ${gugun == '양천구' ? 'selected' : ''}>양천구</option>
						    <option ${gugun == '영등포구' ? 'selected' : ''}>영등포구</option>
						    <option ${gugun == '용산구' ? 'selected' : ''}>용산구</option>
						    <option ${gugun == '은평구' ? 'selected' : ''}>은평구</option>
						    <option ${gugun == '종로구' ? 'selected' : ''}>종로구</option>
						    <option ${gugun == '중구' ? 'selected' : ''}>중구</option>
						    <option ${gugun == '중랑구' ? 'selected' : ''}>중랑구</option>
						</select>
                        </div>
                    </div>
                </div>
                <button class="submitButton">개인 정보 수정 완료</button>
            </form>
        </div>
    </div>
    <script>
        $(".close-button").click(function() {
            $("#myModal").css("display", "none");
        });

        $("#sido").change(function() {
            if ($(this).val() === "서울특별시") {
                $("#gugun").prop("disabled", false);
            } else {
                $("#gugun").prop("disabled", true).val("");
            }
        });

        function handleSubmit() {
            return true;
        }
    </script>
</body>
</html>