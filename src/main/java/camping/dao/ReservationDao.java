package camping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import camping.dto.ReservationDto;
import camping.oracle.DBConnectionManager;

public class ReservationDao {

	// insert
	public int insertReservationInfo(String sDate, String eDate, int usernumber, int c_id) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "insert into tm_reservation"
					+ " values( (select NVL(MAX(r_number),0)+1 from tm_reservation), ?, ?, ?, ?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sDate);
			psmt.setString(2, eDate);
			psmt.setInt(3, usernumber);
			psmt.setInt(4, c_id);

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

			// 쿼리문!
			String sql = "select * "
					+ " from tm_reservation"
					+ " where usernumber = 1";

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