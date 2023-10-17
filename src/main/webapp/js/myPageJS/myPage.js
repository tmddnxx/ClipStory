
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
    // "전체 선택" button1
    const checkAllButton1 = document.querySelector('.btn-checkAll1');
    const checkboxes1 = document.querySelectorAll('.list-group-1 input[type="checkbox"]');
    handleCheckAllButtonClick(checkAllButton1, checkboxes1);

    // "전체 선택" button2
    const checkAllButton2 = document.querySelector('.btn-checkAll2');
    const checkboxes2 = document.querySelectorAll('.list-group-2 input[type="checkbox"]');
    handleCheckAllButtonClick(checkAllButton2, checkboxes2);

    // "전체 선택" button3
    const checkAllButton3 = document.querySelector('.btn-checkAll3');
    const checkboxes3 = document.querySelectorAll('.list-group-3 input[type="checkbox"]');
    handleCheckAllButtonClick(checkAllButton3, checkboxes3);

    // "전체 선택" 버튼 클릭 시 체크
    function handleCheckAllButtonClick(button, checkboxes) {
        button.addEventListener('click', function () {
            const checked = this.value === '전체 선택';
            checkboxes.forEach(checkbox => {
                checkbox.checked = checked;
            });
            this.value = checked ? '전체 해제' : '전체 선택';
        });
    }

    // X 버튼 클릭 시 개별 컨텐츠 삭제 자바스크립트
    const removeButtonX = document.querySelectorAll(".remove-btn");
    const xhr = new XMLHttpRequest(); // ajax 작업을 위한 객체 생성


    removeButtonX.forEach(button => {
        button.addEventListener("click", function (event) {
            event.preventDefault(); // 경로 설정
            const listItem = this.closest("li"); // 삭제 할 컨텐츠 리스트
            if (listItem) {
                const confirmRemove = confirm("삭제 하시겠습니까?"); // 경고 문구
                if (confirmRemove) {
                    listItem.remove(); // 삭제 실행
                }
            }
        });
    });



    // 선택 삭제 선택 시 삭제
    // function handleRemoveSelected(btnClass, listClass) {
    //     const removeSelectedButton = document.querySelector(btnClass);
    //     removeSelectedButton.addEventListener('click', function () {
    //         const checkboxes = document.querySelectorAll(listClass + ' input[type="checkbox"]');
    //         const selectedItems = [];
    //
    //         checkboxes.forEach(checkbox => {
    //             if (checkbox.checked) {
    //                 selectedItems.push(checkbox.value);
    //             }
    //         });
    //
    //         if (selectedItems.length === 0) {
    //             alert(`마이 컨텐츠가 없습니다.`);
    //         } else {
    //             const confirmMessage = `해당 컨텐츠, ${selectedItems.length} 개를 삭제하시겠습니까?`;
    //             if (confirm(confirmMessage)) {
    //                 selectedItems.forEach(contentNo => {
    //                     const listItem = document.querySelector(`#chk${contentNo}`).closest('li');
    //                     if (listItem) {
    //                         listItem.remove();
    //                     }
    //                 });
    //
    //             }
    //         }
    //     });
    // }
    function removeSelectedContents(listNo){
            const listClass = ".list-group-" + listNo;
            const frmNo = 'form[name=frmRemoveSelected' + listNo + ']';
            const checkboxes = document.querySelectorAll(listClass + ' input[type="checkbox"]');
            const frmRemoveSelected = document.querySelector(frmNo);
            const selectedItems = [];

            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selectedItems.push(checkbox.value);
                }
            });

            if (selectedItems.length === 0) {
                alert(`마이 컨텐츠가 없습니다.`);
            } else {
                const confirmMessage = `해당 컨텐츠 ${selectedItems.length} 개를 삭제하시겠습니까?`;
                if (confirm(confirmMessage)) {
                    // selectedItems.forEach(contentNo => {
                    //     const listItem = document.querySelector(`#chk${contentNo}`).closest('li');
                    //     if (listItem) {
                    //         listItem.remove();
                    //     }
                    // });
                    frmRemoveSelected.submit();
                }
            }
    }

    const removeBoardBtn = document.querySelector(".btn-selected1"); // 게시글 삭제 버튼
    const removeCommentBtn = document.querySelector(".btn-selected2"); // 댓글 삭제 버튼
    const removeReviewBtn = document.querySelector(".btn-selected3"); // 리뷰 삭제 버튼

    removeBoardBtn.addEventListener('click', function(){removeSelectedContents(1)}); // 버튼 클릭시 게시글 삭제 함수 실행


    // 각 부분으로 동작하게 호출
    // handleRemoveSelected('.btn-selected1', '.list-group-1');
    // handleRemoveSelected('.btn-selected2', '.list-group-2');
    // handleRemoveSelected('.btn-selected3', '.list-group-3');

    // 전체 삭제 선택 시 삭제
    function handleRemoveAll(btnClass, listClass) {
        const removeAllButton = document.querySelector(btnClass);
        removeAllButton.addEventListener('click', function () {
            const checkboxes = document.querySelectorAll(listClass + ' input[type="checkbox"]');
            const selectedItems = [];

            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selectedItems.push(checkbox.value);
                }
            });

            if (selectedItems.length !== checkboxes.length) {
                alert('체크박스를 모두 체크해주세요.')
            }
            else { // 체크박스 다 체크된 경우만
                const confirmMessage = `모두 삭제하시겠습니까?`;
                if (confirm(confirmMessage)) {
                    selectedItems.forEach(contentNo => {
                        const listItem = document.querySelector(`#chk${contentNo}`).closest('li');
                        if (listItem) {
                            listItem.submit();
                            listItem.remove();
                        }
                    });
                }
            }
        });
    }
    // 각 부분으로 동작하게 호출
    handleRemoveAll('.btn-removeAll1', '.list-group-1');
    handleRemoveAll('.btn-removeAll2', '.list-group-2');
    handleRemoveAll('.btn-removeAll3', '.list-group-3');










