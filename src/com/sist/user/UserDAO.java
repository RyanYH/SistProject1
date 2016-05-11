package com.sist.user;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {
	static Connection conn;
	static PreparedStatement ps;
	static UserDAO dao;
	static final String URL = "jdbc:oracle:thin:@211.238.142.35:1521:ORCL";
	
	public UserDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static UserDAO newInstance() {
		if (dao == null){
			dao = new UserDAO();
		}
		return dao;
	}
	
	public static void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "scott", "tiger");
			conn.setAutoCommit(true);
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void disConnection() throws Exception {
		if (ps != null){
			ps.close();
		}
		
		if (conn != null){
			conn.close();
		}
	}
	
	public boolean isLogin(String id, String pwd) throws Exception{
		boolean res = false;
		
		try {
			getConnection();
			String sql = "select count(*) from gameuser where id =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int count = rs.getInt(1);
			
			if (count == 0){
				res = false;
			} else {
				sql = "select pwd from gameuser where id=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				rs.next();
				
				String getPwd = rs.getString(1);
				System.out.println(getPwd);
				
				if (getPwd.equals(pwd)) {
					res = true;
				} else {
					res = false;
				}
			}
			System.out.println(res);
			
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}
		return res;
	}
	
	public static boolean userInsert(String id, String nick, String pwd, String gender, String avatar) throws Exception{
		boolean check = false;
		
		try {
			getConnection();
			String sql = "insert into gameuser values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, nick);
			ps.setString(3, pwd);
			ps.setString(4, gender);
			ps.setString(5, avatar);
			
			int result = ps.executeUpdate();
			
			if (result > 0) {
				check = true;
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}
		
		return check;
	}
	
	public static UserDTO userDataByid(String id) throws Exception{
		UserDTO d = new UserDTO();
		
		try {
			getConnection();
			String sql = "select * from gameuser where id =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			d.setId(rs.getString(1));
			d.setNickname(rs.getString(2));
			d.setPwd(rs.getString(3));
			d.setGender(rs.getString(4));
			d.setAvatar(rs.getString(5));
			
		} catch (Exception ex){
			System.out.println(ex.getMessage()+4);
		} finally {
			disConnection();
		}
		return d;
	}
	
	public static UserDTO userDataBynick(String nick) throws Exception{
		UserDTO d = new UserDTO();
		
		try {
			getConnection();
			String sql = "select * from gameuser where nick =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nick);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			d.setId(rs.getString(1));
			d.setNickname(rs.getString(2));
			d.setPwd(rs.getString(3));
			d.setGender(rs.getString(4));
			d.setAvatar(rs.getString(5));
			
		} catch (Exception ex){
			System.out.println(ex.getMessage()+3);
		} finally {
			disConnection();
		}
		return d;
	}
	
	public static boolean checkID(String id) throws Exception {
		boolean res = false;
		try {
			getConnection();
			String sql = "select count(*) from gameuser where id =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int count = rs.getInt(1);
			
			if (count != 0){
				res = true;
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage()+2);
		} finally {
			disConnection();
		}
		return res;
	}
	
	public static boolean checkNick(String nick) throws Exception {
		boolean res = false;
		try {
			getConnection();
			String sql = "select count(*) from gameuser where nick =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nick);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int count = rs.getInt(1);
			
			if (count != 0){
				res = true;
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage()+1);
		} finally {
			disConnection();
		}
		return res;
	}
}
