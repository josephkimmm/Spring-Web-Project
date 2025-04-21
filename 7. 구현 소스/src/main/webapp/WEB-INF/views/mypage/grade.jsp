<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <title>열정등급</title>
    <style>
        /* 모달창 전체 */
        .modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            justify-content: center;
            align-items: center;
            border-radius: 16px;
            display: none; /* 초기에는 숨김 */
        }

        .modal {
            background-color: #f9f7ff;
            padding: 30px;
            border-radius: 12px;
            width: 500px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.25);
            position: relative;
            animation: fadeIn 0.3s ease-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* 닫기 버튼 스타일 개선 */
    .close-btn {
        position: absolute;
        top: 15px;
        right: 15px;
        background: none;
        border: none;
        cursor: pointer;
        padding: 5px;
    }
    .close-btn img {
        width: 18px;
        height: 18px;
        opacity: 0.6;
        transition: opacity 0.2s;
    }
    .close-btn:hover img {
        opacity: 1;
    }

        /* 배지 컨테이너 */
        .badge-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
            margin-top: 25px;
        }

        /* 각 배지 스타일 */
        .badge {
            display: flex;
            align-items: center;
            gap: 15px;
            border: 1px solid #e0e0e0;
            padding: 15px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            background-color: white;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .badge:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
        }

        .badge-icon {
            width: 60px;
            height: 60px;
            margin-left: 15px;
        }

        .badge-info h3 {
            font-size: 16px;
            margin: 0;
            font-weight: 600;
            color: #333;
        }

        .badge-info p {
            font-size: 14px;
            color: #555;
            margin-top: 5px;
            line-height: 1.4;
        }

        /* 각 등급에 따른 스타일 */
        .master {
            border-left: 4px solid #ff6b6b;
        }

        .diamond {
            border-left: 4px solid #4cc9f0;
        }

        .gold {
            border-left: 4px solid #f8961e;
        }

        .silver {
            border-left: 4px solid #adb5bd;
        }
    </style>
</head>
<body onload="openModal()">
    <!-- 모달 -->
    <div class="modal-overlay" id="modalOverlay">
        <div class="modal">
            <button class="close-btn" onclick="closeModal()">
            <img src="https://cdn-icons-png.flaticon.com/512/2961/2961937.png" alt="Close">
        </button>
            <div class="modal-content">
                <div class="badge-container">
                    <div class="badge master">
                        <img src="/lighting/images/마스터.png" alt="Master" class="badge-icon">
                        <div class="badge-info">
                            <h3>마스터 등급</h3>
                            <p>별점 4점 이상 5점 이하인 모임에 열정 넘치는 프로 참여!</p>
                        </div>
                    </div>
                    <div class="badge diamond">
                        <img src="/lighting/images/다이아.png" alt="Diamond" class="badge-icon">
                        <div class="badge-info">
                            <h3>다이아 등급</h3>
                            <p>별점 3점 이상 4점 미만인 모임에 진심인 준프로 참여!</p>
                        </div>
                    </div>
                    <div class="badge gold">
                        <img src="/lighting/images/골드.png" alt="Gold" class="badge-icon">
                        <div class="badge-info">
                            <h3>골드 등급</h3>
                            <p>별점 2점 이상 3점 미만인 모임에 욕심이 생긴 참가자!</p>
                        </div>
                    </div>
                    <div class="badge silver">
                        <img src="/lighting/images/실버.png" alt="Silver" class="badge-icon">
                        <div class="badge-info">
                            <h3>실버 등급</h3>
                            <p>회원의 기본 등급으로 모임에 흥미가 생긴 신입 참가자!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
    // 모달 열기
    function openModal() {
        document.getElementById('modalOverlay').style.display = 'flex';
        
        // ESC 키로 모달 닫기 이벤트 등록
        document.addEventListener('keydown', function(e) {
            if (e.key === 'Escape') {
                closeModal();
            }
        });
    }

    // 모달 닫기
    function closeModal() {
        document.getElementById('modalOverlay').style.display = 'none';
    }
    
    // 모달 외부 클릭 시 닫기
    document.getElementById('modalOverlay').addEventListener('click', function(e) {
        if (e.target === this) {
            closeModal();
        }
    });
    </script>
</body>
</html>
