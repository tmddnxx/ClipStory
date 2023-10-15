<%@ page import="java.util.List" %>
<%@ page import="com.example.movie.model.dao.BoardDAO" %>
<%@ page import="com.example.movie.model.dao.CommentDAO" %>
<%@ page import="com.example.movie.model.dto.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="currentTime" class="java.util.Date" />
<%
  List boardDTOList = (List) request.getAttribute("boardDTOList");
  int adminTotalRecord = (Integer) request.getAttribute("adminTotalRecord");
  int adminPageNum = (Integer) request.getAttribute("adminPageNum");
  int adminTotalPage = (Integer) request.getAttribute("adminTotalPage");
  int limit = (Integer) request.getAttribute("limit");

  int startNum = (Integer) request.getAttribute("startNum"); // 페이지 시작 일련번호

  String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
  String text = request.getParameter("text") != null ? request.getParameter("text") : "";

  int pagePerBlock = 5; // 페이지 범위
  int totalBlock = adminTotalPage % pagePerBlock == 0 ? adminTotalPage / pagePerBlock : adminTotalPage / pagePerBlock + 1; // block의 전체 갯수(페이지 범위 단위의 총 갯수)
  int thisBlock = ((adminPageNum -1) / pagePerBlock) + 1; // 현재 블럭
  int firstPage = ((thisBlock -1) * pagePerBlock) + 1; // 블럭의 첫 페이지
  int lastPage = thisBlock * pagePerBlock; // 블럭의 마지막 페이지
  lastPage = (lastPage > adminTotalPage) ? adminTotalPage : lastPage;

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="/css/boardCSS/boardList.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
  <script src="/js/boardJS/boardList.js" defer></script>
  <title>관리자 게시판 목록</title>
</head>
<body>
<div class="wrap">
  <jsp:include page="../../inc/header.jsp"/>
  <div class="main">
    <h2>게시판 목록</h2>
    <ul class="list-group">
      <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
        <%-- 현재시간 , 작성시간 구하기 --%>
        <fmt:parseNumber value="${currentTime.time / (1000*60*60)}" integerOnly="true" var="currentFmtTime" scope="request"/>
        <fmt:parseNumber value="${boardDTO.addDate.time / (1000*60*60)}" integerOnly="true" var="addFmtTime" scope="request"/>
        <a href="/admin?action=boardGet&contentNo=${boardDTO.contentNo}" class="list-a">
          <li class="list">
            <input type="checkbox" class="content-checkbox">
            <div class="first">
              <div class="NH">
                <c:if test="${(currentFmtTime - addFmtTime) < 24}"> <%-- 현재시간-작성시간 24시간 미만이면 N표시 뜨게함 --%>
                  <span>N</span>
                  <c:if test="${boardDTO.hit > 10}"> <%-- 24시간 미만이고 조회수가 10이상이면 H표시 --%>
                    <span>H</span>
                  </c:if>
                </c:if>
              </div>
              &nbsp;[<%=(adminTotalRecord--)-(adminPageNum-1)*limit%>] ${boardDTO.title} (${boardDTO.cnt})
            </div>
            <p>${boardDTO.addDate}</p>
            <p>${boardDTO.nickName}</p>
            <p>${boardDTO.hit}</p>
          </li>
        </a>
        <%
          startNum--;
        %>
      </c:forEach>
    </ul>
    <hr>
    <%--페이징--%>
    <div align="center" class="paging">
      <a href="<c:url value="/admin?action=boardList&adminPageNum=1"/>"><span>첫페이지</span></a>
      <%
        if(thisBlock > 1) {
      %>
      <a href="/admin?action=boardList&adminPageNum=<%=(firstPage - 1)%>"><span> < 이전 </span></a>
      <%
        }
      %>
      <c:set var="adminPageNum" value="<%=adminPageNum%>" />
      <c:forEach var="i" begin="<%=firstPage%>" end="<%=lastPage%>">
        <a href="/admin?action=boardList&adminPageNum=${i}" class="pager">
          <c:choose>
            <c:when test="${adminPageNum==i}"> <!--현재 페이지이면 볼드처리 -->
              <span>[${i}]</span>
            </c:when>
            <c:otherwise>
              <span>[${i}]</span>
            </c:otherwise>
          </c:choose>
        </a>
      </c:forEach>
      <%
        if(thisBlock < totalBlock) {
      %>
      <a href="/admin?action=boardList&adminPageNum=<%=(lastPage + 1)%>"><span> 다음 ></span> </a>
      <%
        }
      %>
      <a href="<c:url value="/admin?action=boardList&adminPageNum=${adminTotalPage}"/>"><span>끝페이지</span></a>
    </div>
    <c:if test="${error != null}">
      <div class="alert alert-danger alert-dismissible fade show mt-3">
        에러 발생 : ${error}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </c:if>
    <%--페이징 끝--%>

    <%--검색창--%>
    <div class="form-box">
      <form name="frmList" action="/admin?action=boardList" method="post">
        <input type="hidden" name="pageNum" value="<%=adminPageNum%>">
        <input type="hidden" name="num">
        <table>
          <tr>
            <td>
              <select name="items" class="txt">
                <option value="title" <% if(items.equals("title")){%>selected<%}%>>제목</option>
                <option value="content" <% if(items.equals("content")){%>selected<%}%>>내용</option>
                <option value="nickName" <% if(items.equals("nickName")){%>selected<%}%>>닉네임</option>
              </select>
              <input name="text" type="text" value="<%=text%>" placeholder="검색어를 입력해주세요"/>
              <input type="button" id="btn-search" class="btn btn-primary" value="검색"/>
              <input type="button" id="btn-reset" class="btn btn-secondary" value="취소"/>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<jsp:include page="../../inc/footer.jsp"/>
</body>
</html>