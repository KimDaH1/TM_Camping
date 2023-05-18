<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="camping.dao.ReservationDao" %>	
<%@ page import="camping.dao.OrderDao" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script src="https://js.tosspayments.com/v1/payment"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

body {
	font-family: 'Noto Sans KR', sans-serif;
}

.mb2 {
	margin-bottom: 25px;
}

.gray {
	color: rgb(78, 89, 104);
	font-size: 13px;
}

.red {
	color: rgb(240, 68, 82);
	font-size: 13px;
}

.box_section {
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 10px 20px rgb(0 0 0/ 1%), 0 6px 6px rgb(0 0 0/ 6%);
}
.container {
	margin-top: 80px;
}
</style>
<script>
  $( function() {
    $( ".datepicker" ).datepicker({
    	dateFormat: 'yy-mm-dd' //달력 날짜 형태
            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
            ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
            ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
            ,minDate: 0
    });    
  } );
  </script>
</head>
<body class="bg-light">
<%@ include file="header2.jsp"%>
<%
	String userId = (String)session.getAttribute("userId");
	
	ReservationDao reservationDao = new ReservationDao();
	int userNumber = reservationDao.getUserNumber(userId);

	//캠핑장 기본키 값이 넘어 올 예정
	int camp_id = Integer.parseInt(request.getParameter("id"));
	//int camp_id = 1;
	String cpname = reservationDao.getCampName(camp_id);
%>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>

			<div class="col-md-6">

				<h2 style="margin-top: 10px; padding: 20px 0px 10px 0px; text-align: center;">
					<img width="45px" src="https://static.toss.im/3d-emojis/u1F60E-apng.png">
					<img width="45px" src="https://static.toss.im/3d-emojis/u1F60E-apng.png"> 
					<img width="45px" src="https://static.toss.im/3d-emojis/u1F60E-apng.png"><br>
					삼남자 캠핑 예약 페이지
				</h2>
				<form id="reservForm" name="reservationForm"
					action="reservation_proc.jsp">
					<div id="requestPayment_form" class="box_section"
						style="padding: 40px 30px 50px 30px; margin-top: 30px; margin-bottom: 50px">

						<div class="mb2">
							<label class="form-label">캠핑장명 <span
								class="gray">(camp_id) </span></label> <input id="orderId"
								class="form-control form-control-lg" type="text" name="cpname" value="<%=cpname %>"
								readonly="readonly">
							<input type="hidden" name="c_id" value=<%=camp_id %>>
						</div>
						<div class="mb2">
							<label class="form-label" for="sdate">시작일 <span
								class="gray">(s_date) </span></label> <input id="sdate"
								class="form-control form-control-lg datepicker" type="text"
								name="sdate" placeholder="여기를 클릭해서 날짜를 선택하세요!" onchange="calDiffDays()">
						</div>
						<div class="mb2">
							<label class="form-label" for="edate">종료일 <span
								class="gray">(e_date) </span></label> <input id="edate"
								class="form-control form-control-lg datepicker" type="text"
								name="edate" placeholder="여기를 클릭해서 날짜를 선택하세요!" onchange="calDiffDays()">
						</div>
						
						<div class="mb2">
							<label class="form-label">금액 <span
								class="gray">(amount) </span></label> <input id="amount"
								class="form-control form-control-lg" type="text"
								name="amount" value="0">
						</div>

						<div class="mb2">
							<label class="form-label">회원번호 <span
								class="gray">(userNumber)</span></label> <input id="userNumber"
								class="form-control form-control-lg" type="text" value="<%=userNumber%>">
						</div>

						<div class="d-grid gap-2">
							<button id="reservBtn" type="button"
								class="btn btn-lg btn-primary">예약하기</button>
						</div>

					</div>
					<div class="col-md-3"></div>
				</form>
			</div>
		</div>
	</div>
	<script>
		document.getElementById('reservBtn').addEventListener('click', ()=>{
			//let form = document.reservationForm;
			
			let sdate = document.getElementById('sdate').value;
			let edate = document.getElementById('edate').value;
			
			if(sdate != "" && edate != "") {
				if(edate >= sdate) {
					/* if(confirm('예약하시겠습까?')) {
						form.submit();
					} */
					<%
						System.out.print("예약이 완료되었습니다");
					%>
					showAlert();
	//				form.submit();
				} else {
					showWarning();
				}
			} else {
				showWarning();
			}
			
			
			/* if(confirm('예약하시겠습까?')) {
				form.submit();
			} */
			
		});
		function showAlert() {
			let form = document.reservationForm;
			let sdate = document.getElementById('sdate').value;
			let edate = document.getElementById('edate').value;
			  Swal.fire({
			    title: '예약하시겠습니까?',
			    text: '시작일 : ' + sdate + ' ~ ' + '종료일 : ' + edate,
			    icon: 'question',
			    showCancelButton: true,
			    confirmButtonText: '확인',
			    cancelButtonText: '취소',
			    
			    reverseButton: true,
			    
			  }).then(result => {
				  if(result.isConfirmed) {
					  form.submit();
				  }
			  });
			}
		function showWarning() {
			  Swal.fire({
			    title: '날짜 선택이 잘못되었습니다!',
			    text: '시작일과 종료일을 다시 확인해주세요.',
			    icon: 'warning',
			    confirmButtonText: '확인',
			    
			  });
			}
		function calDiffDays()
		{
		    var sdd = document.getElementById("sdate").value;
		    var edd = document.getElementById("edate").value;
		    var ar1 = sdd.split('-');
		    var ar2 = edd.split('-');
		    var da1 = new Date(ar1[0], ar1[1], ar1[2]);
		    var da2 = new Date(ar2[0], ar2[1], ar2[2]);
		    var dif = da2 - da1;
		    var cDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
		    var cMonth = cDay * 30;// 월 만듬
		    var cYear = cMonth * 12; // 년 만듬
		 if(sdd && edd){
		    //document.getElementById('years').value = parseInt(dif/cYear)
		    //document.getElementById('months').value = parseInt(dif/cMonth)
		    if( edd >= sdd) {
			    document.getElementById('amount').value = ( parseInt(dif/cDay) + 1 ) * 100000;
		    } else {
		    	document.getElementById('amount').value = 0;
		    }
		 }
		}
		/*
		$("#edate").on("change keyup paste", function(){
			//alert(sdate);
			if(sdate != "") {
				let sdate = $('#sdate').val();
				//const startDate = new date(sdate);
				alert(sdate);
			}
			//const dateA = new Date('2022/06/01');
			//alert(dateA);
			//$('#amount').val('100000000');
		})
		*/
		/* $("#amount").on("input", function(){
		    $(this).val($(this).val().replace(/\,/g, '').replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'));
		}); */
	</script>
<%@ include file="footer2.jsp"%>	
</body>
</html>