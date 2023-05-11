<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="camping.dao.PersonDao" %>
<%@ page import="camping.dto.PersonDto" %>
<%@ page import="Controller.mainController" %>
<%@ page import="camping.dto.campzone" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="camping.dto.emp" %>

<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Example</title>
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
	body{
	background-color: black;
	}
header {
  z-index: 6;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 97.2%;
  padding: 20px;
  height: 60px;
  background-color: #383a3f;
  position: fixed; /* Add this line */
  top: 0; /* Add this line */
}
    footer{
  position: relative;
  top: 880px;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  width: 97.2%;
  height: 260px;
  background-color: #383a3f;
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
  left : 40%;

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
  left: 55%;
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
      left: 0;
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


 <header>
  <h1 style="position: absolute; left: 100px;">TM_Camping</h1>
  <nav>
    <a href="main1.html"><img src="캠핑메인.png" alt="" style="width: 150px; position: relative; left: 300px; bottom: 5px;"></a>
    <ul>
      <li><a class="submenu1"href="#">이용안내</a></li>
      <li class="has-submenu"><a class="submenu1" href="#">예약하기</a>
        <ul class="submenu">
          <li><a class="submenu1" href="#">캠핑장 예약</a></li>
          <li><a class="submenu1" href="#">코레일 예약</a></li>
          <li><a class="submenu1" href="#">렌터카 예약</a></li>
          <li><a class="submenu1" href="#">렌탈장비 예약</a></li>
        </ul>
      </li>
      <li class="has-submenu"><a class="submenu1" href="#">도별여행지</a>
        <ul class="submenu">
          <li><a class="submenu1" href="#">서울</a></li>
          <li><a class="submenu1" href="#">경기도</a></li>
          <li><a class="submenu1" href="#">강원도</a></li>
          <li><a class="submenu1" href="#">충청도</a></li>
          <li><a class="submenu1" href="#">경상도</a></li>
          <li><a class="submenu1" href="#">전라도</a></li>
          <li><a class="submenu1" href="#">제주도</a></li>
        </ul>
      </li>
      <li><a class="submenu1" href="#">커뮤니티</a></li>
    </ul>
  <div class="sidebar">
   <span class="login-area">
   <%  if (session.getAttribute("userId") != null) { %>
  <% String userId = (String)session.getAttribute("userId"); %>
  <span class="login-area">어서오세요 : <%= userId %> 님&nbsp; &nbsp; &nbsp;</span>
<% } else { %>
  <a class="submenu1 login-area" href="login.jsp">로그인</a>
  <a class="submenu1 login-area" href="join.jsp">회원가입</a>
<% } %>   <button id="logout">로그아웃</button>
  <a class="submenu1 " href="https://www.weather.go.kr/w/weather/forecast/short-term.do">기상청날씨</a>
	<script>
		document.getElementById('logout').addEventListener('click', ()=>{location.href='logout.jsp'});
	</script>
</span>
</div>
  </nav>
</header>
<body>

<div class="box diagonal-image1">
<br><br><br>
<h1>충청도</h1></div>
<div class="box diagonal-image2">
<br><br><br>
<h1>&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;경기도</h1></div>
<div class="box diagonal-image3"><br><br><br>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;전라도</h1></div>
<div class="box diagonal-image4"><br><br><br>
<h1>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;경상도</h1></div>
<div class="box diagonal-image5"><br><br><br>
<h1>&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;강원도</h1></div>
<div class="box diagonal-image6"><br><br><br>
<h1>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;제주도</h1></div>



<style>

    
    .box {
    	text-align: center;
        display: inline-block;
        position: absolute;
        height: 700px;
        background-size: cover;
        background-repeat: no-repeat;
        transition: transform 0.3s ease;
        margin-top:140px
    }

    .box:hover {
        transform: scale(1.2);
    }

    .diagonal-image1 {
        width: 355px;
        left: 10px;
        background-image: url('천안.jpg');
        clip-path: polygon(0 0, 92% 0, 36% 100%, 0 100%);
       
    }

    .diagonal-image2 {
        width: 470px;
        left: 135px;
        background-image: url('서울.jpg');
        clip-path: polygon(42% 0, 87% 0, 42% 100%, 0 100%);
      
    }

    .diagonal-image3 {
        width: 450px;
        left: 331px;
        background-image: url('전라도.jpg');
        clip-path: polygon(47% 0, 90% 0, 45% 100%, 0 100%);
     
    }

    .diagonal-image4 {
        width: 450px;
        left: 523px;
        background-image: url('부산.jpg');
        clip-path: polygon(47% 0, 92% 0, 49% 100%, 2% 100%);
     
    }

    .diagonal-image5 {
        width: 450px;
        left: 740px;
        background-image: url('홍천.jpg');
        clip-path: polygon(43% 0, 88% 0, 46% 100%, 0 100%);
      
    }
    .diagonal-image6 {
        width: 350px;
        left: 910px;
        background-image: url('제주도.jpg');
        clip-path: polygon(64% 0, 100% 0, 100% 100%, 10% 100%);
      
    }

</style>

</body>
        <footer>
          <div>
				<img src="sns.PNG" alt="" style="border-radius: 15px; position: relative; ">
                <p>&nbsp; &nbsp; &nbsp;업체명 : TM_Camping</p>
                <p>&nbsp; &nbsp; &nbsp;대표자 : 7조 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;사업자등록번호 : 111-11-11111 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;주소 : 충청남도 천안시 동남구 대흥동 134 휴먼교육센터 8층 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;전화번호 : &nbsp;041-561-1122<br><span style="color: rgb(246, 179, 82);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(문의 시간 AM 9:00 ~ PM 8:00 / 점심시간 12:00~13:00)<br>
                </span>&nbsp; &nbsp; &nbsp;캠핑장 시설 안내 및 이용안내 문의만 부탁드립니다.</p>
          </div>
          <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&amp;q=천안시+동남구+휴먼+교육센터" style="width: 400px; height: 250px;"></iframe>
					<div>
						<span style="padding: 20px;">이용약관</span><span style="padding: 20px;">개인정보처리방침</span><span style="padding: 20px;">새로운 메뉴</span>
          </div>
        </footer>
        </html>