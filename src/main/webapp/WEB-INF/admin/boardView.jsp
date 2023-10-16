<%@ page import="com.example.movie.model.dto.BoardDTO" %>
<%@ page import="com.example.movie.model.dto.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  BoardDTO boardDTO = (BoardDTO) request.getAttribute("boardDTO");
  String pageNum = request.getParameter("pageNum");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="/css/adminCSS/adminBoardGet.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
  <script src="/js/adminJS/adminBoardGet.js" defer></script>
  <title>관리자 게시글 상세페이지</title>
</head>
<body>
<div class="wrap">
  <jsp:include page="./inc/adminHeader.jsp"/>
  <div class="main">
    <h2>${boardDTO.title}</h2>
    <hr>
    <div class="text-box">
      <div class="text-box-in">
        <p>Date: ${boardDTO.addDate}</p>
        <p>조회수 : ${boardDTO.hit}</p>
        <p>닉네임 : ${boardDTO.nickName}</p>
      </div>
    </div>
    <div class="content-box">
      <div class="content-box-inner">
        <p class="content-text">Content: ${boardDTO.content}</p>
      </div>
    </div>
    <hr>
    <div class="form-group row user-comment-list">
      <ul>
      </ul>
    </div>
    <form name ="frmCommentView" method="post">
      <input type="hidden" name="contentNo" value="<%=boardDTO.getContentNo()%>">
      <input type="hidden" name="nickName" value="${loginInfo.nickName}">
      <input type="hidden" name="memberId" value="${loginInfo.memberId}">
    </form>
    <div>
      <form name="frmView" method="post">
        <input type="hidden" name="pageNum" value="<%=pageNum%>">
        <input type="hidden" name="num" value="<%=boardDTO.getContentNo()%>">
      </form>
      <div class="btn-box">
        <button type="button" class="back-btn" onclick=window.location.href="/admin?action=boardList">뒤로가기</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>