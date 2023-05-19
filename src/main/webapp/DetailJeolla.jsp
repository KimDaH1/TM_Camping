<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- 사용하는 controller java쪽 클래스들 -->
<%@ page import="Controller.mainController" %>

<%@ page import="camping.dto.campzone" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
 @font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
*{
      font-family: 'omyu_pretty' ;
    }
body{
background-size:cover;
}
 
    a{
    text-decoration-line:none;color:black;}
</style>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- jQuery -->
	 <script
  src="https://code.jquery.com/jquery-3.7.0.js"
  integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
  crossorigin="anonymous"></script>

</head>
<body>
<body style="background-image: url(./image/전라도배경.jpg);">
<br><br><br>
<div id="bbsList" style = "text-align: center;">
<%@ include file = "header2.jsp" %>
	<div id="bbsList_title">
	<h1><a href="http://localhost:8080/ThreeMenCamping/main.jsp" style="text-decoration-line:none;color:black;"> TM_Camping </a></h1>

	</div>
	<table class = "table table-striped" border="1">
			<tr>
<!-- 				<td>
					고유번호 cpname
				</td> -->
				<td>
					야영장명 <!-- cpname -->
				</td>
				<td>
					인트로 <!-- cpLineIntro-->
				</td>
				<td>
					입지구분 <!-- cpLctCl -->
				</td>
				<td>
					주소 <!-- addr -->
				</td>
				<td>
					주변이용가능시설 <!-- cpPosblFcltyCl -->
				</td>
				<td>
					전화번호 <!-- cptel -->
				</td>
				<td>
					홈페이지 <!-- cpHomepage -->
				</td>
				<td>
					애완동물출입 <!-- cpAnimalCmgCl -->
				</td>
				<td>
					캠핑장 형태 <!-- cpInduty -->
				</td>
				<td>
					예약하기
				</td>
	<%
	 	request.setCharacterEncoding("UTF-8");
    	String data = request.getParameter("data");
		String aa = "";
		mainController mains = new mainController();
		 aa = mains.TestingApiChongchung(request);
		campzone campzonelist = new campzone();
		//하이퍼 링크를 통하여 넘어온 값을 저장하여 활용.
		campzonelist = mains.ChongchungDBDetail(data);
				
	%>
	<h2><%out.print(campzonelist.getCpname()); %></h2>
			<tr>
<%-- 				<td>
					<%out.print(campzonelist.getIdx()); %>
				</td> --%>
				<td>
					<%out.print(campzonelist.getCpname()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCpIntro()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCpLctCl()); %>
				</td>
				<td>
					<%out.print(campzonelist.getAddr()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCpPosblFcltyCl()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCptel()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCpHomepage()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCpAnimalCmgCl()); %>
				</td>
				<td>
					<%out.print(campzonelist.getCpInduty()); %>
				</td>
				<td>
					<button id="<%=campzonelist.getIdx() %>" class="btn btn-primary orderBtn">예약하기</button>
				</td>
			</tr>	
	</table>
</div>

<!-- 지도를 담을 영역 만들기 500*400-->
<div style="display: flex; justify-content: center; align-items: center;">
<div id="map" style="width:500px;height:400px;">지도</div>
<img id="map" style="width:500px;height:400px;" src="<%=campzonelist.getCpFirstImageUrl() %>" alt="대표이미지">
</div>


<!-- 실제 지도를 그리는 javascript API를 불러오기-->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=246e66fed598ccee26be58e6e7e2bf5c"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(<%= campzonelist.getLng()%>, <%= campzonelist.getLat() %>),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>

<script>
	 $(document).ready(function () {
		 
         $('.orderBtn').click(function(){
        	 
             let tempId = $(this).attr('id');
             var link = 'http://localhost:8080/ThreeMenCamping/reservation.jsp?id=' +tempId;
             location.href=link; //페이지 이동
         });
  
     });
	 </script>
<%@ include file = "footer2.jsp" %>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


</body>
</html>