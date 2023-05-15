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

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;


//게시판 기능구현을 위한 emp dto 추가
import camping.dto.emp;
import camping.dto.campzone;
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
					+ "&keyword=경기도");//import java.ner.url; 추가

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
		urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8")+ "=" + URLEncoder.encode("제주","UTF-8"));
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
				//
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
			String sql = "INSERT INTO TM_CAMPINGZONE_1 (IDX, CPNAME, CPTEL, LAT, LNG, ADDR, cpLineIntro, cpLctCl, cpPosblFcltyCl, cpHomepage, cpAnimalCmgCl, cpInduty) VALUES ((SELECT NVL(MAX(idx)+1,1) FROM tm_campingzone_1), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

	

	public String TestingApiThree() throws IOException{
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
		urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8")+ "=" + URLEncoder.encode("서울시","UTF-8"));

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
	
	
	
	
//	public List<campzone> TestingApiThree() throws IOException{
//		String strResult = "";
//		String cpname = "";
//		String cptel = "";
//		double lat;
//		double lng;
//		String addr = "";
//				
//		String cpLineIntro = ""; // 한줄소개
//		String cpIntro = ""; // 소개
//		int cpAllar; // 전체면적
//		String cpInsrncAt = ""; // 영업배상책임보험 가입여부
//		String cpTrsagntNo = ""; // 관광사업자번호
//		String cpBizrno = ""; // 사업자번호
//		String cpFacltDivNm = ""; // 사업주체, 구분
//		String cpMangeDivNm = ""; // 운영주체, 관리주체
//		String cpMgcDiv = ""; // 운영기관, 관리기관
//		String cpManageSttus = ""; // 운영상태, 관리상태
//		String cpHvofBgnde = ""; // 휴장기간, 휴무기간 시작일
//		String cpHvofEnddle = ""; // 휴장기간, 휴무기간 종료일
//		String cpFeatureNm = ""; // 특징
//		String cpInduty = ""; // 업종☆
//		String cpLctCl = ""; // 입지구분☆
//		String cpDoNm = ""; // 도
//		String cpSigunguNm = ""; //시군구
//		int cpZipcode; // 우편번호
//		String cpAddr2 = ""; // 주소상세
//		String cpDirection = ""; //오시는길 컨텐츠
//		String cpHomepage = ""; //홈페이지
//		String cpResveUrl = ""; //예약 페이지
//		String cpResveCl = ""; //예약 구분
//		int cpManageNmpr; // 상주관리인원
//		int cpGnrlSiteCo; // 주요시설 일반야영장
//		int cpAutoSiteCo; // 주요시설 자동차야영장
//		int cpGlampSiteCo; //주요시설 글램핑
//		int cpCaravSiteCo; //주요시설 카라반
//		int cpIndvdlCaravSiteCo; //주요시설 개인 카라반
//		int cpSitedStnc; //사이트간 거리
//		int cpSiteMg1Width; //사이트 크기1 가로
//		int cpSiteMg2Width; //사이트 크기2 가로
//		int cpSiteMg3Width; //사이트 크기3 가로
//		int cpSiteMg1Vrticl; //사이트 크기 1 세로
//		int cpSiteMg2Vrticl; //사이트 크기2 세로
//		int cpSiteMg3Vrticl; //사이트 크기 3 세로
//		int cpSiteMg1Co; //사이트 크기 1 수량
//		int cpSiteMg2Co; //사이트 크기 2 수량
//		int cpSiteMg3Co; // 사이트 크기 3 수량
//		int cpSiteBottomCl1; //잔디
//		int cpSiteBottomCl2; //파쇄석
//		int cpSiteBottomCl3; // 테크
//		int cpSiteBottomCl4; // 자갈
//		int cpSiteBottomCl5; // 맨흙
//		String cpTooltip = ""; //툴팁
//		String cpGlampInnerFclty = ""; // 글램핑 내부시설
//		String cpCaravInnerFclty = ""; // 카라반 내부시설
//		String cpPrmisnDe = ""; // 인허가일자
//		String cpOperPdCl = ""; // 운영기간
//		String cpOperDeCl = ""; // 운영일
//		String cpTrlerAcmpnyAt = ""; //개인 트레일러 동반 여부(Y:사용, N:미사용)
//		String cpCaravAcmpnyAt = ""; //개인 카라반 동반 여부(Y:사용, N:미사용)
//		int cpToiletCo; //화장실 개수
//		int cpSwrmCo; //샤워실 개수
//		int cpWtrplCo; //개수대 개수
//		String cpBrazierCl = ""; //화로대
//		String cpSbrsCl = ""; //부대시설
//		String cpSbrsEtc = ""; //부대시설 기타
//		String cpPosblFcltyCl = ""; //주변이용가능시설
//		String cpPosblFcltyEtc = ""; //주변이용가능시설 기타
//		String cpClturEventAt = ""; //자체문화행사 여부(Y:사용, N:미사용)
//		String cpClturEvent = ""; //자체문화행사명
//		String cpExprnProgrmAt = "";//체험프로그램 여부(Y:사용, N:미사용)
//		String cpExprnProgrm = ""; // 체험프로그램명
//		int cpExtshrCo; //소화기개수
//		int cpFrprvtWrppCo; //방화수 개수
//		int cpFrprvtSandCo; //방화사 개수
//		int cpFireSensorCo; // 화재감지기 개수
//		String cpThemaEnvrnCl = ""; // 테마환경
//		String cpEqpmnLendCl = ""; // 캠핑장비대여
//		String cpAnimalCmgCl = ""; //애완동물출입
//		String cpTourEraCl = ""; // 여행시기
//		String cpFirstImageUrl = ""; // 대표이미지
//		String cpCreatedtime = ""; //등록일
//		String cpModifiedtime = ""; //수정일
//
//		int iresult = 0;
//
//		/*URL apiUrl = new URL("https://apis.data.go.kr/B551011/GoCamping/searchList"
//				+ "?serviceKey=nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D"
//				+ "&numOfRows=10"
//				+ "&pageNo=1"
//				+ "&MobileOS=ETC"
//				+ "&MobileApp=AppTest"
//				+ "&_type=JSON"
//				+ "&keyword=천안");*/
//		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/GoCamping/searchList");
//		// 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
//		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "nHYvwaA5iMdJN%2BZfRIwpkLrcYSbqND87jXVtes2Z6I7kb5%2F8Ycv1UCLzedNfTfWQ%2FVoOtadIC9WpwTLtTnukPA%3D%3D"); /*Service Key*/
//		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8")+ "=" + URLEncoder.encode("1","UTF-8"));
//		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8")+ "=" + URLEncoder.encode("10","UTF-8"));
//		urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8")+ "=" + URLEncoder.encode("ETC","UTF-8"));
//		urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8")+ "=" + URLEncoder.encode("AppTest","UTF-8"));
//		urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8")+ "=" + URLEncoder.encode("json","UTF-8"));
//		urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8")+ "=" + URLEncoder.encode("천안","UTF-8"));
//		// 3. URL 객체 생성
//		URL url = new URL(urlBuilder.toString());
//
//		// 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//		// 5. 통신을 위한 메소드 SET
//		conn.setRequestMethod("GET");
//
//		// 6. 통신을 위한 Content-type SET
//		conn.setRequestProperty("Content-type", "application/json");
//
//		// 7. 통신 응답 코드 확인
//		System.out.println("Response code : " + conn.getResponseCode());
//
//		// 8. 전달받은 데이터를 BufferedReader 객체로 저장
//		BufferedReader rd;
//		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//
//		// 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//
//		// 10. 객체 해제
//		rd.close();
//		conn.disconnect();
//
//		// 11. 전달받은 데이터 확인
//		System.out.println("전달받은 데이터 확인 : " + sb.toString());
//
//		strResult = sb.toString();
//
//		JSONParser jsonParser1 = new JSONParser();//import org.json.simple.parser.JSONParser; 추가
//		JSONObject jsonObject1;//import org.json.simple.JSONObject; 추가
//		List<campzone> json_arr = new ArrayList<campzone>();
//		try {
//			System.out.println("3");
//			jsonObject1 = (JSONObject) jsonParser1.parse(strResult);
//			System.out.println("jsonObject1 = " + jsonObject1);
//
//			// Gson의 역할 json통신된 json을 만들어놓은 dto모델에 담는다.
//			campzoneJsonModel roots = new Gson().fromJson(jsonObject1.toString(), campzoneJsonModel.class);
//
//			for(int i = 0; i<roots.response.body.items.item.size(); i++) {
//				campzone json_datas = new campzone();
//				System.out.println(roots.response.body.items.item.get(i).addr1.toString());
//
//				cpname = roots.response.body.items.item.get(i).facltNm.toString(); // 야영장명☆
//				cpLineIntro = roots.response.body.items.item.get(i).lineIntro.toString(); // 한줄소개
//				cpAllar = roots.response.body.items.item.get(i).allar; // 전체면적
//				cpInsrncAt = roots.response.body.items.item.get(i).insrncAt.toString(); // 영업배상책임보험 가입여부
//				cpTrsagntNo = roots.response.body.items.item.get(i).trsagntNo.toString(); // 관광사업자번호
//				cpBizrno = roots.response.body.items.item.get(i).bizrno.toString(); // 사업자번호
//				cpFacltDivNm = roots.response.body.items.item.get(i).facltDivNm.toString(); // 사업주체, 구분
//				cpMangeDivNm = roots.response.body.items.item.get(i).mangeDivNm.toString(); // 운영주체, 관리주체
//				cpMgcDiv = roots.response.body.items.item.get(i).mgcDiv.toString(); // 운영기관, 관리기관
//				cpManageSttus = roots.response.body.items.item.get(i).manageSttus.toString(); // 운영상태, 관리상태
//				cpHvofBgnde = roots.response.body.items.item.get(i).hvofBgnde.toString(); // 휴장기간, 휴무기간 시작일
//				cpHvofEnddle = roots.response.body.items.item.get(i).hvofEnddle.toString(); // 휴장기간, 휴무기간 종료일
//				cpFeatureNm = roots.response.body.items.item.get(i).featureNm.toString(); // 특징
//				cpInduty = roots.response.body.items.item.get(i).induty.toString(); // 업종
//				cpLctCl = roots.response.body.items.item.get(i).lctCl.toString(); // 입지구분
//				cpDoNm = roots.response.body.items.item.get(i).doNm.toString(); // 도
//				cpSigunguNm = roots.response.body.items.item.get(i).sigunguNm.toString(); //시군구
//				cpZipcode = roots.response.body.items.item.get(i).zipcode; // 우편번호
//				addr = roots.response.body.items.item.get(i).addr1.toString(); // 주소
//				cpAddr2 = roots.response.body.items.item.get(i).addr2.toString(); // 주소상세
//				lat = roots.response.body.items.item.get(i).mapX; //경도
//				lng = roots.response.body.items.item.get(i).mapY; //위도
//				cpDirection = roots.response.body.items.item.get(i).direction.toString(); //오시는길 컨텐츠
//				cptel = roots.response.body.items.item.get(i).tel.toString(); //전화
//				cpHomepage = roots.response.body.items.item.get(i).homepage.toString(); //홈페이지
//				cpResveUrl = roots.response.body.items.item.get(i).resveUrl.toString(); //예약 페이지
//				cpResveCl = roots.response.body.items.item.get(i).resveCl.toString(); //예약 구분
//				cpManageNmpr = roots.response.body.items.item.get(i).manageNmpr; // 상주관리인원
//				cpGnrlSiteCo = roots.response.body.items.item.get(i).gnrlSiteCo; // 주요시설 일반야영장
//				cpAutoSiteCo = roots.response.body.items.item.get(i).autoSiteCo; // 주요시설 자동차야영장
//				cpGlampSiteCo = roots.response.body.items.item.get(i).glampSiteCo; //주요시설 글램핑
//				cpCaravSiteCo = roots.response.body.items.item.get(i).caravSiteCo; //주요시설 카라반
//				cpIndvdlCaravSiteCo = roots.response.body.items.item.get(i).indvdlCaravSiteCo; //주요시설 개인 카라반
//				cpSitedStnc = roots.response.body.items.item.get(i).sitedStnc; //사이트간 거리
//				cpSiteMg1Width = roots.response.body.items.item.get(i).siteMg1Width; //사이트 크기1 가로
//				cpSiteMg2Width = roots.response.body.items.item.get(i).siteMg2Width; //사이트 크기2 가로
//				cpSiteMg3Width = roots.response.body.items.item.get(i).siteMg3Width; //사이트 크기3 가로
//				cpSiteMg1Vrticl = roots.response.body.items.item.get(i).siteMg1Vrticl; //사이트 크기 1 세로
//				cpSiteMg2Vrticl = roots.response.body.items.item.get(i).siteMg2Vrticl; //사이트 크기2 세로
//				cpSiteMg3Vrticl = roots.response.body.items.item.get(i).siteMg3Vrticl; //사이트 크기 3 세로
//				cpSiteMg1Co = roots.response.body.items.item.get(i).siteMg1Co; //사이트 크기 1 수량
//				cpSiteMg2Co = roots.response.body.items.item.get(i).siteMg2Co; //사이트 크기 2 수량
//				cpSiteMg3Co = roots.response.body.items.item.get(i).siteMg3Co; // 사이트 크기 3 수량
//				cpSiteBottomCl1 = roots.response.body.items.item.get(i).siteBottomCl1; //잔디
//				cpSiteBottomCl2 = roots.response.body.items.item.get(i).siteBottomCl2; //파쇄석
//				cpSiteBottomCl3 = roots.response.body.items.item.get(i).siteBottomCl3; // 테크
//				cpSiteBottomCl4 = roots.response.body.items.item.get(i).siteBottomCl4; // 자갈
//				cpSiteBottomCl5 = roots.response.body.items.item.get(i).siteBottomCl5; // 맨흙
//				cpTooltip = roots.response.body.items.item.get(i).tooltip.toString(); //툴팁
//				cpGlampInnerFclty = roots.response.body.items.item.get(i).glampInnerFclty.toString(); // 글램핑 내부시설
//				cpCaravInnerFclty = roots.response.body.items.item.get(i).caravInnerFclty.toString(); // 카라반 내부시설
//				cpPrmisnDe = roots.response.body.items.item.get(i).prmisnDe.toString(); // 인허가일자
//				cpOperPdCl = roots.response.body.items.item.get(i).operPdCl.toString(); // 운영기간
//				cpOperDeCl = roots.response.body.items.item.get(i).operDeCl.toString(); // 운영일
//				cpTrlerAcmpnyAt = roots.response.body.items.item.get(i).trlerAcmpnyAt.toString(); //개인 트레일러 동반 여부(Y:사용, N:미사용)
//				cpCaravAcmpnyAt = roots.response.body.items.item.get(i).caravAcmpnyAt.toString(); //개인 카라반 동반 여부(Y:사용, N:미사용)
//				cpToiletCo = roots.response.body.items.item.get(i).toiletCo; //화장실 개수
//				cpSwrmCo = roots.response.body.items.item.get(i).swrmCo; //샤워실 개수
//				cpWtrplCo = roots.response.body.items.item.get(i).wtrplCo; //개수대 개수
//				cpBrazierCl = roots.response.body.items.item.get(i).brazierCl.toString(); //화로대
//				cpSbrsCl = roots.response.body.items.item.get(i).sbrsCl.toString(); //부대시설
//				cpSbrsEtc = roots.response.body.items.item.get(i).sbrsEtc.toString(); //부대시설 기타
//				cpPosblFcltyCl = roots.response.body.items.item.get(i).posblFcltyCl.toString(); //주변이용가능시설
//				cpPosblFcltyEtc = roots.response.body.items.item.get(i).posblFcltyEtc.toString(); //주변이용가능시설 기타
//				cpClturEventAt = roots.response.body.items.item.get(i).clturEventAt.toString(); //자체문화행사 여부(Y:사용, N:미사용)
//				cpClturEvent = roots.response.body.items.item.get(i).clturEvent.toString(); //자체문화행사명
//				cpExprnProgrmAt = roots.response.body.items.item.get(i).exprnProgrmAt.toString();//체험프로그램 여부(Y:사용, N:미사용)
//				cpExprnProgrm = roots.response.body.items.item.get(i).exprnProgrm.toString(); // 체험프로그램명
//				cpExtshrCo = roots.response.body.items.item.get(i).extshrCo; //소화기개수
//				cpFrprvtWrppCo = roots.response.body.items.item.get(i).frprvtWrppCo; //방화수 개수
//				cpFrprvtSandCo = roots.response.body.items.item.get(i).frprvtSandCo; //방화사 개수
//				cpFireSensorCo = roots.response.body.items.item.get(i).fireSensorCo; // 화재감지기 개수
//				cpThemaEnvrnCl = roots.response.body.items.item.get(i).themaEnvrnCl.toString(); // 테마환경
//				cpEqpmnLendCl = roots.response.body.items.item.get(i).eqpmnLendCl.toString(); // 캠핑장비대여
//				cpAnimalCmgCl = roots.response.body.items.item.get(i).animalCmgCl.toString(); //애완동물출입
//				cpTourEraCl = roots.response.body.items.item.get(i).tourEraCl.toString(); // 여행시기
//				cpFirstImageUrl = roots.response.body.items.item.get(i).firstImageUrl.toString(); // 대표이미지
//				cpCreatedtime = roots.response.body.items.item.get(i).createdtime.toString(); //등록일
//				cpModifiedtime = roots.response.body.items.item.get(i).modifiedtime.toString(); //수정일
//				
//				json_datas.setAddr(cpAddr2);
//				json_datas.setCpAddr2(cpAddr2);
//				json_datas.setCpAllar(cpAllar);
//				
//				json_arr.add(json_datas);
//				
				


				//insert methods 만들어 놓고 호출

				//한줄씩 넣기 때문에 결과값 피드백은 1을 받게 되어 있음 //executeUpdate() 참고
//				iresult = InsertCampDtAll(cpname, cptel, lat, lng, addr, cpLineIntro, cpAllar, cpInsrncAt, 
//						cpTrsagntNo, cpBizrno, cpFacltDivNm, cpMangeDivNm, cpMgcDiv, cpManageSttus, cpHvofBgnde,
//						cpHvofEnddle, cpFeatureNm, cpInduty, cpLctCl, cpDoNm, cpSigunguNm, cpZipcode, cpAddr2, 
//						cpDirection, cpHomepage, cpResveUrl, cpResveCl, cpManageNmpr, cpGnrlSiteCo, cpAutoSiteCo, 
//						cpGlampSiteCo, cpCaravSiteCo, cpIndvdlCaravSiteCo, cpSitedStnc, cpSiteMg1Width, cpSiteMg2Width, 
//						cpSiteMg3Width, cpSiteMg1Vrticl, cpSiteMg2Vrticl, cpSiteMg3Vrticl, cpSiteMg1Co, cpSiteMg2Co, 
//						cpSiteMg3Co, cpSiteBottomCl1, cpSiteBottomCl2, cpSiteBottomCl3, cpSiteBottomCl4, cpSiteBottomCl5, 
//						cpTooltip, cpGlampInnerFclty, cpCaravInnerFclty, cpPrmisnDe, cpOperPdCl, cpOperDeCl, cpTrlerAcmpnyAt, 
//						cpCaravAcmpnyAt, cpToiletCo, cpSwrmCo, cpWtrplCo, cpBrazierCl, cpSbrsCl, cpSbrsEtc, cpPosblFcltyCl,
//						cpPosblFcltyEtc, cpClturEventAt, cpClturEvent, cpExprnProgrmAt, cpExprnProgrm, cpExtshrCo, cpFrprvtWrppCo, 
//						cpFrprvtSandCo, cpFireSensorCo, cpThemaEnvrnCl, cpEqpmnLendCl, cpAnimalCmgCl, cpTourEraCl, cpFirstImageUrl, 
//						cpCreatedtime, cpModifiedtime);
//				if(iresult != 1) {
//					break;
//				}

//			}

//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//
//		System.out.println();
//
//		return json_arr;
//	} 

//	private int InsertCampDtAll(String cpname, String cptel, double lat, double lng, String addr, String cptel,
//			String cpLineIntro,	int cpAllar,	String cpInsrncAt, String cpFacltDivNm,	String cpMangeDivNm,	String cpMgcDiv,	String cpManageSttus,
//			String cpHvofBgnde,	String cpHvofEnddle,	String cpFeatureNm,	String cpInduty,
//			String cpLctCl,	String cpDoNm, 	String cpSigunguNm,	int cpZipcode, 	String cpAddr2,
//			String cpDirection,	String cpHomepage, String cpResveUrl, String cpResveCl,	int cpManageNmpr,
//			int cpGnrlSiteCo, 	int cpAutoSiteCo,	int cpGlampSiteCo,	int cpCaravSiteCo,	int cpIndvdlCaravSiteCo,
//			int cpSitedStnc,	int cpSiteBottomCl1,	int cpSiteBottomCl2,	int cpSiteBottomCl3,	int cpSiteBottomCl4,	int cpSiteBottomCl5,
//			String cpTooltip,	String cpGlampInnerFclty,	String cpCaravInnerFclty,	String cpPrmisnDe,	String cpOperPdCl,
//			String cpOperDeCl,	String cpTrlerAcmpnyAt,	String cpCaravAcmpnyAt,	String cpBrazierCl,	String cpSbrsCl,	String cpSbrsEtc,	String cpPosblFcltyCl,	String cpPosblFcltyEtc,	String cpClturEventAt,
//			String cpClturEvent, String cpExprnProgrmAt, String cpExprnProgrm, int cpExtshrCo,	int cpFrprvtWrppCo,
//			int cpFrprvtSandCo,	int cpFireSensorCo,	String cpThemaEnvrnCl,	String cpEqpmnLendCl,	String cpAnimalCmgCl,
//			String cpTourEraCl,	String cpFirstImageUrl,	String cpCreatedtime,	String cpModifiedtime) {
//		Connection conn = null; //import java.sql.Connection;
//		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
//		ResultSet rs = null; //import java.sql.ResultSet;
//
//		int result = 0;
//
//		try {
//			conn = DBConnectionManager.getConnection();
//
//			//쿼리문
//			String sql = "INSERT INTO TM_CAMPINGZONE_ALL (IDX, CPNAME, CPTEL, LAT, LNG, ADDR, cpLineIntro, \r\n"
//					+ "cpIntro, cpAllar, cpInsrncAt, cpTrsagntNo, cpBizrno, cpFacltDivNm, cpMangeDivNm, \r\n"
//					+ "cpMgcDiv, cpManageSttus, cpHvofBgnde, cpHvofEnddle, cpFeatureNm, cpInduty, cpLctCl, \r\n"
//					+ "cpDoNm, cpSigunguNm, cpZipcode, cpAddr2, cpDirection, cpHomepage, cpResveUrl, cpResveCl, \r\n"
//					+ "cpManageNmpr, cpGnrlSiteCo, cpAutoSiteCo, cpGlampSiteCo, cpCaravSiteCo, cpIndvdlCaravSiteCo, \r\n"
//					+ "cpSitedStnc, cpSiteMg1Width, cpSiteMg2Width, cpSiteMg3Width, cpSiteMg1Vrticl, cpSiteMg2Vrticl, \r\n"
//					+ "cpSiteMg3Vrticl, cpSiteMg1Co, cpSiteMg2Co, cpSiteMg3Co, cpSiteBottomCl1, cpSiteBottomCl2, \r\n"
//					+ "cpSiteBottomCl3, cpSiteBottomCl4, cpSiteBottomCl5, cpTooltip, cpGlampInnerFclty, \r\n"
//					+ "cpCaravInnerFclty, cpPrmisnDe, cpOperPdCl, cpOperDeCl, cpTrlerAcmpnyAt, cpCaravAcmpnyAt, \r\n"
//					+ "cpToiletCo, cpSwrmCo, cpWtrplCo, cpBrazierCl, cpSbrsCl, cpSbrsEtc, cpPosblFcltyCl, \r\n"
//					+ "cpPosblFcltyEtc, cpClturEventAt, cpClturEvent, cpExprnProgrmAt, cpExprnProgrm, cpExtshrCo, \r\n"
//					+ "cpFrprvtWrppCo, cpFrprvtSandCo, cpFireSensorCo, cpThemaEnvrnCl, cpEqpmnLendCl, cpAnimalCmgCl, \r\n"
//					+ "cpTourEraCl, cpFirstImageUrl, cpCreatedtime, cpModifiedtime) VALUES ((SELECT NVL(MAX(idx)+1,1) FROM tm_campingzone_all), "
//					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
//					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
//					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
//					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, cpname);
//			psmt.setString(2, cptel);
//			psmt.setDouble(3, lat);
//			psmt.setDouble(4, lng);
//			psmt.setString(5, addr);
//			psmt.setString (6, cpLineIntro);	
//			psmt.setInt (7, cpAllar);
//			psmt.setString (8, cpInsrncAt);	
//			psmt.setString (9,cpTrsagntNo);
//			psmt.setString (10, cpBizrno);
//			psmt.setString (11, cpFacltDivNm);	
//			psmt.setString (12, cpMangeDivNm);	
//			psmt.setString (13, cpMgcDiv);
//			psmt.setString (14, cpManageSttus);
//			psmt.setString (15, cpHvofBgnde);	
//			psmt.setString (16, cpHvofEnddle);	
//			psmt.setString (17, cpFeatureNm);	
//			psmt.setString (18, cpInduty);
//			psmt.setString (19, cpLctCl);	
//			psmt.setString (20, cpDoNm); 	
//			psmt.setString (21, cpSigunguNm);	
//			psmt.setInt (22, cpZipcode); 	
//			psmt.setString (23, cpAddr2);
//			psmt.setString (24, cpDirection);	
//			psmt.setString (25, cpHomepage); 
//			psmt.setString (26, cpResveUrl); 
//			psmt.setString (27, cpResveCl);	
//			psmt.setInt (28, cpManageNmpr);
//			psmt.setInt (29, cpGnrlSiteCo); 	
//			psmt.setInt (30, cpAutoSiteCo);
//			psmt.setInt (31, cpGlampSiteCo);	
//			psmt.setInt (32, cpCaravSiteCo);	
//			psmt.setInt (33, cpIndvdlCaravSiteCo);
//			psmt.setInt (34, cpSitedStnc);	
//			psmt.setInt (35, cpSiteMg1Width);	
//			psmt.setInt (36, cpSiteMg2Width);	
//			psmt.setInt (37, cpSiteMg3Width);	
//			psmt.setInt (38, cpSiteMg1Vrticl);
//			psmt.setInt (39, cpSiteMg2Vrticl);	
//			psmt.setInt (40, cpSiteMg3Vrticl);	
//			psmt.setInt (41, cpSiteMg1Co);	
//			psmt.setInt (42, cpSiteMg2Co);	
//			psmt.setInt (43, cpSiteMg3Co);
//			psmt.setInt (44, cpSiteBottomCl1);	
//			psmt.setInt (45, cpSiteBottomCl2);	
//			psmt.setInt (46, cpSiteBottomCl3);	
//			psmt.setInt (47, cpSiteBottomCl4);	
//			psmt.setInt (48, cpSiteBottomCl5);
//			psmt.setString (49, cpTooltip);	
//			psmt.setString (50, cpGlampInnerFclty);	
//			psmt.setString (51, cpCaravInnerFclty);	
//			psmt.setString (52, cpPrmisnDe);	
//			psmt.setString (53, cpOperPdCl);
//			psmt.setString (54, cpOperDeCl);	
//			psmt.setString (55, cpTrlerAcmpnyAt);	
//			psmt.setString (56, cpCaravAcmpnyAt);	
//			psmt.setInt (57, cpToiletCo);	
//			psmt.setInt (58, cpSwrmCo);	
//			psmt.setInt (59, cpWtrplCo);
//			psmt.setString (60, cpBrazierCl);	
//			psmt.setString (61, cpSbrsCl);	
//			psmt.setString (62, cpSbrsEtc);	
//			psmt.setString (63, cpPosblFcltyCl);	
//			psmt.setString (64, cpPosblFcltyEtc);	
//			psmt.setString (65, cpClturEventAt);
//			psmt.setString (66, cpClturEvent); 
//			psmt.setString (67, cpExprnProgrmAt); 
//			psmt.setString (68, cpExprnProgrm); 
//			psmt.setInt (69, cpExtshrCo);	
//			psmt.setInt (70, cpFrprvtWrppCo);
//			psmt.setInt (71, cpFrprvtSandCo);	
//			psmt.setInt (72, cpFireSensorCo);	
//			psmt.setString (73, cpThemaEnvrnCl);	
//			psmt.setString (74, cpEqpmnLendCl);	
//			psmt.setString (75, cpAnimalCmgCl);
//			psmt.setString (76, cpTourEraCl);	
//			psmt.setString (77, cpFirstImageUrl);	
//			psmt.setString (78, cpCreatedtime);	
//			psmt.setString (79, cpModifiedtime);
//			
//			result = psmt.executeUpdate();//추가시킨 low값 만큼 int return
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBConnectionManager.close(rs, psmt, conn);
//		}
//		System.out.println("처리결과 : " + result);
//		return result;
//
//	}
	
	// 켐핑 테이블 생성 준비 
	public List<campzone> TestCampDB(int idx) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT idx, cpname, cptel, lat, lng, addr FROM TM_CAMPINGZONE_1";
			if(idx != 0) {
				sql += " where idx =" + idx;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("idx"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setcptel(rs.getString("cptel"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
	}
	
	// 경기 게시판 테이블 생성 준비 
	public List<campzone> TestCampDBGyeonggi(int idx) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT rownum idx, cpname, cpinduty, lat, lng, addr FROM TM_CAMPINGZONE_EXAL WHERE addr LIKE '%경기%'";
			if(idx != 0) {
				sql += " and rownum =" + idx;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("rownum"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpinduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
	}
	
	// 전라 게시판 테이블 생성 준비 
	public List<campzone> TestCampDBJeolla(int idx) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT rownum, cpname, cpInduty, lat, lng, addr FROM TM_CAMPINGZONE_EXAL WHERE addr LIKE '%전라%'";
			if(idx != 0) {
				sql += " and rownum =" + idx;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("idx"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpInduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
	}
	
	// 경상 게시판 테이블 생성 준비 
	public List<campzone> TestCampDBGyeongsang(int idx) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT rownum, cpname, cpInduty, lat, lng, addr FROM TM_CAMPINGZONE_EXAL WHERE addr LIKE '%경상%'";
			if(idx != 0) {
				sql += " where rownum =" + idx;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("idx"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpInduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
	}
	
	// 강원 게시판 테이블 생성 준비 
	public List<campzone> TestCampDBGangwon(int idx) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT rownum, cpname, cpInduty, lat, lng, addr FROM TM_CAMPINGZONE_EXAL WHERE addr LIKE '%강원%'";
			if(idx != 0) {
				sql += " and rownum =" + idx;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("idx"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpInduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
	}
	
	// 제주 게시판 테이블 생성 준비 
	public List<campzone> TestCampDBJeju(int idx) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT rownum, cpname, cpInduty, lat, lng, addr FROM TM_CAMPINGZONE_EXAL WHERE addr LIKE '%제주%'";
			if(idx != 0) {
				sql += " where rownum =" + idx;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("idx"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpinduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
	}
	
	// 충청도 캠핑장 테이블 생성 준비 
	public List<campzone> TestChongchungDB(int rownum) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;
		// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			sql = "SELECT rownum, cpname, cpinduty, lat, lng, addr FROM TM_CAMPINGZONE_EXAL WHERE addr LIKE '%충청%'";
			if(rownum != 0) {
				sql += " and rownum =" + rownum;
			}			
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("rownum"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpinduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		// System.out.println(result);
		return  _campzone;	
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

			sql = "SELECT a.EMPNO, a.ENAME, a.JOB, a.mgr, b.ename MgrName, a.HIREDATE, a.SAL, a.COMM, a.DEPTNO "
					+ " FROM EMP a left outer join EMP b "
					+ " ON a.MGR = b.empno";
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
				//3. mgr이 null일 경우 정용진

				if(rs.getString("mgr") != null ) {
					semp.setMGR(chageMgr(rs.getString("mgr")));
				} else {
					semp.setMGR("정용진");
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

	//리스트 게시판용 쿼리 mariadb라서 강제로 rownum 생성 한것을 다시 오라클db로 변경
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
					semp.setMGR("정용진");
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

	// CampDBLists 게시판
	public List<campzone> CampDBLists(int start, int end, String strValue) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;

		// 게시판용 리스트
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			strValue = "%" + strValue + "%";
 
			sql = "SELECT * FROM TM_CAMPINGZONE_1"
					+ " WHERE CPNAME LIKE ?"
					+ " and idx >= ? and idx <= ?"; //and 절이 누락되어 오류가 발생되었음

			psmt = conn.prepareStatement(sql);	
			psmt.setString(1, strValue);
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			rs = psmt.executeQuery();
			while (rs.next()) {
				campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("idx"));
	            System.out.println(rs.getInt("idx"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setcptel(rs.getString("cptel"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		return _campzone;	

	}	
	
// 경기 게시판
	public List<campzone> CampDBListsGyeonggi(int start, int end, String strValue) {
		List<campzone> _campzone = new ArrayList<campzone>();
		String result = "a";
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;

		// 게시판용 리스트
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			strValue = "%" + strValue + "%";
 
			sql =  "select rnum, cpname, cpinduty, lat, lng, addr from "
					+ " (SELECT rownum as rnum, cpname, cpInduty, lat, lng, addr "
					+ "	 FROM TM_CAMPINGZONE_EXAL "
					+ "	 WHERE (addr like '%경기%' or addr like '%서울%') "
					+ "	 and CPNAME LIKE ? ) subTB "
					+ "	 where rnum >= ? and rnum <= ?"; //and 절이 누락되어 오류가 발생되었음

			psmt = conn.prepareStatement(sql);	
			psmt.setString(1, strValue);
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			rs = psmt.executeQuery();
			while (rs.next()) {
				campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("rnum"));
	            System.out.println(rs.getInt("rnum"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpInduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		return _campzone;	

	}

	// 전라 게시판
		public List<campzone> CampDBListsJeolla(int start, int end, String strValue) {
			List<campzone> _campzone = new ArrayList<campzone>();
			String result = "a";
			Connection conn = null; //import java.sql.Connection;
			PreparedStatement psmt = null; //import java.sql.PreparedStatement;
			ResultSet rs = null; //import java.sql.ResultSet;

			// 게시판용 리스트
			try {			
				conn = DBConnectionManager.getConnection();			
				//쿼리문
				//String sql = "SELECT * FROM EMP e";
				String sql = "";
				strValue = "%" + strValue + "%";
	 
				sql = "select rnum, cpname, cpinduty, lat, lng, addr from "
						+ " (SELECT rownum as rnum, cpname, cpInduty, lat, lng, addr "
						+ "	 FROM TM_CAMPINGZONE_EXAL "
						+ "	 WHERE addr like '%전라%' "
						+ "	 and CPNAME LIKE ? ) subTB "
						+ "	 where rnum >= ? and rnum <= ?"; //and 절이 누락되어 오류가 발생되었음

				psmt = conn.prepareStatement(sql);	
				psmt.setString(1, strValue);
				psmt.setInt(2, start);
				psmt.setInt(3, end);
				rs = psmt.executeQuery();
				while (rs.next()) {
					campzone scampzone = new campzone();
		            scampzone.setIdx(rs.getInt("rnum"));
		            System.out.println(rs.getInt("rnum"));
		            scampzone.setCpname(rs.getString("cpname"));
		            scampzone.setCpInduty(rs.getString("cpInduty"));
		            scampzone.setLat(rs.getDouble("lat"));
		            scampzone.setLng(rs.getDouble("lng"));
		            scampzone.setAddr(rs.getString("addr"));
		            _campzone.add(scampzone);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnectionManager.close(rs, psmt, conn);
			}		
			return _campzone;	

		}

		// 경상 게시판
		public List<campzone> CampDBListsGyeongsang(int start, int end, String strValue) {
			List<campzone> _campzone = new ArrayList<campzone>();
			String result = "a";
			Connection conn = null; //import java.sql.Connection;
			PreparedStatement psmt = null; //import java.sql.PreparedStatement;
			ResultSet rs = null; //import java.sql.ResultSet;

			// 게시판용 리스트
			try {			
				conn = DBConnectionManager.getConnection();			
				//쿼리문
				//String sql = "SELECT * FROM EMP e";
				String sql = "";
				strValue = "%" + strValue + "%";
	 
				sql = "select rnum, cpname, cpinduty, lat, lng, addr from "
						+ " (SELECT rownum as rnum, cpname, cpInduty, lat, lng, addr "
						+ "	 FROM TM_CAMPINGZONE_EXAL "
						+ "	 WHERE addr like '%경상%' "
						+ "	 and CPNAME LIKE ? ) subTB "
						+ "	 where rnum >= ? and rnum <= ?"; //and 절이 누락되어 오류가 발생되었음

				psmt = conn.prepareStatement(sql);	
				psmt.setString(1, strValue);
				psmt.setInt(2, start);
				psmt.setInt(3, end);
				rs = psmt.executeQuery();
				while (rs.next()) {
					campzone scampzone = new campzone();
		            scampzone.setIdx(rs.getInt("rnum"));
		            System.out.println(rs.getInt("rnum"));
		            scampzone.setCpname(rs.getString("cpname"));
		            scampzone.setCpInduty(rs.getString("cpInduty"));
		            scampzone.setLat(rs.getDouble("lat"));
		            scampzone.setLng(rs.getDouble("lng"));
		            scampzone.setAddr(rs.getString("addr"));
		            _campzone.add(scampzone);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnectionManager.close(rs, psmt, conn);
			}		
			return _campzone;	

		}
		
		// 강원 게시판
		public List<campzone> CampDBListsGangwon(int start, int end, String strValue) {
			List<campzone> _campzone = new ArrayList<campzone>();
			String result = "a";
			Connection conn = null; //import java.sql.Connection;
			PreparedStatement psmt = null; //import java.sql.PreparedStatement;
			ResultSet rs = null; //import java.sql.ResultSet;

			// 게시판용 리스트
			try {			
				conn = DBConnectionManager.getConnection();			
				//쿼리문
				//String sql = "SELECT * FROM EMP e";
				String sql = "";
				strValue = "%" + strValue + "%";
	 
				sql = "select rnum, cpname, cpinduty, lat, lng, addr from "
						+ " (SELECT rownum as rnum, cpname, cpInduty, lat, lng, addr "
						+ "	 FROM TM_CAMPINGZONE_EXAL "
						+ "	 WHERE addr like '%강원%' "
						+ "	 and CPNAME LIKE ? ) subTB "
						+ "	 where rnum >= ? and rnum <= ?";

				psmt = conn.prepareStatement(sql);	
				psmt.setString(1, strValue);
				psmt.setInt(2, start);
				psmt.setInt(3, end);
				rs = psmt.executeQuery();
				while (rs.next()) {
					campzone scampzone = new campzone();
		            scampzone.setIdx(rs.getInt("rnum"));
		            System.out.println(rs.getInt("rnum"));
		            scampzone.setCpname(rs.getString("cpname"));
		            scampzone.setCpInduty(rs.getString("cpInduty"));
		            scampzone.setLat(rs.getDouble("lat"));
		            scampzone.setLng(rs.getDouble("lng"));
		            scampzone.setAddr(rs.getString("addr"));
		            _campzone.add(scampzone);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnectionManager.close(rs, psmt, conn);
			}		
			return _campzone;	

		}
		
		// 제주 게시판
		public List<campzone> CampDBListsJeju(int start, int end, String strValue) {
			List<campzone> _campzone = new ArrayList<campzone>();
			String result = "a";
			Connection conn = null; //import java.sql.Connection;
			PreparedStatement psmt = null; //import java.sql.PreparedStatement;
			ResultSet rs = null; //import java.sql.ResultSet;

			// 게시판용 리스트
			try {			
				conn = DBConnectionManager.getConnection();			
				//쿼리문
				//String sql = "SELECT * FROM EMP e";
				String sql = "";
				strValue = "%" + strValue + "%";
	 
				sql = "select rnum, cpname, cpinduty, lat, lng, addr from "
						+ " (SELECT rownum as rnum, cpname, cpInduty, lat, lng, addr "
						+ "	 FROM TM_CAMPINGZONE_EXAL "
						+ "	 WHERE addr like '%제주%' "
						+ "	 and CPNAME LIKE ? ) subTB "
						+ "	 where rnum >= ? and rnum <= ?"; //and 절이 누락되어 오류가 발생되었음

				psmt = conn.prepareStatement(sql);	
				psmt.setString(1, strValue);
				psmt.setInt(2, start);
				psmt.setInt(3, end);
				rs = psmt.executeQuery();
				while (rs.next()) {
					campzone scampzone = new campzone();
		            scampzone.setIdx(rs.getInt("rnum"));
		            System.out.println(rs.getInt("rnum"));
		            scampzone.setCpname(rs.getString("cpname"));
		            scampzone.setCpInduty(rs.getString("cpInduty"));
		            scampzone.setLat(rs.getDouble("lat"));
		            scampzone.setLng(rs.getDouble("lng"));
		            scampzone.setAddr(rs.getString("addr"));
		            _campzone.add(scampzone);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnectionManager.close(rs, psmt, conn);
			}		
			return _campzone;	

		}
	
// 충청도 게시판
	public List<campzone> CampDBListChongchung(int start, int end, String strValue, String lction) {

		List<campzone> _campzone = new ArrayList<campzone>();
		System.out.println("start : "+start);
		System.out.println("end : "+end);
		System.out.println("lction" + lction);
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;

		// 충청 게시판용 리스트
		try {			

			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "";
			if(lction.equals("1")) {
				lction = "%서울%";
			} else if(lction.equals("2")) {
				lction = "%경기%";
			} else if(lction.equals("3")) {
				lction = "%강원%";
			} else if(lction.equals("4")) {
				lction = "%충청%";
			} else if(lction.equals("5")) {
				lction = "%전라%";
			} else if(lction.equals("6")) {
				lction = "%경상%";
			} else if(lction.equals("7")) {
				lction = "%제주%";
			}
			System.out.println("lction : " + lction);
			
			strValue = "%" + strValue + "%";

			sql = "select rnum, cpname, cpinduty, lat, lng, addr from "
					+ " (SELECT rownum as rnum, cpname, cpInduty, lat, lng, addr "
					+ " FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE addr like ? "
					+ " and CPNAME LIKE ? ) subTB"
					+ " where rnum >= ? and rnum <= ?"; 

			psmt = conn.prepareStatement(sql);	
			psmt.setString(1, lction);
			psmt.setString(2, strValue);
			psmt.setInt(3, start);
			psmt.setInt(4, end);

			rs = psmt.executeQuery();
			while (rs.next()) {
				campzone scampzone = new campzone();
	            scampzone.setIdx(rs.getInt("rnum"));
	            System.out.println(rs.getInt("rnum"));
	            scampzone.setCpname(rs.getString("cpname"));
	            scampzone.setCpInduty(rs.getString("cpInduty"));
	            scampzone.setLat(rs.getDouble("lat"));
	            scampzone.setLng(rs.getDouble("lng"));
	            scampzone.setAddr(rs.getString("addr"));
	            _campzone.add(scampzone);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		return _campzone;	
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

	//CampList.jsp의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountCP(String strValue) {
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

			sql = "SELECT COUNT(idx) AS CNT FROM TM_CAMPINGZONE_1 \r\n"
					+ "WHERE cpname like ?";						
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
	
	//경기도의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountGyeonggi(String strValue) {
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

			sql = "SELECT COUNT(rownum) AS CNT FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE (addr like '%경기%' or addr like '%서울%')  and cpname like ?";						
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
	
	//전라도의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountJeolla(String strValue) {
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

			sql = "SELECT COUNT(rownum) AS CNT FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE addr like '%전라%' and cpname like ?";						
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
	
	//경상도의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountGyeongsang(String strValue) {
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

			sql = "SELECT COUNT(rownum) AS CNT FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE addr like '%경상%' and  cpname like ?";						
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
	
	//강원도의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountGangwon(String strValue) {
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

			sql = "SELECT COUNT(rownum) AS CNT FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE addr like '%강원%' and  cpname like ?";						
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
	
	//제주도의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountJeju(String strValue) {
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

			sql = "SELECT COUNT(rownum) AS CNT FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE addr like '%제주%' and cpname like ?";						
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
	
	//CampListChongchung.jsp의 전체데이터의 갯수 구하기(캠핑)
	public int getDataTotalCountChongchung(String strValue) {
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

			sql = "SELECT COUNT(rownum) AS CNT FROM TM_CAMPINGZONE_EXAL"
					+ " WHERE addr like '%충청%' and cpname like ?";						
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

//emp리스트 세부정보
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
					+ " where a.empno ="+empno;		

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
					semp.setMGR("정용진");
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
	
	//캠핑 세부정보
	public campzone CampDBDetail(String cpname) {
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

			sql = "SELECT cpname, cpLineIntro, cpLctCl, addr, cpPosblFcltyCl, cptel, cpAnimalCmgCl, cpInduty FROM tm_campingzone_1 where cpname ='"+cpname+"'";

			psmt = conn.prepareStatement(sql);			

			rs = psmt.executeQuery();


			while (rs.next()) {				
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


	//새로운 검색 기능 테스트 1조 참조
	public List<campzone> selectSearchInfoList(String cpname, String cptel, Double lat, Double lng, String addr) {
		campzone scamp = new campzone();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<campzone> searchInfoList = null;
		
		searchInfoList = new ArrayList<campzone>();
		
		//Select 검색 키워드 리스트
		try {
			conn = DBConnectionManager.getConnection();

			String sql = "";
			sql = "SELECT cpname, cptel, lat, lng, addr FROM tm_campingzone_1";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while(rs.next()) {	
//				scamp.setCpname(rs.getString("cpname"));
//				scamp.setcptel(rs.getString("cptel"));
//				scamp.setLat(rs.getDouble("lat"));
//				scamp.setLng(rs.getDouble("lng"));
//				scamp.setAddr(rs.getString("addr"));
			    campzone campzons = new campzone();
			    campzons.setCpname(rs.getString("cpname"));
			    campzons.setcptel(rs.getString("cptel"));
			    campzons.setLat(rs.getDouble("lat"));
			    campzons.setLng(rs.getDouble("lng"));
			    campzons.setAddr(rs.getString("addr"));
			    searchInfoList.add(campzons);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);			
		}
		return searchInfoList;
	}
	
	//충청도(Database 자료에서 선택한 값을 공공api에서 호출하기)
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
				iresult = InsertCampDtCC(cpname, cptel, lat, lng, addr, cpLineIntro, cpLctCl, cpPosblFcltyCl, cpHomepage, cpAnimalCmgCl, cpInduty);
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

	private int InsertCampDtCC(String cpname, String cptel, double lat, double lng, String addr, String cpLineIntro, String cpLctCl, String cpPosblFcltyCl, String cpHomepage, String cpAnimalCmgCl, String cpInduty) {
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
