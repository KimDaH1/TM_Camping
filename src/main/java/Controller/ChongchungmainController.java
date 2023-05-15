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
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import camping.dto.PersonDto;
import camping.dto.campzone;
import camping.dto.campzoneJsonModel;
import camping.oracle.DBConnectionManager;

//javascript에서 값 넘겨 받기 테스트
import javax.servlet.http.HttpServletRequest;


public class ChongchungmainController {

	public String TestingApiChongchung(HttpServletRequest request) throws IOException{
		
	    String str = request.getParameter("data");

		String strResult = "";
		String cpname = "";
		String cptel = "";
		double lat;
		double lng;
		String addr = "";
		
		String cpLineIntro = "";
		String cpLctCl = "";
		String cpPosblFcltyCl = "";
		String cpHomepage = "";
		String cpAnimalCmgCl = "";;
		String cpInduty = "";
		
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
		urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8")+ "=" + URLEncoder.encode(str,"UTF-8"));
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
				
				cpLineIntro = roots.response.body.items.item.get(i).lineIntro.toString();
				cpLctCl = roots.response.body.items.item.get(i).lctCl.toString();
				cpPosblFcltyCl = roots.response.body.items.item.get(i).posblFcltyCl.toString();
				cpHomepage = roots.response.body.items.item.get(i).homepage.toString();
				cpAnimalCmgCl = roots.response.body.items.item.get(i).animalCmgCl.toString();
				cpInduty = roots.response.body.items.item.get(i).induty.toString();

				//insert methods 만들어 놓고 호출

				//한줄씩 넣기 때문에 결과값 피드백은 1을 받게 되어 있음 //executeUpdate() 참고
				iresult = InsertCampDt(cpname, cptel, lat, lng, addr, cpLineIntro, cpLctCl, cpPosblFcltyCl, cpHomepage, cpAnimalCmgCl, cpInduty);
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

	private int InsertCampDt(String cpname, String cptel, double lat, double lng, String addr, String cpLineIntro, String cpLctCl, String cpPosblFcltyCl, String cpHomepage, String cpAnimalCmgCl, String cpInduty) {
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;

		int result = 0;

		try {
			conn = DBConnectionManager.getConnection();

			//쿼리문
			String sql = "INSERT INTO TM_CAMPINGZONE_Chongchung "
					+ " (IDX, CPNAME, CPTEL, LAT, LNG, ADDR, cpLineIntro, cpLctCl, cpPosblFcltyCl, cpHomepage,"
					+ " cpAnimalCmgCl, cpInduty) VALUES ((SELECT NVL(MAX(idx)+1,1) "
					+ " FROM TM_CAMPINGZONE_Chongchung), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cpname);
			psmt.setString(2, cptel);
			psmt.setDouble(3, lat);
			psmt.setDouble(4, lng);
			psmt.setString(5, addr);
			psmt.setString(6, cpLineIntro);
			psmt.setString(7, cpLctCl);
			psmt.setString(8, cpPosblFcltyCl);
			psmt.setString(9, cpHomepage);
			psmt.setString(10, cpAnimalCmgCl);
			psmt.setString(11, cpInduty);			

			result = psmt.executeUpdate();//추가시킨 low값 만큼 int return

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}
		System.out.println("처리결과 : " + result);
		return result;

	}
	
	//충청db세부정보
	public campzone ChongchungDBDetail(String cpname) throws IOException{
		campzone scamp = new campzone();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		
		try {
			conn = DBConnectionManager.getConnection();
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";

			sql = "SELECT idx, cpname, cpLineIntro, cpLctCl, addr, cpPosblFcltyCl, cptel, cpAnimalCmgCl, cpInduty FROM TM_CAMPINGZONE_Chongchung where cpname ='"+cpname+"' order by idx";

			psmt = conn.prepareStatement(sql);			

			rs = psmt.executeQuery();


			while (rs.next()) {		
				scamp.setIdx(rs.getInt("idx"));
				System.out.println(rs.getInt("idx"));
				scamp.setCpname(rs.getString("cpname"));
				scamp.setCpIntro(rs.getString("cpLineIntro"));
				scamp.setCpLctCl(rs.getString("cpLctCl"));
				scamp.setAddr(rs.getString("addr"));
				scamp.setCpPosblFcltyCl(rs.getString("cpPosblFcltyCl"));
				scamp.setcptel(rs.getString("cptel"));
				scamp.setCpAnimalCmgCl(rs.getString("cpAnimalCmgCl"));
				scamp.setCpInduty(rs.getString("cpInduty"));
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return scamp;	

	}
	
}
