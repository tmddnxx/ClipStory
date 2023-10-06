<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    *{
        margin: 0;
        padding: 0;

    }
    .header{
        height: 150px;
        position: fixed;
        width: 100%;
        background-color: rgb(0,0,0);
        color: white;
        transition: opacity 0.3s ease-in-out;
    }
    .header-top{
        text-decoration: none;
    }
    .top-list{
        display: flex;
        justify-content: right
    }
    .nav-link{
        margin-right: 20px;
        font-size: 20pt;
    }
    .top-inner{
        text-align: right;
    }
    .header-main{
        height: 110px;
    }
    .main-inner{
        text-align: center;
    }
    .logo{
        color: red;
        font-weight: bold;
        font-size: 40pt;
    }
</style>
<div class="header">
    <div class="header-top">
        <div class="top-inner">
            <ul class="top-list">
                <c:choose>
                    <c:when test="${empty loginInfo}">
                        <li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
                        <li class="nav-item"><a class="nav-link" href="/register.member?action=register">회원 가입</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item" style="padding-top: 7px; color: white;">${loginInfo.nickName} 님</li>
                        <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                        <li class="nav-item"><a class="nav-link" href="/modify.member?action=modify&memberId=${loginInfo.memberId}">회원 수정</a></li>
                        <li class="nav-item"><a class="nav-link" href="/remove.member?action=remove&memberId=${loginInfo.memberId}">회원 탈퇴</a></li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item"><a class="nav-link" href="/list.movie?action=list">영화</a></li>
                <li class="nav-item"><a class="nav-link" href="/list.board?action=list">게시판</a></li>
            </ul>
        </div>
    </div>
    <div class="header-main">
        <div class="main-inner">
            <a href="/main.movie?action=main"><h1 class="logo">MOP</h1></a>
        </div>
    </div>
</div>
<script>
    window.addEventListener("scroll", function() {
        const header = document.querySelector('.header');
        const scrollPosition = window.scrollY;

        if (scrollPosition > 0) {
            // 스크롤이 아래로 내려갈 때
            header.style.opacity = "0.7";
        } else {
            // 스크롤이 맨 위로 올라올 때
            header.style.opacity = "1";
        }
    });
</script>