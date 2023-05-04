<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDao" %>
<%@ page import="user.UserDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		UserDto user = new UserDto();
		user.setUserID(request.getParameter("userID"));
		user.setUserPassword(request.getParameter("userPassword"));
		user.setUserName(request.getParameter("userName"));
		user.setUserGender(request.getParameter("userGender"));
		user.setUserEmail(request.getParameter("userEmail"));
		user.setUserPhone(request.getParameter("userPhone"));
		
		UserDao userDao = new UserDao();
		int result = userDao.join(user);
		
		if(result == 1) {
			out.println("<script>");
			out.println("alert('회원가입이 완료되었습니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
/* 		if(result == 1){
			out.println("<script>");
			out.println("alert('회원가입이 완료되었습니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		}
		else if (result ==0){
			out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		} */
		/* else if (result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("존재하지 않는 아이디 입니다.");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("데이터베이스 오류가 발생했습니다.");
			script.println("history.back()");
			script.println("</script>"); 
		}*/
	%>
	%>
</body>
</html>