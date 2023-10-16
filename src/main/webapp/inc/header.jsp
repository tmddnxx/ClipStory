<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/e83b9315c8.js" crossorigin="anonymous"></script>
<script src="/js/incJS/header.js" defer></script>
<link href="/css/incCSS/header.css" rel="stylesheet">
<%--font-family: 'Caprasimo', cursive;--%>
<%--font-family: 'Noto Sans KR', sans-serif;--%>
<%--font-family: 'Righteous', cursive;--%>
<div class="header">
    <div class="header-top">
        <div class="top-inner">
            <ul class="top-list">
                <li class="nav-item"><a class="nav-link" href="/list.movie?action=list">영화</a></li>
                <li class="nav-item"><a class="nav-link" href="/list.board?action=list">게시판</a></li>
                <li class="nav-item"><a class="nav-link" href="/list.board?action=noticeList">공지사항</a></li>
                <c:choose>
                    <c:when test="${empty loginInfo}">
                        <li class="nav-item"><a class="nav-link" href="/login"><i class="fa-solid fa-arrow-right-to-bracket login"></i></a></li>
                        <li class="nav-item"><a class="nav-link" href="/register.member?action=register"><i class="fa-solid fa-user-plus join"></i></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item nickName">${loginInfo.nickName} 님</li>
                        <li class="nav-item"><a class="nav-link" href="/logout"><i class="fa-solid fa-arrow-right-from-bracket logout"></i></a></li>
                        <li class="nav-item"><a class="nav-link" href="/list.mypage?action=list"><i class="fa-solid fa-house-user mypage"></i></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
    <div class="header-main">
        <div class="main-inner">
            <a href="/main.movie?action=main"><h1 class="logo">MOP</h1></a>
        </div>
    </div>
</div>