<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <title>Title</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">로그인</h1>
  </div>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-9">
      <h3 class="form-signin-heading">Please sign in</h3>
      <form class="frmlogin" action="/login" method="post">
        <div class="form-group">
          <label for="memberId" class="sr-only">User Id</label>
          <input type="text" name="memberId" id="memberId" class="form-control" placeholder="ID" required autofocus>
        </div>
        <div class="form-group">
          <label for="passwd" class="sr-only">Password</label>
          <input type="password" name="passwd" id="passwd" class="form-control" placeholder="Password" required>
        </div>
        <button class="btn btn btn-lg btn-success btn-block" type="submit">로그인</button>
        <button class="btn btn btn-lg btn-success btn-block"><a href="/register.member?action=register" style="text-decoration-line: none; color: white">회원가입</a></button>
      </form>
    </div>
  </div>
</div>
</body>
</html>
