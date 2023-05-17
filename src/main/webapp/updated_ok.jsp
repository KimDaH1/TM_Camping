<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="camping.oracle.DBConnectionManager"%>

<%@page import="java.net.URLEncoder"%>

<%@page import="java.net.URLDecoder"%>

<%@page import="camping.dao.BoardDAO"%>

<%-- <%@page import="com.util.DBConn"%> --%>
<%@ page import="Controller.mainController" %>


<%@page import="java.sql.Connection"%>

<%

	request.setCharacterEncoding("UTF-8");

	String cp = request.getContextPath();

%>

<jsp:useBean id="dto" class="camping.dto.BoardDTO" scope="page"/>

<jsp:setProperty property="*" name="dto"/>

<%

	String pageNum = request.getParameter("pageNum");

	Connection conn = DBConnectionManager.getConnection();

	BoardDAO dao = new BoardDAO(conn);

	dao.updateData(dto);

	

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

	String param = "";

	if(!searchValue.equals("")){

		param = "&searchKey="+searchKey;

		param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");

	}

	conn.close();

	response.sendRedirect("list.jsp?pageNum="+pageNum+param);

%>