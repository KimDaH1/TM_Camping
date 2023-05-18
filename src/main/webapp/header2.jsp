<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>header</title>
    <style>
  @font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
    @font-face {
    font-family: 'HS-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/HS-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

    *{
      font-family: 'omyu_pretty' ;
    }
/* 	body{
	background-color: black;
	} */
header {
  z-index: 6;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 20px;
  height: 70px;
  background-color: #383a3f;
  position: absolute;
  top: 0;
  left:%;
}
 
    nav {
      display: flex;
      justify-content: center;
      align-items: center;
    }


    nav ul {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
  position: relative;
  left : 60%;

    }

    nav li {
      margin-right: 20px;
      position: relative;
    }

    nav a {
      color: #fff;
      text-decoration: none;
      transition: background-color 0.5s ease;
    }

    nav .submenu1:hover {
      background-color: #ffc107;
    }

.sidebar{
  position: relative;
  left: 72%;
}

.login-area a {
  color: #fff;
  text-decoration: none;
  margin-left: 10px;
}

.weather {
  color: #ffc107;
  font-weight: bold;
  margin-right: 10px;
}

    .submenu {
      display: none;
      position: absolute;
      top: 100%;
      left: 0px;
      min-width: 120px;
      z-index: 6;
    }

    .has-submenu:hover .submenu {
      display: block;
    }

    .submenu a {
      display: block;
      background-color: #383a3f;
      color: #fff;
      padding: 10px;
      transition: background-color 0.5s ease;
    }

    .submenu a:hover {
      background-color: #ffc107;

  </style>
</head>
<body>
<header>
  <h1 style="position: absolute; left: 150px;">TM_Camping</h1>
  <nav>
    <a href="main.jsp"><img src="./image/캠핑메인.png" alt="" style="width: 85px; position: relative; left: 15px; bottom: 5px;"></a>
    <ul>
      <li><a class="submenu1"href="#">이용안내</a></li>
      <li class="has-submenu"><a class="submenu1" href="#">각종예약</a>
        <ul class="submenu">
          <li><a class="submenu1" href="reservationInfo.jsp" target="_blank">캠핑장 예약</a></li>
          <li><a class="submenu1" href="https://www.letskorail.com/" target="_blank">코레일 예약</a></li>
          <li><a class="submenu1" href="https://starcamp.co.kr/" target="_blank">렌탈장비 예약</a></li>
        </ul>
      </li>
      <li class="has-submenu"><a class="submenu1" href="#">도별여행지</a>
        <ul class="submenu">	
          <li><a class="submenu1" href="CampListGyeonggi.jsp">서울/경기</a></li>
          <li><a class="submenu1" href="CampListGangwon.jsp">강원도</a></li>
          <li><a class="submenu1" href="CampListChongchung2.jsp">충청도</a></li>
          <li><a class="submenu1" href="CampListGyeongsang.jsp">경상도</a></li>
          <li><a class="submenu1" href="CampListJeolla.jsp">전라도</a></li>
          <li><a class="submenu1" href="CampListJeju.jsp">제주도</a></li>
        </ul>
      </li>
      <li><a class="submenu1" href="#">커뮤니티</a></li>
    </ul>
 <div class="sidebar">
  <span class="login-area">
<%  if (session.getAttribute("userId") != null) { %>
  <% String userId = (String)session.getAttribute("userId"); %>
  <span class="login-area">어서오세요 : <%= userId %> 님&nbsp; &nbsp; &nbsp;</span>
  <button id="logout">로그아웃</button>
  <script>
    document.getElementById('logout').addEventListener('click', ()=>{location.href='logout.jsp'});
  </script>
  <a class="submenu1 " href="mypage.jsp">마이페이지</a>
<% } else if (session.getAttribute("naverId") != null) { %>
  <% String naverId = (String)session.getAttribute("naverId"); %>
  <span class="login-area">네이버 어서오세요 : 사용자 <%= naverId %> 님&nbsp; &nbsp; &nbsp;</span>
  <button id="logout">로그아웃</button>
  <script>
    document.getElementById('logout').addEventListener('click', ()=>{location.href='logout.jsp'});
  </script>
  <a class="submenu1 " href="mypage.jsp">마이페이지</a>
<% } else { %>
  <a class="submenu1 login-area" href="login.jsp">로그인</a>
  <a class="submenu1 login-area" href="usercheck.jsp">회원가입</a>
<% } %>  
  <a class="submenu1 " href="https://www.weather.go.kr/w/weather/forecast/short-term.do">기상청날씨</a>
</span>

</div>

  </nav>
</header>
</body>
</html>