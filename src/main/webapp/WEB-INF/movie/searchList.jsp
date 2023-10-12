<%@ page import="java.util.List" %>
<%@ page import="com.example.movie.model.dto.SearchVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <script type="text/javascript" src="../../js/movieJS/searchList.js"></script>
    <link href="../../css/movieCSS/searchList.css" rel="stylesheet" type="text/css">
    <link href="./css/common.css" rel="stylesheet">
    <head>
    <title>Title</title>
</head>
<%
    List<SearchVO> searchVOList = (List) request.getAttribute("searchVOList");
    String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
    String text = request.getParameter("text") != null ? request.getParameter("text") : "";
%>
<body>
<jsp:include page="/inc/header.jsp"/>
<div class="wrap">
<div class="main">
    <h2><p>"<%=text%>"</p> 에 대한 검색결과입니다</h2>
    <h1>검색 목록</h1>
    <hr>
    <div class="chooseBox">
        <a href="#allListBox" class="allBtn">전체</a>
        <a href="#boxofficeBox" class="boxofficeBtn">박스오피스</a>
        <a href="#ottBox" class="ottBtn">OTT</a>
    </div>
    <hr>
    <div id="listWrap">
    <div id="allListBox" class="searchBox"> <%--전체목록--%>
        <p>전체</p>
        <ul id="allList" class="list-group">
            <c:forEach var="searchVO" items="${searchVOList}" varStatus="status">
                <li class="searchContent">
                    <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                        <img src="${searchVO.poster}">
                    </a>
                    <div class="searchContentFlex">
                        <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                            <div class="searchContentLink">
                                <p>순위 : [${searchVO.ranking}]</p>
                                <p>${searchVO.movieName}</p>
                            </div>
                        </a>
                        <div class="searchContentInfo">
                            <p>${searchVO.releaseDate}</p>
                            <p>${searchVO.genre}</p>
                            <p>${searchVO.mo}</p>
                        </div>
                    </div>
                </li>
                <hr>
            </c:forEach>
            <c:if test="${fn:length(searchVOList) >= 5}">
                <button class="moreView"> 더보기 + </button>
                <button class="noMoreView"> 접기 - </button>
            </c:if>
        </ul>
        <c:if test="${empty searchVOList}"> <%-- 검색결과가 없는경우 --%>
            <p>검색결과가 없습니다</p>
        </c:if>
    </div>
    <div id="boxofficeBox" class="searchBox"> <%--박스오피스목록--%>
        <p>박스오피스</p>
        <ul id="boxoffice" class="list-group">
            <c:forEach var="searchVO" items="${searchVOListM}" varStatus="status">
                <li class="searchContent">
                    <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                        <img src="${searchVO.poster}">
                    </a>
                    <div class="searchContentFlex">
                        <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                            <div class="searchContentLink">
                                <p>순위 : [${searchVO.ranking}]</p>
                                <p>${searchVO.movieName}</p>
                            </div>
                        </a>
                        <div class="searchContentInfo">
                            <p>${searchVO.releaseDate}</p>
                            <p>${searchVO.genre}</p>
                            <p>${searchVO.mo}</p>
                        </div>
                    </div>
                </li>
                <hr>
            </c:forEach>
            <c:if test="${fn:length(searchVOListM) >= 5}">
                <button class="moreView"> 더보기 + </button>
                <button class="noMoreView"> 접기 - </button>
            </c:if>
        </ul>
        <c:if test="${empty searchVOListM}"> <%-- 검색결과가 없는경우 --%>
            <p>검색결과가 없습니다</p>
        </c:if>
    </div>
    <div id="ottBox" class="searchBox"> <%--OTT목록--%>
        <p>OTT</p>
        <ul id="ott" class="list-group">
        <c:forEach var="searchVO" items="${searchVOListO}" varStatus="status">
            <li class="searchContent">
                <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                    <img src="${searchVO.poster}">
                </a>
                <div class="searchContentFlex">
                    <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                        <div class="searchContentLink">
                            <p>순위 : [${searchVO.ranking}]</p>
                            <p>${searchVO.movieName}</p>
                        </div>
                    </a>
                    <div class="searchContentInfo">
                        <p>${searchVO.releaseDate}</p>
                        <p>${searchVO.genre}</p>
                        <p>${searchVO.mo}</p>
                    </div>
                </div>
            </li>
            <hr>
        </c:forEach>
        <c:if test="${empty searchVOListO}"> <%-- 검색결과가 없는경우 --%>
            <p>검색결과가 없습니다</p>
        </c:if>
    </ul>
        <c:if test="${fn:length(searchVOListO) >= 5}">
            <button class="moreView"> 더보기 + </button>
            <button class="noMoreView"> 접기 - </button>
        </c:if>
    </div>
    </div>
    <jsp:include page="../../inc/footer.jsp"/>
</div>
</div>
</body>
</html>
