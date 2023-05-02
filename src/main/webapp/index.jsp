<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sample.dao.PersonDao" %>
<%@ page import="sample.dto.PersonDto" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> 7조 </h1>
	
<%-- 	<% //자바코드 --%>
<!-- // 		boolean flag = true; -->
<!-- // 		out.println("Test"); -->
<!-- // 		if(flag){ -->
<!-- // 			out.println("flag true Test"); -->
<!-- // 		} else { -->
<!-- // 			out.println("flag false Test"); -->
<!-- // 		} -->
		
<!-- // 		for(int i=0; i<10; i++) { -->
<!-- // 			out.println("반복문" + i + " <br>"); -->
<!-- // 		} -->
		
<!-- // 		for(int i=0; i<10; i++){ -->
<%-- 	%>	 --%>
<%-- 		<p>ptag 글자 <%=i%>  </p> --%>
<%-- 	<% --%>
<!-- // 		} -->
<%-- 	%> --%>
	
	
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
			</tr>
		</thead>
		<tbody>
	<%
		PersonDao personDao = new PersonDao();
		List<PersonDto> personList = personDao.selectPersonInfoList();
		
		for(PersonDto item : personList){
	%>
			<tr>
				<td><%=item.getId()%></td>
				<td><a href="./personInfoDetail.jsp?id=<%=item.getId()%>" class="btn btn-secondary"><%=item.getName()%></a></td>
			</tr>
	<%
		}
	%>
		</tbody>	
	</table>
	<button id='test'>test</button>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script>
		document.querySelector('#test').addEventListener('click', ()=>{alert('test')})
		
// 		if(true){
<%-- 			<% personList.get(1) %>	 --%>
// 		}
		
	</script>
</body>
</html>













