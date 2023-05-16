package camping.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import camping.dto.ReservationDto;
import camping.oracle.DBConnectionManager;

public class ReservationDao {

	// insert
	public int insertReservationInfo(String s_date, String e_date, String amount, int userid, int c_id, String r_state) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "insert into tm_reservation(r_number, s_date, e_date, amount, userid, c_id, r_state)"
					+ " values( (select NVL(MAX(r_number),0)+1 from tm_reservation), ?, ?, ?, ?, ?, ?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s_date);
			psmt.setString(2, e_date);
			psmt.setString(3, amount);
			psmt.setInt(4, userid);
			psmt.setInt(5, c_id);
			psmt.setString(6, r_state);

			result = psmt.executeUpdate();

			System.out.println("처리결과:" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}

		return result;
	}
	
	//insert 날짜 중복체크 적용
	public int insertReservation(String s_date, String e_date, String amount, String userid, int c_id, String r_state) {

		Connection conn = null;
		//PreparedStatement psmt = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "{ call date_duplicate_check_test(?, ?, ?, ?, ?, ?, ?) }";

			cstmt = conn.prepareCall(sql);
			//cstmt.setDate(1, java.sql.Date.valueOf(sDate));
			cstmt.setString(1, s_date);
			//cstmt.setDate(2, java.sql.Date.valueOf(eDate));
			cstmt.setString(2, e_date);
			cstmt.setString(3, amount);
			cstmt.setString(4, userid);
			cstmt.setInt(5, c_id);
			cstmt.setString(6, r_state);
			cstmt.registerOutParameter(7, Types.INTEGER);
			
			cstmt.execute();
			result = cstmt.getInt(7);
			

			System.out.println("처리결과:" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, cstmt, conn);
		}

		return result;
	}
	
	public int cancelReservationInfo(int r_number) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "update tm_reservation"
						+" SET r_state = '취소됨'"
						+" WHERE r_number = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, r_number);
			
			result = psmt.executeUpdate();
			
			System.out.println("처리결과:" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}
		
		return result;
	}
	
	public List<ReservationDto> getReservationList(String userid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<ReservationDto> reservationList = null;
		
		try {
			conn = DBConnectionManager.getConnection();

			// 예약 정보 리스트
			// 예약이 취소 되었거나, 결제가 된 상태이면 리스트에 제외
			String sql = "select r.r_number, s_date, e_date, r.amount, r.userid, c_id, nvl(o_state, '미결제')"
					+ " from tm_reservation r"
					+ " left outer join tm_order o"
					+ " on r.r_number = o.r_number"
					+ " where r.userid = ?"
					+ " and o.o_state is null"
					+ " and r_state = '예약됨'"
					+ " order by r.r_number";
			
			/*
			String sql = "select * "
					+ " from tm_reservation"
					+ " where usernumber = 1"
					+ " and r_state = '예약됨'"
					+ " order by r_number";
			*/
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userid);
			rs = psmt.executeQuery(); // 쿼리를 실행!!

			reservationList = new ArrayList<ReservationDto>();
			
			// rs.next() 다음 행으로 이동 후 데이터가 있으면 true, 없으면 false를 리턴
			while (rs.next()) {
				ReservationDto reservationDto = new ReservationDto();
				reservationDto = new ReservationDto();
				
				reservationDto.setR_number(rs.getInt("r_number"));
				reservationDto.setS_date(rs.getString("s_date"));
				reservationDto.setE_date(rs.getString("e_date"));
				reservationDto.setAmount(rs.getString("amount"));
				reservationDto.setUserid(rs.getString("userid"));
				reservationDto.setC_id(rs.getInt("c_id"));
				
				reservationList.add(reservationDto);
			}

			// DB에 쿼리문 실행
			// 쿼리 결과를 반환 -> 활용
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}
		
		return reservationList;
	}
	
	//userId -> 회원번호
	public int getUserNumber(String userId) {
		
		int userNumber = 0;
		
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select usernumber from tm_users where userid = ?";						
			psmt = conn.prepareStatement(sql);	
			psmt.setString(1, userId);

			rs = psmt.executeQuery();
			if (rs.next()) {
				userNumber = rs.getInt("usernumber");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		

		return userNumber;
	}
	
	public int getReservationCount(int userNumber) {
		int totalCount = 0;

		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select count(usernumber) from tm_reservation where usernumber = ?";						
			psmt = conn.prepareStatement(sql);	
			psmt.setInt(1, userNumber);

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
	
	public String getCampName(int c_id) {
		
		String campName = null;
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select cpname "
					   + "from tm_campingzone_chongchung "
					   + "where idx = ?";
			psmt = conn.prepareStatement(sql);	
			psmt.setInt(1, c_id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				campName = rs.getString("cpname");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		

		return campName;
	}
	
	public ReservationDto getDates(int r_number) {
		
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		
		ReservationDto reservationDto = null;
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select s_date, e_date from tm_reservation where r_number = ?";						
			psmt = conn.prepareStatement(sql);	
			psmt.setInt(1, r_number);

			rs = psmt.executeQuery();
			if (rs.next()) {
				reservationDto = new ReservationDto();
				reservationDto.setS_date(rs.getString("s_date"));
				reservationDto.setE_date(rs.getString("e_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		

		return reservationDto;
	}
	
	public String getUserName(int r_number) {
		
		String userName = null;
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select username "
					   + "from tm_reservation r, tm_users u "
					   + "where r.userid = u.userid "
					   + "and r_number = ?";
			psmt = conn.prepareStatement(sql);	
			psmt.setInt(1, r_number);

			rs = psmt.executeQuery();
			if (rs.next()) {
				userName = rs.getString("username");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		

		return userName;
	}
}