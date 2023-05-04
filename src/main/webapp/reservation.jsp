<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>예약 페이지</h1>
		<form name="reservationForm" action="reservation_proc.jsp">
		입실 : <input id="sdate" type="date" name="sdate"> - 퇴실 : <input id="edate" type="date" name="edate">
		<button id="reservBtn">예약하기</button>
	</form>
	
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
		
		document.getElementById('insertBtn').addEventListener('click', ()=>{
			
			let form = document.personAddForm;
			
			let inputName = document.getElementById('inputName');
			if(inputName.value == "") {
				alert('이름은 필수입니다.');
				inputName.focut();
			} else {
				if(confirm('추가하시겠습니까?')) {
					form.submit();
				}	
			}
		});
	</script>
</body>
</html>