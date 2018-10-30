package com.servlet;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao {
	
	
	public  static String update(int sno,String name,String email,String gender,String mobile,InputStream inputStream){
		String sql="update students_tbl set name=?,email=?,gender=?,mobile=?,photo=? where sno=?";
		String message="";
		Connection conn=null;
		PreparedStatement statement=null;
		try {
						conn = DBConnectionPool.getConnFromPool();
						// constructs SQL statement
						 statement = conn.prepareStatement(sql);
						statement.setString(1, name);
						statement.setString(2,email);
						statement.setString(3, gender);
						statement.setString(4,mobile);
						if (inputStream != null) {
							// fetches input stream of the upload file for the blob column
							statement.setBlob(5, inputStream);
						}
						statement.setInt(6,sno);
						// sends the statement to the database server
						int row = statement.executeUpdate();
						if (row > 0) {
							message = "File updated and saved into database";
						}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return message;
	}	
	

	public  static String save(String name,String email,String gender,String mobile,InputStream inputStream,Timestamp doe){
		String sql="insert into students_tbl(name,email,gender,mobile,photo,doe) values(?,?,?,?,?,?)";
		String message="";
		Connection conn=null;
		PreparedStatement statement=null;
		try {
						conn = DBConnectionPool.getConnFromPool();
						// constructs SQL statement
						 statement = conn.prepareStatement(sql);
						statement.setString(1, name);
						statement.setString(2,email);
						statement.setString(3, gender);
						statement.setString(4,mobile);
						if (inputStream != null) {
							// fetches input stream of the upload file for the blob column
							statement.setBlob(5, inputStream);
						}
						statement.setTimestamp(6,doe);
						// sends the statement to the database server
						int row = statement.executeUpdate();
						if (row > 0) {
							message = "File uploaded and saved into database";
						}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return message;
	}	
	
	public static List<ProfileVO> findProfiles(){
		String sql="select sno,name,email,gender,mobile,photo,doe from students_tbl";
		PreparedStatement statement=null;
		Connection conn=null;
		List<ProfileVO> profileVOs=new ArrayList<ProfileVO>();
		try {
						conn = DBConnectionPool.getConnFromPool();
						statement=conn.prepareStatement(sql);
						ResultSet rs= statement.executeQuery();
						while(rs.next()){
							int sno=rs.getInt(1);
							String name=rs.getString(2);
							String email=rs.getString(3);
							String gender=rs.getString(4);
							String mobile=rs.getString(5);
							Blob blob=rs.getBlob(6);
			                byte[] photo=blob.getBytes(1,(int)blob.length());
							Timestamp doe=rs.getTimestamp(7);
							ProfileVO profileVO=new ProfileVO(sno, name, email, mobile, gender, new byte[]{}, doe);
							profileVOs.add(profileVO);
						}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return profileVOs;
	}
	
	public static byte[] findImageBySno(int sno){
		String sql="select photo from students_tbl where sno="+sno;
		PreparedStatement statement=null;
		Connection conn=null;
		byte[] photo=new byte[]{};
		try {
						conn = DBConnectionPool.getConnFromPool();
						statement=conn.prepareStatement(sql);
						ResultSet rs= statement.executeQuery();
						if(rs.next()){
							 Blob blob=rs.getBlob(1);
			                 photo=blob.getBytes(1,(int)blob.length());
						}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return photo;
	}
	
	public static String deleteProfileBySno(int sno){
		String sql="delete from students_tbl where sno="+sno;
		PreparedStatement statement=null;
		Connection conn=null;
		int rows=0;
		try {
						conn = DBConnectionPool.getConnFromPool();
						statement=conn.prepareStatement(sql);
						 rows= statement.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rows>0?"success":"fail";
	}
	
}
