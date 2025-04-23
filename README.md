> 🇰🇷 [한국어 README 보기](#오늘어때---모임-관리-플랫폼) | 🇺🇸 [View English README](#how-about-today---social-gathering-management-platform)

---

# 오늘어때 - 모임 관리 플랫폼

## 📌 프로젝트 개요

**오늘어때**는 날씨, 요일, 시간대 등 실시간 조건을 반영하여 모임을 추천하고,  
참여 및 주최 기능을 제공하는 소셜 모임 플랫폼입니다.  
사용자는 모임에 참여하거나 직접 생성할 수 있으며, 회원들간의 친구 관리 및 평가 기능으로 신뢰가 기반이 된 모임 커뮤니티를 형성할 수 있습니다.

## 💼 담당 파트: 마이페이지

회원 정보 관리부터,친구 추가/삭제, 친구차단/해제, 모임 신청·참여 내역, 평가, 탈퇴 등 다양한 기능을 제공하는 마이페이지 기능을 담당하였습니다.  

## 🧯 문제 해결 및 트러블슈팅 (Troubleshooting)
⭐ 사용자 평가 및 자동 등급 시스템 구현 경험 (STAR 기법 기반)

🟡 S - 상황 (Situation)
‘오늘어때’는 신뢰 기반의 소셜 모임 플랫폼이지만, 사용자 활동에 대한 신뢰도를 판단할 수 있는 기준이 부족했습니다. 이에 따라 사용자 간 평가와 누적 점수를 기반으로 등급을 자동 산정하는 기능이 요구되었습니다.

🟡 T - 목표 (Task)
모임 종료 후 사용자 간 별점 평가를 통해 상호 신뢰도를 반영하고, 누적 점수 기반으로 실버~마스터 등급을 자동 부여하는 시스템을 구현하는 것이 목표였습니다.

🟡 A - 행동 (Action)
평가 테이블에 (모임ID, 평가자ID, 피평가자ID)를 복합 키로 구성하여 중복 평가를 방지하고

평가 평균 점수 기반으로 다음과 같은 등급 자동 산정 로직 구현:

0.0 ~ 2.0 → 🥈 실버  
2.0 ~ 3.0 → 🥇 골드  
3.0 ~ 4.0 → 💎 다이아  
4.0 ~ 5.0 → 👑 마스터
JSP와 Java Servlet 기반으로 평가 입력 및 등급 뱃지 표시 UI 구현

자기 자신 또는 미참여자에 대한 평가 차단 로직 추가

🟡 R - 결과 (Result)
등급을 통해 사용자의 모임 활동 신뢰도가 시각화되었고, 모임 신청자의 판단 기준으로 활용됨

사용자 참여 유도 효과를 보았으며, “믿고 만날 수 있는 사람을 찾기 쉬워졌다”는 긍정 피드백 확보

데이터 처리와 사용자 경험을 모두 고려한 기능 구현 능력을 키울 수 있었음

## 🧩 주요 기능 및 화면 예시

### ✅ 내가 만든 모임 신청 보기  
내가 주최한 모임에 신청한 회원들의 정보를 확인하고, 수락/거절할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/4bea6ba6-6bfb-42c7-be b9-16e30cf149fd

### ✅ 내가 신청한 모임 보기  
내가 신청한 모임의 승인 상태(대기, 수락, 거절)를 확인할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/e8bc4b19-555e-4ca0-ba55-1f8e59b00723)

### ✅ 내가 작성한 글 보기  
내가 등록한 모임 게시글을 목록으로 확인하고, 수정하거나 삭제할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/b76026b5-113f-4bf8-b591-041774b432e5)

### ✅ 내가 찜한 모임 보기  
관심 있는 모임을 찜해두고 리스트로 확인할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/8f4cdee1-8721-48b0-9799-eb1b1b839f90)

### ✅ 내가 참여한 모임 보기  
참여한 모임 목록을 확인하고, 다시 참여할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/3f1635e0-d9fa-4b72-a757-3b5385930050)

### ✅ 회원 정보 수정  
이름, 닉네임, 활동지역, 성별 등의 정보를 변경할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/8438756d-bb2c-4906-a29c-e22ff912942e)

### ✅ 프로필 이미지 변경  
프로필 사진을 업로드하여 변경할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/ca1ef7a3-5e4b-469f-95cd-eb75cde21bc8)

### ✅ 공개 여부 설정  
프로필의 공개/비공개 상태를 전환할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/e9cf8574-8932-404d-952e-da3fdbd9a363)

