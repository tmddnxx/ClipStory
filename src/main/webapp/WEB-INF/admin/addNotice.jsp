<%@ page import="com.example.movie.model.dto.MemberDTO" %>
<%@ page import="com.example.movie.model.dto.AdminBoardDTO" %>
<%@ page import="com.example.movie.model.dto.AdminDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <link href="/css/boardCSS/boardAdd.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">
    <script src="/js/adminJS/adminNoticeAdd.js" defer></script>
    <title>공지등록</title>
</head>
<%
    AdminBoardDTO adminBoardDTO = (AdminBoardDTO) request.getAttribute("adminBoardDTO");
    AdminDTO adminDTO = (AdminDTO) session.getAttribute("superInfo");
%>
<body>
<div class="wrap">
    <jsp:include page="./inc/adminHeader.jsp"/>
    <div class="write-content" style="padding-top: 15vh">
        <form method="post" action="/admin?action=noticeAdd" class="writeForm">
            <div class="title-box">
                <label class="content-title-in">제목</label>
                <input type="text" name="title" class="form-control" value="[공지사항]  " autofocus>
            </div>
            <div class="content-box">
                <label class="form-label">내용</label>
                <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
            </div>
            <input type="text" name="superId" class="form-control" value="${superInfo.superId}" hidden>
            <input type="text" name="superName" class="form-control" value="${superInfo.superName}" hidden>
            <div class="write-btnbox">
                <button type="submit" class="write-submit-btn">저장</button>
                <a href="/admin?action=noticeList" class="write-back-btn" onclick="return confirm('입력하신 내용이 저장되지 않았습니다.');">뒤로가기</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
