<%@ page import="com.example.movie.model.dto.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    BoardDTO boardDTO = (BoardDTO) request.getAttribute("boardDTO");
    String pageNum = request.getParameter("pageNum");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <title>게시글 상세페이지</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container w-75 mt-5 mx-auto">
    <h2 style="text-align: center">${boardDTO.title}</h2>
    <hr>
    <h4 class="card-title" style="text-align: right">Date: ${boardDTO.addDate}</h4>
    <p>조회수 : ${boardDTO.hit}</p>
    <h4 style="text-align: right">닉네임 : ${boardDTO.nickName}</h4>
    <div class="card w-75 mx-auto">
        <div class="card-body">
            <p class="card-text">Content: ${boardDTO.content}</p>
        </div>
    </div>
    <hr>
    <form name ="frmCommentView" method="post">
        <input type="hidden" name="contentNo" value="<%=boardDTO.getContentNo()%>">
    </form>
    <div>
        <form name="frmView" method="post">
            <input type="hidden" name="pageNum" value="<%=pageNum%>">
            <input type="hidden" name="num" value="<%=boardDTO.getContentNo()%>">
        </form>
        <a href="javascript:history.back()" class="btn btn-primary"><< Back</a>
        <a href="modify.board?action=modify&contentNo=${boardDTO.contentNo}" class="btn btn-primary" style="text-align: right">Modify</a>
    </div>
    <c:if test="${loginInfo != null}">
        <form name="frmComment" method="post">
            <input type="hidden" name="contentNo" value="${boardDTO.contentNo}">
            <div class="form-group row">
                    <input type="text" name="nickName" value="${loginInfo.nickName}" class="form-control" readonly>
            </div>
            <div class="form-group row">
                <textarea name="comment" class="form-control" cols="50" rows="3"></textarea>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <span class="btn btn-primary" id="goCommentSubmit">등록</span>
                </div>
            </div>
        </form>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const xhr = new XMLHttpRequest();
                const btnCommentSubmit = document.querySelector('#goCommentSubmit');
                const frmComment = document.querySelector('form[name=frmComment]');

                btnCommentSubmit.addEventListener('click', function() {
                    const contentNo = frmComment.contentNo.value;
                    const nickName = frmComment.nickName.value;
                    const comment = frmComment.comment.value;
                    xhr.open('POST', '/comment/add?contentNo=' + contentNo + '&nickName=' + nickName + '&comment=' + comment);
                    xhr.send();
                    xhr.onreadystatechange = () => {
                        if(xhr.readyState !== XMLHttpRequest.DONE)
                            return;

                        if(xhr.status === 200){
                            console.log(xhr.response);
                            const json = JSON.parse(xhr.response);
                            if (json.result === 'true'){
                                frmComment.comment.value = '';
                            } else {
                                alert('등록에 실패했습니다.');
                            }
                        } else {
                            console.error(xhr.status, xhr.statusText);
                        }
                    }
                })
            });
        </script>
    </c:if>
</div>
</body>
</html>