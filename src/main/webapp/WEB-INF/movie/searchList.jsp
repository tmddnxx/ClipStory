<%@ page import="java.util.List" %>
<%@ page import="com.example.movie.model.dto.SearchVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/movieJS/searchList.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="../../css/movieCSS/searchList.css" rel="stylesheet" type="text/css">
    <head>
    <title>Title</title>
</head>
<%
    List<SearchVO> searchVOList = (List) request.getAttribute("searchVOList");
    String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
    String text = request.getParameter("text") != null ? request.getParameter("text") : "";
%>
<body>
<jsp:include page="inc/movieMainHeader.jsp"/>
<a href="javascript:history.back()" class="btn btn-primary"><< back</a>
<div class="container w-75 mt-5 mx-auto">
    <h2>검색 목록</h2>
    <h3>"<%=text%>" 에 대한 검색결과입니다</h3>
    <hr>
    <a href="#allList" class="allBtn">전체 | </a>
    <a href="#boxoffice" class="boxofficeBtn">박스오피스 | </a>
    <a href="#ott" class="ottBtn">OTT</a>

    <div class="allListBox">
    <p style="font-weight: bold">전체</p>
    <ul id="allList" class="list-group">
        <c:forEach var="searchVO" items="${searchVOList}" varStatus="status">
            <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                       [${searchVO.ranking}] [${searchVO.movieName}], <img src="${searchVO.poster}" width="100px" height="100px">
                    </a>
                <p>${searchVO.releaseDate}</p>
                <p style="text-align: right">${searchVO.genre}</p>
                <p style="text-align: right">${searchVO.mo}</p>
            </li>
        </c:forEach>
        <c:if test="${empty searchVOList}"> <%-- 검색결과가 없는경우 --%>
            <p>검색결과가 없습니다</p>
        </c:if>
    </ul>
    </div>
    <hr>
    <div class="boxofficeBox">
    <p style="font-weight: bold">박스오피스</p>
    <ul id="boxoffice" class="list-group">
        <c:forEach var="searchVO" items="${searchVOListM}" varStatus="status">
                <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                        [${searchVO.ranking}] [${searchVO.movieName}],<img src="${searchVO.poster}" width="100px" height="100px">
                    </a>
                    <p>${searchVO.releaseDate}</p>
                    <p style="text-align: right">${searchVO.genre}</p>
                    <p style="text-align: right">${searchVO.mo}</p>
                </li>
        </c:forEach>
        <c:if test="${empty searchVOListM}"> <%-- 검색결과가 없는경우 --%>
            <p>검색결과가 없습니다</p>
        </c:if>
    </ul>
    </div>
    <hr>
    <div class="ottBox">
    <p style="font-weight: bold">OTT</p>
    <ul id="ott" class="list-group">
        <c:forEach var="searchVO" items="${searchVOListO}" varStatus="status">
            <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                <a href="view.movie?action=view&movieNo=${searchVO.movieNo}" class="text-decoration-none">
                    [${searchVO.ranking}] [${searchVO.movieName}],<img src="${searchVO.poster}" width="100px" height="100px">
                </a>
                <p>${searchVO.releaseDate}</p>
                <p style="text-align: right">${searchVO.genre}</p>
                <p style="text-align: right">${searchVO.mo}</p>
            </li>
        </c:forEach>
        <c:if test="${empty searchVOListO}"> <%-- 검색결과가 없는경우 --%>
            <p>검색결과가 없습니다</p>
        </c:if>
    </ul>
    </div>
</div>
<jsp:include page="inc/movieFooter.jsp"/>
</body>
</html>
