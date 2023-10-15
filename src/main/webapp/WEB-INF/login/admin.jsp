<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<%
  String result = request.getParameter("result");
  //pageContext.setAttribute("result", result);
%>
<head>
  <title>관리자 로그인</title>
  <link href="/css/memberCSS/login.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../../inc/header.jsp"/>

<div class="login-movie-container">
  <main>
    <section>
      <div class="login-container">
        <!-- 맨 위 -->
        <div class="top-container">
          <h2 class="head-title">관리자 로그인</h2>
        </div>
        <!-- 아이디와 비밀번호 입력 폼 -->
        <form class="adminLogin" action="/login/admin" method="post">
          <div class="input-container">
            <input type="text" placeholder="아이디" name="superId">
            <input type="password" placeholder="비밀번호" name="superPw">
            <c:if test="${result != null}">
              <span>관리자 권한이 없습니다.</span>
            </c:if>
          </div>
          <div class="login-btn-container">
            <button type="submit">로그인</button>
          </div>
        </form>

      </div>
    </section>
  </main>
</div>
</body>
</html>