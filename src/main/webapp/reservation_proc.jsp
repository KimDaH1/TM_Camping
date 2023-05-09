<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="camping.dao.ReservationDao"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@2.6.14/dist/vue.js"></script>
<link href="https://unpkg.com/v-calendar@2.4.0/lib/v-calendar.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="app">
		<v-calendar></v-calendar>
	</div>

	<script>
	    new Vue({
	      el: '#app'
	    });
	  </script>
	<%
		request.setCharacterEncoding("UTF-8"); //한글 정상 인식을 위해 써준다.
		String sDate = request.getParameter("sdate");
		String eDate = request.getParameter("edate");
	
		ReservationDao reservationDao = new ReservationDao();
		int result = reservationDao.insertReservationInfo(sDate, eDate);
	
		// id, 이름, 주소, 번호, 취미 설정
		/* PersonDto personDto = new PersonDto();
		personDto.setId(id);
		personDto.setName(name);
		int result2 = personDao.updatePersonInfo(personDto); //객체단위로 넘기기 */
	
		if (result == 1) {
			//추가성공
	%>

	<script>
		alert('예약성공');
	</script>
	<%
		} else {
	%>
	<script>
			alert('예약실패..');
	</script>
	<%
		}
	%>
	<!-- calendar test -->
	<noscript>
		<strong>We're sorry but v-calendar doesn't work
			properly without JavaScript enabled. Please enable it to continue.</strong>
	</noscript>
	<div id="app">
		<section>
			<p>This uses the built version of the plugin</p>
		</section>
		<section>
			<div class="selectors">
				<label for="weeks"> <input id="weeks" type="checkbox"
					v-model="weeks" /> Show week numbers
				</label>
			</div>
			<v-calendar :show-weeknumbers="weeks" :key="weeks" />
		</section>
		<section>
			<div class="selectors">
				<label for="single"> <input id="single" type="radio"
					value="single" v-model="mode" /> Single
				</label> <label for="multiple"> <input id="multiple" type="radio"
					value="multiple" v-model="mode" /> Multiple
				</label> <label for="range"> <input id="range" type="radio"
					value="range" v-model="mode" /> Range
				</label>
			</div>
			<v-date-picker :mode="mode" v-model="date" />
		</section>
	</div>
	<!-- built files will be auto injected -->
	<script type="text/javascript">
    var app = new Vue({
      el: '#app',
      data() {
        return {
          mode: 'single',
          weeks: false,
          date: new Date()
        }
      }
    })
    </script>

</body>
</html>