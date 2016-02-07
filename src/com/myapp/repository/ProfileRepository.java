package com.myapp.repository;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Decoder.BinaryStream;

import com.myapp.entity.ProfileEntity;
import com.mysql.jdbc.Blob;


public class ProfileRepository {
	private Connection conn;

	public ProfileRepository() {
		DatabaseConnection db=new DatabaseConnection();
		Connection connection= db.connect();
		this.setConn(connection);;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public ProfileEntity buildProfileEntity(Map<String,String> data,InputStream inputStream) {
		ProfileEntity profile=new ProfileEntity();
		profile.setProperties(data,inputStream);
		return profile;

	}
	public void createProfile(Map<String,String> profile_data,InputStream inputStream) {
		ProfileEntity entity=this.buildProfileEntity(profile_data,inputStream);
		String sql="Insert INTO profile "+ "(user_email, username, picture,full_name,occupation,hometown,current_city,dob,experience,interest)VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getUser_email());
			preparedStatement.setString(2, entity.getUsername());
			preparedStatement.setBinaryStream(3, entity.getPicture());
			preparedStatement.setString(4, entity.getFull_name());
			preparedStatement.setString(5, entity.getOccupation());
			preparedStatement.setString(6, entity.getHometown());
			preparedStatement.setString(7, entity.getCurrent_city());
			preparedStatement.setString(8, entity.getDob());
			preparedStatement.setString(9, entity.getExperience());
			preparedStatement.setString(10, entity.getInterest());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ProfileEntity findProfileByEmail(String email) {
		//ProfileEntity entity=this.buildProfileEntity(profile_data,inputStream);
		String sql="Select * from profile where user_email=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultset=preparedStatement.executeQuery();
			Map<String,String> profile_data=new HashMap<String,String>();
			InputStream inputstream = null;
			OutputStream output=null;
			while(resultset.next()) {
			//Map<String,String> profile_data=new HashMap<String,String>();
			profile_data.put("user_email", resultset.getString("user_email"));
			profile_data.put("username", resultset.getString("username"));
			profile_data.put("full_name", resultset.getString("full_name"));
			profile_data.put("hometown", resultset.getString("hometown"));
			profile_data.put("current_city", resultset.getString("current_city"));
			profile_data.put("occupation", resultset.getString("occupation"));
			profile_data.put("experience", resultset.getString("experience"));
			profile_data.put("interest", resultset.getString("interest"));
			profile_data.put("dob", resultset.getString("dob"));
			inputstream= resultset.getBinaryStream("picture");
			//inputstream=image;
			
			}
			
			ProfileEntity entity=this.buildProfileEntity(profile_data,inputstream);
			return entity;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	public ArrayList<ProfileEntity> search(String pattern) {
		ArrayList<ProfileEntity> list=new ArrayList<ProfileEntity>();
		String sql="Select * from profile where full_name like ? or username like ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, pattern+"%");
			preparedStatement.setString(2, pattern+"%");
			ResultSet resultset=preparedStatement.executeQuery();
			Map<String,String> profile_data=new HashMap<String,String>();
			InputStream inputstream = null;
			OutputStream output=null;
			while(resultset.next()) {
			//Map<String,String> profile_data=new HashMap<String,String>();
			profile_data.put("user_email", resultset.getString("user_email"));
			profile_data.put("username", resultset.getString("username"));
			profile_data.put("full_name", resultset.getString("full_name"));
			profile_data.put("hometown", resultset.getString("hometown"));
			profile_data.put("current_city", resultset.getString("current_city"));
			profile_data.put("occupation", resultset.getString("occupation"));
			profile_data.put("experience", resultset.getString("experience"));
			profile_data.put("interest", resultset.getString("interest"));
			profile_data.put("dob", resultset.getString("dob"));
			inputstream= resultset.getBinaryStream("picture");
			ProfileEntity entity=this.buildProfileEntity(profile_data,inputstream);
			list.add(entity);
			}
			
			
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public ProfileEntity findProfileByUsername(String username) {
		//ProfileEntity entity=this.buildProfileEntity(profile_data,inputStream);
		String sql="Select * from profile where username=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultset=preparedStatement.executeQuery();
			Map<String,String> profile_data=new HashMap<String,String>();
			InputStream inputstream = null;
			OutputStream output=null;
			while(resultset.next()) {
			//Map<String,String> profile_data=new HashMap<String,String>();
			profile_data.put("user_email", resultset.getString("user_email"));
			profile_data.put("username", resultset.getString("username"));
			profile_data.put("full_name", resultset.getString("full_name"));
			profile_data.put("hometown", resultset.getString("hometown"));
			profile_data.put("current_city", resultset.getString("current_city"));
			profile_data.put("experience", resultset.getString("experience"));
			profile_data.put("interest", resultset.getString("interest"));
			profile_data.put("occupation", resultset.getString("occupation"));
			profile_data.put("dob", resultset.getString("dob"));
			inputstream=resultset.getBinaryStream("picture");
			//inputstream=image.getBinaryStream();
			
			}
			
			ProfileEntity entity=this.buildProfileEntity(profile_data,inputstream);
			return entity;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
