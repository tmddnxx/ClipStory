<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
  <title>게시글 수정페이지</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3 style="text-align: center">게시물 수정</h3>
<%--<div class="collapse" id="addForm">--%>
<%--  <div class="card card-body">--%>
<%--    <p style="text-align: right">${boardDTO.nickName}</p>--%>
<%--    <form method="post" action="./modify.board?action=modify">--%>
<%--      <label class="form-label">제목</label>--%>
<%--      <input type="text" name="title" class="form-control" value="${boardDTO.title}">--%>
<%--      <label class="form-label">내용</label>--%>
<%--      <textarea cols="50" rows="5" name="content" class="form-control">${boardDTO.content}</textarea>--%>
<%--      <input type="text" name="memberId" class="form-control" value="테스트 멤버아이디" hidden>--%>
<%--      <input type="text" name="nickName" class="form-control" value="테스트 닉네임" hidden>--%>
<%--      <button type="submit" class="btn btn-success mt-3">수정</button>--%>
<%--    </form>--%>
<%--  </div>--%>
<%--</div>--%>
<div>
  <p style="text-align: right">${boardDTO.nickName}</p>
  <div>
    <form method="post" action="./modify.board?action=modify&contentNo=${boardDTO.contentNo}">
      <label>제목</label>
      <input type="text" name="title" value="${boardDTO.title}">
      <label>내용</label>
      <textarea cols="50" rows="5" name="content">${boardDTO.content}</textarea>
      <input type="text" name="memberId" value="테스트 멤버아이디" hidden>
      <input type="text" name="nickName" value="테스트 닉네임" hidden>
      <button type="submit">수정</button>
    </form>
  </div>
</div>
</body>
</html>