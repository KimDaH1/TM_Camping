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
	public int insertReservationInfo(String sDate, String eDate, int usernumber, int c_id, String r_state) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "insert into tm_reservation(r_number, s_date, e_date, usernumber, c_id, r_state)"
					+ " values( (select NVL(MAX(r_number),0)+1 from tm_reservation), ?, ?, ?, ?, ?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sDate);
			psmt.setString(2, eDate);
			psmt.setInt(3, usernumber);
			psmt.setInt(4, c_id);
			psmt.setString(5, r_state);

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
	public int insertReservation(String sDate, String eDate, int usernumber, int c_id, String r_state) {

		Connection conn = null;
		//PreparedStatement psmt = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "{ call date_duplicate_check_test(?, ?, ?, ?, ?, ?) }";

			cstmt = conn.prepareCall(sql);
			//cstmt.setDate(1, java.sql.Date.valueOf(sDate));
			cstmt.setString(1, sDate);
			//cstmt.setDate(2, java.sql.Date.valueOf(eDate));
			cstmt.setString(2, eDate);
			cstmt.setInt(3, usernumber);
			cstmt.setInt(4, c_id);
			cstmt.setString(5, r_state);
			cstmt.registerOutParameter(6, Types.INTEGER);
			
			cstmt.execute();
			result = cstmt.getInt(6);
			

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
	
	public List<ReservationDto> getReservationList(int userNumber) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<ReservationDto> reservationList = null;
		
		try {
			conn = DBConnectionManager.getConnection();

			// 예약 정보 리스트
			// 예약이 취소 되었거나, 결제가 된 상태이면 리스트에 제외
			String sql = "select r.r_number, s_date, e_date, r.usernumber, c_id, nvl(o_state, '미결제')"
					+ " from tm_reservation r"
					+ " left outer join tm_order o"
					+ " on r.r_number = o.r_number"
					+ " where r.usernumber = 1"
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

			rs = psmt.executeQuery(); // 쿼리를 실행!!

			reservationList = new ArrayList<ReservationDto>();
			
			// rs.next() 다음 행으로 이동 후 데이터가 있으면 true, 없으면 false를 리턴
			while (rs.next()) {
				ReservationDto reservationDto = new ReservationDto();
				reservationDto = new ReservationDto();
				
				reservationDto.setR_id(rs.getInt("r_number"));
				reservationDto.setsDate(rs.getString("s_date"));
				reservationDto.seteDate(rs.getString("e_date"));
				reservationDto.setUserNumber(rs.getInt("usernumber"));
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
}