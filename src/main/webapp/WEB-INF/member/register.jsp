<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <title>회원 가입</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">회원 가입</h1>
  </div>
</div>

<div class="container">
  <form name="frmMember" action="/register.member?action=register" method="post">
    <div class="form-group row">
      <label class="col-sm-2">아이디</label>
      <div class="col-sm-3">
        <input type="text" name="memberId" class="form-control">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">비밀번호</label>
      <div class="col-sm-3">
        <input type="text" name="passwd" class="form-control">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">비밀번호 확인</label>
      <div class="col-sm-3">
        <input type="text" name="passwd-re" class="form-control">
        <span class="passwdCheck"></span>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">이름</label>
      <div class="col-sm-3">
        <input type="text" name="name" class="form-control">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">닉네임</label>
      <div class="col-sm-3">
        <input type="text" name="nickName" class="form-control">
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-10">
        <input type="submit" class="btn btn-primary" value="등록">
      </div>
    </div>
  </form>
</div>
</body>
</html>