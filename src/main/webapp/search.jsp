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
<title>상세 검색 페이지</title>
</head>
<body>
	
	<h1>상세 검색 페이지</h1>
	
<!-- 	<h3>시군구 기준 날씨</h3>
	<h3>시군구 내 관광지 리스트</h3> -->
	
	
	<table class="table">
		<thead>
			<tr>
			<th>캠핑장명</th>
			<th>전화번호</th>
			<th>위도</th>
			<th>경도	</th>
			<th>주소</th>	
			</tr>
		</thead>
		<tbody>
	<%
			request.setCharacterEncoding("UTF-8");
			
/* 		    String cpname = request.getParameter("cpname");
		    String cptel = request.getParameter("cptel");
		    //Double lat = request.getParameter("lat");
		    //Double lng = request.getParameter("lng");
		    Double lat = Double.parseDouble(request.getParameter("lat"));
			Double lng = Double.parseDouble(request.getParameter("lng"));
		    String addr = request.getParameter("addr"); */
		    

			String cpname = request.getParameter("cpname");
			String cptel = request.getParameter("cptel");
			Double lat = null;
			Double lng = null;
			String addr = request.getParameter("addr");
			
			if (request.getParameter("lat") != null) {
			    try {
			        lat = Double.parseDouble(request.getParameter("lat"));
			    } catch (NumberFormatException e) {
			        // lat 파라미터 값이 숫자가 아닌 경우 처리
			    }
			}
			
			if (request.getParameter("lng") != null) {
			    try {
			        lng = Double.parseDouble(request.getParameter("lng"));
			    } catch (NumberFormatException e) {
			        // lng 파라미터 값이 숫자가 아닌 경우 처리
			    }
			}
		    
		    campzone campzons = new campzone();
		    
		    campzons.setCpname(cpname);
		    campzons.setcptel(cptel);
		    campzons.setLat(lat);
		    campzons.setLng(lng);
		    campzons.setAddr(addr);
		    
		    mainController mains = new mainController();
		    
		    List<campzone> campzonelist = mains.selectSearchInfoList(cpname, cptel, lat, lng, addr);
		
		for(campzone item : campzonelist){
	%>
			<tr>
				<td onClick="clickTest1();" style="cursor:pointer;">
					<%=item.getCpname()%></td>
				<td><%=item.getCptel()%></td>
				<td><%=item.getLat()%></td>
				<td><%=item.getLng()%></td>
				<td><%=item.getAddr()%></td>	
			</tr>
	<%
		}
	%>
		</tbody>	
	</table>
    
</body>
</html>