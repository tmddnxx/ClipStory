document.addEventListener('DOMContentLoaded', function (){
    const allbtn = document.querySelector('.allBtn');
    const boxofficebtn = document.querySelector('.boxofficeBtn');
    const ottbtn = document.querySelector('.ottBtn');
    const allListBox = document.getElementById('allListBox');
    const boxofficeBox = document.getElementById('boxofficeBox');
    const ottBox = document.getElementById('ottBox');

    // 더보기(전체)
    const morebtnA = document.getElementById('moreViewA');
    const nomorebtnA = document.getElementById('noMoreViewA');
    const allListContent = document.getElementById('allList');

    // 더보기(박스오피스)
    const morebtnM = document.getElementById('moreViewM');
    const nomorebtnM = document.getElementById('noMoreViewM');
    const boxListContent = document.getElementById('boxoffice');

    // 더보기(OTT)
    const morebtnO = document.getElementById('moreViewO');
    const nomorebtnO = document.getElementById('noMoreViewO');
    const ottListContent = document.getElementById('ott');


    allbtn.addEventListener('click', function (){ // '전체'버튼 클릭시
        allListBox.style.paddingTop = '100px'
        allListBox.style.display = 'block';
        boxofficeBox.style.display = 'none';
        ottBox.style.display = 'none';
    });
    boxofficebtn.addEventListener('click', function (){ // '박스오피스'버튼 클릭시
        boxofficeBox.style.paddingTop = '100px'
        boxofficeBox.style.display = 'block';
        allListBox.style.display = 'none';
        ottBox.style.display = 'none';
    });
    ottbtn.addEventListener('click', function (){ // 'OTT' 버튼 클릭시
        ottBox.style.paddingTop = '100px'
        ottBox.style.display = 'block';
        boxofficeBox.style.display = 'none';
        allListBox.style.display = 'none';
    });

    // 전체 더보기 +
    morebtnA.addEventListener('click', function (){
        const divHeight = allListBox.clientHeight; // div 높이
        const ulHeight = allListContent.clientHeight; // ul 높이
        const newHeight = divHeight + 480;
        nomorebtnA.style.display = 'block';

        if(newHeight > ulHeight){
            console.log(ulHeight + 'px');
            allListBox.style.height = ulHeight + 60 + 'px';
            morebtnA.style.display = 'none'
        }
        else{
            allListBox.style.height = newHeight + 'px';
        }


    })
    // 전체 접기 -
    nomorebtnA.addEventListener('click', function (){
        allListBox.style.height = '550px';
        nomorebtnA.style.display = 'none';
        morebtnA.style.display = 'block';
    })

    // 박스오피스 더보기 +
    morebtnM.addEventListener('click', function (){
        const divHeight = boxofficeBox.clientHeight; // div 높이
        const ulHeight = boxListContent.clientHeight; // ul 높이
        const newHeight = divHeight + 480;
        nomorebtnM.style.display = 'block';

        if(newHeight > ulHeight){
            console.log(ulHeight + 'px');
            boxofficeBox.style.height = ulHeight + 60 + 'px';
            morebtnM.style.display = 'none'
        }
        else{
            boxofficeBox.style.height = newHeight + 'px';
        }


    })
    // 박스오피스 접기 -
    nomorebtnM.addEventListener('click', function (){
        boxofficeBox.style.height = '550px';
        nomorebtnM.style.display = 'none';
        morebtnM.style.display = 'block';
    })

    // OTT 더보기 +
    morebtnO.addEventListener('click', function (){
        const divHeight = ottBox.clientHeight; // div 높이
        const ulHeight = ottListContent.clientHeight; // ul 높이
        const newHeight = divHeight + 480;
        nomorebtnO.style.display = 'block';

        if(newHeight > ulHeight){
            console.log(ulHeight + 'px');
            ottBox.style.height = ulHeight + 60 + 'px';
            morebtnO.style.display = 'none'
        }
        else{
            ottBox.style.height = newHeight + 'px';
        }


    })
    // OTT 접기 -
    nomorebtnO.addEventListener('click', function (){
        ottBox.style.height = '550px';
        nomorebtnO.style.display = 'none';
        morebtnO.style.display = 'block';
    })


});