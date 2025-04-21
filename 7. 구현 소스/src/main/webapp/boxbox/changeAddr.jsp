<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>개인 정보 수정</title>
    <style>
        .modal {
            display: none;
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

        #infoBox {
            width: 100%;
            padding: 5px;
            border: none;
            outline: none;
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

        #openModalBtn {
            margin: 30px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
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
    <button id="openModalBtn">개인 정보 수정</button>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close-button">&times;</span>
            <div>
                <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
            </div>
            <div id="infoTitle">개인정보 수정</div>
            <div class="infoContainer">
                <div class="infoLabel">전화번호</div>
                <div class="inputContainer">
                    <input type="tel" id="infoBox" placeholder="전화번호">
                </div>
            </div>
            <div class="infoContainer">
                <div class="infoLabel">닉네임</div>
                <div class="inputContainer">
                    <input type="text" id="infoBox" placeholder="닉네임">
                </div>
            </div>

            <div class="locationContainer">
                <p>활동 지역 변경</p>
                <div class="infoContainer">
                    <div class="infoLabel">시/도 변경</div>
                    <div class="inputContainer">
                        <select id="sido">
                            <option>서울시</option>
                            <option>인천광역시</option>
                            <option>경기도</option>
                            <option>경상남도</option>
                            <option>경상북도</option>
                            <option>대구광역시</option>
                            <option>전라남도</option>
                            <option>전라북도</option>
                            <option>광주광역시</option>
                            <option>충청남도</option>
                            <option>충청북도</option>
                            <option>대전광역시</option>
                            <option>강원도</option>
                            <option>제주도</option>
                        </select>
                    </div>
                </div>
                <div class="infoContainer">
                     <div class="infoLabel">구/군 변경</div>
                    <div class="inputContainer">
                        <select id="gugun" disabled>
                            <option>강남구</option>
                            <option>강동구</option>
                            <option>강북구</option>
                            <option>강서구</option>
                            <option>관악구</option>
                            <option>광진구</option>
                            <option>구로구</option>
                            <option>금천구</option>
                            <option>노원구</option>
                            <option>도봉구</option>
                            <option>동대문구</option>
                            <option>동작구</option>
                            <option>마포구</option>
                            <option>서대문구</option>
                            <option>서초구</option>
                            <option>성동구</option>
                            <option>성북구</option>
                            <option>송파구</option>
                            <option>양천구</option>
                            <option>영등포구</option>
                            <option>용산구</option>
                            <option>은평구</option>
                            <option>종로구</option>
                            <option>중구</option>
                            <option>중랑구</option>
                        </select>
                    </div>
                </div>
            </div>

            <button class="submitButton">개인 정보 수정 완료</button>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function() {
            $("#openModalBtn").click(function() {
                $("#myModal").css("display", "block");
            });

            $(".close-button").click(function() {
                $("#myModal").css("display", "none");
            });

            $(window).click(function(event) {
                if (event.target.className === "modal") {
                    $("#myModal").css("display", "none");
                }
            });
            
            // 시/도 선택 시 이벤트 처리
            $("#sido").change(function() {
                if ($(this).val() === "서울시") {
                    $("#gugun").prop("disabled", false);
                } else {
                    $("#gugun").prop("disabled", true);
                    $("#gugun").val("");
                }
            });
        });
        
        
    </script>
</body>
</html>
