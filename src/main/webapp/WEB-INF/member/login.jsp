<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
  <title>Title</title>
  <link href="/css/memberCSS/login.css" rel="stylesheet">
  <link href="/css/common.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/inc/header.jsp"/>
<div class="login-movie-container">
  <main>
    <section>
      <div class="login-container">
        <!--       맨 위             -->
        <div class="top-container">
          <h2 class="head-title">로그인</h2>
        </div>
        <!--       아이디 비밀번호             -->
        <form class="frmlogin" action="/login" method="post">
          <div class="input-container">
            <input type="text" placeholder="아이디" name="memberId">
            <input type="password" placeholder="비밀번호" name="passwd">
          <c:if test="${result != null}">
            <span>ID나 비밀번호가 일치하지 않습니다</span>
          </c:if>
          </div>
          <div class="login-btn-container">
            <button type="submit">로그인</button>
          </div>
        </form>
        <div class="register-container">
          <span>아직 계정이 없으신가요?&nbsp;</span>
          <a class="register-link" href="/register.member?action=register">회원가입하기</a>
        </div>
      </div>
    </section>
  </main>
</div>
</body>
</html>
