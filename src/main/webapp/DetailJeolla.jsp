<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- 사용하는 controller java쪽 클래스들 -->
<%@ page import="camping.dao.PersonDao" %>
<%@ page import="camping.dto.PersonDto" %>
<%@ page import="Controller.mainController" %>

<%@ page import="Controller.ChongchungmainController" %>

<%@ page import="camping.dto.campzone" %>
<%@ page import="camping.dto.emp" %>
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
   footer{
  position: relative;
  float: bottom;
  top: 315px;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  width: 100%;
  height: 310px;
  background-color: #383a3f;
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

<script>
	 $(document).ready(function () {
		 
         $('.orderBtn').click(function(){
        	 
             let tempId = $(this).attr('id');
             var link = 'http://localhost:8080/ThreeMenCamping/reservation.jsp?id=' +tempId;
             location.href=link; //페이지 이동
         });
  
     });
	 </script>

<footer>
          <div>
				<img src="sns.PNG" alt="" style="border-radius: 15px; position: relative; ">
                <p>&nbsp; &nbsp; &nbsp;업체명 : TM_Camping</p>
                <p>&nbsp; &nbsp; &nbsp;대표자 : 7조 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;사업자등록번호 : 111-11-11111 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;주소 : 충청남도 천안시 동남구 대흥동 134 휴먼교육센터 8층 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;전화번호 : &nbsp;041-561-1122<br><span style="color: rgb(246, 179, 82);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(문의 시간 AM 9:00 ~ PM 8:00 / 점심시간 12:00~13:00)<br>
                </span>&nbsp; &nbsp; &nbsp;캠핑장 시설 안내 및 이용안내 문의만 부탁드립니다.</p>
          </div>
          <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&amp;q=천안시+동남구+휴먼+교육센터" style="width: 400px; height: 250px;"></iframe>
					<div>
						<span style="padding: 20px;">이용약관</span><span style="padding: 20px;">개인정보처리방침</span><span style="padding: 20px;">새로운 메뉴</span>
          </div>
        </footer>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


</body>
</html>