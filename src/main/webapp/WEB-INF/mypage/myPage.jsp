<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link href="/css/myPageCSS/myPage.css" rel="stylesheet">
  <script src="/js/myPageJS/myPage.js" defer></script>
  <title>마이 페이지</title>

</head>
<body>
<div class="wrap">
<jsp:include page="../../inc/header.jsp"/>
<div class="profile">
  <h3 class="display-5"> <%-- 프로필 --%>
    <a class="profile-link" href="/modify.member?action=modify&memberId=${loginInfo.memberId}">프로필 <input type="button" value="프로필 수정" class="button"></a>
  </h3>
  <form name="frmMypage" action="/modify.mypage?action=mypage" method="post">
    <div class="profileInfo">
      <label class="col-sm-2">아이디 ${loginInfo.memberId}</label>
      <label class="col-sm-2">이름 ${loginInfo.name}</label>
      <label class="col-sm-2">닉네임 ${loginInfo.nickName}</label>
      <label class="col-sm-2" id="zzimh"><p>찜 &nbsp;</p>
        <i class="fa-solid fa-heart fa-1x heart"></i> &nbsp;<p>${loginInfo.zzimCnt}</p>
      </label>
    </div>
  </form>
</div>
<div class="tab-item"> <!-- 마이페이지 탭 메뉴 부분 -->
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="mytab-link active" href="#tab1">내가 쓴 글</a>
    </li>
    <li class="nav-item">
      <a class="mytab-link" href="#tab2">내가 쓴 댓글</a>
    </li>
    <li class="nav-item">
      <a class="mytab-link" href="#tab3">내가 쓴 리뷰</a>
    </li>
    <li class="nav-item">
      <a class="mytab-link" href="#tab4">내가 찜한 영화</a>
    </li>
  </ul>
</div>
  <!-- "내가 쓴 글" -->
  <div class="tab-content" id="tab1">
    <div class="myContent">
      <div class="button1">
        <!-- 기능 편의 버튼 -->
        <input type="button" class="btn-checkAll1" value="전체 선택">
        <input type="button" class="btn-removeAll1"  value="전체 삭제">
        <input type="button" class="btn-selected1"  value="선택 삭제">
      </div>
        <form name="frmRemoveSelected1" method="post" action="./remove.board?action=remove&contentNo">
          <ul class="list-group-1">
            <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
              <li>
                <div class="flex_my">

                  <!--label 태그는 체크박스를 누르지 않아도 연결된 체크박스를 체크해줌-->
                  <label for="chk-board-${boardDTO.contentNo}">
                    <input type="checkbox" name="selectedItems1" class="button chkButton1" value="${boardDTO.contentNo}" id="chk-board-${boardDTO.contentNo}">
                    <i class="circle"></i>
                  </label>

                    <a href="get.board?action=get&contentNo=${boardDTO.contentNo}" class="text-decoration-none" type="hidden">
                      <p class="myList" > ${boardDTO.title}
                          ${boardDTO.addDate}
                          ${boardDTO.nickName}
                          ${boardDTO.hit}
                      </p>
                    </a>
                    <span href="./myRemove.board?action=myRemove&contentNo=${boardDTO.contentNo}"
                      class="remove-myContent" style="font-size: 30px; line-height: 2.85;"
                       onclick="return confirm('정말 삭제하시겠습니까?');">X</span>
                </div>
              </li>
            </c:forEach>
          </ul>
        </form>
    </div>
  </div>

