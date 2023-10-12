document.addEventListener('DOMContentLoaded', function (){
    const allbtn = document.querySelector('.allBtn');
    const boxofficebtn = document.querySelector('.boxofficeBtn');
    const ottbtn = document.querySelector('.ottBtn');
    const allListBox = document.getElementById('allListBox');
    const boxofficeBox = document.getElementById('boxofficeBox');
    const ottBox = document.getElementById('ottBox');
    const morebtn = document.querySelector('.moreView');
    const nomorebtn = document.querySelector('.noMoreView');
    const content = document.querySelector('.searchContent');

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

    morebtn.addEventListener('click', function (){
        const currentHeight = allListBox.clientHeight;
        const newHeight = currentHeight + 550;
        allListBox.style.height = newHeight+'px';
        if(content.clientHeight <= newHeight){
            morebtn.style.display = 'none';
        }
        nomorebtn.style.display = 'block';
    })

    nomorebtn.addEventListener('click', function (){
        allListBox.style.height = '550px';
        nomorebtn.style.display = 'none';
        morebtn.style.display = 'block';
    })
});