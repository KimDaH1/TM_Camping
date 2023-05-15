<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- 사용하는 controller java쪽 클래스들 -->
<%@ page import="camping.dao.PersonDao" %>
<%@ page import="camping.dto.PersonDto" %>
<%@ page import="Controller.mainController" %>
<%@ page import="camping.dto.campzone" %>
<%@ page import="camping.dto.emp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%@ include file = "header.jsp" %>
<br><br><br>
<div id="bbsList" style = "text-align: center;">
	<div id="bbsList_title">
	<h1> CampList (TM_CAMPINGZONE_1 campDetail test) </h1>
	<h2>공공데이터 게 시 판(JSP)</h2>
	</div>
	<table class = "table table-striped" border="1">
			<tr>
				<td>
					사번
				</td>
				<td>
					사원명
				</td>
				<td>
					직책
				</td>
				<td>
					담당자ID
				</td>
				<td>
					담당자명
				</td>
				<td>
					입사일
				</td>
				<td>
					연봉
				</td>
				<td>
					인센티브
				</td>
				<td>
					부서명
				</td>
			</tr>
	<%
	 	request.setCharacterEncoding("UTF-8");
    	String data = request.getParameter("data");
		String aa = "";
		mainController mains = new mainController();
		// aa = mains.TestingApiTwo();
		emp emplist = new emp();
		//하이퍼 링크를 통하여 넘어온 값을 저장하여 활용.
		emplist = mains.TestMariaDBDetail(data);
				
	%>
			<tr>
				<td>
					<%out.print(emplist.getEMPNO()); %>
				</td>
				<td>
					<%out.print(emplist.getENAME()); %>
				</td>
				<td>
					<%out.print(emplist.getJOB()); %>
				</td>
				<td>
					<%out.print(emplist.getMGR()); %>
				</td>
				<td>
					<%out.print(emplist.getMgrName()); %>
				</td>
				<td>
					<%out.print(emplist.getHIREDATE()); %>
				</td>
				<td>
					<%out.print(emplist.getSAL()); %>
				</td>
				<td>
					<%out.print(emplist.getCOMM()); %>
				</td>
				<td>
					<%out.print(emplist.getDName()); %>
				</td>
			</tr>	
	</table>
</div>
	<javaScript>
	
	

	</javaScript>
	
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<%@ include file = "footer.jsp" %>

</body>
</html>