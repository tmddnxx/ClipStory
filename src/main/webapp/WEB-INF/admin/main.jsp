<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.example.movie.model.dto.AdminDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>관리자 페이지</title>
</head>
<%
    AdminDTO adminDTO = (AdminDTO) session.getAttribute("superInfo");
    if(adminDTO == null){
%>
<script>
    alert('관리자 권한이 필요한 서비스입니다');
    location.href="/login/admin";
</script>
<%
    }
%>
<body>
<jsp:include page="./inc/adminMainHeader.jsp"/>
<div style="margin-top: 100px;">
    <div style="display: flex; justify-content: center;">
        <button onclick='location.href="/admin?action=addMovie";' style="width: 500px; height: 500px; font-size: 60pt;">영화추가</button>
        <button onclick='location.href="/admin?action=addCrew";' style="width: 500px; height: 500px; font-size: 60pt;">감독 / 배우등록</button>
        <button onclick='location.href="/admin?action=movieList";' style="width: 500px; height: 500px; font-size: 60pt;">영화목록</button>
    </div>
    <div style="display: flex; justify-content: center;">
        <button onclick='location.href="/admin?action=boardList";' style="width: 500px; height: 500px; font-size: 60pt;">게시물목록</button>
        <button onclick='location.href="/admin?action=memberList";' style="width: 500px; height: 500px; font-size: 60pt;">회원목록</button>
        <button onclick='location.href="/admin?action=noticeList";' style="width: 500px; height: 500px; font-size: 60pt;">공지사항</button>
    </div>
    <div style="display: flex; justify-content: center;">
        <button onclick='location.href="/main.movie?action=main";' style="width: 500px; height: 500px; font-size: 60pt;">사용자 페이지url</button>
    </div>
</div>
</body>
</html>