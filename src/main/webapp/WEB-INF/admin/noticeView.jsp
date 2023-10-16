<%@ page import="com.example.movie.model.dto.AdminBoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    AdminBoardDTO adminBoardDTO = (AdminBoardDTO) request.getAttribute("adminBoardDTO");
    String pageNum = request.getParameter("pageNum");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/boardCSS/boardGet.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">
    <title>관리자 공지사항 상세페이지</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="../../inc/header.jsp"/>
    <div class="container w-75 mt-5 mx-auto main">
        <h2>${adminBoardDTO.title}</h2>
        <hr>
        <div class="text-box">
            <div class="text-box-in">
                <p>Date: ${adminBoardDTO.addDate}</p>
                <p>작성자 : ${adminBoardDTO.superName}</p>
            </div>
        </div>
        <div class="content-box">
            <div class="content-box-inner">
                <p class="content-text">Content: ${adminBoardDTO.content}</p>
            </div>
        </div>
        <hr>
        <div>
            <form name="frmView" method="post">
                <input type="hidden" name="pageNum" value="<%=pageNum%>">
                <input type="hidden" name="num" value="<%=adminBoardDTO.getCno()%>">
            </form>
            <div class="btn-box">
                <a href="/admin?action=noticeModifyView&cno=${adminBoardDTO.cno}" class="modify-btn" >수정</a>
                <a href="/admin?action=noticeRemove&cno=${adminBoardDTO.cno}" onclick="return confirm('정말 삭제하시겠습니까?');" class="remove-btn">삭제 </a>
                <button type="button" class="back-btn" onclick=window.location.href="/admin?action=noticeList">뒤로가기</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>