<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.sql.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    String kakaoNickname = request.getParameter("kakaoNickname");

    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        // Load the Oracle JDBC driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        // Connect to the database
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        
        // Insert the nickname into the USERS table
        String sql = "INSERT INTO KAKAO_USERS (NICKNAME) VALUES (?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, kakaoNickname);
        pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close the database resources
        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    }

    // Store the nickname in the session and redirect to main.jsp
    session.setAttribute("userId", kakaoNickname);
    response.sendRedirect("main.jsp");
%>