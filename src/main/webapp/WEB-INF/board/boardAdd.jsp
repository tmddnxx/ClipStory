<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="collapse" id="addForm">
  <div class="card card-body">
    <form method="post" action="./add.board?action=add">
      <label class="form-label">제목</label>
      <input type="text" name="title" class="form-control">
      <label class="form-label">내용</label>
      <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
      <input type="text" name="memberId" class="form-control" value="테스트 멤버아이디" hidden>
      <input type="text" name="nickName" class="form-control" value="테스트 닉네임" hidden>
      <button type="submit" class="btn btn-success mt-3">저장</button>
    </form>
  </div>
</div>
</body>
</html>
