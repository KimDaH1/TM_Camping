>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>footer</title>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>footer</title>
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
<body>
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
</body>
</html>