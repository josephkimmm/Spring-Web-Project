<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
/* 일반적인 footer 스타일 (고정되지 않고 content 뒤에 위치) */
    footer {
    width: 1300px;
    margin: 0 auto;
      background-color: #fff;
      padding: 25px 0;
      font-family: 'Pretendard-Regular', sans-serif;
    }
    /* footer 내부 컨테이너 및 영역 스타일 */
    .footer-container {
     /*  width: 1300px; */
      margin: 0 auto 30px;
      display: flex;
      flex-direction: column;
      gap: 20px;
    }
    .footer-links {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      font-size: 14px;
      color: #666;
    }
    .footer-links .left-links,
    .footer-links .right-links {
      display: flex;
      gap: 30px;
      margin: 10px;
    }
    .footer-links .right-links {
      gap: 20px;
    }
    .footer-links a {
      text-decoration: none;
      color: #666;
    }
    .footer-info {
      border-top: 1px solid #ddd;
      padding-top: 20px;
      font-size: 13px;
      color: #999;
      line-height: 2;
      margin: 5px;
     
     }
    .footer-info strong {
      color: #333;
    }
    .footer-social {
      display: flex;
      gap: 15px;
      margin-top: 5px;
      margin: 10px;
    }
    .footer-social a {
      text-decoration: none;
      color: #666;
      font-size: 14px;
    }
</style>


<footer class="fixed-footer">
  <div class="footer-container">
    <!-- 상단 링크 영역 -->
    <div class="footer-links">
      <div class="left-links">
        <a href="#">회사소개</a>
        <a href="#">이용약관</a>
        <a href="#">개인정보처리방침</a>
        <a href="#">자주 묻는 질문</a>
      </div>
      <div class="right-links">
        <span>고객센터: 1588-1234</span>
        <a href="#">1:1 문의하기</a>
      </div>
    </div>
    <!-- 하단 회사 정보 영역 -->
    <div class="footer-info">
      <p>
        (주)오늘뭐해? | 대표자: 홍길동 | 사업자등록번호: 123-45-67890 | 통신판매업신고: 제2025-서울강남-0000호
      </p>
      <p>
        주소: 서울특별시 강남구 테헤란로 123 (오늘뭐해? 빌딩) | 대표번호: 1588-1234 | 이메일: cs@oneulmohae.co.kr
      </p>
      <p>
        © 2025 오늘뭐해? All Rights Reserved.
      </p>
    </div>
    <!-- 소셜 미디어 영역 -->
    <div class="footer-social">
      <a href="#">페이스북</a>
      <a href="#">인스타그램</a>
      <a href="#">트위터</a>
    </div>
  </div>
</footer>
