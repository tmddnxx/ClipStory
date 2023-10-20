<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link href="/css/memberCSS/modify.css" rel="stylesheet">
  <link href="/css/common.css" rel="stylesheet">
  <script src="/js/memberJS/modify.js" defer></script>
  <title>회원 수정</title>
</head>
<body>
<jsp:include page="/inc/header.jsp"/>
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
              <input id="register-input-id" type="text" placeholder="아이디" value="${memberDTO.memberId}" name="memberId" readonly>
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
              <input id="register-input-name" type="text" placeholder="이름" value="${memberDTO.name}" name="name">
              <input id="original-name" type="hidden" value="${memberDTO.name}">
              <div id="register-name-check"><i class="fa-solid fa-check fa-lg" style="color: #000000"></i></div>
            </div>
            <p id="name-warn-span"></p>
          </div>

          <div class="register-input">
            <div class="register-input-div">
              <input id="register-input-nick" type="text" placeholder="닉네임" value="${memberDTO.nickName}" name="nickName">
              <input id="original-nick" type="hidden" value="${memberDTO.nickName}">
              <div id="register-nick-check"><i class="fa-solid fa-check fa-lg" style="color: #000000"></i></div>
            </div>
            <p id="nick-warn-span"></p>
          </div>
        </div>
      </div>

      <div id="register-buttons">
        <input type="button" id="submit-btn" value="수정">
        <input type="button" onclick="location.href='list.mypage'" value="취소">
        <input type="button" onclick="if(confirm('회원정보가 모두 삭제됩니다 정말 탈퇴하시겠습니까?')){location.href='remove.member?action=remove&memberId=${memberDTO.memberId}';}" value="회원탈퇴">
      </div>
    </form>
  </section>
</main>
</body>
</html>