package Controller;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//게시판 기능을 위한 추가
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;

//게시판 기능구현을 위한 emp dto 추가
import camping.dto.emp;
import camping.dto.campzoneJsonModel;
import camping.oracle.DBConnectionManager;

public class mainController {
	// 공공 api를 통하여 데이터를 불러오는 메소드 

	public String TestApi() {
		String strResult = "true";
		try {
			//문화관광부 고캠핑 api 키값
			//Encode : nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D
			//Decode : nHYvwaA5iMdJN+ZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5/8Ycv1UCLzedNfTfWQ/VoOtadIC9WpwTLtTnukPA==

			//참고url : https://apis.data.go.kr/B551011/GoCamping/searchList?serviceKey=nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=JSON&keyword=천안
			URL apiUrl = new URL("https://apis.data.go.kr/B551011/GoCamping/searchList"
					+ "?serviceKey=nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D"
					+ "&numOfRows=10"
					+ "&pageNo=1"
					+ "&MobileOS=ETC"
					+ "&MobileApp=AppTest"
					+ "&_type=JSON"
					+ "&keyword=천안");//import java.ner.url; 추가

			HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
			conn.setRequestMethod("GET");//import java.net.HttpURLConnection; 추가
			conn.setRequestProperty("Content-type", "application/json");

			conn.connect();

			//Gettin the response code
			int responsecode = conn.getResponseCode();
			System.out.println(responsecode);
			System.out.println(conn.getResponseMessage());
			System.out.println(responsecode);
			System.out.println(conn.getResponseMessage());
			System.out.println(responsecode);
			System.out.println(conn.getResponseMessage());
			System.out.println(responsecode);
			System.out.println(conn.getResponseMessage());

			if(responsecode != 200) {
				throw new RuntimeException("HttpResponseCode : " + responsecode);
			} else {
				//Json 가져오기
				//import java.io.BufferedReader; import java.io.InputStreamReader; 추가
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				StringBuilder sb = new StringBuilder();
				String line = "";
				System.out.println("Start = " + br.readLine());
				while ((line = br.readLine()) != null) {
					System.out.println("Inner = " + line);
					sb.append(line);
				}
				strResult = sb.toString();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}

	public String TestingApiTwo() throws IOException{
		String strResult = "";
		String cpname = "";
		String cptel = "";
		double lat;
		double lng;
		String addr = "";
		int iresult = 0;

		/*URL apiUrl = new URL("https://apis.data.go.kr/B551011/GoCamping/searchList"
				+ "?serviceKey=nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D"
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&_type=JSON"
				+ "&keyword=천안");*/
		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/GoCamping/searchList");
		// 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8")+ "=" + URLEncoder.encode("1","UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8")+ "=" + URLEncoder.encode("10","UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8")+ "=" + URLEncoder.encode("ETC","UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8")+ "=" + URLEncoder.encode("AppTest","UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8")+ "=" + URLEncoder.encode("json","UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8")+ "=" + URLEncoder.encode("천안","UTF-8"));
		// 3. URL 객체 생성
		URL url = new URL(urlBuilder.toString());

		// 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 5. 통신을 위한 메소드 SET
		conn.setRequestMethod("GET");

		// 6. 통신을 위한 Content-type SET
		conn.setRequestProperty("Content-type", "application/json");

		// 7. 통신 응답 코드 확인
		System.out.println("Response code : " + conn.getResponseCode());

		// 8. 전달받은 데이터를 BufferedReader 객체로 저장
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		// 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		// 10. 객체 해제
		rd.close();
		conn.disconnect();

		// 11. 전달받은 데이터 확인
		System.out.println(sb.toString());

		strResult = sb.toString();

		JSONParser jsonParser1 = new JSONParser();//import org.json.simple.parser.JSONParser; 추가
		JSONObject jsonObject1;//import org.json.simple.JSONObject; 추가
		try {
			System.out.println("3");
			jsonObject1 = (JSONObject) jsonParser1.parse(strResult);
			System.out.println("jsonObject1 = " + jsonObject1);

			// Gson의 역할 json통신된 json을 만들어놓은 dto모델에 담는다.
			campzoneJsonModel roots = new Gson().fromJson(jsonObject1.toString(), campzoneJsonModel.class);

			for(int i = 0; i<roots.response.body.items.item.size(); i++) {
				System.out.println(roots.response.body.items.item.get(i).addr1.toString());

				cpname = roots.response.body.items.item.get(i).facltNm.toString();
				cptel = roots.response.body.items.item.get(i).tel.toString();
				lat = roots.response.body.items.item.get(i).mapX;
				lng = roots.response.body.items.item.get(i).mapY;
				addr = roots.response.body.items.item.get(i).addr1.toString();
				//insert methods 만들어 놓고 호출

				//한줄씩 넣기 때문에 결과값 피드백은 1을 받게 되어 있음 //executeUpdate() 참고
				iresult = InsertCampDt(cpname, cptel, lat, lng, addr);
				if(iresult != 1) {
					break;
				}

			}

		} catch(Exception ex) {
			ex.printStackTrace();
		}

		System.out.println();

		return strResult;
	} 

	private int InsertCampDt(String cpname, String cptel, double lat, double lng, String addr) {
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;

		int result = 0;

		try {
			conn = DBConnectionManager.getConnection();

			//쿼리문
			String sql = "INSERT INTO TM_CAMPINGZONE (IDX, CPNAME, CPTEL, LAT, LNG, ADDR) VALUES ((SELECT NVL(MAX(idx)+1,1) FROM tm_campingzone), ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cpname);
			psmt.setString(2, cptel);
			psmt.setDouble(3, lat);
			psmt.setDouble(4, lng);
			psmt.setString(5, addr);

			result = psmt.executeUpdate();//추가시킨 low값 만큼 int return

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}
		System.out.println("처리결과 : " + result);
		return result;

	}

	// 테이블 생성 준비
	public List<emp> TestMariaDB(int empno) {
		List<emp> _emp = new ArrayList<emp>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		
		// 모델 담으라고? db 전직원 조회를 테이블 방식으로 뿌리고, 사원번호, 이름 연봉
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";

			sql = "SELECT a.EMPNO, a.ENAME, a.JOB, a.mgr, b.ename MgrName, a.HIREDATE, a.SAL, a.COMM, a.DEPTNO\r\n"
					+ "FROM EMP a left outer join EMP b\r\n"
					+ "ON a.MGR = b.empno";
			if(empno != 0) {
				sql += " where a.empno =" + empno;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();

			while (rs.next()) {
				emp semp = new emp();
				semp.setEMPNO(rs.getInt("empno"));
				semp.setENAME(rs.getString("ename"));
				
				//1. 조장을 조장님으로 바꾸고 사번번호 붙이기
				if(rs.getString("job").equals("조장")) {
					semp.setJOB("조장님" + rs.getString("empno")); 
				} else {
					semp.setJOB(rs.getString("job"));
				}

				//2. 1011(서지희) 1022(최희두) 7839(KING)만 이름으로 뿌려 스트링 반환 메소드 만들어서 풀기
				if(rs.getString("mgr") != null ) {
					semp.setMGR(chageMgr(rs.getString("mgr")));
				} else {
					semp.setMGR("0");
				}
				semp.setHIREDATE(rs.getString("hiredate"));
				semp.setSAL(rs.getInt("sal"));
				semp.setCOMM(rs.getInt("comm"));
				semp.setDEPTNO(rs.getInt("deptno"));
				semp.setMgrName(rs.getString("Mgrname"));

				//3. db에서 쿼리 변경 mgr을 이름으로...  db접속해서, db부화... mgr을 이름으로 바꿔서 뿌려

				_emp.add(semp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return _emp;	

	}

	//리스트 게시판용 쿼리 mariadb라서 강제로 rownum 생성.
	public List<emp> TestMariaDBLists(int start, int end, String strValue) {
		List<emp> _emp = new ArrayList<emp>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;

		// 모델 담으라고? db 전직원 조회를 테이블 방식으로 뿌리고, 사원번호, 이름 연봉
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			strValue = "%" + strValue + "%";

			//			 sql = "SELECT * FROM \r\n"
			//			 		+ "(\r\n"
			//			 		+ "	SELECT  ROWNUM AS rnum,\r\n"
			//			 		+ "			a.EMPNO, 		 a.ENAME, 	 a.JOB, a.mgr, \r\n"
			//			 		+ "			b.ename MgrName, a.HIREDATE, a.SAL, a.COMM, \r\n"
			//			 		+ "			a.DEPTNO\r\n"
			//			 		+ "	FROM    EMP a \r\n"
			//			 		+ "	inner join EMP b\r\n"
			//			 		+ "	INNER JOIN (SELECT rownum:=0) R\r\n"
			//			 		+ "	ON 	  a.MGR = b.empno\r\n"
			//			 		+ " WHERE a.ename like ? \r\n "
			//			 		+ ") TB\r\n"
			//			 		+ "WHERE rnum >= ? and rnum <= ? ";
			sql = "SELECT * \r\n"
					+ "FROM (SELECT ROWNUM RNUM\r\n"
					+ "            , a.EMPNO\r\n"
					+ "            , a.ENAME\r\n"
					+ "            , a.JOB\r\n"
					+ "            , a.mgr\r\n"
					+ "            , b.ename MgrName\r\n"
					+ "            , a.HIREDATE\r\n"
					+ "            , a.SAL\r\n"
					+ "            , a.COMM\r\n"
					+ "            , a.DEPTNO\r\n"
					+ "FROM EMP a left outer join EMP b\r\n"
					+ "ON a.MGR = b.empno\r\n"
					+ "WHERE A.ENAME LIKE ?) TB\r\n"
					+ "WHERE rnum >= ? and rnum <= ?";

			psmt = conn.prepareStatement(sql);	
			psmt.setString(1, strValue);
			psmt.setInt(2, start);
			psmt.setInt(3, end);

			rs = psmt.executeQuery();

			while (rs.next()) {
				emp semp = new emp();
				semp.setEMPNO(rs.getInt("empno"));
				semp.setENAME(rs.getString("ename"));
				//1. 조장을 조장님으로 바꾸기+조장님 사번번호 붙이기
				if(rs.getString("job").equals("조장")) {
					semp.setJOB("조장님" + rs.getString("empno")); 
				} else {
					semp.setJOB(rs.getString("job"));
				}

				//2. 1011(서지희) 1022(최희두) 7839(KING)만 이름으로 뿌려 스트링 반환 메소드 만들어서 풀기
				if(rs.getString("mgr") != null ) {
					semp.setMGR(chageMgr(rs.getString("mgr")));
				} else {
					semp.setMGR("0");
				}
				semp.setHIREDATE(rs.getString("hiredate"));
				semp.setSAL(rs.getInt("sal"));
				semp.setCOMM(rs.getInt("comm"));
				semp.setDEPTNO(rs.getInt("deptno"));
				semp.setMgrName(rs.getString("Mgrname"));

				_emp.add(semp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		return _emp;	

	}


	//ListTest.jsp의 전체데이터의 갯수 구하기
	public int getDataTotalCount(String strValue) {
		int totalCount = 0;

		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			strValue = "%" + strValue + "%";

			sql = "SELECT COUNT(empno) AS CNT FROM EMP a \r\n"
					+ "WHERE ename like ?";						
			psmt = conn.prepareStatement(sql);	
			psmt.setString(1, strValue);

			rs = psmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		

		return totalCount;
	}



	//2. 1011(서지희) 1022(최희두) 7839(KING)만 이름으로 뿌려 스트링 반환 메소드 만들어서 풀기
	private String chageMgr(String mgrNUM) {
		String strResult = "";
		if(mgrNUM.equals("1011")) {
			strResult = "서지희";
		} else if(mgrNUM.equals("1022")) {
			strResult = "최희두";
		} else if(mgrNUM.equals("7839")) {
			strResult = "KING";
		} else {
			strResult = mgrNUM;
		}

		return strResult;
	}


	public emp TestMariaDBDetail(String empno) {
		emp semp = new emp();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		
		// 모델 담으라고? db 전직원 조회를 테이블 방식으로 뿌리고, 사원번호, 이름 연봉
		try {
			conn = DBConnectionManager.getConnection();
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";

			sql = "SELECT a.EMPNO, a.ENAME, a.JOB, a.mgr, b.ename MgrName, a.HIREDATE, a.SAL, a.COMM, a.DEPTNO\r\n"
					+ "		,(SELECT DNAME FROM DEPT WHERE DEPTNO = a.DEPTNO) as DName \r\n"
					+ " FROM EMP a \r\n"
					+ " left outer join EMP b \r\n"
					+ " ON a.MGR = b.empno"		
					+ " where a.empno =" + empno;		

			psmt = conn.prepareStatement(sql);			

			rs = psmt.executeQuery();


			while (rs.next()) {				
				semp.setEMPNO(rs.getInt("empno"));
				semp.setENAME(rs.getString("ename"));

				//1. 사원을 연구원으로 바꾸기
				if(rs.getString("job").equals("사원")) {
					semp.setJOB("연구원" + rs.getString("empno")); 
				} else {
					semp.setJOB(rs.getString("job"));
				}

				//2. 1011 1022 7839만 이름으로 뿌려 스트링 반환 메소드 만들어서 풀기
				if(rs.getString("mgr") != null ) {
					semp.setMGR(chageMgr(rs.getString("mgr")));
				} else {
					semp.setMGR("0");
				}
				semp.setHIREDATE(rs.getString("hiredate"));
				semp.setSAL(rs.getInt("sal"));
				semp.setCOMM(rs.getInt("comm"));
				semp.setDEPTNO(rs.getInt("deptno"));
				semp.setMgrName(rs.getString("Mgrname"));
				semp.setDName(rs.getString("DName"));
				
				//3. db에서 쿼리 변경 mgr을 이름으로...  db접속해서, db부화... mgr을 이름으로 바꿔서 뿌려				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return semp;	

	}

}
