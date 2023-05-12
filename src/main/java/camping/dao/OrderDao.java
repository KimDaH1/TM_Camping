package camping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import camping.oracle.DBConnectionManager;

public class OrderDao {

	// insert
		public int insertOrderInfo(String o_number, int amount, String paytype, int userNumber, int r_id) {

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = DBConnectionManager.getConnection();

				// 쿼리문!
//				String sql = "insert into tm_order(o_number, amount, order_item, usernumber)"
//						+ " values( ?, ?, ?, ?)";
				String sql = "insert into tm_order "
						+ " values( (select NVL(MAX(orderno),0)+1 from tm_order), ?, ?, to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?)";

				psmt = conn.prepareStatement(sql);
				psmt.setString(1, o_number);
				psmt.setInt(2, amount);
				psmt.setString(3, paytype);
				psmt.setInt(4, userNumber);
				psmt.setInt(5, r_id);

				result = psmt.executeUpdate();
				System.out.println(o_number);
				System.out.println(amount);
				System.out.println(userNumber);
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
