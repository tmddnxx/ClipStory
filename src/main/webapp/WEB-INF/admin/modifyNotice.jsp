<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/boardCSS/boardModify.css" rel="stylesheet">
    <link href="/common.css" rel="stylesheet">
    <script src="/js/adminJS/adminNoticeModify.js" defer></script>
    <title>관리자 공지사항 수정페이지</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="../../inc/header.jsp"/>
    <div class="main">
        <h3 id="title">공지사항 수정</h3>
        <hr>
        <p>${adminBoardDTO.nickName}</p>
        <div class="modify-content">
            <form method="post" action="/admin?action=noticeModify&cno=${adminBoardDTO.cno}">
                <div class="title-box">
                    <label class="content-title-in">제목</label>
                    <input type="text" name="title" class="form-control" value="${adminBoardDTO.title}">
                </div>
                <div class="content-box">
                    <label class="form-label">내용</label>
                    <textarea cols="50" rows="5" name="content" class="form-control">${adminBoardDTO.content}</textarea>
                </div>
                <input type="text" name="superId" class="form-control" value="${adminBoardDTO.superId}" hidden>
                <input type="text" name="superName" class="form-control" value="${adminBoardDTO.superName}" hidden>
                <div class="modify-btnbox">
                    <button type="submit" class="modify-submit-btn">수정</button>
                    <a href="/admin?action=noticeGet&cno=${adminBoardDTO.cno}" class="modify-back-btn" onclick="return confirm('입력하신 내용이 저장되지 않았습니다.');">취소</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>