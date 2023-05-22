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
<title>로그인 액션</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <%
        UserDao userDao = new UserDao();
        int result = userDao.login(user.getUserID(), user.getUserPassword());
        String alertScript = "";
        if(result == 1) {
            session.setAttribute("userId", user.getUserID());
            String userId = (String)session.getAttribute("userID");
            alertScript = "swal({title: 'Good job!', text: '로그인에 성공하였습니다.', icon: 'success'}).then(function() { window.location = 'main.jsp'; });";
        } else {
            alertScript = "swal({title: 'Oops!', text: '아이디 또는 비밀번호가 틀립니다.', icon: 'error'}).then(function() { history.back(); });";
        }
    %>
    <script>
        <%=alertScript%>
    </script>
</body>
</html>
