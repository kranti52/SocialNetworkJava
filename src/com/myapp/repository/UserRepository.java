package com.myapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.myapp.entity.UserEntity;

public class UserRepository {
	private Connection conn;
	
	public UserRepository() {
		DatabaseConnection db=new DatabaseConnection();
		Connection connection= db.connect();
		this.setConn(connection);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<UserEntity> findUserByEmail(String email) {
		ArrayList<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		ArrayList<UserEntity> entityList=new ArrayList<UserEntity>();
		int i=0;
		String sql="Select * from user where user_email=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset==null) {
				return null;
			}
			while(resultset.next()) {
				//System.out.println("email is:"+email);
				Map<String, String> data = new HashMap<String, String>();
				data.put("user_email",resultset.getString("user_email"));
				data.put("id",Integer.toString(resultset.getInt("id")));
				data.put("password",resultset.getString("password"));
				data.put("verified",resultset.getString("verified"));
				data.put("created_at",resultset.getString("created_at"));
				data.put("profile_done",resultset.getString("profile_done"));
				dataList.add(data);
			}
			for (Map<String, String> data: dataList) {
				entityList.add(buildUserEntity(data));
			}
			return entityList;

		} catch (SQLException e) {
			e.printStackTrace();
			return entityList;
		}
	}
	
	public ArrayList<UserEntity> findUserByEmailPassword(String email, String password) {
		ArrayList<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		ArrayList<UserEntity> entityList=new ArrayList<UserEntity>();
		int i=0;
		String sql="Select * from user where user_email=? and password=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset==null) {
				return null;
			}
			while(resultset.next()) {
				//System.out.println("email is:"+email);
				Map<String, String> data = new HashMap<String, String>();
				data.put("user_email",resultset.getString("user_email"));
				data.put("id",Integer.toString(resultset.getInt("id")));
				data.put("password",resultset.getString("password"));
				data.put("verified",resultset.getString("verified"));
				data.put("created_at",resultset.getString("created_at"));
				data.put("profile_done",Boolean.toString(resultset.getBoolean("profile_done")));
				dataList.add(data);
			}
			for (Map<String, String> data: dataList) {
				entityList.add(buildUserEntity(data));
			}
			return entityList;

		} catch (SQLException e) {
			e.printStackTrace();
			return entityList;
		}
	}
	
	public UserEntity buildUserEntity(Map<String,String> user_data) {
		UserEntity user=new UserEntity();
		user.setProperties(user_data);
		return user;
		
	}
	public void createUser(Map<String,String> user_data) {
		UserEntity entity=this.buildUserEntity(user_data);
		entity.setVerified("0");
		entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Insert INTO user "+ "(user_email, password, verified,created_at)VALUES(?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getemail());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setString(3, entity.getVerified());
			preparedStatement.setString(4, entity.getCreated_at());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void updateProfile(String email) {
		String sql="Update user SET profile_done=? where user_email=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setInt(1, 1); 
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void updatePassword(String email, String password) {
		String sql="Update user SET password=? where user_email=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, password); 
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void updateVerified(String email) {
		String sql="Update user SET verified=? where user_email=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, "1"); 
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
