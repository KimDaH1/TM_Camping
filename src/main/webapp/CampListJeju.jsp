<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    <!-- 사용하는 controller java쪽 클래스들 -->
<%@ page import="Controller.mainController" %>
<%@ page import="camping.dto.campzone" %>
<%@ page import="camping.dto.emp" %>
<%@ page import="java.util.*" %>
<%@ page import="camping.Utils.myUtil" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();// /ThreeMenCamping 여기까지 찍힘
	
	myUtil myutil = new myUtil();
	mainController maincontroller = new mainController();	
	//넘어온 페이지 번호를 get방식으로 넘기는것 같음
	String pageNum = request.getParameter("pageNum");
	
	//첫시작시 현재페이지 1
	int currentPage = 1;
	
	//넘어온 페이지 번호가 존재할 경우 현재페이지에 값 넣어주기
	if(pageNum != null){
		currentPage = Integer.parseInt(pageNum);
	}
	
	//검색키와 값	
	String searchValue = request.getParameter("searchValue");
	
	//검색어가 있을 경우
	if(searchValue != null){
		if(request.getMethod().equalsIgnoreCase("GET")){
			searchValue = URLDecoder.decode(searchValue,"UTF-8");
		}
	} else {//검색어가 없을 경우		
		searchValue = "";
	}
	
	
	// 전체데이터 갯수 구하기
	int dataCount = maincontroller.getDataTotalCountJeju(searchValue);
	
	// 한페이지에 표시할 데이터의 갯수
	int numPerPage = 10;
	
	// 전체 페이지수 구하기
	int totalPage = myutil.getPageCount(numPerPage, dataCount);
	
	// 전체 페이지수가 표시할 페이지수보다 큰 경우(삭제로 인해)
	if(currentPage > totalPage){
		currentPage = totalPage;
	}
	
	// 데이터베이스에서 가져올 rownum의 시작과 끝
	int start = (currentPage-1)*numPerPage+1;
	int end = currentPage*numPerPage;
	
	List<campzone> campzonelist = new ArrayList<campzone>();

	campzonelist = maincontroller.CampDBListsJeju(start, end, searchValue);
	//데이터베이스에서 해당 페이지를 가져온다
	//List<BoardDTO> lists = dao.getLists(start end, searchKey, searchValue);
	
	//검색(기능을 사용할 경우 get방식의 주소에 추가로 적용시켜주겠다. 사용자 정의)
	String param = "";
	if(!searchValue.equals("")){				
		param = "&" + URLEncoder.encode("searchValue", "UTF-8") + "=" + URLEncoder.encode(searchValue, "UTF-8");
	}
	
	
	//페이징 처리
	String listUrl = "CampListJeju.jsp"+param;
	String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);
	
	//글 내용 보기 주소 정리(html에서 onclick의 주소가 너무 길기 때문에 한번에 정리)
/* 	String articleUrl = cp + "/board/article.jsp";
	if(param.equals("")){
		articleUrl += "?pageNum="+currentPage;
	} else {
		aricleUrl += param + "&pageNum=" + currentPage;
	} */
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>게 시 판</title>
<style>
  @font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
*{
      font-family: 'omyu_pretty' ;
    }
	
</style>
<script type="text/javascript">
	function sendIt(){
		var f = document.searchForm;
		f.action = "<%=cp %>/CampListJeju.jsp"; 
		f.submit();
	}
	function clickTest(){
		var table =document.getElementById('testTable');
		  var rowList = table.rows;
		  
		  for (i=1; i<rowList.length; i++) {//thead부분 제외.
		    
		      var row = rowList[i]; //로우 수
		      var str = ""; 
		    
		      row.onclick = function(){ 
		          return function(){ 
		          
		          //개별적으로 값 가져오기		       
		          var number =this.cells[0].innerHTML; //사번		          
		          
		         str = number.trim(); //사번을 공백없이 변수에 담기
		         var link = 'http://localhost:8080/ThreeMenCamping/DetailJeju.jsp?data=' + str;
		         location.href=link; //페이지 이동		         
		       };
		    }(row);
		  }	
	}	
</script>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body style="background-image: url(./image/제주도배경.jpg);">
<style>
body{
background-size:cover;
}
   footer{
  position: relative;
  top: 80px;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  width: 100%;
  height: 310px;
  background-color: #383a3f;
    }
a{
    text-decoration-line:none;
    color:black;}
    
</style>
	

