<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<style>
*{
  font-family:Cafe24Ssurround;
  text-align:center;
  margin-top:20px;
}
body{
background-size: cover;
            background-image: url(배경화면.png); 
    }
.click{
 margin: 5px;
}
ul{
   list-style:none;
   }
   .snslogin{
    display: inline-block;
   }

   @font-face {
    font-family: 'Cafe24Ssurround';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

</style>
<body id="pull" style="background-image:url(배경화면.png)" >
  <div class="logoimage"></div>
		<form method="post" action="loginAction.jsp" >
				<h3 style="text-align: conter; color: white;" >로그인화면</h3>
				<div>
					<input type="text" class="from-control" placeholder="아이디" name="userID" maxlength="20"> 
				</div>
				<div>
					<input type="password" class="from-control" placeholder="비밀번호" name="userPassword" maxlength="20"> 
				</div>
        <div class="">
				<input type="submit" value="로그인" style="margin-top : 20px;">
       </div>
				</form>
				<div>
					<span class="click"></span><a href="join.jsp"><input type="submit" value="회원가입"></a>
            <input type="submit" value="아이디찾기"><input type="submit" value="비밀번호찾기" style="position: relative; left: 5px;"></span>
				</div>
	<div class="snslogin">
		<ul>
	<li onclick="kakaoLogin();">
       <a href="javascript:void(0)">
          <img src="kakao_login_small.png" width="115" height="45">
      </a>
</ul>
</div>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('8cef13d36a8d0ab00cb1cd204240b087'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
        	  window.location.href = "main.html";
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
      window.location.href = "login.jsp";
    }
  }  
</script>
 <%
    String clientId = "VIu1TfHxKPf44pxkVbXl";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8080/ThreeMenCamping/main.html", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
         + "&client_id=" + clientId
         + "&redirect_uri=" + redirectURI
         + "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>"><img height="44" width="115" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>