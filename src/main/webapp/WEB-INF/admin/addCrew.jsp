<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/css/memberCSS/register.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">
<%--    <script src="/js/memberJS/register.js" defer></script>--%>
    <title>배우/감독 등록</title>
</head>
<body>
<jsp:include page="./inc/adminHeader.jsp"/>
<main>
    <section>
        <div id="register-title">
            <h3 class="head-title">배우/감독 등록</h3>
        </div>
        <form name="frmMember" action="/admin?action=addCrewProcess" method="post" enctype="multipart/form-data">
            <div id="register-container">
                <div id="register-input-container">
                    <div class="register-input">
                        <div class="register-input-div">
                            <input id="register-input-id" type="text" placeholder="이름" name="crewName">
                        </div>
                    </div>
                    <div class="register-input">
                        <div class="register-input-div">
                            <input id="register-input-pw" type="file" placeholder="이미지" name="crewImg">
                        </div>
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
</body>
</html>