<%--  <!-- "내가 쓴 댓글" -->--%>
  <div class="tab-content" id="tab2">
    <div class="myComment">
      <div class="button1">
        <!-- 기능 편의 버튼 -->
        <input type="button" class="btn-checkAll2"  value="전체 선택">
        <input type="button" class="btn-removeAll2"  value="전체 삭제">
        <input type="button" class="btn-selected2" value="선택 삭제">
      </div>
        <form name="frmRemoveSelected2" method="post" action="/comment/myRemove">
          <ul class="list-group-2">
            <c:forEach var="commentDTO" items="${commentDTOList}" varStatus="status">
              <li>
                <div class="flex_my">

                  <!--label태그는 체크박스를 누르지 않아도 연결된 체크박스를 체크해줌-->
                  <label for="chk-comment-${commentDTO.commentNo}">
                    <input type="checkbox" name="selectedItems2" class="button chkButton2" value="${commentDTO.commentNo}" id="chk-comment-${commentDTO.commentNo}">
                    <i class="circle"></i>
                  </label>

                    <a href="get.board?action=get&contentNo=${commentDTO.contentNo}" class="text-decoration-none" >
                       <p class="myList"> ${commentDTO.commentNo}
                           ${commentDTO.addDate}
                           ${commentDTO.nickName}

                       </p>
                    </a>
                    <span href="/comment/myRemoveOne?commentNo=${commentDTO.commentNo}&parentNo=${commentDTO.parentNo}"
                       class="remove-myComment" style="font-size: 30px; line-height: 2.85;"
                       onclick="return confirm('정말 삭제하시겠습니까?');">X</span>
                </div>
              </li>
            </c:forEach>
          </ul>
        </form>
    </div>
  </div>

  <!-- "내가 쓴 리뷰" -->
  <div class="tab-content" id="tab3">
    <div class="myReview">
      <div class="button1">
        <!-- 기능 편의 버튼 -->
        <input type="button" class="btn-checkAll3"  value="전체 선택">
        <input type="button" class="btn-removeAll3" value="전체 삭제">
        <input type="button" class="btn-selected3"  value="선택 삭제">
      </div>
        <form name="frmRemoveSelected3" method="post" action="/review/myRemove">
          <ul class="list-group-3">
            <c:forEach var="reviewDTO" items="${reviewDTOList}" varStatus="status">
              <li>
                <div class="flex_my">

                  <!--label태그는 체크박스를 누르지 않아도 연결된 체크박스를 체크해줌-->
                  <label for="chk-review-${reviewDTO.reviewNo}">
                    <input type="checkbox" name="selectedItems3" class="button chkButton3" value="${reviewDTO.reviewNo}" id="chk-review-${reviewDTO.reviewNo}">
                    <i class="circle"></i>
                  </label>
                    <a href="view.movie?action=view&movieNo=${reviewDTO.movieNo}" class="text-decoration-none" >
                      <p class="myList"> ${reviewDTO.review}
                          ${reviewDTO.addDate}
                          ${reviewDTO.nickName}
                      </p>
                    </a>
                    <span href="/review/myRemoveOne?reviewNo=${reviewDTO.reviewNo}"
                       class="remove-myReview" style="font-size: 30px; line-height: 2.85;"
                       onclick="return confirm('정말 삭제하시겠습니까?');">X</span>
                  </div>
              </li>
            </c:forEach>
          </ul>
        </form>
      </div>
  </div>

  <div class="tab-content" id="tab4">
    <!-- "내가 찜한 영화" -->
    <div class="Zzim">

      <form name="frmRemoveZZim" method="post" action="/myZZimRemove.movie?action=myZZimRemove&movieNo=">
        <c:forEach var="movieDTO" items="${zzimMovieList}" varStatus="status">
            <li class="list-group-zzim" style="position: relative" >
              <button class="myBtn" style="top: 10px; right: 5px; position: absolute; background: transparent; border: none; cursor: pointer;">
                  <i class="fa-solid fa-heart fa-2x heart" onclick="return confirm('정말 삭제하시겠습니까?');"></i>
                  <a href=/myZZimRemove.movie?action=myZZimRemove&movieNo==${movieDTO.movieNo}" class="myZZimDel"></a>
              </button>
              <a href="view.movie?action=view&movieNo=${movieDTO.movieNo}" class="text-decoration-4" >
                    <img src="${movieDTO.poster}" alt="영화 이미지">
              <p>${movieDTO.movieName}</p>
              </a>
            </li>
            <c:if test="${status.index % 4 == 3}">
            <div class="margin-img"></div>
            </c:if>
        </c:forEach>
      </form>
    </div>
  </div>
</div>
<jsp:include page="../../inc/footer.jsp"/>
</body>
</html>