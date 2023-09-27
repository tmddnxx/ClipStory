<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List boardDTOList = (List) request.getAttribute("boardDTOList");
    int totalRecord = (Integer) request.getAttribute("totalRecord");
    int pageNum = (Integer) request.getAttribute("pageNum");
    int totalPage = (Integer) request.getAttribute("totalPage");
    int limit = (Integer) request.getAttribute("limit");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <title>게시판 목록</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container w-75 mt-5 mx-auto">
    <h2>게시판 목록</h2>
    <hr>
    <ul class="list-group">
        <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
            <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                <a href="get.board?action=get&contentNo=${boardDTO.contentNo}" class="text-decoration-none">
                    [<%=(totalRecord--)-(pageNum-1)*limit%>] ${boardDTO.title}, ${boardDTO.nickName}
                </a>
                <p style="text-align: right">${boardDTO.addDate}</p>
                <a href="./remove.board?action=remove&contentNo=${boardDTO.contentNo}">
                    <span class="badge bg-secondary">&times;</span>
                </a>
            </li>
        </c:forEach>
    </ul>
    <hr>
    <div align="center">
        <c:set var="pageNum" value="<%=pageNum%>" />
        <c:forEach var="i" begin="1" end="<%=totalPage%>">
            <a href="pageNum.board?action=list&pageNum=${i}">
                <c:choose>
                    <c:when test="${pageNum==i}"> <!--현재 페이지이면 볼드처리 -->
                        <font color="4C5317"><b> [${i}]</b></font>
                    </c:when>
                    <c:otherwise>
                        <font color="4C5317"> [${i}]</font>
                    </c:otherwise>
                </c:choose>
            </a>
        </c:forEach>
    </div>
    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생 : ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <button class="btn btn-outline-info mb-3" type="button" onclick="location.href='add.board?action=add'">글 쓰기</button>
    <div class="collapse" id="addForm">
        <div class="card card-body">
            <form method="post" action="./add.board?action=add">
                <label class="form-label">제목</label>
                <input type="text" name="title" class="form-control">
                <label class="form-label">내용</label>
                <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
                <input type="text" name="memberId" class="form-control" value="테스트 멤버아이디" hidden>
                <input type="text" name="nickName" class="form-control" value="테스트 닉네임" hidden>
                <button type="submit" class="btn btn-success mt-3">저장</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>