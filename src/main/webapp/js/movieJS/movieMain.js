document.addEventListener('DOMContentLoaded', function () {
    const submitBtn = document.querySelector('#btn-search');

    submitBtn.addEventListener('click', function (e) {
        const input = document.querySelector('#search-input').value;
        // console.log("검색창 : "+input);
        if (input === "") {
            e.preventDefault();
            alert("검색어는 특수문자 제외 2자이상 입력하셔야 합니다.");
        }
    });
});