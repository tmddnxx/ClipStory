<%@ page import="java.util.List" %>
<%@ page import="com.example.movie.model.dto.AdminBoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  AdminBoardDTO adminBoardDTO = (AdminBoardDTO) request.getAttribute("adminBoardDTO");
  List adminBoardDTOList = (List) request.getAttribute("adminBoardDTOList");
  int totalRecord = (Integer) request.getAttribute("totalRecord");
  int pageNum = (Integer) request.getAttribute("pageNum");
  int totalPage = (Integer) request.getAttribute("totalPage");
  int limit = (Integer) request.getAttribute("limit");

  int startNum = (Integer) request.getAttribute("startNum"); // 페이지 시작 일련번호

  String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
  String text = request.getParameter("text") != null ? request.getParameter("text") : "";

  int pagePerBlock = 5; // 페이지 범위
  int totalBlock = totalPage % pagePerBlock == 0 ? totalPage / pagePerBlock : totalPage / pagePerBlock + 1; // block의 전체 갯수(페이지 범위 단위의 총 갯수)
  int thisBlock = ((pageNum -1) / pagePerBlock) + 1; // 현재 블럭
  int firstPage = ((thisBlock -1) * pagePerBlock) + 1; // 블럭의 첫 페이지
  int lastPage = thisBlock * pagePerBlock; // 블럭의 마지막 페이지
  lastPage = (lastPage > totalPage) ? totalPage : lastPage;

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="/css/adminCSS/adminBoardList.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
  <script src="/js/adminJS/adminNoticeList.js" defer></script>
  <title>관리자 공지사항 목록</title>
</head>
<body>
<div class="wrap">
  <jsp:include page="./inc/adminHeader.jsp"/>
  <div class="main">
    <h2>공지사항 목록</h2>
    <form name="frmBoardList" action="/admin?action=noticeRemove" method="post">
    <ul class="list-group">
      <div class="checkBtnBox">
        <div class="allCheck">
          <input type="button" name="allCheck" id="allCheck" value="전체 선택" />
        </div>
        <div class="delBtn">
          <button type="button" class="selectDelete_btn">선택 삭제</button>
        </div>
      </div>
      <c:forEach var="adminBoardDTO" items="${adminBoardDTOList}" varStatus="status">
        <a href="/admin?action=noticeGet&cno=${adminBoardDTO.cno}" class="list-a">
          <li class="list">
            <input type="checkbox" name="chBox" class="chBox" id="chk${adminBoardDTO.cno}" value="${adminBoardDTO.cno}">
              &nbsp;[<%=(totalRecord--)-(pageNum-1)*limit%>] ${adminBoardDTO.title}
            <p>${adminBoardDTO.addDate}</p>
            <p>${adminBoardDTO.superName}</p>
            <a href="/admin?action=noticeRemove&cno=${adminBoardDTO.cno}"
               class="delete-btn" onclick="return confirm('정말 삭제하시겠습니까?')"> X </a>
          </li>
        </a>
        <%
          startNum--;
        %>
      </c:forEach>
    </ul>
    </form>
    <hr>
    <%--페이징--%>
    <div align="center" class="paging">
      <a href="<c:url value="/admin?action=noticeList&pageNum=1"/>"><span>첫페이지</span></a>
      <%
        if(thisBlock > 1) {
      %>
      <a href="/admin?action=noticeList&pageNum=<%=(firstPage - 1)%>"><span> < 이전 </span></a>
      <%
        }
      %>
      <c:set var="pageNum" value="<%=pageNum%>" />
      <c:forEach var="i" begin="<%=firstPage%>" end="<%=lastPage%>">
        <a href="/admin?action=noticeList&pageNum=${i}" class="pager">
          <c:choose>
            <c:when test="${pageNum==i}"> <!--현재 페이지이면 볼드처리 -->
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
      <a href="/admin?action=noticeList&pageNum=<%=(lastPage + 1)%>"><span> 다음 ></span> </a>
      <%
        }
      %>
      <a href="<c:url value="/admin?action=noticeList&pageNum=${totalPage}"/>"><span>끝페이지</span></a>
    </div>
    <c:if test="${error != null}">
      <div class="alert alert-danger alert-dismissible fade show mt-3">
        에러 발생 : ${error}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </c:if>
    <%--페이징 끝--%>
    <button class="write-btn" type="button" onclick="location.href='/admin?action=noticeAddView'">글 쓰기</button>
    <%--검색창--%>
    <div class="form-box">
      <form name="frmList" action="/admin?action=noticeList" method="get">
        <input type="hidden" name="pageNum" value="<%=pageNum%>">
        <input type="hidden" name="action" value="noticeList">
        <input type="hidden" name="num">
        <table>
          <tr>
            <td>
              <select name="items" class="txt">
                <option value="title" <% if(items.equals("title")){%>selected<%}%>>제목</option>
                <option value="content" <% if(items.equals("content")){%>selected<%}%>>내용</option>
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