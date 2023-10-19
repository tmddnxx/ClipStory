let photoCnt = 2;
let selectedItems = []; // 선택한 목록을 저장하는 배열

// 출연진 버튼 클릭 시 팝업 열기
document.getElementById("addCastButton").addEventListener("click", function() {
    const popupURL = "/admin?action=addCast";
    const popup = window.open(popupURL, "출연진 등록", "width=400, height=400, popup=yes");

    // 팝업 페이지로부터 데이터를 수신하는 이벤트 리스너
    window.addEventListener("message", function(event) {
        if (event.data && Array.isArray(event.data)) {
            selectedItems = event.data;
            displaySelectedItems();
        }
    });
});

// 이미지 업로드 미리보기
function displayPreviewImg(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            input.previousElementSibling.firstChild.src = e.target.result;
            input.previousElementSibling.firstChild.style.display = "flex";
            if(input.parentNode.parentNode.firstElementChild.id === "photo-title"){ // 포토 이미지 업로드 했을시
                input.nextElementSibling.innerHTML = '<button type="button" class="removePhotoBtn" id="removePhotoBtn' + photoCnt + '">X</button>'; // 삭제 버튼 추가

                const removePhotoBtn = document.getElementById("removePhotoBtn" + photoCnt);

                console.log(removePhotoBtn);
                // 포토 삭제 버튼 클릭시
                removePhotoBtn.addEventListener("click", function(e) {
                    e.target.parentElement.parentElement.remove(); // 해당 요소 제거

                    const inputPhotoList = document.querySelectorAll("input[class=photo]"); // 포토 네임 다시 정하기
                    for(let i = 1; i <= inputPhotoList.length; i++ ){
                        inputPhotoList[i-1].name = "photo" + i;
                    }
                });
            }
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        input.previousElementSibling.firstChild.src = "";
        input.previousElementSibling.firstChild.style.display = "none";
    }
}
// 포토 추가 버튼 클릭
document.getElementById("morePhotoBtn").addEventListener("click", function (){
    const morePhotoBtn = document.getElementById("morePhotoBtn");
    let photoDiv = document.createElement("div");
    photoDiv.className += "Movie-input-div";
    photoDiv.innerHTML =
        '<div><img width="100%" height="100%" style="display: none"></div>'
        + '<input type="file" placeholder="포토" class="photo" onchange="displayPreviewImg(this);">'
        + '<div></div>';
    // + '<button type="button" class="removePhotoBtn">X</button>';
    morePhotoBtn.parentNode.insertBefore(photoDiv,morePhotoBtn);

    //이름 카운팅 다시
    const inputPhotoList = document.querySelectorAll("input[class=photo]");
    for(let i = 1; i <= inputPhotoList.length; i++ ){
        inputPhotoList[i-1].name = "photo" + i;
    }
    photoCnt++;
})

// 선택한 목록을 표시
function displaySelectedItems() {
    const castListDiv = document.getElementById("castList");
    castListDiv.innerHTML = ""; // 이전 목록 초기화
    console.log(selectedItems);
    selectedItems.forEach(function(crew) {
        console.log(crew);
        let listItem = document.createElement("div");
        listItem.innerHTML = '<div><span class="crew-Name-span">' + crew.crewName + '</span></div>'
            + '<input type="hidden" name="crewNo" value="' + crew.crewNo + '">'
            + '<label class="cast-role-label">'
            + '<input type="radio" name="castRole_' + crew.crewNo + '" value="감독"> 감독'
            + '</label>'
            + '<label class="cast-role-label">'
            + '<input type="radio" name="castRole_' + crew.crewNo + '" value="출연" checked> 출연'
            + '</label>'
            + '<button type="button" class="removeCastBtn" data-crewNo="' + crew.crewNo + '">x</button>';

        console.log(listItem);
        castListDiv.appendChild(listItem);
    });

    // 캐스트 삭제 버튼 클릭 시 해당 항목 삭제
    const removeCastBtns = castListDiv.getElementsByClassName("removeCastBtn");
    for (let i = 0; i < removeCastBtns.length; i++) {
        removeCastBtns[i].addEventListener("click", function() {
            let crewNoToRemove = this.getAttribute("data-crewNo");
            // 선택한 목록에서 해당 항목을 제거
            selectedItems = selectedItems.filter(item => item.crewNo != crewNoToRemove);
            displaySelectedItems(); // 목록 다시 표시
        });
    }
}