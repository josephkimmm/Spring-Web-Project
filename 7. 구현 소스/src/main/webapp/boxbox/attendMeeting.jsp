<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="css/lighting.css">
<title>내가 참여한 모임</title>
<style>
		 body {
            background-color: #f9f7ff;
            margin: 0;
            padding: 0;
        }
        
             .container {
            width:900PX;
            margin: 30px auto;
            background-color: #f9f7ff;
            padding: 20px;
            /* box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);*/
            position: relative;
        }
        
        h1 {
            font-size: 24px;
            color: black;
            text-align: left;
            margin-bottom: 20px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        td {
            padding: 6px;
            text-align: center;
            border-bottom: 1px solid #c7d5f3;
            height:30px;
         
        }

        th {
            background-color: #f9f7ff;
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #1e62c8;
            font-size: 18px;
        }

        /* "열정(등급) 보기" 버튼을 오른쪽 상단에 배치 */
        .enthusiasm-btn {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: #1e62c8;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
        }

        .enthusiasm-btn:hover {
           /*  background-color: #155a96; */
        }

        .action-btn {
            background-color: #1e62c8;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 5px; /* 버튼 위에 여백 추가 */
        }

        .action-btn:hover {
            /* background-color: #155a96; */
        }

        .action-select {
            background-color: #fff;
           /*  color: #333; */
            border: 2px solid #1e62c8;
            padding: 5px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
            
        }

        .action-select:hover {
           /*  background-color: #e9e9e9; */
        }
</style>
</head>
<body>

  <div class="container">
    <h1>내가 참여한 모임 보기</h1>

    <!-- "열정(등급) 보기" 버튼 -->
    <button class="enthusiasm-btn">열정(등급) 보기</button>

    <!-- 테이블 -->
    <table>
        <thead>
            <tr>
                <th>모임 일자</th>
                <th>장소</th>
                <th>제목</th>
                <th>모집인원</th>
                <th>
                <select  class="action-select">
                    <option value="newest">최신순</option>
                    <option value="oldest">과거순</option>
                </select>
                </th> 
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>2025년 4월 2일</td>
                <td>서울 강남구</td>
                <td>4월 2일 강남 그늘 스터디 모임</td>
                <td>3명</td>
                <td>
                   <button class="action-btn">회원 평가하기</button>
                </td>
            </tr>
            <!-- 추가 데이터가 여기에 들어갑니다. -->
        </tbody>
    </table>

</div>
    
    
</body>
</html>