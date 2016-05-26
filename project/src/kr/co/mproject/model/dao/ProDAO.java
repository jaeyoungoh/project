package kr.co.mproject.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.mproject.cont.SetupDB;
import kr.co.mproject.model.dto.ProDTO;
import kr.co.mproject.model.dto.UserDTO;

public class ProDAO {
	private ProDTO pDto;
	private UserDTO uDto;
	private SetupDB db;
	private StringBuilder sql;
	private List<ProDTO> list;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	public List<ProDTO> listviewProject() {

		try {
			db = new SetupDB();
			list = new ArrayList<ProDTO>();
			sql = new StringBuilder(
					"select p_id, p_type, p_subject, p_contents, p_priority, p_upper, to_char(p_sdate,'YYYY-MM-DD'), to_char(p_edate,'YYYY-MM-DD'), p_depth, u_id, p_progress, p_use_yn from project order by p_id desc");
			rs = db.select(sql.toString());
			while (rs.next()) {
				pDto = new ProDTO();
				pDto.setP_id(rs.getInt("p_id"));
				pDto.setP_type(rs.getString("p_type"));
				pDto.setP_subject(rs.getString("p_subject"));
				pDto.setP_contents(rs.getString("p_contents"));
				pDto.setP_priority(rs.getString("p_priority"));
				pDto.setP_upper(rs.getInt("p_upper"));
				pDto.setP_sdate(rs.getString("to_char(p_sdate,'YYYY-MM-DD')"));
				pDto.setP_edate(rs.getString("to_char(p_edate,'YYYY-MM-DD')"));
				pDto.setP_depth(rs.getInt("p_depth"));
				pDto.setU_id(rs.getString("u_id"));
				pDto.setP_progress(rs.getInt("p_progress"));
				pDto.setP_use_yn(rs.getString("p_use_yn"));
				list.add(pDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeDB();
		}

		return list;
	}

	public Map<String, Object> viewProject(int p_id) {

		db = new SetupDB();
		pDto = new ProDTO();
		uDto = new UserDTO();
		Map<String, Object> map = new HashMap<String, Object>();
		sql = new StringBuilder();
		sql.append(
				"select p_id, p_type, p_subject, p_contents, p_priority, p_upper, to_char(p_sdate,'YYYY-MM-DD'), to_char(p_edate,'YYYY-MM-DD'), p_depth, U.U_id, p_progress, p_use_yn, U.U_NAME, U.U_EMAIL from project P, USERS U where p_id = ? AND U.U_ID =P.U_ID order by p_id desc");

		try {

			pstmt = db.getCon().prepareStatement(sql.toString());
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pDto.setP_id(rs.getInt("p_id"));
				pDto.setP_type(rs.getString("p_type"));
				pDto.setP_subject(rs.getString("p_subject"));
				pDto.setP_contents(rs.getString("p_contents"));
				pDto.setP_priority(rs.getString("p_priority"));
				pDto.setP_upper(rs.getInt("p_upper"));
				pDto.setP_sdate(rs.getString("to_char(p_sdate,'YYYY-MM-DD')"));
				pDto.setP_edate(rs.getString("to_char(p_edate,'YYYY-MM-DD')"));
				pDto.setP_depth(rs.getInt("p_depth"));
				pDto.setU_id(rs.getString("U_id"));
				pDto.setP_progress(rs.getInt("p_progress"));
				pDto.setP_use_yn(rs.getString("p_use_yn"));
				uDto.setU_name(rs.getString("U_NAME"));
				uDto.setU_email(rs.getString("U_EMAIL"));
				map.put("pDto", pDto);
				map.put("uDto", uDto);

			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			db.closeDB();
		}

		return map;
	}

	public int updateProject(ProDTO pDto) {
		int chk = 0;
		db = new SetupDB();
		sql = new StringBuilder(
				"update project set  P_TYPE=?, P_SUBJECT=?, P_CONTENTS=?, P_PRIORITY=?, P_UPPER=?, P_SDATE=?, P_EDATE=?, P_DEPTH=?, U_ID=?, P_PROGRESS=?");
		sql.append(" where p_id=?");

		try {
			pstmt = db.getCon().prepareStatement(sql.toString());
			pstmt.setString(1, pDto.getP_type());
			pstmt.setString(2, pDto.getP_subject());
			pstmt.setString(3, pDto.getP_contents());
			pstmt.setString(4, pDto.getP_priority());
			pstmt.setInt(5, pDto.getP_upper());
			pstmt.setString(6, pDto.getP_sdate());
			pstmt.setString(7, pDto.getP_edate());
			pstmt.setInt(8, pDto.getP_depth());
			pstmt.setString(9, pDto.getU_id());
			pstmt.setInt(10, pDto.getP_progress());
			pstmt.setInt(11, pDto.getP_id());
			chk = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			db.closeDB();
		}

		return chk;
	}

	public int removeProject(int p_id) {
		db = new SetupDB();
		sql = new StringBuilder("update project set P_USE_YN='N' WHERE P_ID=?");
		int chk = 0;

		try {
			pstmt = db.getCon().prepareStatement(sql.toString());
			pstmt.setInt(1, p_id);
			chk = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			db.closeDB();
		}

		return chk;
	}

	public int addProject(ProDTO pDto) {
		int chk = 0;
		db = new SetupDB();
		sql = new StringBuilder(
				"insert into project(P_ID, P_TYPE, P_SUBJECT, P_CONTENTS, P_PRIORITY, P_UPPER, P_SDATE, P_EDATE, P_DEPTH, U_ID, P_PROGRESS) ");
		sql.append("values(SQE_PROJECT_P_ID.nextval,?,?,?,?,?,?,?,?,?,?)");
		try {
			pstmt = db.getCon().prepareStatement(sql.toString());
			pstmt.setString(1, pDto.getP_type());
			pstmt.setString(2, pDto.getP_subject());
			pstmt.setString(3, pDto.getP_contents());
			pstmt.setString(4, pDto.getP_priority());
			pstmt.setInt(5, pDto.getP_upper());
			pstmt.setString(6, pDto.getP_sdate());
			pstmt.setString(7, pDto.getP_edate());
			pstmt.setInt(8, pDto.getP_depth());
			pstmt.setString(9, pDto.getU_id());
			pstmt.setInt(10, pDto.getP_progress());
			chk = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			db.closeDB();
		}

		return chk;
	}

	public ProDTO modLoad(int p_id) {

		pDto = new ProDTO();
		db = new SetupDB();
		sql = new StringBuilder(
				"select p_id, p_type, p_subject, p_contents, p_priority, p_upper, to_char(p_sdate,'YYYY-MM-DD'), to_char(p_edate,'YYYY-MM-DD'), p_depth, u_id, p_progress, p_use_yn from project");
		sql.append(" where p_id=?");

		try {
			pstmt = db.getCon().prepareStatement(sql.toString());
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pDto.setP_id(rs.getInt("p_id"));
				pDto.setP_type(rs.getString("p_type"));
				pDto.setP_subject(rs.getString("p_subject"));
				pDto.setP_contents(rs.getString("p_contents"));
				pDto.setP_priority(rs.getString("p_priority"));
				pDto.setP_upper(rs.getInt("p_upper"));
				pDto.setP_sdate(rs.getString("to_char(p_sdate,'YYYY-MM-DD')"));
				pDto.setP_edate(rs.getString("to_char(p_edate,'YYYY-MM-DD')"));
				pDto.setP_depth(rs.getInt("p_depth"));
				pDto.setU_id(rs.getString("u_id"));
				pDto.setP_progress(rs.getInt("p_progress"));
				pDto.setP_use_yn(rs.getString("p_use_yn"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			db.closeDB();
		}

		return pDto;
	}

	public List<String> searchUid() {
		List<String> ulist = new ArrayList<String>();
		db = new SetupDB();
		StringBuilder sql = new StringBuilder("select u_id from users");
		rs = db.select(sql.toString());
		try {
			while (rs.next()) {

				ulist.add(rs.getString("u_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeDB();
		}

		return ulist;
	}

	public List<ProDTO> searchquery(String s_type, String s_query) {
		list = new ArrayList<ProDTO>();
		db = new SetupDB();
		StringBuilder sql = new StringBuilder("select p_id, p_type, p_subject, p_contents, p_priority, p_upper, to_char(p_sdate,'YYYY-MM-DD'), to_char(p_edate,'YYYY-MM-DD'), p_depth, u_id, p_progress, p_use_yn from project where ");
		if (s_type.equals("유형")) {
			sql.append("p_type=?");
		} else if (s_type.equals("우선순위")) {
			sql.append("p_priority=?");
		} else if (s_type.equals("제목")) {
			sql.append("p_subject Like %?%");
		} else if (s_type.equals("담당자")) {
			sql.append("u_id Like %?%");
		} else if (s_type.equals("번호")) {
			sql.append("p_id=?");
		}
		sql.append(" order by p_id desc");
		
		try {
			pstmt = db.getCon().prepareStatement(sql.toString());
			pstmt.setString(1, s_query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pDto = new ProDTO();
				pDto.setP_id(rs.getInt("p_id"));
				pDto.setP_type(rs.getString("p_type"));
				pDto.setP_subject(rs.getString("p_subject"));
				pDto.setP_contents(rs.getString("p_contents"));
				pDto.setP_priority(rs.getString("p_priority"));
				pDto.setP_upper(rs.getInt("p_upper"));
				pDto.setP_sdate(rs.getString("to_char(p_sdate,'YYYY-MM-DD')"));
				pDto.setP_edate(rs.getString("to_char(p_edate,'YYYY-MM-DD')"));
				pDto.setP_depth(rs.getInt("p_depth"));
				pDto.setU_id(rs.getString("u_id"));
				pDto.setP_progress(rs.getInt("p_progress"));
				pDto.setP_use_yn(rs.getString("p_use_yn"));
				list.add(pDto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeDB();
		}

		return list;
	}

}
