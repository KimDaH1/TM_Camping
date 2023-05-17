<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
    integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <style>
    * {
      text-align: center;
      color: white;
      margin-top: ;
    }

    body {
      width: 500px;
      height: 700px;
      top: 100px;
      position: relative;
      margin-top: 15%;
      left: 29%;
      border: 2px solid rgb(97, 97, 100);
      border-radius: 15px;
      background-size: cover;
      background-image: url(배경화면.avif);
      background-position: 0% 12%;

    }

    input {
        text-align: left;
        color: white;
        border: 1px solid rgb(97, 97, 100);
        background-color: transparent;
        border-top: 0px;
        border-left: 0px;
        border-right: 0px;
    }
    
     i{
    color: black;
   }
      .video-box {
    position: fixed;
    top: 0;
    left: 0;
    width:10px;
    height: 500px;
    z-index: -1;
    opacity: 1.0;
}

video {
    position: absolute;
    top: 280px;
    left: 560px;
    z-index: -10;
    transform: translateX(-50%) translateY(-45%);
    background: no-repeat;
    background-size: cover;
}
  </style>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<img src="./image/캠핑메인.png" alt="" width="150">
<h1>TM_Camping</h1>
<body>
  <div class="video-box">
            <video muted autoplay loop>
                <source src="./image/회원가입.mp4" type="video/mp4">
            </video>
        </div>

 <form id="joinForm" method="post" action="joinAction.jsp" onsubmit="event.preventDefault(); submitForm();">
    <span>아이디</span><br>
    <i class="fa-solid fa-user"></i> <label for="userID"></label>
    <input type="text" id="userID" name="userID" placeholder="User ID" style="margin-bottom: 25px;"><br>
    <span>비밀번호</span><br>
    <i class="fa-solid fa-lock"></i> <label for="userPassword"></label>
    <input type="password" id="userPassword" name="userPassword" placeholder="User Password"
      style="margin-bottom: 25px;"><br>
    <span>이름</span><br>
    <label for="userName"></label>
    <i class="fa-solid fa-circle-user"></i><input type="text" id="userName" name="userName" style="margin-bottom: 25px;" placeholder="User Name"><br>
    <span>성별</span><br>
    <label for="userGender"></label>
    <input type="radio" name="userGender" value="m" checked >남자
    <input type="radio" name="userGender" value="w" style="margin-bottom: 25px; margin-left: 70px;" >여자<br>
  <span>이메일</span><br>
    <label for="userEmail1"></label>
    <input type="text" id="userEmail1" style="margin-bottom: 25px; color:white;">@
    <input type="text" id="userEmail2" style="margin-bottom: 25px;color:white;"><br>
     <span>전화번호</span><br>
    <label for="userPhone1"></label>
    <input type="text" id="userPhone1" style="margin-bottom: 25px; width: 100px; text-align: center; color:white;">
    <input type="text" id="userPhone2" style="margin-bottom: 25px; width: 100px; text-align: center; color:white;">
    <input type="text" id="userPhone3" style="margin-bottom: 25px; width: 100px; text-align: center; color:white;">
    <br>
     <input type="submit" value="확인" style="margin-top : 12px; text-align: center; background-color: rgb(97, 97, 100);
             border-radius: 8px; width: 200px; height: 37px; font-size: 20px; position: relative; right: 5px;
              color:white">
  </form>
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script>
    function submitForm() {
        var phone1 = document.getElementById('userPhone1').value;
        var phone2 = document.getElementById('userPhone2').value;
        var phone3 = document.getElementById('userPhone3').value;

        var fullPhone = phone1 + '-' + phone2 + '-' + phone3;

        var email1 = document.getElementById('userEmail1').value;
        var email2 = document.getElementById('userEmail2').value;

        var fullEmail = email1 + '@' + email2;

        var form = document.getElementById('joinForm');
        form.appendChild(createHiddenInput('userPhone', fullPhone));
        form.appendChild(createHiddenInput('userEmail', fullEmail));
        form.submit();
    }

    function createHiddenInput(name, value) {
        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', name);
        input.setAttribute('value', value);

        return input;
    }
</script>
</body>

</html>