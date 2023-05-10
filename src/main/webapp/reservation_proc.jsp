<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="camping.dao.ReservationDao"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@2.6.14/dist/vue.js"></script>
<link href="https://unpkg.com/v-calendar@2.4.0/lib/v-calendar.min.css"
	rel="stylesheet">
</head>
<body>	
	<%
		request.setCharacterEncoding("UTF-8"); //한글 정상 인식을 위해 써준다.
		String sDate = request.getParameter("sdate");
		String eDate = request.getParameter("edate");
	
		ReservationDao reservationDao = new ReservationDao();
		int result = reservationDao.insertReservationInfo(sDate, eDate);
	
		// id, 이름, 주소, 번호, 취미 설정
		/* PersonDto personDto = new PersonDto();
		personDto.setId(id);
		personDto.setName(name);
		int result2 = personDao.updatePersonInfo(personDto); //객체단위로 넘기기 */
	
		if (result == 1) {
			//추가성공
	%>

	<script>
		alert('예약성공');
	</script>
	<%
		} else {
	%>
	<script>
			alert('예약실패..');
	</script>
	<%
		}
	%>
	<script>
		location.href = './index.html';
	</script>

</body>
</html>