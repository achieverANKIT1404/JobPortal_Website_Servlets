package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entity.Jobs;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class JobDao {

	private Connection conn;

	public JobDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addJobs(Jobs j){
		
		boolean f = false;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("insert into jobs(title,description,category,status,location)values(?,?,?,?,?)");
			
		    ps.setString(1, j.getTitle());
		    ps.setString(2, j.getDescription());
		    ps.setString(3, j.getCategory());
		    ps.setString(4, j.getStatus());
		    ps.setString(5, j.getLocation());
		    
		    int i = ps.executeUpdate();
		    
		    if(i==1){
		    	
		    	f = true;
		    }
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return f;
	}
	
	public ArrayList<Jobs> getAllJobs(){
		
		ArrayList<Jobs> list = new ArrayList<Jobs>();
		
		Jobs j = null;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("select * from jobs order by id desc");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				j = new Jobs();
				
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				j.setPdate(rs.getTimestamp(7)+"");
				
				list.add(j);
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return list;
	}
	
public ArrayList<Jobs> getAllJobsForUser(){
		
		ArrayList<Jobs> list = new ArrayList<Jobs>();
		
		Jobs j = null;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("select * from jobs where status=? order by id desc");
			
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				j = new Jobs();
				
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				j.setPdate(rs.getTimestamp(7)+"");
				
				list.add(j);
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return list;
	}
	
public Jobs getJobById(int id){
				
		Jobs j = null;
		
		try{
			
			PreparedStatement ps = conn.prepareStatement("select * from jobs where id=?");
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				j = new Jobs();
				
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				j.setPdate(rs.getTimestamp(7)+"");
				
			}
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Hello Error Bhai");
		}
		return j;
	}

    public boolean updateJob(Jobs j){
    	
	boolean f = false;
			
			try{
				
				PreparedStatement ps = conn.prepareStatement("update  jobs set title=?,description=?,category=?,status=?,location=? where id =?");
				
			    ps.setString(1, j.getTitle());
			    ps.setString(2, j.getDescription());
			    ps.setString(3, j.getCategory());
			    ps.setString(4, j.getStatus());
			    ps.setString(5, j.getLocation());
			    ps.setInt(6, j.getId());
			    
			    int i = ps.executeUpdate();
			    
			    if(i==1){
			    	
			    	f = true;
			    }
			}catch(Exception e){
				
				e.printStackTrace();
			}
			return f;
	    }
    
    public boolean deleteJobs(int id){
    	
    	boolean f = false;
    	
    	try{
    		
    		PreparedStatement ps = conn.prepareStatement("delete  from jobs where id=?");
    		ps.setInt(1, id);
    		
    		int i = ps.executeUpdate();
    		
    		if(i==1){
    			
    			f = true;
    		}
    	}catch (Exception e) {
			
    		e.printStackTrace();
		}
    	return f;
    }

    public ArrayList<Jobs> getJobORLocAndCat(String category, String location){
    	
    	ArrayList<Jobs> list = new ArrayList<Jobs>();
    	
    	Jobs j = null;
    	
    	try{
    		
    		PreparedStatement ps = conn.prepareStatement("select * from jobs where category=? or location=? order by id desc");
    		
    		ps.setString(1, category);
    		ps.setString(2, location);
    		
    		ResultSet rs = ps.executeQuery();
            while(rs.next()){
				
				j = new Jobs();
				
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				j.setPdate(rs.getString(7));
				
				list.add(j);
				
			}
    	}catch (Exception e) {
			
    		e.printStackTrace();
		}
    	return list;
    	
    }
 public ArrayList<Jobs> getJobANDLocAndCate(String category, String location){
    	
    	ArrayList<Jobs> list = new ArrayList<Jobs>();
    	
    	Jobs j = null;
    	
    	try{
    		
    		PreparedStatement ps = conn.prepareStatement("select * from jobs where category=? and location=? order by id desc");
    		
    		ps.setString(1, category);
    		ps.setString(2, location);
    		
    		ResultSet rs = ps.executeQuery();
            while(rs.next()){
				
				j = new Jobs();
				
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				j.setPdate(rs.getString(7));
				
				list.add(j);
				
			}
    	}catch (Exception e) {
			
    		e.printStackTrace();
		}
    	return list;
    	
 }
    
}
