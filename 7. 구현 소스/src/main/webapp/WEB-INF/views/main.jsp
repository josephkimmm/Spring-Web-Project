<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<title>오늘어때?</title>

<style>
/* container를 flex로 구성하여 footer가 항상 하단에 위치하도록 함 */
    .container {
        display: flex;
        flex-direction: column;
        min-height: 100vh;
        width: 1300px;
        margin: 0 auto;
        background-color: #f9f7ff;
    }
    /* content 영역은 flex-grow: 1로 설정하여 여유 공간을 채움 */
    .content {
      flex: 1;
    }
    
</style>

</head>
<body>
    <div class="container">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="content">
      <%@ include file="/WEB-INF/views/inc/body.jsp" %>
    </div>
    <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
  </div>

<script></script>

</body>
</html>