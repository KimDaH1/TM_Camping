<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="camping.dao.OrderDao" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
</head>
<body>
<script>
	function showAlert() {
	  Swal.fire({
	    title: '결제가 취소되었습니다.',
	    //text: '',
	    icon: 'success',
	    confirmButtonText: '확인',
	    
	  }).then(result => {
		  if(result.isConfirmed) {
			  location.href = './orderInfo.jsp';
		  }
		});
	}
	</script>
	<%
		request.setCharacterEncoding("UTF-8"); //한글 정상 인식을 위해 써준다.
		int pay_no = Integer.parseInt(request.getParameter("id"));
	
		OrderDao orderDao = new OrderDao();
		int result = orderDao.cancelOrderInfo(pay_no);
	
		if (result == 1) {
			//취소성공
	%>
	<script>
		showAlert();
	</script>
	<%
		} else {
	%>
	<script>
			alert('결제 취소 실패..');
	</script>
	<%
		}
	%>
</body>
</html>