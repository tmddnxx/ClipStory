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
    <form method="post" action="./add.board?action=add" class="writeForm">
      <label class="form-label">제목</label>
      <input type="text" name="title" class="form-control">
      <label class="form-label">내용</label>
      <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
      <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" hidden>
      <input type="text" name="nickName" class="form-control" value="${loginInfo.nickName}" hidden>
      <button type="submit" class="btn btn-success mt-3">저장</button>
      <button type="button" class="btn btn-success mt-3" onclick=window.location.href="list.board">뒤로가기</button>
    </form>
  </div>
</body>
<script>
  document.addEventListener('DOMContentLoaded', function (){
    const titlebox = document.querySelector('input[name=title]');
    const contentbox = document.querySelector('textarea[name=content]');
    const subbtn = document.querySelector('button[type=submit]');

    subbtn.addEventListener('click', function (e){
      if(titlebox.value.trim() ==""){
        alert("제목을 입력해주세요");
        titlebox.focus();
        e.preventDefault();
      }
      else if(contentbox.value.trim() == ""){
        alert("내용을 입력해주세요");
        contentbox.focus();
        e.preventDefault()
      }
    })
  })
</script>
</html>
