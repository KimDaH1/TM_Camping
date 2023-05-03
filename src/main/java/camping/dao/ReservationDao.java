package camping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import camping.oracle.DBConnectionManager;

public class ReservationDao {

	// insert
	public int insertReservationInfo(String sDate, String eDate) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "insert into tm_reservation"
					+ " values( (select NVL(MAX(r_number),0)+1 from tm_reservation)  , ?, ?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sDate);
			psmt.setString(2, eDate);

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
}