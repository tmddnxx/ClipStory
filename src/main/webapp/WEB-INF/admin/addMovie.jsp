<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/css/memberCSS/register.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">
    <%--    <script src="/js/memberJS/register.js" defer></script>--%>
    <title>회원 가입</title>
</head>
<body>
<jsp:include page="./inc/adminHeader.jsp"/>
<main>
    <section>
        <div id="addMovie-title">
            <h3 class="head-title">영화 등록</h3>
        </div>
        <form name="frmMember" action="/admin?action=addMovieProcess" method="post" enctype="multipart/form-data">
            <div id="register-container">
                <div id="register-input-container">
                    <div class="register-input">
                        <div class="register-input-div">
                            <input id="register-input-id" type="text" placeholder="제목" name="movieName">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input id="register-input-pw" type="date" placeholder="개봉일" name="releaseDate">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input  type="text" placeholder="국가" name="region">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input  type="text" placeholder="장르" name="genre">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input  type="text" placeholder="러닝타임" name="runningtime">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <textarea cols="50" rows="5" name="outline" placeholder="개요" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input  type="file" placeholder="포스터" name="poster">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <label>
                                <input  type="radio" value="m" name="mo">
                                MOVIE
                            </label>
                            <label>
                                <input  type="radio" value="m" name="mo">
                                OTT
                            </label>
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <button type="button" id="addCastButton">출연진 등록</button>
                            <div id="selectedItems">
                                <!-- Cast 버튼을 통해 선택한 목록이 여기에 추가됩니다. -->
                            </div>
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input type="file" placeholder="포토" name="photo1">
                        </div>
                        <button type="button" id="morePhotoBtn">포토 추가</button>
                    </div>
                </div>
            </div>

            <div id="register-buttons">
                <input type="submit" id="submit-btn" value="등록">
                <input type="button" onclick=window.location.href="/admin?action=main" value="취소">
            </div>

        </form>
    </section>
</main>

<script>
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

    // 포토 추가 버튼 클릭
    document.getElementById("morePhotoBtn").addEventListener("click", function (){
        const morePhotoBtn = document.getElementById("morePhotoBtn");
        let photoDiv = document.createElement("div");
        photoDiv.innerHTML = '<div class="register-input-div">'
            + '<input type="file" placeholder="포토" name="photo' + photoCnt + '">'
            + '</div>';
        morePhotoBtn.parentNode.insertBefore(photoDiv, morePhotoBtn);

        photoCnt++;
    })

    // 선택한 목록을 표시
    function displaySelectedItems() {
        const selectedItemsDiv = document.getElementById("selectedItems");
        selectedItemsDiv.innerHTML = ""; // 이전 목록 초기화
        console.log(selectedItems);
        selectedItems.forEach(function(crew) {
            console.log(crew);
            let listItem = document.createElement("div");
            listItem.innerHTML = '<span>' + crew.crewName + '</span>'
                + '<input type="hidden" name="crewNo" value="' + crew.crewNo + '">'
                + '<label>'
                + '<input type="radio" name="castRole_' + crew.crewNo + '" value="감독"> 감독'
                + '</label>'
                + '<label>'
                + '<input type="radio" name="castRole_' + crew.crewNo + '" value="출연"> 출연'
                + '</label>'
                + '<button class="removeItem" data-crewNo="' + crew.crewNo + '">x</button>';

                console.log(listItem);
            selectedItemsDiv.appendChild(listItem);
        });

        // 삭제 버튼 클릭 시 해당 항목 삭제
        const removeButtons = selectedItemsDiv.getElementsByClassName("removeItem");
        for (let i = 0; i < removeButtons.length; i++) {
            removeButtons[i].addEventListener("click", function() {
                let crewNoToRemove = this.getAttribute("data-crewNo");
                // 선택한 목록에서 해당 항목을 제거
                selectedItems = selectedItems.filter(item => item.crewNo != crewNoToRemove);
                displaySelectedItems(); // 목록 다시 표시
            });
        }
    }
</script>
</body>
</html>