document.addEventListener("DOMContentLoaded", function () {
    //페이징 영역의 a태그를 클릭할 때 a 태그의 기능을 막고, 폼을 적용
    const btnPaging = document.querySelectorAll('.paging a');
    const frmList = document.querySelector('form[name=frmList]');
    btnPaging.forEach(btn => {
        btn.addEventListener('click', function (e){
            e.preventDefault(); // a링크 막음
            frmList.pageNum.value = e.target.parentNode.href.split('?')[1].split('&')[1].split('=')[1];
            frmList.action = '/admin?action=memberList';
            console.log(e.target);
            frmList.submit();
        }, false);
    });
    // 검색 클릭시 검색 조건은 그대로, 1페이지로 이동
    const  btnSearch = document.querySelector('#btn-search');
    btnSearch.addEventListener('click', function (){
        frmList.pageNum.value=1;
        frmList.submit();
    });

    const btnReset = document.querySelector('#btn-reset');
    btnReset.addEventListener('click', function (){
        frmList.pageNum.value = 1;
        frmList.items.value = 'memberId';
        frmList.text.value = '';
        frmList.submit();
    });
});