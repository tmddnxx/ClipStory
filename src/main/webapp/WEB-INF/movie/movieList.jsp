<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <title>영화 소개 사이트</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container" style="max-width: 1700px;">
    <nav class="nav" style="padding-top: 26px; text-align: center; justify-content: center; font-size: 26px;">
<%--        <a class="nav-link active" aria-current="page" href="#" style="font-weight: 600; color: black;">홈</a>--%>
        <a class="nav-link" href="#" style="font-weight: 600; color: black;">랭킹</a>
        <a class="nav-link" href="#" style="font-weight: 600; color: black;">상영/예정작</a>
<%--        <a class="nav-link " aria-disabled="true" style="font-weight: 600; color: black;">게시판</a>--%>
    </nav>
    <h2>영화 목록</h2>
    <hr>
    <div class="container" style="display: flex; flex-wrap: wrap; justify-content: space-between; max-width: 1700px;">
        <c:forEach var="movie" items="${movieList}" varStatus="status">
            <div class="mh-100" style="width: 200px; height: 200px;">
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    [${status.count}]
                </a>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary">${movie.movieName}, ${movie.releaseDate}</span>
                </a>
                <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}">
                    <span class="badge bg-secondary">&times;</span>
                </a>
            </div>
        </c:forEach>
    </div>
    <hr>
    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생 : ${error}
            <button type="submit" class="btn-class" date-bs-dismiss="alert"></button>
        </div>
    </c:if>
</div>
</body>
</html>