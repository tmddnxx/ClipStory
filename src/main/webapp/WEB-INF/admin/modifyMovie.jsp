<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/css/adminCSS/adminAddMovie.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <script src="/js/adminJS/adminModifyMovie.js" defer></script>
    <title>영화 수정</title>
</head>
<body>
<jsp:include page="./inc/adminHeader.jsp"/>
<main>
    <section>
        <div id="Movie-title">
            <h3 class="head-title">영화 수정</h3>
        </div>
        <form name="frmModifyMovie" action="/admin?action=modifyMovieProcess" method="post" enctype="multipart/form-data">
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
                            <div><img id="poster-preview" src="${movieDTO.poster}" width="100%" height="300px"></div>
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
                    <div class="Movie-input" id="photo-input-div">
                        <input type="text" class="etc-btn" id="photo-title" value="포토 등록" readonly/>
                        <c:forEach var="photoDTO" items="${photoList}">
                            <div class="movie-input-div">
                                <input type="hidden" name="defaultPhoto" value="${photoDTO.photoImg}">
                                <div><img width="100%" height="100%" src="${photoDTO.photoImg}"></div>
                                <input type="text" value="기존 이미지" class="defaultPhoto" readonly>
                                <div><button type="button" class="removeDefaultPhotoBtn">X</button></div>
                            </div>
                        </c:forEach>
                        <div class="Movie-input-div">
                            <div><img width="100%" height="100%" style="display: none"></div>
                            <input type="file" placeholder="포토" class="photo" onchange="displayPreviewImg(this);">
                            <div></div>
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
</body>
</html>