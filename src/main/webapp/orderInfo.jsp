<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="camping.dao.OrderDao" %>
<%@ page import="camping.dto.OrderDto" %>
<%@ page import="camping.Utils.MyDateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>


<%@ include file="header2.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();// /ThreeMenCamping 여기까지 찍힘

OrderDao orderDao = new OrderDao();

String userId = (String)session.getAttribute("userId");
String campName = null;

DecimalFormat decFormat = new DecimalFormat("###,###");

List<OrderDto> orderList;
orderList = new ArrayList<OrderDto>();
orderList = orderDao.getOrderList(userId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 리스트 페이지</title>
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
삼남자 캠핑 결제 리스트 페이지</h1>

	<div id="bbsList">
		
		<table class="table table-striped" id="testTable" border="1">
			<thead>
				<tr>
					<th>결제번호</th>
					<th>주문번호</th>
					<th>캠핑장명</th>
					<th>결제시간</th>
					<th>결제금액</th>
					<!-- <th>결제하기</th> -->
					<th>결제취소</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < orderList.size(); i++) {
				%>
				<tr>
					<td id="o_id<%=i%>" style="cursor: pointer;">
						<%
						out.print(orderList.get(i).getPay_no());
						%>
					</td>
					<td class="o_number">
						<%
						out.print(orderList.get(i).getOrder_no());
						%>
					</td>
					<td class="empno">
						<%
							campName = orderDao.getCampName(orderList.get(i).getR_number());
							out.print(campName);
						%>
						<%-- <%
						out.print(orderList.get(i).getC_id());
						%> --%>
					</td>
					<td class="order_date">
						<%
						out.print(orderList.get(i).getOrder_date());
						//out.print( MyDateUtil.convertStringToUtilDate(orderList.get(i).getOrder_date()) );
						%>
					</td>
					<td class="mgr">
						<%
						out.print( decFormat.format(orderList.get(i).getAmount()) );
						%>
					</td>
					<td class="cancel">
						<%-- <button id="cancel<%=i %>" class="btn btn-danger cancelBtn">취소</button> --%>
						<button id="<%=orderList.get(i).getPay_no() %>" class="btn btn-danger cancelBtn">취소</button>
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
			    title: '결제를 취소하시겠습니까?',
			    text: '확인버튼 : 결제 취소하기',
			    icon: 'warning',
			    showCancelButton: true,
			    confirmButtonText: '확인',
			    cancelButtonText: '취소',
			    
			    reverseButton: true,
			    
			  }).then(result => {
				  if(result.isConfirmed) {
					  location.href='./cancelOrder_proc.jsp?id='+tempId;
				  }
			  });
			}
	</script>
</body>
</html>