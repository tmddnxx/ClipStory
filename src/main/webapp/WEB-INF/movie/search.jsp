<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List searchVOList = (List) request.getAttribute("searchVOList");
  int limit = (Integer) request.getAttribute("limit");

  String items = request.getParameter("items") != null ? request.getParameter("items") : "title";
  String text = request.getParameter("text") != null ? request.getParameter("text") : "";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div align="left">
  <form name="searchList" action="./list.search?action=list" method="post">
    <table>
      <tr>
        <td width="100%" align="left">
          <select name="items" class="txt">
            <option value="movieName" <% if(items.equals("movieName")){%>selected<%}%>>영화제목</option>
            <option value="actor" <% if(items.equals("actor")){%>selected<%}%>>배우</option>
            <option value="genre" <% if(items.equals("genre")){%>selected<%}%>>장르</option>
          </select> <input name="text" type="text" value="<%=text%>"/>
          <input type="submit" id="btn-search" class="btn btn-primary" value="검색"/>
          <input type="reset" id="btn-reset" class="btn btn-secondary" value="취소"/>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
