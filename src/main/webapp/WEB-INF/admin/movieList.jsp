<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script type="text/javascript" src="../../js/movieJS/movieList.js?after"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="../../css/adminCSS/movieList.css?after" rel="stylesheet" type="text/css">
    <title>관리자 영화 목록</title>
</head>
<body>
<div>
<jsp:include page="./inc/adminHeader.jsp"/>
<div class="outContainer"><!-- 제일 바깥 container div -->
    <div style="display: flex; justify-content: center; margin-top: 100px;">
        <button onclick='location.href="/admin?action=addMovie";' style="background: green; width: 200px;" class="btn btn-success" type="button">영화 추가하기</button>
    </div>
    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생 : ${error}
            <button type="submit" class="btn-class" date-bs-dismiss="alert"></button>
        </div>
    </c:if>
</div>
    <div class="container-fluid" style="display: flex; justify-content: center; align-items: center; width: 1600px;">
            <div class="col" style="display: flex; justify-content: space-around;">
                <div class="card" style="width: 700px;">
                    <div class="card-header" style="text-align: center; font-size: 30px;">
                        영화
                    </div>
                    <div class="card-body">
                        <c:forEach var="movie" items="${listMovie}" varStatus="status">
                            <div class="oneBox"> <!-- 영화 1개 정보 div -->
                                <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                                <a href="admin?action=movieView&movieNo=${movie.movieNo}" class="text-decoration-none">
                                    <span class="badge bg-secondary oneMovieSpan"><!-- 영화 이름, 개봉일 span -->
                                            ${movie.movieName}, ${movie.releaseDate}
                                    </span>
                                </a>
                                <a href="./admin?action=removeMovie&movieNo=${movie.movieNo}">
                                    <span class="badge bg-danger">삭제</span>
                                </a>
                                <a href="./admin?action=removeMovie&movieNo=${movie.movieNo}">
                                    <span class="badge bg-primary">수정</span>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="card" style="width: 700px;">
                    <div class="card-header" style="text-align: center; font-size: 30px;">
                        OTT
                    </div>
                    <div class="card-body">
                        <div>
                            <c:forEach var="movie" items="${listOtt}" varStatus="status">
                                <div class="oneBox"> <!-- 영화 1개 정보 div -->
                                    <h2 class="contentCnt"><strong>${status.count}</strong></h2>
                                    <a href="admin?action=movieView&movieNo=${movie.movieNo}" class="text-decoration-none">
                                        <span class="badge bg-secondary oneMovieSpan"><!-- 영화 이름, 개봉일 span -->
                                                ${movie.movieName}, ${movie.releaseDate}
                                        </span>
                                    </a>
                                    <a href="./admin?action=removeMovie&movieNo=${movie.movieNo}">
                                        <span class="badge bg-danger">삭제</span>
                                    </a>
                                    <a href="./admin?action=removeMovie&movieNo=${movie.movieNo}">
                                        <span class="badge bg-primary">수정</span>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <style>

    </style>
</body>
</html>