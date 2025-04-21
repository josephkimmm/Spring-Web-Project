<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 애플리케이션</title>
<!-- jQuery 라이브러리 (CDN 이용) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
    .button-group {
        margin-bottom: 10px;
    }
    .button-group button {
        padding: 8px 12px;
        margin-right: 5px;
        cursor: pointer;
    }
    .button-group button.active {
        background-color: #007BFF;
        color: #fff;
    }
</style>
</head>
<body>
    <h2>채팅방</h2>
    
    <!-- 닉네임 버튼 그룹 -->
    <div class="button-group">
    <button type="button" class="toggle-btn" data-nickname="즐거운햇살68">즐거운햇살68(1)</button>
      <button type="button" class="toggle-btn" data-nickname="상냥한별64">상냥한별64(5)</button>
      <button type="button" class="toggle-btn" data-nickname="상냥한숲03">상냥한숲03(10)</button>
      <button type="button" class="toggle-btn" data-nickname="용감한사자25">용감한사자25(15)</button>
    </div>
    
    
    
    <!-- 채팅 메시지가 출력되는 영역 -->
    <div id="chatBox" style="width:500px; height:300px; border:1px solid #000; overflow-y:scroll; padding:5px;">
        <!-- 서버로부터 전달받은 메시지들이 여기에 표시됩니다 -->
    </div>
    <!-- 메시지 전송 폼 -->
    <form id="chatForm">
        <input type="text" id="nickname" name="nickname" placeholder="받는 사람의 닉네임" required /><br/><br/>
        <textarea id="content" name="content" placeholder="메시지를 입력하세요..." rows="3" cols="50" required></textarea><br/><br/>
        <button type="submit">전송</button>
    </form>
    
    
    <script>
    
    $(document).ready(function(){
        // 토글 버튼 클릭 이벤트 핸들러
        $('.toggle-btn').click(function(){
            // 이미 활성 상태이면 비활성화하고 입력 필드 클리어
            if($(this).hasClass('active')){
                $(this).removeClass('active');
                $('#nickname').val('');
            } else {
                // 다른 버튼은 모두 비활성화하고, 클릭한 버튼만 활성화
                $('.toggle-btn').removeClass('active');
                $(this).addClass('active');
                // 버튼의 데이터 값을 입력 필드에 설정
                $('#nickname').val($(this).data('nickname'));
            }
            // 버튼 토글 후 바로 채팅 내용 갱신
            loadMessages();
        });
        
        
        // 채팅 내역 조회 함수
        function loadMessages(){
            var nick = $('#nickname').val().trim();
            if(nick === ""){
                // 닉네임이 없으면 요청하지 않음
                return;
            }
            $.ajax({
                url: '/lighting/chat/chat.do',
                type: 'GET',
                dataType: 'json',
                data: { nickname: nick },
                success: function(data){
                    var chatBox = $('#chatBox');
                    chatBox.empty();
                    $.each(data, function(index, content){
                        chatBox.append('<p><strong>' + content.sender+ ':</strong> ' + content.content + ' <em>(' + content.postDate + ')</em></p>');
                    });
                },
                error: function(a, b, c) {
                    console.error(a, b, c);
                }
            });
        }
    
    // 일정 시간마다 채팅 내용을 갱신 (3초 간격)
    setInterval(loadMessages, 3000);
    
    // 메시지 전송 시 AJAX 요청
    $('#chatForm').submit(function(e){
        e.preventDefault();
        var nickname = $('#nickname').val();
        var content = $('#content').val();
        $.ajax({
            url: '/lighting/chat/chat.do',
            type: 'POST',
            data: { nickname: nickname, content: content },
            success: function(){
                $('#content').val('');
                loadMessages();
            },
            error: function(a, b, c) {
            	console.error(a,b,c);
            }
        });
    });
});
</script>


</body>


</html>
