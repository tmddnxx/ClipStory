<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
    <%
        List searchVOList = (List) request.getAttribute("searchVOList");
        int limit = (Integer) request.getAttribute("limit");
        String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
        String text = request.getParameter("text") != null ? request.getParameter("text") : "";
    %>
    <title>영화 소개 사이트</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div>
<div class="container" style="max-width: 5000px; overflow: hidden; padding-top:200px; height: 1000px">
    <hr>
    <div class="container" style="width: 700px">
        <form name="searchList" action="./list.search?action=list" method="post" style="display: flex; justify-content: center;">
            <select name="items" class="txt">
                <option value="movieName" <% if(items.equals("movieName")){%>selected<%}%>>영화제목</option>
                <option value="actor" <% if(items.equals("actor")){%>selected<%}%>>배우</option>
                <option value="genre" <% if(items.equals("genre")){%>selected<%}%>>장르</option>
            </select> <input class="form-control" id="search-input" name="text" type="text" value="<%=text%>"/>
            <input type="submit" id="btn-search" class="btn btn-primary" value="검색"/>
        </form>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const submitBtn = document.querySelector('#btn-search');

            submitBtn.addEventListener('click', function (e) {
                const input = document.querySelector('#search-input').value;
                // console.log("검색창 : "+input);
                if (input === "") {
                    e.preventDefault();
                    alert("검색어는 특수문자 제외 2자이상 입력하셔야 합니다.");
                }
            });
        });
    </script>
    <hr>
    <%--  영화 리스트  --%>
    <h3 style="text-align: center; margin-bottom: 50px">영화</h3>
    <div class="center" style="overflow: hidden; display: flex; flex-wrap: wrap; justify-content: space-between; width: 3600px; padding-left: 130px;">
        <c:forEach var="movie" items="${listMovie}" varStatus="status">
            <div class="rounded-lg shadow-xl bg-indigo-500 hover:shadow-indigo-500/40 hover:blur-sm" style="width: 200px; height: 200px; text-align: center; margin-top: 20px; margin-bottom: 50px">
                <h2 style="margin: 10px 150px 0px 0px; font-style: italic;"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary" style="margin-top: 100px;">
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
    <%--  OTT 리스트  --%>
    <h3 style="text-align: center; margin-bottom: 50px">OTT</h3>
    <div class="center" style="overflow: hidden; display: flex; flex-wrap: wrap; justify-content: space-between; width: 3200px; max-height: 220px; padding-left: 130px;">
        <c:forEach var="movie" items="${listOtt}" varStatus="status">
            <div class="mh-100" style="width: 200px; height: 200px; border: 1px solid black; text-align: center;">
                <h2 style="margin: 10px 150px 0px 0px; font-style: italic;"><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary" style="margin-top: 100px;">
                        ott이름 : ${movie.movieName}<br>
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
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>