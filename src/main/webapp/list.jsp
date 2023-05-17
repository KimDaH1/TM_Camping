<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="camping.oracle.DBConnectionManager"%>

<%-- <%@page import="com.util.DBCPConn"%> --%>

<%@page import="java.net.URLEncoder"%>

<%@page import="java.net.URLDecoder"%>

<%@page import="camping.Utils.myUtil"%>

<%@page import="camping.dto.BoardDTO"%>

<%@page import="java.util.List"%>

<%@page import="camping.dao.BoardDAO"%>

<%-- <%@page import="com.util.DBConn"%> --%>

<%@page import="java.sql.Connection"%>


<%

	request.setCharacterEncoding("UTF-8");

	String cp = request.getContextPath();

	

	Connection conn = DBConnectionManager.getConnection();

	BoardDAO dao = new BoardDAO(conn);

	myUtil myutil = new myUtil();

	

	//넘어온 페이지 번호(MyUtil,article,update,delete)

	String pageNum = request.getParameter("pageNum");

	

	//첫시작시 현재페이지 1

	int currentPage = 1;

	

	//넘어온 페이지 번호가 존재할 경우 현재페이지에 값 넣어주기

	if(pageNum!=null)

		currentPage = Integer.parseInt(pageNum);

	

	//검색키와 값

	String searchKey = request.getParameter("searchKey");

	String searchValue = request.getParameter("searchValue");

	

	//검색어가 있을 경우

	if(searchValue!=null){

		if(request.getMethod().equalsIgnoreCase("GET")){

			searchValue = URLDecoder.decode(searchValue,"UTF-8");

		}

	

	}else{//검색어가 없을 경우

		searchKey="subject";

		searchValue = "";

	}

	

	//전체데이터 갯수 구하기

	int dataCount = dao.getDataCount(searchKey, searchValue);

	

	//한페이지에 표시할 데이터의 갯수

	int numPerPage = 5;

	

	//전체 페이지수 구하기

	int totalPage = myutil.getPageCount(numPerPage, dataCount);

	

	//전체페이지수가 표시할 페이지수보다 큰 경우(삭제로 인해)

	if(currentPage>totalPage)

		currentPage = totalPage;



	//데이터베이스에서 가져올 rownum의 시작과 끝

	int start = (currentPage-1)*numPerPage+1;

	int end = currentPage*numPerPage;

	

	//데이터베이스에서 해당 페이지를 가져온다.

	List<BoardDTO> lists = dao.getLists(start, end, searchKey, searchValue);

	

	//검색(기능을 사용할 경우 get방식의 주소에 추가로 적용시켜주겠다. 사용자정의)

	String param = "";

	if(!searchValue.equals("")){

		param = "?searchKey="+searchKey;

		param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");

	}

	

	//페이징 처리

	String listUrl = "list.jsp"+param;

	String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);

	

	//글내용 보기 주소 정리(html에서 onclick의 주소가 너무 기니까 한번에 정리)

	String articleUrl = cp+"/article.jsp";

	if(param.equals("")){

		articleUrl += "?pageNum="+currentPage;

		//article.jsp?pageNum=5

	}else{

		articleUrl += param + "&pageNum="+currentPage;

		//article.jsp?searchKey=name&searchValue=suzi&pageNum=5

	}

	conn.close();

%>

<!DOCTYPE html >

<html>

<head>

<meta charset="UTF-8">

<title>게 시 판</title>

<link rel="stylesheet" href="<%=cp %>/board/css/style.css" type="text/css" />

<link rel="stylesheet" href="<%=cp %>/board/css/list.css" type="text/css" />

<script type="text/javascript">

	function sendIt(){

		var f = document.searchForm;

		f.action = "<%=cp %>/list.jsp";  검색버튼을 누를경우 리스트페이지로 이동

		f.submit();

	}

</script>

</head>

<body>

<div id="bbsList">

	<div id="bbsList_title">

	게 시 판(JSP)

	</div>

	<div id="bbsList_header">

		<div id="leftHeader">

		<form action="" name="searchForm" method="post">

			<select name="searchKey" class="selectField">

				<option value="subject">제목</option>

				<option value="name">작성자</option>

				<option value="content">내용</option>

			</select>

			<input type="text" name="searchValue" value="<%=searchValue %>" class="textField"/>

			<input type="button" value="검  색" class="btn2" onclick="sendIt()"/>

		</form>

		</div>

		<div id="rightHeader">

			<input type="button" value=" 글올리기 " class="btn2"

			onclick="javascript:location.href='<%=cp %>/created.jsp';" />

		</div>

	</div>

	

	<div id="bbsList_list">

		<div id="title">

			<dl>

				<dt class="num">번호</dt>

				<dt class="subject">제목</dt>

				<dt class="name">작성자</dt>

				<dt class="created">작성일</dt>

				<dt class="hitCount">조회수</dt>

			</dl>

		</div>

		

		<div id="lists">

		<% for(BoardDTO dto: lists){ %>

			<dl>

				<dd class="num"><%=dto.getNum() %></dd>

				<dd class="subject">

					<a href="<%=articleUrl %>&num=<%=dto.getNum() %>">

					<%=dto.getSubject() %>

					</a>

				</dd>

				<dd class="name"><%=dto.getName() %></dd>

				<dd class="created"><%=dto.getCreated() %></dd>

				<dd class="hitCount"><%=dto.getHitCount() %></dd>

			</dl>

		<% } %>

		</div>

		

		<div id="footer">

			<p>

				<%=pageIndexList %> 

                                <!-- 마우스를 올려놨을때 주황색으로 바뀌도록 a:hover에 스타일 부여함  -->

			</p>

		</div>

	</div>

</div>

</body>

</html>