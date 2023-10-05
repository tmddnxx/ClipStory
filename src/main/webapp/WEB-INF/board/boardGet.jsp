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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%--    <script type="text/javascript" charset="UTF-8" src="/js/boardGet.js" defer></script>--%>
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
    <div class="form-group row user-comment-list">
        <ul>



        </ul>
    </div>
    <form name ="frmCommentView" method="post">
        <input type="hidden" name="contentNo" value="<%=boardDTO.getContentNo()%>">
        <input type="hidden" name="nickName" value="${loginInfo.nickName}">
        <input type="hidden" name="memberId" value="${loginInfo.memberId}">
    </form>
    <script>
        const xhr = new XMLHttpRequest();

        const getComments = function () {
            const contentNo = document.querySelector('form[name=frmCommentView] input[name=contentNo]').value;
            xhr.open('GET', '/comment/get?contentNo=' + contentNo);
            xhr.send();
            xhr.onreadystatechange = function () {
                if (xhr.readyState !== XMLHttpRequest.DONE) return;

                if (xhr.status === 200) {
                    console.log(xhr.response);
                    const json = JSON.parse(xhr.response);
                    for (let data of json) {
                        console.log(data);
                    }
                    addCommentTag(json);
                } else {
                    console.error('Error', xhr.status, xhr.statusText);
                }
            }
        }

        const addCommentTag = function (items){
            const contentNo = document.querySelector('form[name=frmCommentView] input[name=contentNo]').value;
            const nickName = document.querySelector('form[name=frmCommentView] input[name=nickName]').value;
            const memberId = document.querySelector('form[name=frmCommentView] input[name=memberId]').value;
            const tagUl = document.querySelector('.user-comment-list ul');
            tagUl.innerHTML = '';
            for (const item of items){
                const tagLi = document.createElement('li');
                if(item.commentNo !== item.parentNo)
                    tagLi.innerHTML = '➥  '
                tagLi.innerHTML += item.comment + ' | ' + item.nickName + ' | ' + item.addDate;
                if (item.isLogin === true) {
                    tagLi.innerHTML +=
                        ' <span class="btn btn-danger" onclick="goCommentDelete(\'' + item.commentNo + '\');">삭제</span>';
                }
                if(item.commentNo === item.parentNo) {
                    tagLi.innerHTML += '<c:if test="${loginInfo != null}">' +
                        ' <span class="btn btn-primary" onclick="displayCommentRe(this);">답글</span>' +
                        '</c:if>';
                }

                tagLi.innerHTML += '<form name="frmCommentRe" method="post" style="display: none">' +
                    '<input type="hidden" name="contentNo" value="' + contentNo + '"/>' +
                    '<input type="hidden" name="parentNo" value="' + item.commentNo + '"/>' +
                    '<input type="hidden" name="memberId" value="' + memberId + '"/>' +
                    '<div class="form-group row">' +
                    '<input type="text" name="nickName" value="' +  nickName + '" class="form-control" readonly/>' +
                    '</div>' +
                    '<div class="form-group row">' +
                    '<textarea name="comment" class="form-control" cols="50" rows="3"></textarea>' +
                    '</div>' +
                    '<div class="form-group row">' +
                    '<div class="col-sm-4">' +
                    '<span class="btn btn-primary" onclick="submitCommentRe(this)">등록</span>' +
                    '</div>' +
                    '</div>' +
                    '</form>';

                tagLi.setAttribute('class', 'list-group-item');
                tagUl.append(tagLi);

            }
        }

        const displayCommentRe = function (btn) {
            const commentReFrm = btn.nextElementSibling;
            const commentReFrmAll = document.querySelectorAll('form[name=frmCommentRe]');
            if(commentReFrm.style.display === 'flex'){
                commentReFrm.style.display = 'none';
            }
            else{
                commentReFrmAll.forEach(function (frm){
                    frm.style.display = 'none';
                })
                commentReFrm.style.display = 'flex';
            }
        }

        const submitCommentRe = function (btn){
            const xhr = new XMLHttpRequest();
            const commentReFrm = btn.parentNode.parentNode.parentNode;
            const contentNo = commentReFrm.contentNo.value;
            const nickName = commentReFrm.nickName.value;
            const comment = commentReFrm.comment.value;
            const parentNo = commentReFrm.parentNo.value;
            xhr.open('POST', '/comment/addre?contentNo=' + contentNo + '&nickName=' + nickName + '&comment=' + comment + '&parentNo=' + parentNo);
            xhr.send();
            xhr.onreadystatechange = () => {
                if(xhr.readyState !== XMLHttpRequest.DONE)
                    return;

                if(xhr.status === 200){
                    console.log(xhr.response);
                    const json = JSON.parse(xhr.response);
                    if (json.result === 'true'){
                        commentReFrm.comment.value = '';
                        getComments();
                    } else {
                        alert('등록에 실패했습니다.');
                    }
                } else {
                    console.error(xhr.status, xhr.statusText);
                }
            }
        }
        const goCommentDelete = function(commentNo) {
            if (confirm("삭제하시겠습니까?")) {
                xhr.open('POST', '/comment/remove?commentNo=' + commentNo);
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState !== XMLHttpRequest.DONE) return;

                    if (xhr.status === 200) {
                        console.log(xhr.response);
                        const json = JSON.parse(xhr.response);
                        if (json.result === 'true'){
                            getComments();
                        }
                        else {
                            alert("삭제에 실패했습니다.");
                        }
                    } else {
                        console.error('Error', xhr.status, xhr.statusText);
                    }
                }
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            getComments();
        });
    </script>
    <div>
        <form name="frmView" method="post">
            <input type="hidden" name="pageNum" value="<%=pageNum%>">
            <input type="hidden" name="num" value="<%=boardDTO.getContentNo()%>">
        </form>
        <a href="javascript:history.back()" class="btn btn-primary"><< Back</a>
        <%
            if(boardDTO.getMemberId().equals(sessionId)){ // 본인이 작성한 글일 시 수정/ 삭제 버튼 활성화
        %>
        <a href="modify.board?action=modify&contentNo=${boardDTO.contentNo}" class="btn btn-primary" style="text-align: right">수정</a>
        <a href="./remove.board?action=remove&contentNo=${boardDTO.contentNo}" class="btn btn-danger">삭제 </a>
        <%
            }
        %>
<%--        <a href="modify.board?action=modify&contentNo=${boardDTO.contentNo}" class="btn btn-primary" style="text-align: right">Modify</a>--%>
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
                                getComments();
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