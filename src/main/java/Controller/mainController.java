package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
				BufferedReader br = new BufferedReader(new InputStreamReader(conn,getInputStream(),"UTF-8"));
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
}
