<%@ page import="com.example.movie.model.dto.AdminDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  AdminDTO adminDTO = (AdminDTO) session.getAttribute("superInfo");
%>
<script src="https://kit.fontawesome.com/e83b9315c8.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Caprasimo&family=Noto+Sans+KR:wght@100;200;300;400;500;600;700;800;900&family=Righteous&display=swap');
  *{
    margin: 0;
    padding: 0;
  }
  a{
    text-decoration: none;
    color: inherit;
  }
  li{
    list-style: none;
  }
  .mypage,
  .join,
  .login,
  .logout{
    color: white;
    font-size: 25px;
  }
  #header{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 5vh;
    color: white;
    padding: 1rem;
    z-index: 100;
  }

  .top-list{
    display: flex;
    justify-content:space-between;
    align-items: center;
  }
  .goContent{
    margin-right: 30px;
    font-size: 20pt;
  }

  .nickName{
    font-size: 14pt;
  }
  .top-inner{
    display: flex;
    justify-content: center;
    height: 100%;
    text-align: center;
  }
  .logo{
    color: red;
    font-weight: bold;
    font-size: 45px;
    font-family: 'Righteous', cursive;
    margin-right: 100px;
  }
  .active{
    background-color: rgba(0,0,0,0.8);
    backdrop-filter: blur(20px);
    transition-duration: 1s;
    transition-timing-function: ease;

    -webkit-transition-duration:0.4s;
    -webkit-transition-timing-function:ease;

  }

  .deactive {
    background-image: linear-gradient(180deg,rgba(0,0,0,0.3),transparent);
    transition-duration: 0.5s;
    transition-timing-function: ease;
    backdrop-filter: blur(1px);
    -webkit-transition-duration:0.4s;
    -webkit-transition-timing-function:ease;
  }

</style>

<header id="header" class="deactive">
  <div style="width: 357px;"></div>
  <div>
    <a href="/admin?action=main" class="logo">MOP</a>
  </div>
  <div class="top-inner">
    <ul class="top-list">
      <c:choose>
        <c:when test="${empty superInfo}">
          <li class="nav-item"><a class="goContent">(관리자)로그인</a></li>
        </c:when>
        <c:otherwise>
          <li class="nav-item nickName">[${superInfo.superName} 님]</li>
          <li class="nav-item"><a class="goContent" href="/logout"><i class="fa-solid fa-arrow-right-from-bracket logout"></i></a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</header>

<script>
  $(document).on('scroll', function(){
    if($(window).scrollTop() > 0){
      $("#header").removeClass("deactive");
      $("#header").addClass("active");
    }else{
      $("#header").removeClass("active");
      $("#header").addClass("deactive");
    }
  });
</script>