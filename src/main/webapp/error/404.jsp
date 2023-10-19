<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 존재하지 않는 페이지</title>
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Caprasimo&family=Noto+Sans+KR:wght@100;200;300;400;500;600;700;800;900&family=Righteous&display=swap');
    body{
        background-color: black;
        position: relative;
    }
    div{
        width: 500px;
        height: 500px;
        background-color: white;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: flex;
        flex-direction: column;
        justify-content: center;
        text-align: center;
    }
    a{
        text-decoration: none;
    }
    .logo{
        color: red;
        font-weight: bold;
        font-size: 45pt;
        font-family: 'Righteous', cursive;
    }
</style>
<body>
    <div>
        <h1 class="logo">MOP</h1>
        <h1>유효하지 않은 주소입니다</h1>
        <a href="main.movie">☞ 새 주소 바로가기</a>
    </div>
</body>
</html>
