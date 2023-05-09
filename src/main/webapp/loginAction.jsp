<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDao" %>
<%@ page import="user.UserDto" %>
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.UserDto" scope="page"/>
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPassword"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		UserDao userDao = new UserDao();
		int result = userDao.login(user.getUserID(), user.getUserPassword());

		if(result == 1) {
			out.println("<script>");
			out.println("location.href='main.html';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('잘못된 입력입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
	%>
</body>
</html>