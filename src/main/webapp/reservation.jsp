<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
</style>
</head>
<body>
	<h1>예약 페이지</h1>
	<div id="joinForm">
		<form name="reservationForm" action="reservation_proc.jsp">
		입실 : <input id="sdate" type="date" name="sdate"> - 퇴실 : <input id="edate" type="date" name="edate">
		<button id="reservBtn">예약하기</button>
		</form>
	</div>
	
	<script>
		document.getElementById('reservBtn').addEventListener('click', ()=>{
			
			let form = document.reservationForm;
			
			let sdate = document.getElementById('sdate').value;
			let edate = document.getElementById('edate').value;
			console.log(sdate);
			console.log(edate);
			
			if(confirm('예약하시겠습까?')) {
				form.submit();
			}
			
		});		
	</script>
</body>
</html>