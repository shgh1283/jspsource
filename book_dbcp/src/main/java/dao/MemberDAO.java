package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BookDTO;
import dto.ChangeDTO;
import dto.MemberDTO;

public class MemberDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
//	static {
//		// 드라이버 로드
//	try {	
//		Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//		e.printStackTrace();
//		}
//	}
	public Connection getConnection() {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
			 con = ds.getConnection();
			//etc.
		} catch (Exception e) {
			e.printStackTrace();
		} return con;
	}
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection con, PreparedStatement pstmt) {
		try {	
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CRUD 메소드
	
	// R(Read) - 전체 조회, 특성 조회, 제목 조회
	// 조회 메소드 작성하기
	// 리턴타입 : List<~~~DTO> or DTO ==> sql 구문 보고 결정
	//  List<~~~DTO> : where 절 없는 경우, where 절이 pk 가 아니면
	// ~~~DTO where 절이 pk인 경우
	public MemberDTO isLogin(MemberDTO loginDto) {
		
		MemberDTO dto = null;
		
		try {
			con=getConnection();
			
			String sql ="SELECT *FROM membertbl WHERE userid=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginDto.getUserid());	
			pstmt.setString(2, loginDto.getPassword());	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt, rs);
		}
		return dto;
	}
	
	public boolean dupId(String userid) {
		
		MemberDTO dto = null;
		
		try {
			con=getConnection();
			
			String sql ="SELECT *FROM membertbl WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);	
			rs = pstmt.executeQuery();
			if(rs.next()) { // 아이디 있음
				return false;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt, rs);
		}
		return true; // 증복아이디 없음
	}
	

	public int insert(MemberDTO insertDto) {
		int insertRow = 0;
		try {
			con = getConnection();
			String sql = "INSERT INTO membertbl VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getUserid());
			pstmt.setString(2, insertDto.getName());
			pstmt.setString(3, insertDto.getPassword());
			
			insertRow =pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt);
		}
		return insertRow;
	}	
	

	public int update(ChangeDTO changeDto) {
		int updateRow=0;
		try {
			con= getConnection();
	
			String sql = "UPDATE MEMBERTBL SET PASSWORD = ? WHERE USERID =? AND PASSWORD =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, changeDto.getChangePassword());
			pstmt.setString(2, changeDto.getUserid());
			pstmt.setString(3, changeDto.getCurrentPassword());
			
			updateRow= pstmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(con,pstmt);
		} return updateRow;
	}
	}


	