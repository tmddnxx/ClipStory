<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand  navbar-dark bg-dark">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/main.movie?action=main">Home</a>
        </div>
        <div>
            <ul class="navbar-nav mr-auto">
                <c:choose>
                    <c:when test="${empty loginInfo}">
                        <li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
                        <li class="nav-item"><a class="nav-link" href="/register.member?action=register">회원 가입</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item" style="padding-top: 7px; color: white;">${loginInfo.nickName} 님</li>
                        <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item"><a class="nav-link" href="/list.movie?action=list">영화</a></li>
                <li class="nav-item"><a class="nav-link" href="/list.board?action=list">게시판</a></li>
                <li class="nav-item"><a class="nav-link" href="/search.search">검색</a></li>
                <li class="nav-item"><a class="nav-link" href="/list.mypage?action=list">마이페이지</a></li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
