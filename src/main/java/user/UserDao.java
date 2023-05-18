package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//암호화 복호화를 위함 import
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import user.DBConnectionManager;

public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String result;
	
	//알고리즘
	String alg = "AES/CBC/PKCS5Padding";
	//키
	String aesKey = "abcdefghabcdefghabcdefghabcdefgh"; //32byte

	String aesIv = "0123456789abcdef"; //16byte
	

	public UserDao() {

		try {
			
			String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID = "scott";
			String dbPassword = "tiger";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
	}

	
	public int login(String userID, String userPassword) {

		String SQL = "SELECT userPassword FROM TM_USERS WHERE userID = ?";
		try {
			
			conn = DBConnectionManager.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String encrypedPassword = rs.getString(1); //DB에서 암호화된 패스워드를 가져옴
				// 복호화할 때 사용한 알고리즘, 키 초기화 백터(IV) 정보
				//알고리즘 aes-256 암호화 해석 코드 **********[복호화]**********
				Cipher cipher = Cipher.getInstance(alg);
				SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
				IvParameterSpec ivParamSpec = new IvParameterSpec(aesIv.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
				//암호화 해석
				byte[] decodedPassword = Base64.getDecoder().decode(encrypedPassword);
				System.out.println("DB에서 불러온 암호화된 비밀번호 : " + encrypedPassword);
	
				byte[] decryptedPassword = cipher.doFinal(decodedPassword);
				System.out.println("1차 복호화된 비밀번호 : " + decodedPassword);
				
				String decryptedPasswordString = new String(decryptedPassword, "UTF-8");
				System.out.println("복호화된 비밀번호 : " + decryptedPasswordString);
				
				if(decryptedPasswordString.equals(userPassword)) {
					return 1; //로그인성공
				} else {
					return 0; //비밀번호 불일치
				}

			}
			return -1; //아이디가없음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
	}
	

	public int join(UserDto user) {
		//암호화 할 유저 패스워드
		String userPassword = user.getUserPassword();
		//암호화된 유저 아이디
		String encPassword = "";
		
		try {

			Cipher cipher = Cipher.getInstance(alg);

			//키로 비밀키 생성
			SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
			//iv 로 spec 생성
			IvParameterSpec ivParamSpec = new IvParameterSpec(aesIv.getBytes());
			//암호화 적용
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
			System.out.println("입력된 비밀번호 : " + userPassword);
			//암호화 실행
			byte[] encrypted1 = cipher.doFinal(userPassword.getBytes("UTF-8")); // ID 암호화(인코딩 설정)
			System.out.println("1차 암호화된 비밀번호 : " + encrypted1);
			encPassword = Base64.getEncoder().encodeToString(encrypted1); // 암호화 인코딩 후 저장
		} catch (Exception e) {
			System.out.println("암호화 중 오류 발생 ");
			e.printStackTrace();
		}
		int result = 0;
		String SQL = "INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, "
				+ " userEmail, userPhone)"
				+ " values((SELECT NVL(MAX(usernumber),0)+1 from tm_users), ?, ?, ?, ?, ?, ?)";
		try {
			
			conn = DBConnectionManager.getConnection();
			
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			System.out.println("AES-256 암호화된 비밀번호 : " + encPassword);
			pstmt.setString(2, encPassword);//암호화된 비밀번호 저장
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setString(6, user.getUserPhone());
			result = pstmt.executeUpdate();
			System.out.println("회원가입 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; //데이터베이스 오류 
	}
}
