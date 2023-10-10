
    const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
    const zzimBtn = document.querySelector('.movielike');
    const zzimCheck = document.querySelector('input[name=zzim]');
    const frmZzim = document.querySelector('form[name=frmZzim]');

    console.log("first : " + zzimCheck.value);

    if(zzimCheck.value === "true"){
        zzimBtn.innerHTML = '<i class="fa-solid fa-heart fa-2x"></i>';
    }
    else{
        zzimBtn.innerHTML = '<i class="fa-regular fa-heart fa-2x"></i>'
    }
    zzimBtn.addEventListener('click', function (){
        const movieNo = frmZzim.movieNo.value;
        const memberId = frmZzim.memberId.value;

        if(memberId !== "") {
            if (zzimCheck.value === "true") {
                xhr.open('POST', '/zzimRemove.movie?action=zzimRemove&movieNo=' + movieNo + '&memberId=' + memberId);
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState !== XMLHttpRequest.DONE)
                        return;

                    if (xhr.status === 200) {
                        const json = JSON.parse(xhr.response);
                        if (json.result === 'true') {
                        } else {
                            alert('등록에 실패했습니다.');
                        }
                    } else {
                        console.error(xhr.status, xhr.statusText);
                    }
                }
                zzimCheck.value = "false";
                zzimBtn.innerHTML = '<i class="fa-regular fa-heart fa-2x"></i>'
            } else {
                xhr.open('POST', '/zzimAdd.movie?action=zzimAdd&movieNo=' + movieNo + '&memberId=' + memberId);
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState !== XMLHttpRequest.DONE)
                        return;

                    if (xhr.status === 200) {
                        const json = JSON.parse(xhr.response);
                        if (json.result === 'true') {
                        } else {
                            alert('등록에 실패했습니다.');
                        }
                    } else {
                        console.error(xhr.status, xhr.statusText);
                    }
                }
                zzimCheck.value = "true";
                zzimBtn.innerHTML = '<i class="fa-solid fa-heart fa-2x"></i>';
            }
        }

    })

    const getReviews = function () {
        const num = document.querySelector('form[name=frmReviewView] input[name=num]').value;
        xhr.open('GET', '/review/get?num=' + num);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState !== XMLHttpRequest.DONE) return;

            if (xhr.status === 200) {
                console.log(xhr.response);
                const json = JSON.parse(xhr.response);
                addReviewTag(json);
            }
            else {
                console.error('Error', xhr.status, xhr.statusText);
            }
        }
    };

    const addReviewTag = function (items) {

        const tagUl = document.querySelector('.user-review-list ul');
        tagUl.innerHTML = '';
        for (const item of items) {
            const tagLi = document.createElement('li');
            tagLi.innerHTML = '평점 : ' + item.score + ' | ' + item.review + ' | ' + item.nickName + ' | ' + item.addDate;
            if (item.isLogin === true) {
                tagLi.innerHTML +=
                    '<span class="btn btn-danger" onclick="goReviewDelete(\'' + item.reviewNo + '\');">>삭제</span>'
            }
            tagLi.setAttribute('class', 'list-group-item');
            tagUl.append(tagLi);
        }

    };
    getReviews();

    const goReviewDelete = function (reviewNo) { // 리뷰 삭제 메소드
        if (confirm("삭제하시겠습니까?")) {
            xhr.open('POST', '/review/remove?reviewNo=' + reviewNo);
            xhr.send();

            xhr.onreadystatechange = () => {
                if (xhr.readyState !== XMLHttpRequest.DONE) {
                    return;
                }
                if (xhr.status === 200) {
                    console.log(xhr.response);
                    const json = JSON.parse(xhr.response);
                    if (json.result === 'true') {
                        getReviews();
                        window.location.reload();
                    }
                    else {
                        alert("삭제에 실패했습니다.");
                    }
                }
                else {
                    console.error('Error', xhr.status, xhr.statusText);
                }
            }
        }
    };
