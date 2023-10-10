document.addEventListener('DOMContentLoaded', function () {
    getReviews(); // 페이지 로딩후 리뷰목록을 가져온다

    const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성
    const btnReviewSubmit = document.querySelector('#groReviewSubmit'); // 리플 등록 버튼
    const frmReview = document.querySelector('form[name=fmReview]'); // 리뷰 등록 form



    btnReviewSubmit.addEventListener('click', function (e) { // 등록 버튼 클릭시
        // form 안에 input 태그가 있지만 form을 submit하는 것이 아니라 ajax로 값을 남겨야 되어서 값을 추출 함.
        const num = frmReview.num.value; // 해당 영화 고유 번호(movieNo)를 들고 온다.
        const nickName = frmReview.nickName.value; // 현재 로그인한 유저의 닉네임을 들고 온다.
        const review = frmReview.review.value; // 유저가 작성한 리뷰내용을 들고 온다.
        const score = frmReview.score.value; // 유저가 매긴 평점을 들고 온다.


        if (review === "") { // 리뷰를 빈칸으로 적을 시 출력
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
                } else {
                    alert('등록에 실패했습니다.');
                }
            } else {
                console.error(xhr.status, xhr.statusText);
            }
        }
    });
});