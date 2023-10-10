<%@ page import="com.example.movie.model.dto.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
  <link href="/css/boardCSS/boardAdd.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
  <script src="/js/boardJS/boardAdd.js" defer></script>
    <title>글쓰기페이지</title>
</head>
<%
  MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginInfo");
  if(memberDTO == null){
%>
<script>
  alert('로그인이 필요한 서비스입니다');
  location.href="/login";
</script>
<%
  }
%>
<body>
<div class="wrap">
<jsp:include page="../../inc/header.jsp"/>
  <div class="write-content" style="padding-top: 15vh">
    <form method="post" action="./add.board?action=add" class="writeForm">
      <div class="title-box">
        <label class="content-title-in">제목</label>
        <input type="text" name="title" class="form-control">
      </div>
      <div class="content-box">
        <label class="form-label">내용</label>
        <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
      </div>
      <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" hidden>
      <input type="text" name="nickName" class="form-control" value="${loginInfo.nickName}" hidden>
      <div class="write-btnbox">
        <button type="submit" class="write-submit-btn">저장</button>
        <a href="list.board" class="write-back-btn" onclick="return confirm('입력하신 내용이 저장되지 않았습니다.');">뒤로가기</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
