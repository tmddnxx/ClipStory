<%@ page import="com.example.movie.model.dto.BoardDTO" %>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <title>게시글 상세페이지</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container w-75 mt-5 mx-auto">
    <h2 style="text-align: center">${boardDTO.title}</h2>
    <hr>
    <h4 class="card-title" style="text-align: right">Date: ${boardDTO.addDate}</h4>
    <p>조회수 : ${boardDTO.hit}</p>
    <h4 style="text-align: right">닉네임 : ${boardDTO.nickName}</h4>
    <div class="card w-75 mx-auto">
        <div class="card-body">
            <p class="card-text">Content: ${boardDTO.content}</p>
        </div>
    </div>
    <hr>
    <div>
        <form name="frmView" method="post">
            <input type="hidden" name="pageNum" value="<%=pageNum%>">
            <input type="hidden" name="num" value="<%=boardDTO.getContentNo()%>">
        </form>
        <a href="javascript:history.back()" class="btn btn-primary"><< Back</a>
        <a href="modify.board?action=modify&contentNo=${boardDTO.contentNo}" class="btn btn-primary" style="text-align: right">Modify</a>
    </div>
</div>
</body>
</html>