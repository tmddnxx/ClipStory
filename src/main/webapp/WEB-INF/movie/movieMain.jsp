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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="slick/slick.min.js"></script>
    <title>영화 소개 사이트</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class=container"" style="max-width: 5000px; overflow: hidden;">
    <nav class="nav" style="padding-top: 26px; text-align: center; justify-content: center; font-size: 26px;">
        <%--        <a class="nav-link active" aria-current="page" href="#" style="font-weight: 600; color: black;">홈</a>--%>
        <a class="nav-link" href="#" style="font-weight: 600; color: black;">랭킹</a>
        <a class="nav-link" href="#" style="font-weight: 600; color: black;">상영/예정작</a>
        <%--        <a class="nav-link " aria-disabled="true" style="font-weight: 600; color: black;">게시판</a>--%>
    </nav>
    <hr>
    <form class="search-model-form" style="text-align: center">
        <input type="text" name="searchbtn" id="search-input">
    </form>
    <hr>
    <%--  영화 리스트  --%>
    <h3 style="text-align: center; margin-bottom: 50px">영화</h3>
    <div class="center" style="overflow: hidden; display: flex; flex-wrap: wrap; justify-content: space-between; width: 2300px; max-height: 220px; padding-left: 130px;">
        <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <div class="mh-100" style="width: 200px; height: 200px; border: 1px solid black;">
                <h2 style="margin-left: 20px"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary" style="margin-top: 100px;margin-left: 28px;">
                        영화이름 : ${movie.movieName}<br>
                        개봉일 : ${movie.releaseDate}<br>
                        평점 : ${movie.avgScore}
                    </span>
                </a>
                <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}" hidden="hidden">
                    <span class="badge bg-secondary">&times;</span>
                </a>
            </div>
        </c:forEach>
    </div>
<%--    <script>--%>
<%--        $('.center').slick({--%>
<%--            centerMode: true,--%>
<%--            centerPadding: '60px',--%>
<%--            slidesToShow: 3,--%>
<%--            responsive: [--%>
<%--                {--%>
<%--                    breakpoint: 768,--%>
<%--                    settings: {--%>
<%--                        arrows: false,--%>
<%--                        centerMode: true,--%>
<%--                        centerPadding: '40px',--%>
<%--                        slidesToShow: 3--%>
<%--                    }--%>
<%--                },--%>
<%--                {--%>
<%--                    breakpoint: 480,--%>
<%--                    settings: {--%>
<%--                        arrows: false,--%>
<%--                        centerMode: true,--%>
<%--                        centerPadding: '40px',--%>
<%--                        slidesToShow: 1--%>
<%--                    }--%>
<%--                }--%>
<%--            ]--%>
<%--        });--%>
<%--    </script>--%>
    <hr>
    <h3 style="text-align: center; margin-bottom: 50px">OTT</h3>
    <%--  OTT 리스트  --%>
    <div class="" style="overflow: hidden; display: flex; flex-wrap: wrap; justify-content: space-between; width: 2300px; max-height: 220px; padding-left: 130px;" >
        <c:forEach var="movie" items="${listOtt}" varStatus="status">
            <div class="mh-100" style="width: 200px; height: 200px; border: 1px solid black;">
                <h2 style="margin-left: 20px"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                        <span class="badge bg-secondary" style="margin-top: 100px;margin-left: 28px;">
                            영화이름 : ${movie.movieName}<br>
                            개봉일 : ${movie.releaseDate}<br>
                            평점 : ${movie.avgScore}
                        </span>
                </a>
                <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}" hidden="hidden">
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