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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;

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
        System.out.println("1");
        JSONObject jsonObject1;//import org.json.simple.JSONObject; 추가
        System.out.println("2");
        try {
            System.out.println("3");
        	jsonObject1 = (JSONObject) jsonParser1.parse(strResult);
        	System.out.println("jsonObject1 = " + jsonObject1);
        	
        	// Gson의 역할 json통신된 json을 만들어놓은 dto모델에 담는다.
        	campzoneJsonModel roots = new Gson().fromJson(jsonObject1.toString(), campzoneJsonModel.class);
        	
        	for(int i = 0; i<roots.response.body.items.item.size(); i++) {
        		System.out.println(roots.response.body.items.item.get(i).addr1.toString());
        		
        		System.out.println("InsertBefore");
        		cpname = roots.response.body.items.item.get(i).facltNm.toString();
        		cptel = roots.response.body.items.item.get(i).tel.toString();
        		lat = roots.response.body.items.item.get(i).mapX;
        		lng = roots.response.body.items.item.get(i).mapY;
        		addr = roots.response.body.items.item.get(i).addr1.toString();
        		System.out.println("InsertgAfter");
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
		return result;		

	}
}
