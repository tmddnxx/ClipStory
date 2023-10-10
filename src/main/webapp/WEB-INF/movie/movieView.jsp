<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    getReviews(); // 페이지 로딩후 리뷰목록을 가져온다

                    const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
                    const btnReviewSubmit = document.querySelector('#groReviewSubmit'); // 리플 등록 버튼
                    const frmReview = document.querySelector('form[name=fmReview]'); // 리뷰 등록 form



                    btnReviewSubmit.addEventListener('click', function (e) { // 등록 버튼 클릭시
                        // form 안에 input 태그가 있지만 form을 submit하는 것이 아니라 ajax로 값을 남겨야 되어서 값을 추출 함.
                        const num = frmReview.num.value; // 해당 영화 고유 번호(movieNo)를 들고 온다.
                        const nickName = frmReview.nickName.value; // 현재 로그인한 유저의 닉네임을 들고 온다.
                        const review = frmReview.review.value; // 유저가 작성한 리뷰내용을 들고 온다.
                        const score = frmReview.score.value; // 유저가 매긴 평점을 들고 온다.


                        if (review === "") { // 리뷰를 빈칸으로 적을 시 출력
                            e.preventDefault();
                            alert('리뷰는 한 글자 이상 입력해야 합니다.')
                        }

                        xhr.open('POST', '/review/add?num=' + num + '&nickName=' + nickName + '&review=' + review + '&score=' + score);
                        xhr.send();
                        xhr.onreadystatechange = () => {
                            if (xhr.readyState !== XMLHttpRequest.DONE)
                                return;

                            if (xhr.status === 200) {
                                const json = JSON.parse(xhr.response);
                                if (json.result === 'true') {
                                    frmReview.review.value = '';
                                    getReviews();
                                    window.location.reload();
                                } else {
                                    alert('등록에 실패했습니다.');
                                }
                            } else {
                                console.error(xhr.status, xhr.statusText);
                            }
                        }
                    });
                });
            </script>
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