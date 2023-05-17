<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="camping.oracle.DBConnectionManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.net.URLEncoder"%>

<%@page import="java.net.URLDecoder"%>

<%@page import="camping.dto.BoardDTO"%>

<%@page import="camping.dao.BoardDAO"%>

<%-- <%@page import="com.util.DBConn"%> --%>
<%@ page import="Controller.mainController" %>


<%@page import="java.sql.Connection"%>


<%

	request.setCharacterEncoding("UTF-8");

	String cp = request.getContextPath();	

	int num = Integer.parseInt(request.getParameter("num"));

	String pageNum = request.getParameter("pageNum");

	

	//검색키와 값

	String searchKey = request.getParameter("searchKey");

	String searchValue = request.getParameter("searchValue");

	

	if(searchValue!=null){

		if(request.getMethod().equalsIgnoreCase("GET")){

			searchValue = URLDecoder.decode(searchValue,"UTF-8");

		}

	}else{

		searchKey="subject";

		searchValue = "";

	}

	

	Connection conn = DBConnectionManager.getConnection();

	BoardDAO dao = new BoardDAO(conn);

	

	//조회수 증가

	dao.updateHitCount(num);

	

	//글가져오기

	BoardDTO dto = dao.getReadData(num);

	

	if(dto==null){

		response.sendRedirect("list.jsp");

	}

	

	//글 라인 수

	int lineSu = dto.getContent().split("\n").length;

	

	//글 내용의 엔터를 <br/>로 변경

	dto.setContent(dto.getContent().replaceAll("\n", "<br/>"));

	

	String param = "";

	if(!searchValue.equals("")){

		param = "&searchKey="+searchKey;

		param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");

	}

	

	conn.close();

	

%>

<!DOCTYPE html >

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>게 시 판</title>

<link rel="stylesheet" href="<%=cp %>/css/style.css" type="text/css" />

<link rel="stylesheet" href="<%=cp %>/css/article.css" type="text/css" />

</head>

<body>

<div id="bbs">

	<div id="bbs_title">

	게 시 판

	</div>

	<div id="bbsArticle">

		<div id="bbsArticle_header">

		<%=dto.getSubject() %>

		</div>

		<div class="bbsArticle_bottomLine">

			<dl>

				<dt>작성자</dt>

				<dd><%=dto.getName() %></dd>

				<dt>줄수</dt>

				<dd><%=lineSu %></dd>

			</dl>

		</div>	

		<div class="bbsArticle_bottomLine">

			<dl>

				<dt>등록일</dt>

				<dd><%=dto.getCreated() %></dd>

				<dt>조회수</dt>

				<dd><%=dto.getHitCount() %></dd>

			</dl>

		</div>	

		<div id="bbsArticle_content">

			<table width="600" border="0">

			<tr>

				<td style="padding: 20px 80px 20px 62px;" valign="top" height="200">

				<%=dto.getContent() %>

				</td>

			</tr>

			</table>

		</div>

	</div>

	<div class="bbsArticle_noLine" style="text-align: right;">

		from <%=dto.getIpAddr() %>

	</div>

	<div id="bbsArticle_footer">

		<div id="leftFooter">

			<input type="button" value=" 수정 " class="btn2" onclick="javascript:location.href='<%=cp %>/update.jsp?num=<%=dto.getNum() %>&pageNum=<%=pageNum %><%=param %>';" />

			<input type="button" value=" 삭제 " class="btn2" onclick="javascript:location.href='<%=cp %>/deleted_ok.jsp?num=<%=dto.getNum() %>&pageNum=<%=pageNum %><%=param %>';" />

		</div>

		<div id="rightFooter">

			<input type="button" value=" 리스트 " class="btn2" onclick="javascript:location.href='<%=cp %>/list.jsp?pageNum=<%=pageNum %><%=param %>';" />

		</div>

	</div>

</div>

</body>

</html>