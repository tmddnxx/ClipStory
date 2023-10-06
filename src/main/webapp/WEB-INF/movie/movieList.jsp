<%@ page import="java.util.List" %>
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
<div class="container" style="max-width: 1700px;">
    <h2>영화 목록</h2>
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
        document.addEventListener('DOMContentLoaded', function (e) {
            const input = document.querySelector('#search-input').value;
            const submitBtn = document.querySelector('#btn-search');
            // console.log("검색창 : "+input);

            submitBtn.addEventListener('click', function (e) {
                if (input === "") {
                    e.preventDefault();
                    alert("검색어는 특수문자 제외 2자이상 입력하셔야 합니다.");
                }
            });
        });
    </script>
    <hr>
    <div class="container" style="display: flex; flex-wrap: wrap; justify-content: space-between; max-width: 1700px;">
        <c:forEach var="movie" items="${movieList}" varStatus="status">
            <div class="mh-100" style="width: 200px; height: 200px;">
                <h2><strong>${status.count}</strong></h2>
                <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary" style="margin-top: 100px;margin-left: 28px;">
                            ${movie.movieName}, ${movie.releaseDate}
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