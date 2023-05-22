<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
	<%
		session.invalidate();//logout 처리 끝. 세션 정리
		
		response.sendRedirect("login.jsp"); //페이지 이동
	%>
</body>
</html>