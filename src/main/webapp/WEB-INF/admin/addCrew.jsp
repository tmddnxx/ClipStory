<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/css/adminCSS/adminAddCrew.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
<%--    <script src="/js/memberJS/register.js" defer></script>--%>
    <title>배우/감독 등록</title>
</head>
<body>
<jsp:include page="./inc/adminHeader.jsp"/>
<main>
    <section>
        <div id="addCrew-title">
            <h3 class="head-title">배우/감독 등록</h3>
        </div>
        <form name="frmAddCrew" action="/admin?action=addCrewProcess" method="post" enctype="multipart/form-data">
            <div id="addCrew-container">
                <div id="addCrew-input-container">
                    <div class="addCrew-input">
                        <div class="addCrew-input-div">
                            <input id="addCrew-input-name" type="text" placeholder="이름" name="crewName">
                        </div>
                    </div>
                    <div class="addCrew-input">
                        <div class="addCrew-input-div">
                            <input id="addCrew-input-img" type="file" placeholder="이미지" name="crewImg" onchange="displayPreviewImg(this)">
                            <div><img width="100%" height="400px" style="display: none"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="addCrew-buttons">
                <input type="button" id="submit-btn" value="등록">
                <input type="button" onclick=" if(confirm('입력하신 내용이 저장되지 않았습니다. 취소하시겠습니까?')){location.href='/admin?action=main'};" value="취소">
            </div>
        </form>
    </section>
</main>
<script>
    const frm = document.querySelector('form[name=frmAddCrew]');
    const crewName = document.getElementById("addCrew-input-name");
    const crewImg = document.getElementById("addCrew-input-img");
    const submitBtn = document.getElementById('submit-btn');

    submitBtn.addEventListener('click', function (){
        if(crewName.value.trim() === ""){
            alert('이름을 입력해주세요');
            crewName.focus();
            return;
        }
        else if(crewImg.value.trim() === ""){
            alert('파일을 등록해주세요');
            crewImg.focus();
            return;
        }
        else{
            frm.submit();
        }
    });

    function displayPreviewImg(input) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function(e) {
                input.nextElementSibling.firstChild.src = e.target.result;
                input.nextElementSibling.firstChild.style.display = "flex";
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            input.nextElementSibling.firstChild.src = "";
            input.nextElementSibling.firstChild.style.display = "none";
        }
    }
</script>
</body>
</html>