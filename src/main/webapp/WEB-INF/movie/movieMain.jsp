<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/movieJS/movieMain.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="../../css/movieCSS/movieMain.css?after" rel="stylesheet" type="text/css">
    <%
        List searchVOList = (List) request.getAttribute("searchVOList");
        int limit = (Integer) request.getAttribute("limit");
        String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
        String text = request.getParameter("text") != null ? request.getParameter("text") : "";
    %>
    <title>영화 소개 사이트</title>
</head>
<body>
<jsp:include page="inc/movieMainHeader.jsp"/>
<div class="outerDiv">
    <%--  영화 리스트  --%>
    <section class="movieSection"></section>
    <div class="center movieList">
        <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <div class="rounded-lg shadow-xl bg-indigo-500 hover:shadow-indigo-500/40 hover:blur-sm movieBox">
                <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary contentDetail">
                        영화이름 : <b>${movie.movieName}</b> / 평점 : <b>${movie.avgScore}</b>
                    </span>
                </a>
                <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}" hidden="hidden">
                    <span class="badge bg-secondary">&times;</span>
                </a>
            </div>
        </c:forEach>
    </div>
        <!-- 검색 영역 -->
        <form name="searchList" class="searchList" action="./list.search?action=list" method="post">
            <div class="container searchSector">
                <select name="items" class="txt">
                    <option value="movieName" <% if(items.equals("movieName")){%>selected<%}%>>영화제목</option>
                    <option value="actor" <% if(items.equals("actor")){%>selected<%}%>>배우</option>
                    <option value="genre" <% if(items.equals("genre")){%>selected<%}%>>장르</option>
                </select> <input class="form-control" id="search-input" name="text" type="text" value="<%=text%>"/>
                <input type="submit" id="btn-search" class="btn btn-primary" value="검색"/>
            </div>
        </form>
    <%--  OTT 리스트  --%>
    <section class="ottSection"></section>
        <div class="center ottList">
            <c:forEach var="movie" items="${listOtt}" varStatus="status">
                <div class="rounded-lg shadow-xl bg-indigo-500 hover:shadow-indigo-500/40 hover:blur-sm movieBox">
                    <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                    <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary contentDetail">
                        OTT : <b>${movie.movieName}</b> / 평점 : <b>${movie.avgScore}</b>
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
<jsp:include page="inc/movieFooter.jsp"/>
</body>
</html>