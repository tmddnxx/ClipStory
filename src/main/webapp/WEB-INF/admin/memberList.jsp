<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<link href="./css/common.css" rel="stylesheet">
<link href="/css/boardCSS/boardGet.css" rel="stylesheet">
<div class="AdminMember">
    <h2>회원 목록</h2>
    <form name="frmAdminMember" action="/admin?action=memberList" method="post">
        <ul class="member-list">
            <c:forEach var="memberDTO" items="${memberDTOList}" varStatus="status">
                <a href="admin?action=memberList&memberId=${memberDTO.memberId}" class="list-a">
                    <li class="memberInfo">
                        <p>${memberDTO.memberId}</p>
                        <p>${memberDTO.name}</p>
                        <p>${memberDTO.nickName}</p>
                        <p>${memberDTO.joinDate}</p>
                    </li>
                </a>
            </c:forEach>
        </ul>
    </form>
</body>
</html>
