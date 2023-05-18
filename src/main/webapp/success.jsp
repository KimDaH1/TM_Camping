<%@page import="camping.dto.ReservationDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.Base64.Encoder"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="camping.dao.OrderDao"%>
<%@ page import="camping.dao.ReservationDao"%>
<%@ page import="camping.Utils.MyDateUtil" %>
<%@ page import="java.text.DecimalFormat" %>


<%@ include file = "header2.jsp" %>
<%
	DecimalFormat decFormat = new DecimalFormat("###,###");
 // 결제 승인 API 호출하기
 
  int r_number = Integer.parseInt(request.getParameter("id"));

	ReservationDao reservationDao = new ReservationDao();
	ReservationDto reservationDto = reservationDao.getDates(r_number);
	String s_date = reservationDto.getS_date();
	String e_date = reservationDto.getE_date();
	
	String userName = reservationDao.getUserName(r_number);
	
	

  String userId = (String)session.getAttribute("userId");
  String orderId = request.getParameter("orderId");
  String paymentKey = request.getParameter("paymentKey");
  //String amount = request.getParameter("amount");  
  int amount = Integer.parseInt(request.getParameter("amount"));  
  //String customerName = request.getParameter("customerName");
  String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:";
  
  Encoder encoder = Base64.getEncoder();
  byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
  String authorizations = "Basic "+ new String(encodedBytes, 0, encodedBytes.length);
  paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8);
  
  URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
  
  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  connection.setRequestProperty("Authorization", authorizations);
  connection.setRequestProperty("Content-Type", "application/json");
  connection.setRequestMethod("POST");
  connection.setDoOutput(true);
  JSONObject obj = new JSONObject();
  obj.put("paymentKey", paymentKey);
  obj.put("orderId", orderId);
  obj.put("amount", amount);
  
  
  OutputStream outputStream = connection.getOutputStream();
  outputStream.write(obj.toString().getBytes("UTF-8"));
  
  int code = connection.getResponseCode();
  boolean isSuccess = code == 200 ? true : false;
  
  InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();
  
  Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
  JSONParser parser = new JSONParser();
  JSONObject jsonObject = (JSONObject) parser.parse(reader);
  responseStream.close();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>결제 확인 페이지</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
    <link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
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
</head>
<body class="bg-light">
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>

			<div class="col-md-6">

				<h2
					style="margin-top: 10px; padding: 20px 0px 10px 0px; text-align: center;">
					<img width="45px"
						src="https://static.toss.im/3d-emojis/u1F60E-apng.png"> <img
						width="45px"
						src="https://static.toss.im/3d-emojis/u1F60E-apng.png"> <img
						width="45px"
						src="https://static.toss.im/3d-emojis/u1F60E-apng.png">
						<br>
						삼남자 캠핑결제 확인 페이지
				</h2>				
					<div id="requestPayment_form" class="box_section"
						style="padding: 40px 30px 50px 30px; margin-top: 30px; margin-bottom: 50px">

						<div class="mb2">
							<label class="form-label" for="amount">캠핑장명 <span
								class="gray">(campName) </span></label> <input id="orderId"
								class="form-control form-control-lg" type="text" value="<%= jsonObject.get("orderName") %>"
								readonly="readonly">
						</div>
						<div class="mb2">
							<label class="form-label" for="amount">시작일 <span
								class="gray">(s_date) </span></label> <input id="sdate"
								class="form-control form-control-lg" type="text"
								name="sdate" readonly="readonly" value="<%= MyDateUtil.convertStringToUtilDate(s_date)%>">
						</div>
						<div class="mb2">
							<label class="form-label" for="amount">종료일 <span
								class="gray">(e_date) </span></label> <input id="edate"
								class="form-control form-control-lg" type="text"
								name="edate" readonly="readonly" value="<%= MyDateUtil.convertStringToUtilDate(e_date)%>">
						</div>
						<div class="mb2">
							<label class="form-label" for="amount">결제 금액 <span
								class="gray">(amount) </span></label> <input id="amount"
								class="form-control form-control-lg" type="text"
								value="<%= decFormat.format(jsonObject.get("totalAmount")) %>" readonly="readonly">
						</div>

						<div class="mb2">
							<label class="form-label" for="amount">결제자명 <span
								class="gray">(customerName)</span></label> <input id="customerName"
								class="form-control form-control-lg" type="text" value="<%=userName %>" readonly="readonly">
						</div>
						<div class="mb2">
							<label class="form-label" for="amount">주문번호 <span
								class="gray">(order_no)</span></label> <input id="customerName"
								class="form-control form-control-lg" type="text" value="<%= jsonObject.get("orderId") %>" readonly="readonly">
						</div>
						<div class="mb2">
							<label class="form-label" for="amount">결제 방식 <span
								class="gray">(payType)</span></label> <input id="customerName"
								class="form-control form-control-lg" type="text" value="<%= jsonObject.get("method") %>" readonly="readonly">
						</div>

						<div class="d-grid gap-2">
							<button id="confirmBtn" type="button"
								class="btn btn-lg btn-primary">확인</button>
						</div>

					</div>
					<div class="col-md-3"></div>
			</div>
		</div>
	</div>
<script>
		document.getElementById('confirmBtn').addEventListener('click', ()=>{			
			<%
				OrderDao orderDao = new OrderDao();
				String orderName = jsonObject.get("orderName").toString();
				String method = jsonObject.get("method").toString();
				//int r_number = Integer.parseInt(request.getParameter("id"));
				String o_state = "결제됨";
				
				orderDao.insertOrderInfo(orderId, amount, method, userId, r_number, o_state);
			%>
			location.href='./orderInfo.jsp';
		});
	</script>
<%@ include file = "footer.jsp" %>
</body>
</html>