<%@ page import="com.example.movie.model.dto.ReviewDTO" %>
<%@ page import="com.example.movie.model.dao.MovieDAO" %>
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
    <script src="https://kit.fontawesome.com/2404a35405.js" crossorigin="anonymous"></script>
    <title>영화 상세 페이지</title>
</head>
<body>
<%
    boolean zzim = (boolean) request.getAttribute("zzim");
%>
<jsp:include page="../header.jsp"/>
<div class="container w-75 mt-5 mx-auto">
    <%--    <h2>${movieDTO.movieName}</h2>--%>
    <hr>
    <style>

    </style>
    <div class="card w-75 mx-auto">
        <div class="movie-info">
            <div>
                <img class="card-img-top" src="${movieDTO.poster}">
            </div>
            <div>
                <div class="card-body">
                    <form name="frmZzim" method="post">
                        <input type="hidden" name="zzim" value="${zzim}"/>
                        <input type="hidden" name="movieNo" value="${movieDTO.movieNo}"/>
                        <input type="hidden" name="memberId" value="${loginInfo.memberId}"/>

                        <div class="movielike">
                            <i class="fa-solid fa-heart fa-2x"></i>
                            <i class="fa-regular fa-heart fa-2x"></i>
                        </div>
                    </form>
                    <h4 class="card-title">releaseDate: ${movieDTO.releaseDate}</h4>
                    <p class="card-text">outline: ${movieDTO.outline}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group row user-review-list">
        <ul>

        </ul>
    </div>
    <%-- 리플 목록 출력 영역 --%>
    <form name="frmReviewView" method="post">
        <input type="hidden" name="num" value="${movieDTO.movieNo}">
    </form>
    <script>
        const xhr = new XMLHttpRequest();

        const getReviews = function () {
            const num = document.querySelector('form[name=frmReviewView] input[name=num]').value;
            xhr.open('GET', '/review/get?num=' + num);
            xhr.send();
            xhr.onreadystatechange = function () {
                if (xhr.readyState !== XMLHttpRequest.DONE) return;

                if (xhr.status === 200) {
                    console.log(xhr.response);
                    const json = JSON.parse(xhr.response);
                    for (let data of json) {
                        // console.log(data);
                    }
                    addReviewTag(json);
                }
                else {
                    console.error('Error', xhr.status, xhr.statusText);
                }
            }
        }
        const addReviewTag = function (items) {

            const tagUl = document.querySelector('.user-review-list ul');
            tagUl.innerHTML = '';
            for (const item of items) {
                const tagLi = document.createElement('li');
                tagLi.innerHTML = '평점 : ' + item.score + ' | ' + item.review + ' | ' + item.nickName + ' | ' + item.addDate;
                if (item.isLogin === true) {
                    tagLi.innerHTML +=
                        '<span class="btn btn-danger" onclick="goReviewDelete(\'' + item.reviewNo + '\');">>삭제</span>'
                }
                tagLi.setAttribute('class', 'list-group-item');
                tagUl.append(tagLi);
            }

        };
        document.addEventListener('DOMContentLoaded', function () {
            getReviews();
        });
    </script>
    <%-- 리플 시작 --%>
    <c:if test="${loginInfo != null}">
        <form name="frmReview" method="post">
            <input type="hidden" name="num" value="${movieDTO.movieNo}">
            <div class="form-group row">
                <div class="col-sm-3">
                    <input name="nickName" type="text" class="form-control" value="${nickName}" placeholder="nickName" readonly>
                </div>
            </div>
            <div>
                1<input type="radio" name="score" value="1">
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
                    <span class="btn btn-primary" id="goReviewSubmit">등록</span>
                </div>
            </div>
        </form>
        <script>
            const goReviewDelete = function (reviewNo) {
                if (confirm("삭제하시겠습니까?")) {
                    xhr.open('POST', '/review/remove?reviewNo=' + reviewNo);
                    xhr.send();
                    xhr.onreadystatechange = () => {
                        if (xhr.readyState !== XMLHttpRequest.DONE) {
                            return;
                        }
                        if (xhr.status === 200) {
                            console.log(xhr.response);
                            const json = JSON.parse(xhr.response);
                            if (json.result === 'true') {
                                getReviews();
                            }
                            else {
                                alert("삭제에 실패했습니다.");
                            }
                        }
                        else {
                            console.error('Error', xhr.status, xhr.statusText);
                        }
                    }
                }
            };


            document.addEventListener('DOMContentLoaded', function () {
                const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
                const btnReviewSubmit = document.querySelector('#goReviewSubmit'); // 리플 등록 버튼
                const frmReview = document.querySelector('form[name=frmReview]');

                btnReviewSubmit.addEventListener('click', function () { // 등록 버튼 클릭시
                    // form 안에 input 태그가 있지만 form을 submit하는 것이 아니라 ajax로 값을 남겨야 되어서 값을 추출 함.
                    const num = frmReview.num.value;
                    const nickName = frmReview.nickName.value;
                    const review = frmReview.review.value;
                    const score = frmReview.score.value;

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
                            }
                            else {
                                alert('등록에 실패했습니다.');
                            }
                        }
                        else {
                            console.error(xhr.status, xhr.statusText);
                        }
                    }
                });
            });
        </script>
    </c:if>
    <hr>
    <a href="javascript:history.back()" class="btn btn-primary"><< back</a>
</div>
<hr>
<script>
    document.addEventListener('DOMContentLoaded', function (){
        const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
        const zzimBtn = document.querySelector('.movielike');
        const zzimCheck = document.querySelector('input[name=zzim]');
        const frmZzim = document.querySelector('form[name=frmZzim]');

        console.log("first : " + zzimCheck.value);

        if(zzimCheck.value === "true"){
            zzimBtn.innerHTML = '<i class="fa-solid fa-heart fa-2x"></i>';
        }
        else{
            zzimBtn.innerHTML = '<i class="fa-regular fa-heart fa-2x"></i>'
        }
        zzimBtn.addEventListener('click', function (){
            const movieNo = frmZzim.movieNo.value;
            const memberId = frmZzim.memberId.value;

            if(memberId !== "") {
                if (zzimCheck.value === "true") {
                    xhr.open('POST', '/zzimRemove.movie?action=zzimRemove&movieNo=' + movieNo + '&memberId=' + memberId);
                    xhr.send();
                    xhr.onreadystatechange = () => {
                        if (xhr.readyState !== XMLHttpRequest.DONE)
                            return;

                        if (xhr.status === 200) {
                            const json = JSON.parse(xhr.response);
                            if (json.result === 'true') {
                            } else {
                                alert('등록에 실패했습니다.');
                            }
                        } else {
                            console.error(xhr.status, xhr.statusText);
                        }
                    }
                    zzimCheck.value = "false";
                    zzimBtn.innerHTML = '<i class="fa-regular fa-heart fa-2x"></i>'
                } else {
                    xhr.open('POST', '/zzimAdd.movie?action=zzimAdd&movieNo=' + movieNo + '&memberId=' + memberId);
                    xhr.send();
                    xhr.onreadystatechange = () => {
                        if (xhr.readyState !== XMLHttpRequest.DONE)
                            return;

                        if (xhr.status === 200) {
                            const json = JSON.parse(xhr.response);
                            if (json.result === 'true') {
                            } else {
                                alert('등록에 실패했습니다.');
                            }
                        } else {
                            console.error(xhr.status, xhr.statusText);
                        }
                    }
                    zzimCheck.value = "true";
                    zzimBtn.innerHTML = '<i class="fa-solid fa-heart fa-2x"></i>';
                }
            }

        })
    })
</script>
</body>
</html>