### ✅ 신청자 관리 및 거절 사유 설정  
신청자의 승인을 관리하고, 거절 이유를 선택하여 안내할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/54de11ac-0fa0-4da1-a3be-2ce69dbc6d89)
![Image](https://github.com/user-attachments/assets/f4c0677d-2164-4d5c-bfcf-e38860b68a6b)

### ✅ 회원 평가 기능  
모임 참여자에게 별점을 주고, 누적 평점으로 열정 등급을 책정합니다.  
![Image](https://github.com/user-attachments/assets/683e56c1-b1fd-4ffa-8868-ce3ed4012f0b)

### ✅ 회원 탈퇴  
회원 스스로 계정을 삭제하고 탈퇴할 수 있습니다.  
![Image](https://github.com/user-attachments/assets/6bb93cbc-d3b7-4c1d-996e-6f14b3b00bfc)

## 🛠️ 사용 기술 스택

| 분류        | 기술                                   | 설명                                  |
|-------------|----------------------------------------|---------------------------------------|
| **Frontend** | `HTML5`, `CSS3`, `JavaScript`, `JSP`   | JSP 기반 웹 UI 구성 |
| **Backend**  | `Java`, `Servlet`, `JSP`, `JDBC`        | MVC 기반 서버 로직 구현 |
| **Database** | `Oracle DB`                             | 사용자/모임 데이터 저장 |
| **Web Server** | `Apache Tomcat 9`                    | JSP/Servlet 실행 환경 |
| **Version Control** | `Git`, `GitHub`                  | 형상 관리 및 협업 |
| **Tool**      | `Eclipse IDE`                         | 개발 환경 구성 도구 |

## 🙋‍♂️ 기여자

- **이름**: 김두현  
- **GitHub**: [github.com/josephkimmm](https://github.com/josephkimmm)

---

# How About Today - Social Gathering Management Platform

## 📌 Project Overview

**How About Today** is a social gathering platform that recommends meetups based on real-time conditions such as weather, day of the week, and time of day.  
Users can participate in or host gatherings, manage friendships, and build a trust-based community through mutual evaluations.

## 💼 My Responsibility: My Page

I was in charge of developing the **My Page** feature, where users can check and update all their information and activity.  
It includes functionalities such as managing user information, reviewing gathering applications and participation history, evaluations, and account deletion.

## 🧯 Troubleshooting & Problem Solving (STAR Method)
⭐ Implementing the Member Rating and Auto-Grade System

🟡 S - Situation
As a trust-based community platform, How About Today needed a way to reflect users' participation quality and reliability. However, there was no clear metric for user activity, leading to the need for a rating and automated grading system.

🟡 T - Task
After a gathering ends, users should be able to rate each other with stars. Based on the average score, a trust grade (Silver to Master) would be calculated and automatically assigned.

🟡 A - Action
Designed a dedicated rating table with a composite primary key: (gatheringID, raterID, rateeID) to prevent duplicate ratings

Implemented an automatic grading logic:

mathematica

0.0 ~ 2.0 → 🥈 Silver  
2.0 ~ 3.0 → 🥇 Gold  
3.0 ~ 4.0 → 💎 Diamond  
4.0 ~ 5.0 → 👑 Master
Connected the backend logic with JSP pages and added dynamic badge display

Added validation to prevent self-rating and non-participant ratings

🟡 R - Result
Trust grades provided visual cues about user activity levels and helped others make informed decisions when accepting applicants

Increased user engagement and received positive feedback such as “It’s easier to find reliable people”

Strengthened my ability to design logic that connects data reliability with user experience

## 🧩 Key Features and Screenshots

### ✅ View Applications for My Gatherings  
Check the details of users who applied for your hosted gatherings and accept or reject their applications.  
![Image](https://github.com/user-attachments/assets/4bea6ba6-6bfb-42c7-beb9-16e30cf149fd)

### ✅ View My Applications  
Check the status of your gathering applications (Pending, Accepted, Rejected).  
![Image](https://github.com/user-attachments/assets/e8bc4b19-555e-4ca0-ba55-1f8e59b00723)

### ✅ View My Posts  
Check the gatherings you've posted, and edit or delete them.  
![Image](https://github.com/user-attachments/assets/b76026b5-113f-4bf8-b591-041774b432e5)

### ✅ View Bookmarked Gatherings  
See the list of gatherings you've bookmarked.  
![Image](https://github.com/user-attachments/assets/8f4cdee1-8721-48b0-9799-eb1b1b839f90)

### ✅ View My Participated Gatherings  
Check the gatherings you've joined and rejoin if you wish.  
![Image](https://github.com/user-attachments/assets/3f1635e0-d9fa-4b72-a757-3b5385930050)

### ✅ Edit Profile Information  
Update details like your name, nickname, active region, and gender.  
![Image](https://github.com/user-attachments/assets/8438756d-bb2c-4906-a29c-e22ff912942e)

### ✅ Change Profile Picture  
Upload and change your profile photo.  
![Image](https://github.com/user-attachments/assets/ca1ef7a3-5e4b-469f-95cd-eb75cde21bc8)

### ✅ Set Profile Visibility  
Toggle your profile's visibility status between public and private.  
![Image](https://github.com/user-attachments/assets/e9cf8574-8932-404d-952e-da3fdbd9a363)

### ✅ Manage Applicants and Set Rejection Reasons  
Approve or reject gathering applicants, and provide a reason for rejection.  
![Image](https://github.com/user-attachments/assets/54de11ac-0fa0-4da1-a3be-2ce69dbc6d89)  
![Image](https://github.com/user-attachments/assets/f4c0677d-2164-4d5c-bfcf-e38860b68a6b)

### ✅ Member Rating Feature  
Rate other members with stars and calculate a cumulative passion grade based on those ratings.  
![Image](https://github.com/user-attachments/assets/683e56c1-b1fd-4ffa-8868-ce3ed4012f0b)

### ✅ Account Deletion  
Users can delete their account and leave the platform.  
![Image](https://github.com/user-attachments/assets/6bb93cbc-d3b7-4c1d-996e-6f14b3b00bfc)

## 🛠️ Tech Stack

| Category       | Technology                             | Description                               |
|----------------|-----------------------------------------|-------------------------------------------|
| **Frontend**   | `HTML5`, `CSS3`, `JavaScript`, `JSP`    | UI built using JSP                        |
| **Backend**    | `Java`, `Servlet`, `JSP`, `JDBC`        | MVC-based server logic                    |
| **Database**   | `Oracle DB`                             | Stores user and gathering data            |
| **Web Server** | `Apache Tomcat 9`                       | JSP/Servlet runtime environment           |
| **Version Control** | `Git`, `GitHub`                   | Version control and collaboration         |
| **Tool**       | `Eclipse IDE`                           | Development environment tool              |

## 🙋‍♂️ Contributor

- **Name**: Doohyun Kim  
- **GitHub**: [github.com/josephkimmm](https://github.com/josephkimmm)

