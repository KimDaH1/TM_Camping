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
					���
				</td>
				<td>
					�����
				</td>
				<td>
					��å
				</td>
				<td>
					�����ID
				</td>
				<td>
					����ڸ�
				</td>
				<td>
					�Ի���
				</td>
				<td>
					����
				</td>
				<td>
					�μ�Ƽ��
				</td>
				<td>
					�μ���
				</td>
			</tr>
	<%
	 	request.setCharacterEncoding("UTF-8");
    	String data = request.getParameter("data");
		String aa = "";
		mainController mains = new mainController();
		// aa = mains.TestingApiTwo();
		emp emplist = new emp();
		//������ ��ũ�� ���Ͽ� �Ѿ�� ���� �����Ͽ� Ȱ��.
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
	<h1> 7�� </h1>
	<javaScript>
	
	

	</javaScript>
	
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>