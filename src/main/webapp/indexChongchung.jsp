<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
    <!-- 사용하는 controller java쪽 클래스들 -->
<%@ page import="camping.dao.PersonDao" %>
<%@ page import="camping.dto.PersonDto" %>
<%@ page import="Controller.mainController" %>
<%@ page import="camping.dto.campzone" %>

	<!-- 게시판 구현을 위한 test -->
<%@ page import="camping.dto.emp" %>

<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>충청도 캠핑장 메인페이지</title>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<h1> 7조 Three Men Camping (충청도 캠핑장 테이블) </h1>

	
	<table class = "table table-striped" id = "testTable1" border="1" >
	<thead>
		<tr>
			<th>캠핑장명</th>
			<th>형태</th>
			<th>위도</th>
			<th>경도	</th>
			<th>주소</th>			
		</tr>
	</thead>		
	<tbody>	
	
	<%
		String aa = "";
		mainController mains = new mainController();
	
		List<campzone> campzonelist = new ArrayList<campzone>();
		campzonelist = mains.TestChongchungDB(0);
		out.print("리스트 확인 : "+campzonelist.size());
		for (int i = 0; i < campzonelist.size(); i++){
	%>
	
			<tr>
				<td onClick="clickTest1();" style="cursor:pointer;">
					<% out.print(campzonelist.get(i).getCpname()); %></td>
				<td><%out.print(campzonelist.get(i).getCpInduty()); %></td>
				<td><%out.print(campzonelist.get(i).getLat()); %></td>
				<td><%out.print(campzonelist.get(i).getLng()); %></td>
				<td><%out.print(campzonelist.get(i).getAddr()); %></td>	
			</tr>			
	<%
		}
	%>

	<script>
	function clickTest1(){//캠핑 상세페이지 이동
		var table =document.getElementById('testTable1');
		  var rowList = table.rows;
		  
		  for (i=1; i<rowList.length; i++) {//thead부분 제외.
		    
		      var row = rowList[i]; //로우 수
		      var str = ""; 
		    
		      row.onclick = function(){ 
		          return function(){ 
		          
		          //개별적으로 값 가져오기		       
		          var number =this.cells[0].innerHTML; //사번		          
		          
		         str = number.trim(); //사번을 공백없이 변수에 담기
		         var link = 'http://localhost:8080/ThreeMenCamping/campDetail.jsp?data=' + str;
		         location.href=link; //페이지 이동		         
		       };
		    }(row);
		  }	
	}		
	</script>
	
	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>