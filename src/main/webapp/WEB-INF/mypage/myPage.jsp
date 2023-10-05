<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%--%>
<%--  List boardDTOList = (List) request.getAttribute("boardDTOList");--%>
<%--  int totalRecord = (Integer) request.getAttribute("totalRecord");--%>
<%--  int pageNum = (Integer) request.getAttribute("pageNum");--%>
<%--  int totalPage = (Integer) request.getAttribute("totalPage");--%>
<%--  int limit = (Integer) request.getAttribute("limit");--%>

<%--  int startNum = (Integer) request.getAttribute("startNum"); // 페이지 시작 일련번호--%>

<%--  String items = request.getParameter("items") != null ? request.getParameter("items") : "title";--%>
<%--  String text = request.getParameter("text") != null ? request.getParameter("text") : "";--%>

<%--  int pagePerBlock = 5; // 페이지 범위--%>
<%--  int totalBlock = totalPage % pagePerBlock == 0 ? totalPage / pagePerBlock : totalPage / pagePerBlock + 1; // block의 전체 갯수(페이지 범위 단위의 총 갯수)--%>
<%--  int thisBlock = ((pageNum -1) / pagePerBlock) + 1; // 현재 블럭--%>
<%--  int firstPage = ((thisBlock -1) * pagePerBlock) + 1; // 블럭의 첫 페이지--%>
<%--  int lastPage = thisBlock * pagePerBlock; // 블럭의 마지막 페이지--%>
<%--  lastPage = (lastPage > totalPage) ? totalPage : lastPage;--%>
<%--%>--%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <title>마이페이지</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">마이페이지</h1>
  </div>
</div>

<div class="container">
  <h3 class="display-5">프로필</h3>
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
  <button>
    <a class="nav-link" href="/modify.member?action=modify&memberId=${loginInfo.memberId}">회원 수정</a>
  </button>
  <h3 class="display-5">내가 쓴 글</h3>
  <ul class="list-group">
    <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
      <%-- 현재시간 , 작성시간 구하기 --%>
      <fmt:parseNumber value="${currentTime.time / (1000*60*60)}" integerOnly="true" var="currentFmtTime" scope="request"/>
      <fmt:parseNumber value="${boardDTO.addDate.time / (1000*60*60)}" integerOnly="true" var="addFmtTime" scope="request"/>
      <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
        <a href="get.board?action=get&contentNo=${boardDTO.contentNo}" class="text-decoration-none">
            ${boardDTO.title}
            ${boardDTO.addDate}
            ${boardDTO.nickName}
            ${boardDTO.hit}
        </a>
      </li>
    </c:forEach>
  </ul>
  <h3 class="display-5">내가 쓴 댓글</h3>
  <ul class="list-group">
    <c:forEach var="commentDTO" items="${commentDTOList}" varStatus="status">
      <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
          <a href="get.board?action=get&contentNo=${commentDTO.contentNo}" class="text-decoration-none">
            ${commentDTO.commentNo}
            ${commentDTO.addDate}
            ${commentDTO.nickName}
          </a>
      </li>
    </c:forEach>
  </ul>
</div>
</body>

</html>