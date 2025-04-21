<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>오늘어때?</title>
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: auto;
            text-align: center;
        }
        
        /* 가운데 컨테이너 */
        .container {
            width: 1270px;
            height: 100vh;
            margin: 0;
            background-color: #f9f7ff;
            padding: 40px 20px;
            position: relative;
        }

        #logo {
	        position: absolute;
            left: 20px;
            top: -10px;
            margin: 15px 0 0 0;
            width: 250px;
            height: auto;
            cursor: pointer;
       }

        #logo2 {
            width: 250px;
            margin: 30px 0;
        }

        #loginMsg {
            color: #2765c7;
            font-size: 30px;
            font-weight: bold;
            margin-bottom: 20px;
            font-family: 'Arial', sans-serif;
            margin: 30px;
        }

        #btnCheck {
            width: 400px;
            background-color: #1e62c8;
            color: white;
            font-size: 1.2rem;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
            position: relative;
            top: 130px;
            border-color: none;
        }

        #btnCheck:hover {
            background-color: #1e4ca0;
        }
        
        #message {
            position: relative;
            top: 150px;
            
        }
    </style>
</head>
<body class="body">
    <main class="container">
        <div>
	       <img alt="로고" src="/lighting/images/logo_가로.png" id="logo">
	    </div>
	    <div id="message">
	        <img alt="로고" src="/lighting/images/logo_세로.png" id="logo2">
	        <p id="loginMsg">회원가입이 <br> 완료되었습니다.</p>
	    </div>
	    
	    <form method="GET" action="/lighting/main.do">
	        <button id="btnCheck">모임 참석하러 가기</button>
	    </form>
    </main>
    
    <script>
    
    $('#logo').click(() => { 
        window.location.href =  '/lighting/main.do'; // 메인페이지로 이동
      });
    
    </script>
    <%@ include file="/WEB-INF/views/inc/footer.jsp"%>
    
</body>
</html>