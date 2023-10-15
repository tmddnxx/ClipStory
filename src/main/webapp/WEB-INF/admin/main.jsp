<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>관리자 페이지</title>
</head>
<body>
<div style="position: fixed; margin-top: -100px;">
    <div style="width: 100vw; text-align: center;">
        여기는 관리자 메인 페이지로 오는 로고 박을 거임
    </div>
</div>
<div style="margin-top: 100px;">
    <div style="display: flex; ">
        <button style="width: 500px; height: 500px; font-size: 60pt;" onclick='location.href="/admin?action=addMovie";'>영화추가</button>
        <button style="width: 500px; height: 500px; font-size: 60pt;" onclick='location.href="/admin?action=addCrew";'>감독 / 배우등록</button>
        <button style="width: 500px; height: 500px; font-size: 60pt;">영화수정</button>
    </div>
    <div style="display: flex;">
        <button style="width: 500px; height: 500px; font-size: 60pt;">게시물</button>
        <button style="width: 500px; height: 500px; font-size: 60pt;">회원목록</button>
        <button style="width: 500px; height: 500px; font-size: 60pt;">사용자 페이지url</button>
    </div>
</div>
</body>
</html>