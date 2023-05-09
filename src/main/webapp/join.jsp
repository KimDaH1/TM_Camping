<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
    <form method="post" action="joinAction.jsp">
        <label for="userID">아 이 디</label>
        <input type="text" id="userID" name="userID"><br>
        <label for="userPassword">비밀번호</label>
        <input type="password" id="userPassword" name="userPassword"><br>
        <label for="userName">이  름</label>
        <input type="text" id="userName" name="userName"><br>
        <label for="userGender">성 별</label>
        <input type="radio" name="userGender" value="m" checked>남자
		<input type="radio" name="userGender" value="w">여자<br>
        <label for="userEmail">이 메 일</label>
        <input type="text" id="userEmail" name="userEmail"><br>
        <label for="userPhone">전화번호</label>
        <input type="text" id="userPhone" name="userPhone"><br>
        <input type="submit" value="회원가입">
    </form>
    <!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>