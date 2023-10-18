<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/movieJS/movieList.js?after"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="../../css/movieCSS/movieList.css?after" rel="stylesheet" type="text/css">
    <%
        List searchVOList = (List) request.getAttribute("searchVOList");
        int limit = (Integer) request.getAttribute("limit");
        String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
        String text = request.getParameter("text") != null ? request.getParameter("text") : "";
    %>
    <title>영화 소개 사이트</title>
</head>
<body>
<jsp:include page="../../inc/header.jsp"/>
<div class="outContainer"><!-- 제일 바깥 container div -->
    <div class="searchDiv"><!-- 영화 검색창 영역 -->
        <form name="searchList" class="searchList" action="./list.search?action=list" method="post"><!-- 영화 검색창 Category Select -->
            <select name="items" class="txt">
                <option value="movieName" <% if(items.equals("movieName")){%>selected<%}%>>영화제목</option>
                <option value="actor" <% if(items.equals("actor")){%>selected<%}%>>배우</option>
                <option value="genre" <% if(items.equals("genre")){%>selected<%}%>>장르</option>
            </select> <input class="form-control" id="search-input" name="text" type="text" value="<%=text%>"/>
            <input type="submit" id="btn-search" class="btn btn-primary" value="검색"/>
        </form>
    </div>
    <span class="contentTitle">영화 목록</span>
    <div class="contentList"> <!-- 영화 리스트 container div -->
        <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <div class="rounded-lg shadow-xl bg-indigo-500 hover:shadow-indigo-500/40 hover:blur-sm oneBox"> <!-- 영화 1개 정보 div -->
                <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary oneMovieSpan"><!-- 영화 이름, 개봉일 span -->
                            ${movie.movieName}, ${movie.releaseDate}
                    </span>
                </a>
                <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}" hidden="hidden">
                    <span class="badge bg-secondary">&times;</span>
                </a>
            </div>
        </c:forEach>
    </div>
    <span class="contentTitle">OTT 목록</span>
    <div class="contentList"> <!-- Ott 리스트 container div -->
        <c:forEach var="movie" items="${listOtt}" varStatus="status">
            <div class="rounded-lg shadow-xl bg-indigo-500 hover:shadow-indigo-500/40 hover:blur-sm oneBox"> <!-- Ott 1개 정보 div -->
                <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary oneMovieSpan"><!-- Ott 이름, 개봉일 span -->
                            ${movie.movieName}, ${movie.releaseDate}
                    </span>
                </a>
                <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}" hidden="hidden">
                    <span class="badge bg-secondary">&times;</span>
                </a>
            </div>
        </c:forEach>
    </div>
    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생 : ${error}
            <button type="submit" class="btn-class" date-bs-dismiss="alert"></button>
        </div>
    </c:if>
</div>
<jsp:include page="../../inc/footer.jsp"/>
</body>
</html>