<div id="bbsList" style = "text-align: center;">
	<div id="bbsList_title">
	<h1><a href="http://localhost:8080/ThreeMenCamping/main.jsp"> TM_Camping </a></h1>
	<h2>제주도 캠핑장 목록</h2>
	</div>
	<div id="bbsList_header">
		<div id="leftHeader">
		<form action="" name="searchForm" method="post">
			<select name="searchKey" class="selectField">
				<!-- <option value="subject">제목</option> -->
				<option value="name">캠핑장명</option>
				<!-- <option value="content">내용</option> -->
			</select>
			<input type="text" name="searchValue" value="<%=searchValue %>" class="textField"/>
			<input type="button" value="검  색" class="btn2" onclick="sendIt()"/>
		</form>
		</div>
		<%-- <div id="rightHeader">
			<input type="button" value=" 글올리기 " class="btn2"
			onclick="javascript:location.href='<%=cp %>/board/created.jsp';" />
		</div> --%>
	</div>
	

	<table class = "table table-striped" id = "testTable" border="1" >
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
		<% for (int i = 0; i < campzonelist.size(); i++)
		{ %>
			<tr>
				<td class="empno" onClick="clickTest();" style="cursor:pointer;">
					<% out.print(campzonelist.get(i).getCpname()); %>
				</td>
				<td class="ename">
					<%out.print(campzonelist.get(i).getCpInduty()); %>
				</td>
				<td class="job">
					<%out.print(campzonelist.get(i).getLat()); %>
				</td>
				<td class="mgr">
					<%out.print(campzonelist.get(i).getLng()); %>
				</td>
				<td class="mgrName">
					<%out.print(campzonelist.get(i).getAddr()); %>
				</td>
				
			</tr>
			<%-- <dl>
				<dd class="empno" onClick="clickTest();" style="cursor:pointer;"><%=emplist.get(i).getEMPNO() %></dd>
				<dd class="ename">					
					<%=emplist.get(i).getENAME() %>					
				</dd>
				<dd class="job"><%=emplist.get(i).getJOB() %></dd>
				<dd class="mgr"><%=emplist.get(i).getMGR() %></dd>
				<dd class="mgrName"><%=emplist.get(i).getMgrName() %></dd>
			</dl> --%>
		<% } %> 
		</tbody>	
	</table>
		
		<div id="footer">
			<p>
				<%=pageIndexList %> 
                <!-- 마우스를 올려놨을때 주황색으로 바뀌도록 a:hover에 스타일 부여함  -->
			</p>
		</div>
	</div>
<div class="container mt-5">
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="row">
                    <div class="col"><img src="./image/제주1.jpg" class="d-block w-100" alt="image1" style="width:250px;height:200px;"></div>
                    <div class="col"><img src="./image/제주2.jpg" class="d-block w-100" alt="image2" style="width:250px;height:200px;"></div>
                    <div class="col"><img src="./image/제주3.jpg" class="d-block w-100" alt="image3" style="width:250px;height:200px;"></div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="row">
                    <div class="col"><img src="./image/제주4.jpg" class="d-block w-100" alt="image4" style="width:250px;height:200px;"></div>
                    <div class="col"><img src="./image/제주5.jpg" class="d-block w-100" alt="image5" style="width:250px;height:200px;"></div>
                    <div class="col"><img src="./image/제주6.jpg" class="d-block w-100" alt="image6" style="width:250px;height:200px;"></div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="row">
                    <div class="col"><img src="./image/제주7.jpg" class="d-block w-100" alt="image7" style="width:250px;height:200px;"></div>
                    <div class="col"><img src="./image/제주8.jpg" class="d-block w-100" alt="image8" style="width:250px;height:200px;"></div>
                    <div class="col"><img src="./image/제주9.jpg" class="d-block w-100" alt="image9" style="width:250px;height:200px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $('.carousel').carousel({
      interval: 1000
    });
</script>

<footer>
          <div>
				<img src="sns.PNG" alt="" style="border-radius: 15px; position: relative; ">
                <p>&nbsp; &nbsp; &nbsp;업체명 : TM_Camping</p>
                <p>&nbsp; &nbsp; &nbsp;대표자 : 7조 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;사업자등록번호 : 111-11-11111 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;주소 : 충청남도 천안시 동남구 대흥동 134 휴먼교육센터 8층 &nbsp;</p>
                <p>&nbsp; &nbsp; &nbsp;전화번호 : &nbsp;041-561-1122<br><span style="color: rgb(246, 179, 82);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(문의 시간 AM 9:00 ~ PM 8:00 / 점심시간 12:00~13:00)<br>
                </span>&nbsp; &nbsp; &nbsp;캠핑장 시설 안내 및 이용안내 문의만 부탁드립니다.</p>
          </div>
          <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&amp;q=천안시+동남구+휴먼+교육센터" style="width: 400px; height: 250px;"></iframe>
					<div>
						<span style="padding: 20px;">이용약관</span><span style="padding: 20px;">개인정보처리방침</span><span style="padding: 20px;">새로운 메뉴</span>
          </div>
        </footer>
	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


</body>
</html>