<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="camping.dao.ReservationDao"%>    
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
	    title: '예약이 취소되었습니다.',
	    //text: '',
	    icon: 'success',
	    confirmButtonText: '확인',
	    
	  }).then(result => {
		  if(result.isConfirmed) {
			  location.href = './reservationInfo.jsp';
		  }
		});
	}
	</script>
	<%
		request.setCharacterEncoding("UTF-8"); //한글 정상 인식을 위해 써준다.
		int r_number = Integer.parseInt(request.getParameter("id"));
	
		ReservationDao reservationDao = new ReservationDao();
		int result = reservationDao.cancelReservationInfo(r_number);
	
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
			alert('예약 취소 실패..');
	</script>
	<%
		}
	%>
	<!-- <script>
		//location.href = './index.html';
	</script> -->
</body>
</html>