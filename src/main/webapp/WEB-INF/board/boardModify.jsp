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
  <link href="/css/boardCSS/boardModify.css" rel="stylesheet">
  <link href="/common.css" rel="stylesheet">
  <script src="/js/boardJS/boardModify.js" defer></script>
  <title>게시글 수정페이지</title>
</head>
<body>
<div class="wrap">
<jsp:include page="../../inc/header.jsp"/>
<div class="main">
  <h3 id="title">게시물 수정</h3>
  <hr>
  <p>${boardDTO.nickName}</p>
  <div class="modify-content">
    <form method="post" action="./modify.board?action=modify&contentNo=${boardDTO.contentNo}">
      <div class="title-box">
        <label class="content-title-in">제목</label>
        <input type="text" name="title" class="form-control" value="${boardDTO.title}">
      </div>
      <div class="content-box">
        <label class="form-label">내용</label>
        <textarea cols="50" rows="5" name="content" class="form-control">${boardDTO.content}</textarea>
      </div>
      <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" hidden>
      <input type="text" name="nickName" class="form-control" value="${loginInfo.nickName}" hidden>
      <div class="modify-btnbox">
        <button type="submit" class="modify-submit-btn">수정</button>
        <a href="get.board?action=get&contentNo=${boardDTO.contentNo}" class="modify-back-btn" onclick="return confirm('입력하신 내용이 저장되지 않았습니다.');">취소</a>
      </div>
    </form>
  </div>
</div>
</div>
</body>
</html>