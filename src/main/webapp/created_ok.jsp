<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="camping.oracle.DBConnectionManager"%>

<%@page import="camping.dao.BoardDAO"%>

<%-- <%@page import="com.util.DBConn"%> --%>
<%@ page import="Controller.mainController" %>


<%@page import="java.sql.Connection"%>


<%

	request.setCharacterEncoding("UTF-8");

	String cp = request.getContextPath();

%>

<jsp:useBean id="dto" class="camping.dto.BoardDTO" scope="page"></jsp:useBean>

<jsp:setProperty property="*" name="dto"/>

<%

	Connection conn = DBConnectionManager.getConnection();

	BoardDAO dao = new BoardDAO(conn);

	

	int maxNum = dao.getMaxNum();

	dto.setNum(maxNum+1);

	dto.setIpAddr(request.getRemoteAddr());

	dao.insertData(dto);

	

	conn.close();

	response.sendRedirect("list.jsp");

%>