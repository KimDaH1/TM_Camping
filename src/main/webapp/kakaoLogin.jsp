<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%
request.setCharacterEncoding("UTF-8");
String kakaoNickname = request.getParameter("kakaoNickname");
session.setAttribute("userId", kakaoNickname);
response.sendRedirect("main.jsp");
%>
