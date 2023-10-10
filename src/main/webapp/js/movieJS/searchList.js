document.addEventListener('DOMContentLoaded', function (){
    const allbtn = document.querySelector('.allBtn');
    const boxofficebtn = document.querySelector('.boxofficeBtn');
    const ottbtn = document.querySelector('.ottBtn');
    const allListBox = document.querySelector('.allListBox');
    const boxofficeBox = document.querySelector('.boxofficeBox');
    const ottBox = document.querySelector('.ottBox');

    allbtn.addEventListener('click', function (){
        allListBox.style.display = 'block';
        boxofficeBox.style.display = 'none';
        ottBox.style.display = 'none';
    });
    boxofficebtn.addEventListener('click', function (){
        boxofficeBox.style.display = 'block';
        allListBox.style.display = 'none';
        ottBox.style.display = 'none';
    });
    ottbtn.addEventListener('click', function (){
        ottBox.style.display = 'block';
        boxofficeBox.style.display = 'none';
        allListBox.style.display = 'none';
    });
});