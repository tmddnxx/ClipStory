<%@ page import="com.example.movie.model.dto.BoardDTO" %>
<%@ page import="com.example.movie.model.dto.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    BoardDTO boardDTO = (BoardDTO) request.getAttribute("boardDTO");
    String pageNum = request.getParameter("pageNum");
    String sessionId = (String) session.getAttribute("sessionId");

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/boardCSS/boardGet.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">
    <script src="/js/boardJS/boardGet.js" charset="UTF-8" defer></script>
    <title>게시글 상세페이지</title>
</head>
<body>
<div class="wrap">
<jsp:include page="../../inc/header.jsp"/>
<div class="container w-75 mt-5 mx-auto main">
    <h2>${boardDTO.title}</h2>
    <hr>
    <div class="text-box">
        <div class="text-box-in">
        <p>Date: ${boardDTO.addDate}</p>
        <p>조회수 : ${boardDTO.hit}</p>
        <p>닉네임 : ${boardDTO.nickName}</p>
    </div>
    </div>
    <div class="content-box">
        <div class="content-box-inner">
            <p class="content-text">Content: ${boardDTO.content}</p>
        </div>
    </div>
    <hr>
    <div class="form-group row user-comment-list">
        <ul>
        </ul>
    </div>
    <form name ="frmCommentView" method="post">
        <input type="hidden" name="contentNo" value="<%=boardDTO.getContentNo()%>">
        <input type="hidden" name="nickName" value="${loginInfo.nickName}">
        <input type="hidden" name="memberId" value="${loginInfo.memberId}">
    </form>
    <div>
        <form name="frmView" method="post">
            <input type="hidden" name="pageNum" value="<%=pageNum%>">
            <input type="hidden" name="num" value="<%=boardDTO.getContentNo()%>">
        </form>
        <div class="btn-box">
            <%
                if(boardDTO.getMemberId().equals(sessionId)){ // 본인이 작성한 글일 시 수정/ 삭제 버튼 활성화
            %>
            <a href="modify.board?action=modify&contentNo=${boardDTO.contentNo}" class="modify-btn" >수정</a>
            <a href="./remove.board?action=remove&contentNo=${boardDTO.contentNo}" onclick="return confirm('정말 삭제하시겠습니까?');" class="remove-btn">삭제 </a>
            <%
                }
            %>
<%--            목록 버튼 누르면 페이지넘버가져오기--%>
            <button type="button" class="back-btn" onclick="location.href='list.board?action=list&pageNum=<%=pageNum%>'">목록</button>
        </div>
    </div>
    <%-- 댓글창 --%>
    <c:if test="${loginInfo != null}">
            <form name="frmComment" method="post" class="comment-form">
                <input type="hidden" name="contentNo" value="${boardDTO.contentNo}">
                <div class="comment-nick">
                    <input type="text" name="nickName" value="${loginInfo.nickName}" readonly>
                </div>
                <div class="comment-content">
                    <textarea name="comment" class="form-control" cols="50" rows="3"></textarea>
                </div>
                <div class="comment-submit">
                    <div class="col-sm-4">
                        <span class="btn btn-submit" id="goCommentSubmit">등록</span>
                    </div>
                </div>
            </form>
        <script src="/js/boardJS/boardGet_commentForm.js" defer></script>
    </c:if>
</div>
</div>
</body>
</html>