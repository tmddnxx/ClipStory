<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/e83b9315c8.js" crossorigin="anonymous"></script>
<%--font-family: 'Caprasimo', cursive;--%>
<%--font-family: 'Noto Sans KR', sans-serif;--%>
<%--font-family: 'Righteous', cursive;--%>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Caprasimo&family=Noto+Sans+KR:wght@100;200;300;400;500;600;700;800;900&family=Righteous&display=swap');
    *{
        margin: 0;
        padding: 0;
    }
    .mypage,
    .join,
    .login,
    .logout{
        color: white;
        font-size: 25px;
    }

    .header{
        width: 100%;
        height: 10vh;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 1;
        background-color: rgb(0,0,0);
        color: white;
        transition: opacity 0.3s ease-in-out;
    }
    .header-top{
        position: absolute;
        top: 25%;
        right: 0;
        width: 25%;
        height: 100%;
        text-decoration: none;
    }
    .top-list{
        display: flex;
        justify-content:space-between;
        align-items: center;
    }
    .nav-link{
        margin-right: 20px;
        font-size: 20pt;
    }
    a{
        text-decoration: none;
        color: inherit;
    }
    li{
        list-style: none;

    }
    .nickName{
        font-size: 16pt;
    }
    .top-inner{
        text-align: right;
    }
    .header-main{
        position: absolute;
        top: 25%;
        left: 50%;
        transform: translate(-50%);
        width: 300px;
        height: 100%;
    }
    .main-inner{
        text-align: center;
    }
    .logo{
        color: red;
        font-weight: bold;
        font-size: 45pt;
        font-family: 'Righteous', cursive;
    }
</style>
<div class="header">
    <div class="header-top">
        <div class="top-inner">
            <ul class="top-list">
                <li class="nav-item"><a class="nav-link" href="/list.movie?action=list">영화</a></li>
                <li class="nav-item"><a class="nav-link" href="/list.board?action=list">게시판</a></li>
                <c:choose>
                    <c:when test="${empty loginInfo}">
                        <li class="nav-item"><a class="nav-link" href="/login"><i class="fa-solid fa-arrow-right-to-bracket login"></i></a></li>
                        <li class="nav-item"><a class="nav-link" href="/register.member?action=register"><i class="fa-solid fa-user-plus join"></i></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item nickName">${loginInfo.nickName} 님</li>
                        <li class="nav-item"><a class="nav-link" href="/logout"><i class="fa-solid fa-arrow-right-from-bracket logout"></i></a></li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item"><a class="nav-link" href="/list.mypage?action=list"><i class="fa-solid fa-house-user mypage"></i></a></li>
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