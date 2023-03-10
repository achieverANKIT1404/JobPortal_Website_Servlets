package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import com.entity.User;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class UserDao {

	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addUser(User u){
		
		boolean f = false;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("insert into user(name,qualification,email,password,role) values(?,?,?,?,?)");
			
			ps.setString(1, u.getName());
			ps.setString(2, u.getQualification());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPassword());
			ps.setString(5, "user");
			
			int i = ps.executeUpdate();
			if(i==1){
				
				f = true;
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return f;
	}
	
	public User login(String name, String psw){
		
		User u = null;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("select * from user where name=? and password=?");
			
			ps.setString(1, name);
			ps.setString(2, psw);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				u = new User();
				
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setQualification(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRole(rs.getString(6));
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return u;
	}
	
	public User getUserById(int id){
		
		User u = null;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("select * from user where id =?");
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				
	            u = new User();
				
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setQualification(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRole(rs.getString(6));
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return u;
	}
}
