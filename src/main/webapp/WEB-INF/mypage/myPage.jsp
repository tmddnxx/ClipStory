<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <title>마이페이지</title>
  <style>
    .accordion { /* 아코디언 CSS */
      cursor: pointer;
      background-color: #f1f1f1;
      color: #333;
      padding: 10px;
      border: 2px solid black;
      text-align: left;
      width: 100%;
      outline: none;
      transition: 0.4s;
    }

    .accordion.active { /* 아코디언 반응 시 CSS */
      background-color: #ddd;
    }

    .panel { /* 판넬 CSS */
      display: none;
      padding: 10px;
    }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">마이페이지</h1>
  </div>
</div>

<div class="container">
  <h3 class="display-5"> <%-- 프로필 --%>
    <a class="nav-link" href="/modify.member?action=modify&memberId=${loginInfo.memberId}">프로필</a>
  </h3>
  <a href="/modify.member?action=modify"><input type="button" value="회원수정"></a>
  <form name="frmMypage" action="/modify.mypage?action=mypage" method="post">
    <div class="form-group row">
      <label class="col-sm-2">아이디</label>
      <div class="col-sm-3">
        <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" readonly>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">이름</label>
      <div class="col-sm-3">
        <input type="text" name="name" class="form-control" value="${loginInfo.name}">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">닉네임</label>
      <div class="col-sm-3">
        <input type="text" name="nickName" class="form-control" value="${loginInfo.nickName}">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">찜</label>
      <div class="col-sm-3">
        <input type="text" name="zzim" class="form-control" value="${loginInfo.zzim}">
      </div>
    </div>
  </form>
</div>
<div class="container">
  <button class="accordion">
    <h3 class="display-5">내가 쓴 글</h3> <%-- 내가 쓴 글 리스트 --%>
  </button>
  <div class="panel">
    <ul class="list-group">
      <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
        <%-- 현재시간 , 작성시간 구하기 --%>
        <fmt:parseNumber value="${currentTime.time / (1000*60*60)}" integerOnly="true" var="currentFmtTime" scope="request"/>
        <fmt:parseNumber value="${boardDTO.addDate.time / (1000*60*60)}" integerOnly="true" var="addFmtTime" scope="request"/>
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
          <a href="get.board?action=get&contentNo=${boardDTO.contentNo}" class="text-decoration-none" type="hidden">
              ${boardDTO.title}
              ${boardDTO.addDate}
              ${boardDTO.nickName}
              ${boardDTO.hit}
          </a>
        </li>
      </c:forEach>
    </ul>
  </div>
  <button class="accordion">
    <h3 class="display-5">내가 쓴 댓글</h3> <%-- 댓글 리스트 --%>
  </button>
  <div class="panel">
    <ul class="list-group">
      <c:forEach var="commentDTO" items="${commentDTOList}" varStatus="status">
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
          <a href="get.board?action=get&contentNo=${commentDTO.contentNo}" class="text-decoration-none" >
              ${commentDTO.commentNo}
              ${commentDTO.addDate}
              ${commentDTO.nickName}
          </a>
        </li>
      </c:forEach>
    </ul>
  </div>
  <button class="accordion">
    <h3 class="display-5">내가 쓴 리뷰</h3> <%-- 리뷰 리스트 --%>
  </button>
  <div class="panel">
    <ul class="list-group">
      <c:forEach var="reviewDTO" items="${reviewDTOList}" varStatus="status">
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
          <a href="view.movie?action=view&movieNo=${reviewDTO.movieNo}" class="text-decoration-none" >
              ${reviewDTO.score}
              ${reviewDTO.review}
              ${reviewDTO.nickName}
              ${reviewDTO.addDate}
          </a>
        </li>
      </c:forEach>
    </ul>
  </div>
  <button class="accordion">
    <h3 class="display-5">내가 찜한 영화</h3> <%-- 찜한 영화 리스트 --%>
  </button>
  <div class="panel">
    <ul class="list-group">
      <c:forEach var="movieDTO" items="${zzimMovieList}" varStatus="status">
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
          <a href="view.movie?action=view&movieNo=${movieDTO.movieNo}" class="text-decoration-none" >
              ${movieDTO.movieNo}
              ${movieDTO.movieName}
              ${movieDTO.director}
              ${movieDTO.actor}
              ${movieDTO.releaseDate}
              ${movieDTO.region}
              ${movieDTO.genre}
              ${movieDTO.audience}
              ${movieDTO.ranking}
              ${movieDTO.runningtime}
              ${movieDTO.outline}
              ${movieDTO.poster}
              ${movieDTO.mo}
              ${movieDTO.avgScore}
          </a>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>
<script>
  // 아코디언 자바 스크립트
  const accordions = document.querySelectorAll(".accordion");
  let i;

  for (i = 0; i < accordions.length; i++) {
    accordions[i].addEventListener("click", function() {
      this.classList.toggle("active");
      const panel = this.nextElementSibling;
      if (panel.style.display === "block") {
        panel.style.display = "none";
      } else {
        panel.style.display = "block";
      }
    });
  }
</script>
</body>
</html>