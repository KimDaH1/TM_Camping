package camping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import camping.dto.OrderDto;
import camping.oracle.DBConnectionManager;

public class OrderDao {

	// insert
	public int insertOrderInfo(String o_number, int amount, String paytype, String userid, int r_number, String o_state) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
//			String sql = "insert into tm_order(o_number, amount, order_item, usernumber)"
//					+ " values( ?, ?, ?, ?)";
			String sql = "insert into tm_order "
					+ " values( (select NVL(MAX(pay_no),0)+1 from tm_order), ?, ?, to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?, ?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, o_number);
			psmt.setInt(2, amount);
			psmt.setString(3, paytype);
			psmt.setString(4, userid);
			psmt.setInt(5, r_number);
			psmt.setString(6, o_state);

			result = psmt.executeUpdate();
			//System.out.println(o_number);
			//System.out.println(amount);
			System.out.println("처리결과:" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}

		return result;
	}
	
	//결제 내역 페이지에서 보여줄 리스트
	public List<OrderDto>  getOrderList(String userId) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<OrderDto> orderList = null;
		
		try {
			conn = DBConnectionManager.getConnection();
			
			//결제 내역 리스트
			String sql = "select * from tm_order "
						+"where userid = ? "
						+"and o_state = '결제됨' "
						+"order by pay_no ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			rs = psmt.executeQuery();
			
			
			orderList = new ArrayList<OrderDto>();
			
			while (rs.next()) {
				OrderDto orderDto = new OrderDto();
				orderDto.setPay_no(rs.getInt("pay_no"));
				orderDto.setOrder_no(rs.getString("order_no"));
				orderDto.setAmount(rs.getInt("amount"));
				orderDto.setOrder_date(rs.getString("order_date"));
				orderDto.setPaytype(rs.getString("paytype"));
				orderDto.setUserid(rs.getString("userid"));
				orderDto.setR_number(rs.getInt("r_number"));
				orderDto.setO_state(rs.getString("o_state"));
				
				orderList.add(orderDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}
		
		for (OrderDto orderDto : orderList) {
			System.out.println(orderDto.getPay_no());
		}
		
		return orderList;
	}
	
	//결제 페이지에서 예약 번호와 캠핑장 아이디로 조인을 통해
	//캠핑장 이름과 예약 금액을 가져온다.
	public String getCampName(int c_id) {
		
		String campName = null;
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select c.cpname "
					   + "from tm_reservation r, tm_campingzone_chongchung c "
					   + "where r.c_id = c.idx "
					   + "and r_number = ?";						
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
	
	//예약 금액 가져 온다.
	public int getAmount(int c_id) {
		
		int amount = 0;
		Connection conn = null; //import java.sql.Connection;
		PreparedStatement psmt = null; //import java.sql.PreparedStatement;
		ResultSet rs = null; //import java.sql.ResultSet;// 
		try {			
			conn = DBConnectionManager.getConnection();			
			//쿼리문
			//String sql = "SELECT * FROM EMP e";
			String sql = "select r.amount "
					+ "from tm_reservation r, tm_campingzone_chongchung c "
					+ "where r.c_id = c.idx "
					+ "and r_number = ?";						
			psmt = conn.prepareStatement(sql);	
			psmt.setInt(1, c_id);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				amount = rs.getInt("amount");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);
		}		
		
		return amount;
	}
	
	public int cancelOrderInfo(int pay_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "update tm_order"
						+" SET o_state = '취소됨'"
						+" WHERE pay_no = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, pay_no);
			
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
