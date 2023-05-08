<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
        <input type="text" id="userGender" name="userGender"><br>
        <label for="userEmail">이 메 일</label>
        <input type="text" id="userEmail" name="userEmail"><br>
        <label for="userPhone">전화번호</label>
        <input type="text" id="userPhone" name="userPhone"><br>
        <input type="submit" value="회원가입">
    </form>
</body>
</html>