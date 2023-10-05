<%@ page import="com.example.movie.model.dto.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <title>Title</title>
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
<jsp:include page="../header.jsp"/>
  <div class="card card-body">
    <form method="post" action="./add.board?action=add">
      <label class="form-label">제목</label>
      <input type="text" name="title" class="form-control">
      <label class="form-label">내용</label>
      <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
      <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" hidden>
      <input type="text" name="nickName" class="form-control" value="${loginInfo.nickName}" hidden>
      <button type="submit" class="btn btn-success mt-3">저장</button>
    </form>
  </div>
</body>
</html>
