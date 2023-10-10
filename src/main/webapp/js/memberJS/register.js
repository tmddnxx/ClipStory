const xhr = new XMLHttpRequest();
const confirmCheck = {id: false, pw: false, pw_re: false, name: false, nick: false};
const form = document.forms.namedItem('frmMember');
const confirmBtn= document.getElementById('submit-btn');
const inputTags = document.getElementsByTagName('input');

const memberId = document.getElementById("register-input-id");
const passwd = document.getElementById("register-input-pw");
const passwdRe = document.getElementById("register-input-pw-re");
const name = document.getElementById("register-input-name");
const nickName = document.getElementById("register-input-nick");


const checkID = /^[a-zA-Z0-9]+$/;
const checkNick = /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]+$/;
const checkSpace = /\s/g;
const checkPW =  /^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{8,15}$/;

confirmBtn.addEventListener('click', () => {
    if(!confirm_check()){
        alert("모든 항목을 정확히 입력해주세요");
        return;
    }
    form.submit();
});

function confirm_check(){
    console.log(confirmCheck);
    for(const key in confirmCheck) {
        if (!confirmCheck[key]) {
            return false;
        }
    }
    return true;
}

function check_ID(memberId) {
    const warnID = document.getElementById("id-warn-span");
    const idCheckIcon = document.getElementById("register-id-check");

    confirmCheck.id = false;
    idCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #ffffff;\"></i>"
    const idCheck = "memberId=" + memberId;

    if(memberId === ""){
        warnID.innerHTML = "필수 정보입니다";
    }
    else if(checkSpace.test(memberId)){
        warnID.innerHTML = "공백을 제거해주세요";
    }
    else if(!checkID.test(memberId)){
        warnID.innerHTML = "영어, 숫자만 입력 가능합니다";
    }
    else if(memberId.length < 4 || memberId.length > 12){
        warnID.innerHTML = "4 ~ 12자로 입력해주세요";
    }
    else{
        xhr.open('POST', 'idCheck.member?action=idCheck&' + idCheck);
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE)
                return;

            if (xhr.status === 200) {
                console.log(xhr.response);
                const json = JSON.parse(xhr.response);
                if (json.result === 'true') {
                    warnID.innerHTML = "이미 있는 아이디 입니다";
                }
                else {
                    warnID.innerHTML = "";
                    confirmCheck.id = true;
                    idCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #000000;\"></i>"
                }
            } else {
                console.error(xhr.status, xhr.statusText);
            }
        }
    }

}

function check_password(passwd) {
    const warnPW = document.getElementById("pw-warn-span");
    const pwCheckIcon = document.getElementById("register-pw-check");
    pwCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #ffffff;\"></i>"

    confirmCheck.pw = false;


    if(passwd === ""){
        warnPW.innerHTML = "필수 정보입니다";
    }
    else if(!checkPW.test(passwd) || checkSpace.test(passwd)){
        warnPW.innerHTML = "띄어쓰기 없는 8~15자의 영문 대/소문자, 숫자 또는 특수문자 조합으로 입력하셔야 합니다.";
    }
    else{
        warnPW.innerHTML ="";
        confirmCheck.pw = true;
        pwCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #000000;\"></i>"
    }
}

function check_re_password(passwdRe) {
    const warnPW_RE = document.getElementById("pw-re-warn-span");
    const pwReCheckIcon = document.getElementById("register-pwRe-check");

    pwReCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #ffffff;\"></i>"
    confirmCheck.pw_re = false;

    if(passwdRe === ""){
        warnPW_RE.innerHTML = "필수 정보입니다";
    }
    else if(passwd.value !== passwdRe){
        warnPW_RE.innerHTML ="비밀번호가 다릅니다";
    }
    else{
        warnPW_RE.innerHTML ="";
        confirmCheck.pw_re = true;
        pwReCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #000000;\"></i>"

    }
}
function check_name(name) {
    // 이름 검사

    const warnName = document.getElementById("name-warn-span");
    const nameCheckIcon = document.getElementById("register-name-check");
    const nameCheck = "name=" + name;

    confirmCheck.name = false;
    nameCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #ffffff;\"></i>"

    if(name === ""){
        warnName.innerHTML = "필수 정보입니다";
    }
    else if(checkSpace.test(name)){
        warnName.innerHTML = "공백을 제거해주세요";
    }
    else if(!checkNick.test(name)){
        warnName.innerHTML = "특수문자는 사용이 불가능 합니다";
    }
    else if(name.length < 2 || name.length > 10){
        warnName.innerHTML = "2 ~ 10자로 입력해주세요"
    }
    else{
        xhr.open('POST', 'nameCheck.member?action=nameCheck&' + nameCheck);
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE)
                return;

            if (xhr.status === 200) {
                console.log(xhr.response);
                const json = JSON.parse(xhr.response);
                if (json.result === 'true') {
                    warnName.innerHTML = "이미 있는 이름입니다";
                }
                else {
                    warnName.innerHTML = "";
                    confirmCheck.name = true;
                    nameCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #000000;\"></i>"

                }
            } else {
                console.error(xhr.status, xhr.statusText);
            }
        }
    }
}

function check_nick(nickName) {
    // 닉네임 검사

    const warnNick = document.getElementById("nick-warn-span");
    const nickCheckIcon = document.getElementById("register-nick-check");
    const nickCheck = "nickName=" + nickName;

    confirmCheck.nick = false;
    nickCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #ffffff;\"></i>"

    if(nickName === ""){
        warnNick.innerHTML = "필수 정보입니다";
    }
    else if(checkSpace.test(nickName)){
        warnNick.innerHTML = "공백을 제거해주세요";
    }
    else if(!checkNick.test(nickName)){
        warnNick.innerHTML = "특수문자는 사용이 불가능 합니다";
    }
    else if(nickName.length < 2 || nickName.length > 10){
        warnNick.innerHTML = "2 ~ 10자로 입력해주세요"
    }
    else{
        xhr.open('POST', 'nickCheck.member?action=nickCheck&' + nickCheck);
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE)
                return;

            if (xhr.status === 200) {
                console.log(xhr.response);
                const json = JSON.parse(xhr.response);
                if (json.result === 'true') {
                    warnNick.innerHTML = "이미 있는 닉네임 입니다";
                }
                else {
                    warnNick.innerHTML = "";
                    confirmCheck.nick = true;
                    nickCheckIcon.innerHTML = "<i class=\"fa-solid fa-check fa-lg\" style=\"color: #000000;\"></i>"

                }
            } else {
                console.error(xhr.status, xhr.statusText);
            }
        }
    }
}


function event_handler(e){
    const targetValue = e.target.value;
    switch (e.target){
        case memberId:
            check_ID(targetValue);
            break;
        case passwd:
            check_password(targetValue);
            break;
        case passwdRe:
            check_re_password(targetValue);
            break;
        case name:
            check_name(targetValue);
            break;
        case nickName:
            check_nick(targetValue);
            break;
    }
}
[...inputTags].forEach( inputTag => {
    inputTag.addEventListener('change', e => {
            event_handler(e);
        }
    );
});