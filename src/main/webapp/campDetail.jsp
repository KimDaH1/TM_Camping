<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    <!-- ����ϴ� controller java�� Ŭ������ -->
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
	<table class = "table table-striped" border="1">
			<tr>
				<td>
					�߿���� <!-- cpname -->
				</td>
				<td>
					��Ʈ�� <!-- cpLineIntro-->
				</td>
				<td>
					�������� <!-- cpLctCl -->
				</td>
				<td>
					�ּ� <!-- addr -->
				</td>
				<td>
					�ֺ��̿밡�ɽü� <!-- cpPosblFcltyCl -->
				</td>
				<td>
					��ȭ��ȣ <!-- cptel -->
				</td>
				<td>
					Ȩ������ <!-- cpHomepage -->
				</td>
				<td>
					�ֿϵ������� <!-- cpAnimalCmgCl -->
				</td>
				<td>
					ķ���� ���� <!-- cpInduty -->
				</td>
			</tr>
	<%
	 	request.setCharacterEncoding("UTF-8");
    	String data = request.getParameter("data");
		String aa = "";
		mainController mains = new mainController();
		// aa = mains.TestingApiTwo();
		campzone campzonelist = new campzone();
		//������ ��ũ�� ���Ͽ� �Ѿ�� ���� �����Ͽ� Ȱ��.
		campzonelist = mains.CampDBDetail(data);
				
	%>
			<tr>
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
			</tr>	
	</table>
	<h1> 7�� </h1>
	<javaScript>
	
	

	</javaScript>
	
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>