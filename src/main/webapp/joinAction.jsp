<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDao" %>
<%@ page import="user.UserDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
<script>
	function showJoinOk() {
	  Swal.fire({
	    title: '회원가입이 완료되었습니다.',
	    text: '삼남자 캠핑에 오신 것을 환영합니다.',
	    icon: 'success',
	    confirmButtonText: '확인',
	    
	  	}).then(result => {
		  if(result.isConfirmed) {
			  location.href='http://localhost:8080/ThreeMenCamping/login.jsp';
		  }
	  });
	}
	function showWarning() {
	  Swal.fire({
	    title: '회원가입에 실패하였습니다.',
	    text: '가입을 다시 신청해주세요!',
	    icon: 'warning',
	    confirmButtonText: '확인',
	    
	  }).then(result => {
		  if(result.isConfirmed) {
			  location.href='http://localhost:8080/ThreeMenCamping/join.jsp';
		  }
	  });
	}
</script>
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
			/* out.println("<script>");
			out.println("alert('회원가입이 완료되었습니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>"); */
	%>
			<script>
				showJoinOk();
			</script>
	<%			
		} else {
			/* out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>"); */
			
	%>
			<script>
				showWarning();
			</script>
	<%
		}
	%>
</body>
</html>