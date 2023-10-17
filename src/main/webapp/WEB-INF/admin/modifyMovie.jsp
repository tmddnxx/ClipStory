<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/css/adminCSS/adminAddMovie.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">
    <%--    <script src="/js/memberJS/register.js" defer></script>--%>
    <title>영화 수정</title>
</head>
<body>
<jsp:include page="./inc/adminHeader.jsp"/>
<main>
    <section>
        <div id="Movie-title">
            <h3 class="head-title">영화 수정</h3>
        </div>
        <form name="frmMember" action="/admin?action=modifyMovieProcess" method="post" enctype="multipart/form-data">
            <%-- 이전 포스터 경로--%>
            <input type="hidden" name="prePoster" value="${movieDTO.poster}">
            <input type="hidden" name="movieNo" value="${movieDTO.movieNo}">
            <div id="Movie-container">
                <div id="Movie-input-container">
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input id="Movie-input-title" type="text" placeholder="제목" name="movieName" value="${movieDTO.movieName}">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input id="Movie-input-rel" type="date" placeholder="개봉일" name="releaseDate" value="${movieDTO.releaseDate}">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" placeholder="국가" name="region" value="${movieDTO.region}">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" placeholder="장르" name="genre" value="${movieDTO.genre}">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" placeholder="관객수" name="audience" value="${movieDTO.audience}">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" placeholder="러닝타임" name="runningtime" value="${movieDTO.runningtime}">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <textarea cols="50" rows="5" name="outline" placeholder="개요" class="form-control">${movieDTO.outline}</textarea>
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" class="etc-btn" id="poster-title" value="포스터 등록" readonly/>
                            <div><img id=poster-preview src="${movieDTO.poster}" width="100%" height="300px"></div>
                            <input type="file" id="poster-file" name="poster" onchange="displayPreviewImg(this);">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <button type="button" id="addCastButton" class="etc-btn">출연진 등록</button>
                            <div id="castList">
                                <c:forEach var="castDTO" items="${castList}" varStatus="status">
                                    <div>
                                        <div><span class="crew-Name-span">${castDTO.crewName}</span></div>
                                        <input type="hidden" name="crewNo" value="${castDTO.crewNo}"/>
                                        <label class="cast-role-label">
                                            <input type="radio" name="castRole_${castDTO.crewNo}" value="감독" ${castDTO.castRole.equals("감독") == true ? "checked" : ""}> 감독
                                        </label>
                                        <label class="cast-role-label">
                                            <input type="radio" name="castRole_${castDTO.crewNo}" value="출연" ${castDTO.castRole.equals("출연") == true ? "checked" : ""}> 출연
                                        </label>
                                        <button type="button" class="removeCastBtn" data-crewNo="${castDTO.crewNo}">x</button>
                                    </div>
                                </c:forEach>
                                <!-- Cast 버튼을 통해 선택한 목록이 여기에 추가됩니다. -->
                            </div>
                        </div>
                    </div>
                    <div class="Movie-input">
                        <input type="text" class="etc-btn" id="photo-title" value="포토 등록" readonly/>
                        <div class="Movie-input-div">
                            <input type="file" placeholder="포토" name="photo1">
                        </div>
                        <button type="button" class="etc-btn" id="morePhotoBtn">+ 포토 추가</button>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div" id="mo-radio-div">
                            <label>
                                <input type="radio" value="m" name="mo" ${movieDTO.mo.equals("m") == true ? "checked" : ""}>
                                <span>MOVIE</span>
                            </label>
                            <label>
                                <input type="radio" value="o" name="mo" ${movieDTO.mo.equals("o") == true ? "checked" : ""}>
                                <span>OTT</span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div id="Movie-buttons">
                <input type="submit" id="submit-btn" value="수정">
                <input type="button" onclick=window.location.href="/admin?action=main" value="취소">
            </div>
        </form>
    </section>
</main>

<script>
    let photoCnt = 2;
    let selectedItems = []; // 선택한 목록을 저장하는 배열
    const castListDiv = document.getElementById("castList"); // 출연자 리스트 div

    // default cast 삭제 버튼 등록
    const firstRemoveCastBtns = castListDiv.getElementsByClassName("removeCastBtn");
    for (let i = 0; i < firstRemoveCastBtns.length; i++) {
        firstRemoveCastBtns[i].addEventListener("click", function() {
            let crewNoToRemove = this.getAttribute("data-crewNo");
            // 선택한 목록에서 해당 항목을 제거
            this.parentNode.innerHTML = "";
        });
    }
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
                document.getElementById('poster-preview').src = e.target.result;
                document.getElementById('poster-preview').style.display = "flex";
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('preview').src = "";
            document.getElementById('poster-preview').style.display = "none";
        }
    }

    // 포토 추가 버튼 클릭
    document.getElementById("morePhotoBtn").addEventListener("click", function (){
        const morePhotoBtn = document.getElementById("morePhotoBtn");
        let photoDiv = document.createElement("div");
        photoDiv.innerHTML = '<div class="Movie-input-div">'
            + '<input type="file" placeholder="포토" name="photo' + photoCnt + '">'
            + '</div>';
        morePhotoBtn.parentNode.insertBefore(photoDiv,morePhotoBtn);

        photoCnt++;
    })

    // 선택한 목록을 표시
    function displaySelectedItems() {
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

        // 삭제 버튼 클릭 시 해당 항목 삭제
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
</script>
</body>
</html>