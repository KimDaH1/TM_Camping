<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page import="camping.Utils.myUtil" %>
<%@ page import="camping.dao.ReservationDao" %>
<%@ page import="camping.dto.ReservationDto" %>
<%@ page import="camping.Utils.MyDateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="header2.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();// /ThreeMenCamping 여기까지 찍힘

ReservationDao reservationDao = new ReservationDao();


String userId = (String)session.getAttribute("userId");
/*
// 전체데이터 갯수 구하기
int dataCount = reservationDao.getReservationCount(userNumber);

// 한페이지에 표시할 데이터의 갯수
int numPerPage = 10;

// 전체 페이지수 구하기
int totalPage = myutil.getPageCount(numPerPage, dataCount);

// 전체 페이지수가 표시할 페이지수보다 큰 경우(삭제로 인해)
if (currentPage > totalPage) {
	currentPage = totalPage;
}

// 데이터베이스에서 가져올 rownum의 시작과 끝
int start = (currentPage - 1) * numPerPage + 1;
int end = currentPage * numPerPage;
*/
List<ReservationDto> reservationList;
reservationList = new ArrayList<ReservationDto>();
reservationList = reservationDao.getReservationList(userId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
	integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
</head>
<body>
	<h1 style="text-align: center; margin-top: 80px">
		<img width="45px" src="https://static.toss.im/3d-emojis/u1F60E-apng.png">
		<img width="45px" src="https://static.toss.im/3d-emojis/u1F60E-apng.png">
		<img width="45px" src="https://static.toss.im/3d-emojis/u1F60E-apng.png">
		<br>
		삼남자 캠핑예약 리스트 페이지</h1>

	<div id="bbsList">
		
		<table class="table table-striped" id="testTable" border="1">
			<thead>
				<tr>
					<th>예약번호</th>
					<th>캠핑장명</th>
					<th>시작일</th>
					<th>종료일</th>
					<th>금액</th>
					<th>결제하기</th>
					<th>예약취소</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < reservationList.size(); i++) {
					String cpname = reservationDao.getCampName(reservationList.get(i).getC_id());
				%>
				<tr>
					<td id="r_id<%=i%>" style="cursor: pointer;">
						<%
						out.print(reservationList.get(i).getR_number());
						%>
					</td>
					<td class="empno">
						<%
						//out.print(reservationList.get(i).getC_id());
						out.print(cpname);
						%>
					</td>
					<td class="ename">
						<%
						//out.print(reservationList.get(i).geteDate());
						out.print( MyDateUtil.convertStringToUtilDate(reservationList.get(i).getS_date()) );
						%>
					</td>
					<td class="job">
						<%
						out.print( MyDateUtil.convertStringToUtilDate(reservationList.get(i).getE_date()) );
						//out.print(reservationList.get(i).geteDate());
						%>
					</td>
					<td class="mgr">
						<%
						out.print(reservationList.get(i).getAmount());
						%>
					</td>
					<td class="order">
						<%-- <button id="<%=i %>" class="btn btn-primary orderBtn">결제</button> --%>
						<button id="<%=reservationList.get(i).getR_number() %>" class="btn btn-primary orderBtn">결제</button>
					</td>
					<td class="cancel">
						<%-- <button id="cancel<%=i %>" class="btn btn-danger cancelBtn">취소</button> --%>
						<button id="<%=reservationList.get(i).getR_number() %>" class="btn btn-danger cancelBtn">취소</button>
					</td>

				</tr>				
				<%
				}
				%>
			</tbody>
		</table>
		
	</div>
	<script>
	 $(document).ready(function () {
		 
         $('.orderBtn').click(function(){
        	 
             let tempId = $(this).attr('id');
             //let r_id = document.getElementById('r_id'+tempId).innerText;
             //alert('예약번호:'+r_id);
             //alert(tempId+'번째 데이터');
             location.href='./order.jsp?id='+tempId;
         });
         
         $('.cancelBtn').click(function(){
         	let tempId = $(this).attr('id');
         	//let r_number = document.getElementById('r_id'+tempId).innerText;
         	//alert(tempId);
         	showWarning(tempId);
          });
     });
	 function showWarning(tempId) {
			//alert(tempId);
		    //let r_number = document.getElementById('r_id'+tempId).innerText;
			  Swal.fire({
			    title: '예약을 취소하시겠습니까?',
			    text: '확인버튼 : 예약 취소하기',
			    icon: 'warning',
			    showCancelButton: true,
			    confirmButtonText: '확인',
			    cancelButtonText: '취소',
			    
			    reverseButton: true,
			    
			  }).then(result => {
				  if(result.isConfirmed) {
					  location.href='./cancelReservation.jsp?id='+tempId;
				  }
			  });
			}
	</script>	
</body>
</html>