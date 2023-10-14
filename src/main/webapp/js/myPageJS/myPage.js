
    // 초기 tab-content hidden 설정
    const allTabContents = document.querySelectorAll(".tab-content");
    allTabContents.forEach((content) => {
    content.style.display = "none";
});

    // 탭 링크로 변경함
    const tabs = document.querySelectorAll(".mytab-link");

    // 탭링크 클릭시 함수
    tabs.forEach((tab) => {
    tab.addEventListener("click", (event) => {
        event.preventDefault();

        // 탭링크 초기화
        tabs.forEach((t) => {
            t.style.borderBottom = "none";
        });

        // 클릭시  탭 부분 경계 강조
        tab.style.borderBottom = "5px solid white";

        // 탭 컨텐츠 토글 적용 경우
        const targetTabId = tab.getAttribute("href").substring(1);
        const targetTabContent = document.getElementById(targetTabId);

        if (targetTabContent.style.display === "block") {
            targetTabContent.style.display = "none";
        } else {
            // 다른 탭 컨텐츠 안보이게 적용
            const allTabContents = document.querySelectorAll(".tab-content");
            allTabContents.forEach((content) => {
                content.style.display = "none";
            });

            // 선택 탭 컨텐츠 보이게 적용
            targetTabContent.style.display = "block";
        }
    });
});
    // "전체 선택" button click 핸들 1번
    function checkAll1(checked1) {
    const checkboxes = document.querySelectorAll('.list-group-1 input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
    checkbox.checked = checked1;
});
}

    // "전체 선택" button1 정의
    const checkAllButton1 = document.querySelector('.btn-checkAll1');

    //  "전체 선택" button1
    checkAllButton1.addEventListener('click', function () {
    const checked1 = this.value === '전체 선택';
    checkAll1(checked1);

    // Toggle the button text
    this.value = checked1 ? '전체 해제' : '전체 선택';
});


    // "전체 선택" button click 핸들 2번
    function checkAll2(checked2) {
    const checkboxes = document.querySelectorAll('.list-group-2 input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
    checkbox.checked = checked2;
});
}

    // "전체 선택" button2 정의
    const checkAllButton2 = document.querySelector('.btn-checkAll2');

    // "전체 선택" button2
    checkAllButton2.addEventListener('click', function () {
    const checked2 = this.value === '전체 선택';
    checkAll2(checked2);

    // Toggle the button text
    this.value = checked2 ? '전체 해제' : '전체 선택';
});
    // "전체 선택" button click 핸들 3번
    function checkAll(checked3) {
    const checkboxes = document.querySelectorAll('.list-group-3 input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
    checkbox.checked = checked3;
});
}

    // "전체 선택" button3 정의
    const checkAllButton3 = document.querySelector('.btn-checkAll3');

    // "전체 선택" button3
    checkAllButton3.addEventListener('click', function () {
    const checked3 = this.value === '전체 선택';
    checkAll(checked3);

    // Toggle the button text
    this.value = checked3 ? '전체 해제' : '전체 선택';
});
