<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <title>회원 수정</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">회원 수정</h1>
  </div>
</div>

<div class="container">
  <form name="frmMember" action="/modify.member?action=modify" method="post">
    <div class="form-group row">
      <label class="col-sm-2">아이디</label>
      <div class="col-sm-3">
        <input type="text" name="memberId" class="form-control" value="${loginInfo.memberId}" readonly>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">비밀번호</label>
      <div class="col-sm-3">
        <input type="text" name="passwd" class="form-control" value="${loginInfo.passwd}">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">비밀번호 확인</label>
      <div class="col-sm-3">
        <input type="text" name="passwd-re" class="form-control" value="${loginInfo.passwd}">
        <span class="passwdCheck"></span>
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
      <div class="col-sm-10">
        <input type="submit" class="btn btn-primary" value="수정">
      </div>
    </div>
  </form>
</div>
</body>
</html>