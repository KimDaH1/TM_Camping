<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="camping.dao.ReservationDao"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
</head>
<body>
<%@ include file = "header.jsp" %>
<%
	String userId = (String)session.getAttribute("userId");
%>
<script>
	function showAlert() {
	  Swal.fire({
	    title: '예약이 완료되었습니다.',
	    text: '예약 내역 페이지에서 결제 부탁드립니다. ',
	    icon: 'success',
	    confirmButtonText: '확인',
	    
	  }).then(result => {
		  if(result.isConfirmed) {
			  location.href = './reservationInfo.jsp';
		  }
		});
	}
	function showWarning() {
	  Swal.fire({
	    title: '해당날짜에 이미 예약이 되어있습니다.',
	    text: '다른 날짜를 선택해주세요.',
	    icon: 'warning',
	    confirmButtonText: '확인',
	    
	  }).then(result => {
		  if(result.isConfirmed) {
			  location.href = './reservation.jsp';
		  }
		});
	}
	</script>
	<%
		request.setCharacterEncoding("UTF-8"); //한글 정상 인식을 위해 써준다.
		String s_date = request.getParameter("sdate");
		String e_date = request.getParameter("edate");
		
		String amount = request.getParameter("amount").replace(",", "");
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		
		ReservationDao reservationDao = new ReservationDao();
		//int result = reservationDao.insertReservationInfo(sDate, eDate, 1, 1, "예약됨");
		int result = reservationDao.insertReservation(s_date, e_date, amount, userId, c_id, "예약됨");
	
		// id, 이름, 주소, 번호, 취미 설정
		/* PersonDto personDto = new PersonDto();
		personDto.setId(id);
		personDto.setName(name);
		int result2 = personDao.updatePersonInfo(personDto); //객체단위로 넘기기 */
	
		if (result == 0) {
			//추가성공
	%>
	<script>
		showAlert();
	</script>
	<%
		} else {
	%>
	<script>
			//alert('예약실패..');
		showWarning();
	</script>
	<%
		}
	%>
</body>
</html>