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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
    integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<style>
    @font-face {
        font-family: 'omyu_pretty';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
        font-weight: normal;
        font-style: normal;
    }

    .logoimage {
        position: absolute;
        }

    * {
        
        font-family: 'omyu_pretty';
        text-align: center;
        margin-top: 20px;
        color: white;
    }

    body {
        width: 500px;
        position: relative;
        margin-top: 15%;
        left: 29%;
        border: 2px solid rgb(97, 97, 100);
        border-radius: 15px;
        background-size: cover;
        background-image: url(배경화면.avif);
        background-position: 0% 12%;
    }
.video-box {
    position: fixed;
    top: 0;
    left: 0;
    min-width: 100%;
    min-height: 100%;
    z-index: -1;
    opacity: 0.9;
}

video {
    position: absolute;
    top: 50%;
    left: 50%;
    min-width: 100%;
    min-height: 100%;
    width: auto;
    height: auto;
    z-index: -100;
    transform: translateX(-50%) translateY(-50%);
    background: no-repeat;
    background-size: cover;
}

    .click {
        margin: 5px;
    }

    ul {
        list-style: none;
    }

    .snslogin {
        display: inline-block;
    }

    @font-face {
        font-family: 'Cafe24Ssurround';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
        font-weight: normal;
        font-style: normal;

    }

    input {
        text-align: left;
        height: 23px;
        width: 220px;
        color: black;
        border: 1px solid rgb(97, 97, 100);
        background-color: transparent;
        border-top: 0px;
        border-left: 0px;
        border-right: 0px;
    }
   i{
    color: white;
   }
   input{
   color:white;}
</style>
<header>
<div class="logoimage" style="width: 200px;">
    <h1 style="position: absolute; bottom: 30px; left: 200px;">TM_Camping</h1>
    <img src="캠핑메인.png" alt="" width="150px;" style="position: absolute; top: -200px; left: 50px;">
    
</div>

</header>
<div>
<body>
    <div class="video-box">
            <video muted autoplay loop>
                <source src="로그인.mp4" type="video/mp4">
            </video>
        </div>
    <form method="post" action="loginAction.jsp">
        <h3 style="text-align: conter; color: white;">로그인화면</h3>
        <div>
            <i class="fa-solid fa-user"></i> <input type="text" class="from-control" placeholder="User ID" name="userID" maxlength="20">
        </div>
        <div>
            <i class="fa-solid fa-lock"></i> <input type="password" class="from-control" placeholder="User passowrd " name="userPassword" maxlength="20">
        </div>
        </div>
        <div class="">
            <input type="submit" value="LOGIN" style="margin-top : 12px; text-align: center; background-color: rgb(97, 97, 100); border-radius: 8px; width: 200px; height: 27px; font-size: 20px;color: white" >
        </div>
    </form>
    <div>
        <span class="click"></span><a href="usercheck.jsp">
            <input type="submit" value="회원가입" style="margin-top : 12px; text-align: center; background-color: rgb(97, 97, 100); border-radius: 8px; width: 200px; height: 27px; font-size: 20px; position: relative; right: 5px; color:white"></a>
   
            <div style="width: 80%;height: 2px; background-color: rgb(97, 97, 100); position: absolute; left: 10%; border-radius: 12px; color:white"></div>
        </div>
    </div>
    <div class="snslogin" ><br>
        <h4 style=" color: black;">간편 로그인</h4>
        <ul style="display: flex; justify-content: center;">
            <li onclick="kakaoLogin();" style="margin-right: 10px;">
                <a href="javascript:void(0)">
                    <img src="kakao_login_medium_wide.png" width="180" height="44" style="position: relative; bottom: 42px; right: 15px;">
                </a>
        </ul>
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
                    console.log(response);
                    let kakaoNickname = response.properties.nickname;
                    
                    // 서버로 카카오 닉네임 전송
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function() {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // 세션에 카카오 닉네임이 저장된 후 main.jsp로 리디렉션
                                window.location.href = "main.jsp";
                            } else {
                                console.error("카카오 로그인 처리 중 오류 발생");
                            }
                        }
                    };
                    xhr.open("POST", "kakaoLogin.jsp", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.send("kakaoNickname=" + encodeURIComponent(kakaoNickname));
                },
                fail: function (error) {
                    console.log(error);
                },
            });
        },
        fail: function (error) {
            console.log(error);
        },
    });
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
    String redirectURI = URLEncoder.encode("http://localhost:8080/ThreeMenCamping/main.jsp", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
         + "&client_id=" + clientId
         + "&redirect_uri=" + redirectURI
         + "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a class="snslogin" href="<%=apiURL%>"><img height="44" width="180" style="position: relative; bottom: 80px; right: 2px;" src="네이버로그인.png"/></a>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<div class="snslogin">
</div>
</div>
</body>
</html>