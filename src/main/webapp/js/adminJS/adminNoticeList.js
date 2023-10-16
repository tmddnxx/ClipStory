document.addEventListener("DOMContentLoaded", function () {

    // 전체 선택 , 전체 해제
    const allCheck = document.getElementById('allCheck');
    const chBoxs = document.querySelectorAll('.chBox');
    handleCheckAllButtonClick(allCheck, chBoxs)
    function handleCheckAllButtonClick(button, checkboxes) {
        button.addEventListener('click', function () {
            const checked = this.value === '전체 선택';
            checkboxes.forEach(checkbox => {
                checkbox.checked = checked;
            });
            this.value = checked ? '전체 해제' : '전체 선택';
        });
    }


    // 선택삭제
    const frmBoardList = document.querySelector('form[name=frmBoardList]');
    function handleRemoveSelected(btnClass, listClass) {
        const removeSelectBtn = document.querySelector(btnClass);
        removeSelectBtn.addEventListener('click', function () {
            const checkBoxes = document.querySelectorAll(listClass + ' .chBox');
            const selected = [];

            checkBoxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selected.push(checkbox.value);
                }
            });

            if (selected.length === 0) {
                alert("선택한 항목이 없습니다.");
            } else {
                const confirmMessage = `게시물 ${selected.length} 개를 삭제하시겠습니까?`;
                if (confirm(confirmMessage)) {
                    selected.forEach(contentNo => {
                        const selectList = document.querySelector(`#chk${contentNo}`);
                        if (selectList) {
                            frmBoardList.submit();
                        }
                    });
                }
            }
        });
    }
    handleRemoveSelected('.selectDelete_btn', '.list');




    //페이징 영역의 a태그를 클릭할 때 a 태그의 기능을 막고, 폼을 적용
    const btnPaging = document.querySelectorAll('.paging a');
    const frmList = document.querySelector('form[name=frmList]');
    btnPaging.forEach(btn => {
        btn.addEventListener('click', function (e){
            e.preventDefault(); // a링크 막음
            frmList.pageNum.value = e.target.parentNode.href.split('?')[1].split('&')[1].split('=')[1];
            frmList.action = '/admin?action=noticeList';
            console.log(e.target);
            frmList.submit();
        });
    });
    // 검색 클릭시 검색 조건은 그대로, 1페이지로 이동
    const btnSearch = document.querySelector('#btn-search');
    btnSearch.addEventListener('click', function (){
        frmList.pageNum.value=1;
        frmList.submit();
    });

    const btnReset = document.querySelector('#btn-reset');
    btnReset.addEventListener('click', function (){
        frmList.pageNum.value = 1;
        frmList.items.value = 'subject';
        frmList.text.value = '';
        frmList.submit();
    });
});