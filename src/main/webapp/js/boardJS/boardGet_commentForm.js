document.addEventListener('DOMContentLoaded', function () {
    const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
    const frmComment = document.querySelector('form[name=frmComment]');
    const commentbox = document.querySelector('textarea[name=comment]');
    const btnCommentSubmit = document.querySelector('#goCommentSubmit');
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
    });
});