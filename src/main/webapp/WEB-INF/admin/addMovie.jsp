<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/css/adminCSS/adminAddMovie.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <script src="/js/adminJS/adminAddMovie.js" defer></script>
    <title>영화 등록</title>
</head>
<body>
<jsp:include page="./inc/adminHeader.jsp"/>
<main>
    <section>
        <div id="Movie-title">
            <h3 class="head-title">영화 등록</h3>
        </div>
        <form name="frmAddMovie" action="/admin?action=addMovieProcess" method="post" enctype="multipart/form-data">
            <div id="Movie-container">
                <div id="Movie-input-container">
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input id="Movie-input-title" type="text" placeholder="제목" name="movieName">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input id="Movie-input-rel" type="date" placeholder="개봉일" name="releaseDate">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" id="region" placeholder="국가" name="region">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" placeholder="장르" id="genre" name="genre">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="number" placeholder="관객수" id="audience" name="audience">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="number" placeholder="러닝타임(분)" id="runningtime" name="runningtime">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <textarea cols="50" rows="5" id="outline" name="outline" placeholder="개요" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <input type="text" class="etc-btn" id="poster-title" value="포스터 등록" readonly/>
                            <div><img id=poster-preview width="100%" height="300px" style="display: none"></div>
                            <input type="file" id="poster-file" name="poster" onchange="displayPreviewImg(this);">
                        </div>
                    </div>
                    <div class="Movie-input">
                        <div class="Movie-input-div">
                            <button type="button" id="addCastButton" class="etc-btn">출연진 등록</button>
                            <div id="castList">
                                <!-- Cast 버튼을 통해 선택한 목록이 여기에 추가됩니다. -->
                            </div>
                        </div>
                    </div>
                    <div class="Movie-input" id="photo-input-div">
                        <input type="text" class="etc-btn" id="photo-title" value="포토 등록" readonly/>
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
                                <input type="radio" value="m" name="mo" checked>
                                <span>MOVIE</span>
                            </label>
                            <label>
                                <input type="radio" value="o" name="mo">
                                <span>OTT</span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div id="Movie-buttons">
                <input type="button" id="submit-btn" value="등록">
                <input type="button" onclick=" if(confirm('입력하신 내용이 저장되지 않았습니다. 취소하시겠습니까?')){location.href='/admin?action=main'};" value="취소">
            </div>
        </form>
    </section>
</main>
</body>
</html>