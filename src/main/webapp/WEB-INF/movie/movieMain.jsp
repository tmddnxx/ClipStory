<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/css2/slick.min.js"></script>
    <script type="text/javascript" src="../../js/movieJS/movieMain.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="../../css/movieCSS/movieMain.css?after" type="text/css">
    <link rel="stylesheet" href="/css2/slick.css" type="text/css" />
    <link rel="stylesheet" href="/css2/slick-theme.css" type="text/css" />
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
    <div class="movieSection">
        <div class="movieBackSlide">
            <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <div class="test">
                <div class="oneMovie">
                    <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
    <div class="center movieContentSection">
        <div class="movieList">
            <c:forEach var="movie" items="${listMovie}" varStatus="status">
                <div onclick="location.href='view.movie?action=view&movieNo=${movie.movieNo}';" class="rounded-lg contentBox">
                    <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                    <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                        <span class="badge bg-secondary contentDetail">
                            영화이름 : <b>${movie.movieName}</b> / 유저평점 : <b>${movie.avgScore}</b>
                        </span>
                    </a>
                    <div class="overlay"></div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="/js/movieJS/slick/movieMainMovieSlick.js" defer></script>

    <!-- 검색 영역 -->
        <form name="searchList" class="searchList" action="./list.search?action=list" method="post">
                <select name="items" class="form-select" >
                    <option value="movieName" <% if(items.equals("movieName")){%>selected<%}%>>영화제목</option>
                    <option value="actor" <% if(items.equals("actor")){%>selected<%}%>>배우</option>
                    <option value="genre" <% if(items.equals("genre")){%>selected<%}%>>장르</option>
                </select>
                <input class="form-control" id="search-input" name="text" type="text" value="<%=text%>"/>
                <input type="submit" id="btn-search" class="btn btn-primary" value="검색"/>
        </form>

    <%--  OTT 리스트  --%>
        <div class="ottSection">
            <div class="ottBackSlide">
                <c:forEach var="movie" items="${listOtt}" varStatus="status">
                    <div>
                        <div class="oneOtt">
                            <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="center movieContentSection">
            <div class="ottList">
                <c:forEach var="movie" items="${listOtt}" varStatus="status">
                    <div onclick="location.href='view.movie?action=view&movieNo=${movie.movieNo}';" class="rounded-lg contentBox">
                        <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                        <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                            <span class="badge bg-secondary contentDetail">
                                OTT이름 : <b>${movie.movieName}</b> / 유저평점 : <b>${movie.avgScore}</b>
                            </span>
                        </a>
                        <div class="overlay"></div>
                    </div>
                </c:forEach>
            </div>
        </div>
    <script src="/js/movieJS/slick/movieMainOttslick.js" defer></script>

    <%--에러 발생 영역--%>
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