document.addEventListener('DOMContentLoaded', function (){
    const titlebox = document.querySelector('input[name=title]');
    const contentbox = document.querySelector('textarea[name=content]');
    const subbtn = document.querySelector('button[type=submit]');

    subbtn.addEventListener('click', function (e){
        if(titlebox.value.trim() ==""){
            alert("제목을 입력해주세요");
            titlebox.focus();
            e.preventDefault();
        }
        else if(contentbox.value.trim() == ""){
            alert("내용을 입력해주세요");
            contentbox.focus();
            e.preventDefault()
        }
    });
})