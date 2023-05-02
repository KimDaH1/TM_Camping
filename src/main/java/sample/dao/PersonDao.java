package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sample.dto.PersonDto;
import sample.oracle.DBConnectionManager;

public class PersonDao {

	//select
	public List<PersonDto> selectPersonInfoList(){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<PersonDto> personInfoList = null;

		//Select 리스트 전체
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			//String sql = "select * from emp where empno = 7566";
			String sql = "select * from t_person_info";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery(); //쿼리를 실행!!

			personInfoList = new ArrayList<PersonDto>();

			while(rs.next()) {
				PersonDto personDto = new PersonDto();

				personDto.setId(rs.getInt("id"));
				personDto.setName(rs.getString("name"));

				personInfoList.add(personDto);
			}

			// DB에 쿼리문 실행
			// 쿼리 결과를 반환 -> 활용
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);			
		}

		return personInfoList;		
	}

	public PersonDto selectPersonInfoById(int id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		PersonDto personDto = null;

		//select 한개 단일
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "select * from t_person_info"
					+" WHERE id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			rs = psmt.executeQuery(); //쿼리를 실행!!

			if(rs.next()) {
				personDto = new PersonDto();

				personDto.setId(rs.getInt("id"));
				personDto.setName(rs.getString("name"));
			}

			// DB에 쿼리문 실행
			// 쿼리 결과를 반환 -> 활용
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionManager.close(rs, psmt, conn);			
		}

		return personDto;
	}

	//update
	public int updatePersonInfo(int id, String name) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "update t_person_info"
					+" SET name = ?"
					+" WHERE id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setInt(2, id);

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

	//update
	public int updatePersonInfo(PersonDto personDto) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "update t_person_info"
					+" SET name = ?"
					+" WHERE id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, personDto.getName());
			psmt.setInt(2, personDto.getId());

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

	//insert
	public int insertPersonInfo(String name) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "insert into t_person_info"
					+" values( (select NVL(MAX(id),0)+1 FROM t_person_info)  , ?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);

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

	//delete
	public int deletePersonInfo(int id) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = DBConnectionManager.getConnection();

			// 쿼리문!
			String sql = "DELETE FROM t_person_info"
					+" WHERE id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

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
