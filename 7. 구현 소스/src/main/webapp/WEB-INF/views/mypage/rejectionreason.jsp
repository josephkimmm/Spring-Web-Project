<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <meta charset="UTF-8" />
    <title>거부 사유 모달 예시</title>
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
            width: 500px;
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
            width: 150px;
        }
        #infoTitle {
            width: 100%;
            text-align: center;
            font-weight: bold;
            margin: 30px auto 0;
            font-size: 20px;
        }
        #infoContent {
            display: flex;
            width: 80%;
            height: 100px;
            margin: 10px auto;
            padding: 10px;
            font-size: 18px;
            border-radius: 8px;
            background-color: #fff;
            border: 1px solid #1e62c8;
            justify-content: center;
            align-items: center;
            text-align: center;
            flex-direction: column;
        }
        #ment {
            display: flex;
            width: 80%;
            margin: 10px auto;
        }
        .infoContainer {
            display: flex;
            width: 40%;
            margin: 60px auto;
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
            border-radius: 8px 0 0 8px;
        }
        .radioContainer {
            display: flex;
            background-color: #fff;
            border: 2px solid #1e62c8;
            padding: 10px;
            flex: 1;
            text-align: center;
            box-sizing: border-box;
            border-radius: 0 8px 8px 0;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
    <div id="rejectModalElement" class="modalElement">
        <div class="modalContent">
            <span id="closeRejectModalButton" class="closeModalButton" onclick="window.close();">&times;</span>
            <div>
                <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
            </div>
            <div id="infoTitle">${nickname} 님이 생성한</div>
            <div id="infoContent">${title}</div>
            <div id="ment">모임에 거부되었습니다.</div>
            <div class="infoContainer">
                <div class="infoLabel">거부 사유</div>
                <div class="radioContainer">${reason}</div>
            </div>
        </div>
    </div>
</body>
</html>
