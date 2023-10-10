<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<%--    <script type="text/javascript" src="../../js/movieJS/movieView.js?after"></script>--%>
    <script src="/js/movieJS/movieView.js" defer></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="../../css/movieCSS/movieView.css?after" rel="stylesheet" type="text/css">
    <title>영화 상세 페이지</title>
</head>
<%
    boolean zzim = (boolean) request.getAttribute("zzim");
%>
<body>
<jsp:include page="inc/movieMainHeader.jsp"/>
<div class="outerDiv">
    <div class="blurDiv">
    <%-- 영화 상세 내용 영역 --%>
    <div class="movieDetail">
        <div class="movie-info">
            <img class="rounded-lg movieImg" src="../../css/movieCSS/img/img1234.jpg">
        </div>
        <div class="rounded-lg card-body">
            <form name="frmZzim" method="post">
                <input type="hidden" name="zzim" value="${zzim}"/>
                <input type="hidden" name="movieNo" value="${movieDTO.movieNo}"/>
                <input type="hidden" name="memberId" value="${loginInfo.memberId}"/>
                <strong class="card-title">영화제목: ${movieDTO.movieName}</strong>
                <div class="movielike">
                    <i class="fa-solid fa-heart fa-2x"></i>
                    <i class="fa-regular fa-heart fa-2x"></i>
                </div>
                <p class="card-text">개봉일: ${movieDTO.releaseDate}</p>
                <p class="card-text">감독: ${movieDTO.director}</p>
                <p class="card-text">출연: ${movieDTO.actor}</p>
                <p class="card-text">지역: ${movieDTO.region}</p>
                <p class="card-text">장르: ${movieDTO.genre}</p>
                <p class="card-text">관객 수: ${movieDTO.audience}</p>
                <p class="card-text">순위: ${movieDTO.ranking}</p>
                <p class="card-text">러닝타임: ${movieDTO.runningtime}</p>
                <p class="card-text">평점: ${movieDTO.avgScore}</p>
                <p class="card-text">개요: ${movieDTO.outline}</p>
            </form>
        </div>
    </div>
    <div class="contentDetail">
        <%-- 주요 정보 --%>
        <div class="mainInfo">
            <span class="InfoTitle">주요 정보</span><br>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
        </div>
            <br>
        <%-- 출연 제작 --%>
            <span class="infoTitle">출연 / 제작</span>
        <div class="castInfo">
            <img class="loaded-lg castPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg castPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg castPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg castPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg castPhoto" src="../../css/movieCSS/img/img1235.jpg">
        </div>
            <br>
        <%-- 미디어 --%>
            <span class="infoTitle">예고편 / 포토</span><br>
        <div class="media">
            <img class="loaded-lg mediaPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg mediaPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg mediaPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg mediaPhoto" src="../../css/movieCSS/img/img1235.jpg">
            <img class="loaded-lg mediaPhoto" src="../../css/movieCSS/img/img1235.jpg">
        </div>
            <br>
        <!-- 리뷰 목록 출력 -->
            <span class="infoTitle">리뷰 및 평점</span>
        <div class="form-group row user-review-list">
            <ul>

            </ul>
        </div>
        <form name="frmReviewView" method="post">
            <input type="hidden" name="num" value="${movieDTO.movieNo}">
        </form>

        <%-- 리뷰 작성 --%>
        <c:if test="${loginInfo != null && isWrite != true}">
        <form name="frmReview" class="frmReview" method="post">
            <input type="hidden" name="num" value="${movieDTO.movieNo}">
            <div class="form-group row">
                <div class="col-sm-3">
                    <input name="nickName" type="text" class="form-control" value="${loginInfo.nickName}" readonly>
                </div>
            </div>
            <div>
                1<input type="radio" name="score" value="1" checked>
                2<input type="radio" name="score" value="2">
                3<input type="radio" name="score" value="3">
                4<input type="radio" name="score" value="4">
                5<input type="radio" name="score" value="5">
            </div>
            <div class="form-group row">
                <div class="col-sm-8">
                    <textarea name="review" class="form-control" cols="50" rows="3"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <span class="btn btn-primary" id="goReviewSubmit">등록하기</span>
                </div>
            </div>
            <script src="/js/movieJS/reviewForm.js"></script>
            </c:if>
        </form>
    </div>
    <div class="goMain">
        <a href="/main.movie?action=main" class="btn btn-primary"><< 메인화면</a>
        <br>
        <br>
        <a href="javascript:history.back()" class="btn btn-primary"><< 뒤로가기</a>
    </div>
    </div>
</div>
<jsp:include page="inc/movieFooter.jsp"/>
</body>
</html>