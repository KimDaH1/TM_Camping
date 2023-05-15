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
}
		 div,
        ul,
        li {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 0;
            margin: 0
        }

        a {
            text-decoration: none;
        }

        .quickmenu {
        	z-index: 6;
            position: absolute;
            width: 90px;
            top: 90%;
            margin-top: -50px;
            right: 10px;
            background: #fff;
        }

        .quickmenu ul {
            position: relative;
            float: left;
            width: 100%;
            display: inline-block;
            *display: inline;
            border: 1px solid #ddd;
        }

        .quickmenu ul li {
            float: left;
            width: 100%;
            border-bottom: 1px solid #ddd;
            text-align: center;
            display: inline-block;
            *display: inline;
        }

        .quickmenu ul li a {
            position: relative;
            float: left;
            width: 100%;
            height: 30px;
            line-height: 30px;
            text-align: center;
            color: #999;
            font-size: 9.5pt;
        }

        .quickmenu ul li a:hover {
            color: #000;
        }

        .quickmenu ul li:last-child {
            border-bottom: 0;
        }

        .content {
            position: relative;
            min-height: 1000px;
        }
  </style>
  
</head>


 
<body>
    <div class="quickmenu">
        <ul>
          <li><a href="#">예약확인</a></li>
          <li><a href="#">결제확인</a></li>
          <li><a href="#">My Page</a></li>
        </ul>
      </div>
<%@ include file = "header.jsp" %>

<div class="box diagonal-image1" onclick="location.href='CampListChongchung.jsp';"><br><br><br>
<h1 >충청도</h1></div>
<div class="box diagonal-image2"  onclick="location.href='CampListGyeonggi.jsp';"><br><br><br>
<h1 >&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;경기도</h1></div>
<div class="box diagonal-image3" onclick="location.href='CampListJeolla.jsp';"><br><br><br>
<h1 >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;전라도</h1></div>
<div class="box diagonal-image4" onclick="location.href='CampListGyeongsang.jsp';"><br><br><br>
<h1>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;경상도</h1></div>
<div class="box diagonal-image5" onclick="location.href='CampListGangwon.jsp';"><br><br><br>
<h1>&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;강원도</h1></div>
<div class="box diagonal-image6" onclick="location.href='CampListJeju.jsp';"><br><br><br>
<h1>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;제주도</h1></div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
      integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
      crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
        
$(document).ready(function(){
  var currentPosition = parseInt($(".quickmenu").css("top"));
  $(window).scroll(function() {
    var position = $(window).scrollTop(); 
    $(".quickmenu").stop().animate({"top":position+currentPosition+"px"},1000);
  });
});
    </script>
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
<%@ include file = "footer.jsp" %>

</body>

</html>