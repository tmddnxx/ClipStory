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
    <title>영화 소개 사이트</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container" style="max-width: 5000px;">
    <nav class="nav" style="padding-top: 26px; text-align: center; justify-content: center; font-size: 26px;">
        <%--        <a class="nav-link active" aria-current="page" href="#" style="font-weight: 600; color: black;">홈</a>--%>
        <a class="nav-link" href="#" style="font-weight: 600; color: black;">랭킹</a>
        <a class="nav-link" href="#" style="font-weight: 600; color: black;">상영/예정작</a>
        <%--        <a class="nav-link " aria-disabled="true" style="font-weight: 600; color: black;">게시판</a>--%>
    </nav>
    <h2>영화 목록</h2>
    <hr>
    <form class="search-model-form">
        <input type="text" name="searchbtn" id="search-input">
    </form>
    <hr>
    <%--  영화 리스트  --%>
    <h3>영화</h3>
    <div class="container" style="display: flex; flex-wrap: wrap; justify-content: space-between; width: 5000px; height: 220px;">
        <c:forEach var="movie" items="${listMovie}" varStatus="status">
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
    <h3>OTT</h3>
    <%--  OTT 리스트  --%>
    <div class="container" style="display: inline-flex; flex-wrap: wrap; justify-content: space-between; width: 5000px;">
        <c:forEach var="movie" items="${listOtt}" varStatus="status">
            <h2><strong>${status.count}</strong></h2>
            <a href="view.movie?action=view&movieNo=${movie.movieNo}" class="text-decoration-none">
                    <span class="badge bg-secondary" style="margin-top: 100px;margin-left: 28px;">
                            ${movie.movieName}, ${movie.releaseDate}
                    </span>
            </a>
            <a href="./remove.movie?action=remove&movieNo=${movie.movieNo}" hidden="hidden">
                <span class="badge bg-secondary">&times;</span>
            </a>
        </c:forEach>
    </div>


    <style>

        * { margin: 0; padding: 0; }
        li { list-style: none; }

        /* 슬라이더 */
        section { overflow: hidden; width: 600px; height: 500px; margin: 100px auto; }

        .sliderList { width:300%; }
        .sliderList li { float: left; width: 33.3333%; height: 400px; }

        /* 슬라이더 버튼 */
        .btnList { clear: both; width: 90px; margin: 0 auto;  padding-top: 20px; }
        .btnList span { display: block; float: left; width: 20px; height: 20px; margin: 0 5px; background: #666;
            border-radius: 20px; text-indent: -9999px; cursor: pointer; }
        .btnList .on { background: red; }
    </style>
    </head>
    <body>
    <section>
        <div>
            <ul class="sliderList">
                <li><img src="https://via.placeholder.com/600x400?text=1" /></li>
                <li><img src="https://via.placeholder.com/600x400?text=2" /></li>
                <li><img src="https://via.placeholder.com/600x400?text=3" /></li>
            </ul>
            <div class="btnList">
                <span class="btn1 on">첫 번째</span>
                <span class="btn2">두 번째</span>
                <span class="btn3">세 번째</span>
            </div>
        </div>
    </section>
    <script>
        $(function() {
            const imageWidth = 600;
            const move = function() {
                let idx = $('.btnList span.on').index();
                $('.btnList span').removeClass('on').eq((idx + 1) % 3).addClass('on');
                //한줄로 간소화

                // $('.btnList span').removeClass('on') // 슬라이드가 넘어 갈 때마다 조건문속 addclass가 실행되기전 초기화
                // console.log($('.sliderList').css('margin-left'));


                if(parseInt($('.sliderList').css('margin-left')) < 2*-(imageWidth)) { // 3번 슬라이드
                    $('.sliderList').stop().animate({ marginLeft: 0 });
                    // $('.btnList span').eq(0).addClass('on');


                }

                else {
                    if (parseInt($('.sliderList').css('margin-left')) === 0 ) { //1번 슬라이드
                        // $('.btnList span').eq(1).addClass('on');

                    }
                    else if (parseInt($('.sliderList').css('margin-left')) === -(imageWidth) ) { // 2번 슬라이드
                        // $('.btnList span').eq(2).addClass('on');

                    }
                    // 2, 3번째 이동의 경우 margin left값의 -값 증가
                    $('.sliderList').stop().animate({ marginLeft: '-=' + imageWidth + 'px' });
                }
            };
            let timer = setInterval(move, 3 * 1000);

            $('.btnList span').on('click', function() {

                clearInterval(timer); // 저장된 작업은 모두 실행 되고 다음 작업 스케줄이 중지
                timer = setInterval(move, 3 * 1000); // 시간을 재정의


                let num = $(this).index(); // 해당요소의 인덱스값을 저장

                $('.sliderList').stop().animate({ marginLeft: -(imageWidth) * num });
                // margin left 값을 이용해서 해당 인덱스의 이미지로 이동

                $('.btnList span').removeClass('on') // 클릭 할 때마다 'on'class 제거
                $(this).addClass('on') // 누를때 마다 'on'class 생성


            });

        })

    </script>
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