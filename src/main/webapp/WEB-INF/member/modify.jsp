<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link href="/css/memberCSS/modify.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
  <script src="/js/memberJS/modify.js" defer></script>
  <title>회원 수정</title>
</head>
<body>
<jsp:include page="../../inc/header.jsp"/>

<%--<div class="container">--%>
<%--  <form name="frmMember" action="/modify.member?action=modify" method="post">--%>
<%--    <div class="form-group row">--%>
<%--      <label class="col-sm-2">아이디</label>--%>
<%--      <div class="col-sm-3">--%>
<%--        <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" readonly>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--      <label class="col-sm-2">비밀번호</label>--%>
<%--      <div class="col-sm-3">--%>
<%--        <input type="text" name="passwd" class="form-control" value="${loginInfo.passwd}">--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--      <label class="col-sm-2">비밀번호 확인</label>--%>
<%--      <div class="col-sm-3">--%>
<%--        <input type="text" name="passwd-re" class="form-control" value="${loginInfo.passwd}">--%>
<%--        <span class="passwdCheck"></span>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--      <label class="col-sm-2">이름</label>--%>
<%--      <div class="col-sm-3">--%>
<%--        <input type="text" name="name" class="form-control" value="${loginInfo.name}">--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--      <label class="col-sm-2">닉네임</label>--%>
<%--      <div class="col-sm-3">--%>
<%--        <input type="text" name="nickName" class="form-control" value="${loginInfo.nickName}">--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--      <div class="col-sm-10">--%>
<%--        <input type="submit" class="btn btn-primary" value="수정">--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </form>--%>
<%--</div>--%>

<main>
  <section>
    <div id="register-title">
      <h3 class="head-title">회원수정</h3>
    </div>
    <form name="frmMember" action="/modify.member?action=modify" method="post">
      <div id="register-container">
        <div id="register-input-container">
          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-id" type="text" placeholder="아이디" value="${loginInfo.memberId}" name="memberId" readonly>
            </div>
          </div>

          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-pw" type="password" placeholder="비밀번호" name="passwd">
              <div id="register-pw-check"><i class="fa-solid fa-check fa-lg"></i></div>
            </div>
            <span>영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15 자리<br></span>
            <p id = pw-warn-span></p>
          </div>

          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-pw-re" type="password" placeholder="비밀번호 확인">
              <div id="register-pwRe-check"><i class="fa-solid fa-check fa-lg"></i></div>
            </div>
            <p id = pw-re-warn-span></p>
          </div>

          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-name" type="text" placeholder="이름" value="${loginInfo.name}" name="name">
              <input id="original-name" type="hidden" value="${loginInfo.name}">
              <div id="register-name-check"><i class="fa-solid fa-check fa-lg" style="color: #000000"></i></div>
            </div>
            <p id="name-warn-span"></p>
          </div>

          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-nick" type="text" placeholder="닉네임" value="${loginInfo.nickName}" name="nickName">
              <input id="original-nick" type="hidden" value="${loginInfo.nickName}">
              <div id="register-nick-check"><i class="fa-solid fa-check fa-lg" style="color: #000000"></i></div>
            </div>
            <p id="nick-warn-span"></p>
          </div>
        </div>
      </div>

      <div id="register-buttons">
        <input type="button" id="submit-btn" value="수정">
        <input type="button" onclick="location.href='list.mypage'" value="취소">
      </div>
    </form>
  </section>
</main>
</body>
</html>