const xhr = new XMLHttpRequest();

const getReviews = function () { // 리뷰 출력 메소드
    const num = document.querySelector('form[name=frmReviewView] input[name=num]').value;
    xhr.open('GET', '/review/get?num=' + num);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;

        if (xhr.status === 200) {
            console.log(xhr.response);
            const json = JSON.parse(xhr.response);
            for (let data of json) {
                // console.log(data);
            }
            addReviewTag(json);
        }
        else {
            console.error('Error', xhr.status, xhr.statusText);
        }
    }
}
const addReviewTag = function (items) { // 리뷰 목록에 태그 추가

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

document.addEventListener('DOMContentLoaded', function () {
    getReviews(); // 페이지 로딩후 리뷰목록을 가져온다

    const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
    const btnReviewSubmit = document.querySelector('#goReviewSubmit'); // 리플 등록 버튼
    const frmReview = document.querySelector('form[name=frmReview]'); // 리뷰 등록 form


    btnReviewSubmit.addEventListener('click', function (e) { // 등록 버튼 클릭시
        // form 안에 input 태그가 있지만 form을 submit하는 것이 아니라 ajax로 값을 남겨야 되어서 값을 추출 함.
        const num = frmReview.num.value; // 해당 영화 고유 번호(movieNo)를 들고 온다.
        const nickName = frmReview.nickName.value; // 현재 로그인한 유저의 닉네임을 들고 온다.
        const review = frmReview.review.value; // 유저가 작성한 리뷰내용을 들고 온다.
        const score = frmReview.score.value; // 유저가 매긴 평점을 들고 온다.


        if(review === "") { // 리뷰를 빈칸으로 적을 시 출력
            e.preventDefault();
            alert('리뷰는 한 글자 이상 입력해야 합니다.')
        }

        xhr.open('POST', '/review/add?num=' + num + '&nickName=' + nickName + '&review=' + review + '&score=' + score);
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE)
                return;

            if (xhr.status === 200) {
                const json = JSON.parse(xhr.response);
                if (json.result === 'true') {
                    frmReview.review.value = '';
                    getReviews();
                    window.location.reload();
                }
                else {
                    alert('등록에 실패했습니다.');
                }
            }
            else {
                console.error(xhr.status, xhr.statusText);
            }
        }
    });

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

    });

});