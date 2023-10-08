
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
                if(item.memberId !== "" && item.nickName !== ""){
                    if(item.commentNo !== item.parentNo)
                        tagLi.innerHTML = '➥  '
                    tagLi.innerHTML += item.comment + ' | ' + item.nickName + ' | ' + item.addDate;
                    if (item.isLogin === true) {
                        tagLi.innerHTML +=
                            ' <span class="btn btn-danger" onclick="goCommentDelete(\'' + item.commentNo + '\', \'' + item.parentNo + '\');">삭제</span>';
                    }
                    if(item.commentNo === item.parentNo) {
                        tagLi.innerHTML += '<c:if test="${loginInfo != null}">' +
                            ' <span class="btn btn-primary" onclick="displayCommentRe(this);">답글</span>' +
                            '</c:if>';
                    }
                }
                else{
                    tagLi.innerHTML += item.comment;
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
                    '<span class="btn btn-primary subcommentRe" onclick="submitCommentRe(this)">등록</span>' +
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

            if(comment.trim() == ""){
                alert("내용을 입력해주세요");
                comment.focus();
                return false;
            }
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
        const goCommentDelete = function(commentNo,parentNo) {
            if (confirm("삭제하시겠습니까?")) {
                xhr.open('POST', '/comment/remove?commentNo=' + commentNo + '&parentNo=' + parentNo);
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

    document.addEventListener('DOMContentLoaded', function (){
        getComments();
        const btnCommentSubmit = document.querySelector('#goCommentSubmit');
        const frmComment = document.querySelector('form[name=frmComment]');
        const commentbox = document.querySelector('textarea[name=comment]');

        btnCommentSubmit.addEventListener('click', function(e) {
            if(commentbox.value.trim() == ""){
                alert("내용을 입력해주세요.");
                commentbox.focus();
                return false;
            }

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

