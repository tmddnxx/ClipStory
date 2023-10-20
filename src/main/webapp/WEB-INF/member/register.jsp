<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link href="/css/memberCSS/register.css" rel="stylesheet">
  <link href="/css/common.css" rel="stylesheet">
  <script src="/js/memberJS/register.js" defer></script>
  <title>회원 가입</title>
</head>
<body>
<jsp:include page="/inc/header.jsp"/>
<main>
  <section>
    <div id="register-title">
      <h3 class="head-title">회원가입</h3>
    </div>
    <form name="frmMember" action="/register.member?action=register" method="post">
      <div id="register-container">
        <div id="register-input-container">
          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-id" type="text" placeholder="아이디" name="memberId">
              <div id="register-id-check"><i class="fa-solid fa-check fa-lg"></i></div>
            </div>
            <span>영문 또는 영문, 숫자 조합 4~12 자리<br></span>
            <p id="id-warn-span"></p>
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
              <input id="register-input-name" type="text" placeholder="이름" name="name">
              <div id="register-name-check"><i class="fa-solid fa-check fa-lg"></i></div>
            </div>
            <p id="name-warn-span"></p>
          </div>

          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-nick" type="text" placeholder="닉네임" name="nickName">
              <div id="register-nick-check"><i class="fa-solid fa-check fa-lg"></i></div>
            </div>
            <p id="nick-warn-span"></p>
          </div>
        </div>
      </div>

      <div id="register-buttons">
        <input type="button" id="submit-btn" value="등록">
        <input type="button" onclick=window.location.href="login" value="취소">
      </div>

    </form>
  </section>
</main>
</body>
</html>