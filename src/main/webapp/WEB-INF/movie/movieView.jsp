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
    <title>영화 상세 페이지</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container w-75 mt-5 mx-auto">
    <h2>${movieDTO.movieName}</h2>
    <hr>
    <div class="card w-75 mx-auto">
        <img class="card-img-top" src="${movieDTO.poster}">
        <div class="card-body">
            <h4 class="card-title">releaseDate: ${movieDTO.releaseDate}</h4>
            <p class="card-text">outline: ${movieDTO.outline}</p>
        </div>
    </div>
    <hr>
    <a href="javascript:history.back()" class="btn btn-primary"><< back</a>
</div>
</body>
</html>