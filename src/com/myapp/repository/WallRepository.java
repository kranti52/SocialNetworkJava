package com.myapp.repository;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.myapp.entity.FollowEntity;
import com.myapp.entity.ProfileEntity;
import com.myapp.entity.StatusEntity;
import com.myapp.entity.WallEntity;
import com.mysql.jdbc.Blob;

public class WallRepository {
	private Connection conn;
	private ProfileRepository repository;
	

	public WallRepository() {
		DatabaseConnection db=new DatabaseConnection();
		Connection connection= db.connect();
		this.setConn(connection);
		this.repository=new ProfileRepository();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public WallEntity buildWallEntity(Map<String,String> data) {
		WallEntity wall=new WallEntity();
		wall.setProperties(data);
		return wall;
		
	}
	public StatusEntity buildStatusEntity(Map<String,String> data) {
		StatusEntity status=new StatusEntity();
		status.setProperties(data);
		return status;
		
	}
	public Boolean createStatus(Map<String,String> status_data) {
		StatusEntity entity=this.buildStatusEntity(status_data);
		entity.setSend_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Insert INTO status "+ "(status_from, status_to, content, type, send_at)"+"VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getStatus_from());
			preparedStatement.setString(2, entity.getStatus_to());
			preparedStatement.setString(3, entity.getContent());
			preparedStatement.setString(4, entity.getType());
			preparedStatement.setString(5, entity.getSend_at());
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	public Boolean deleteStatus(String id) {
		String sql="DELETE FROM status WHERE status_id = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, id);

			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	public List<HashMap<String,Object>> getStatusDetails(String to) {
		//FollowEntity entity=this.buildFollowEntity(follow_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		
		List<StatusEntity> entity=new ArrayList<StatusEntity>();
		List<ProfileEntity> profile_entity=new ArrayList<ProfileEntity>();
		String sql="SELECT * from status where status_to=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, to);
			//preparedStatement.setString(2, from);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset==null) {
				return null;
			}
			else {
				while(resultset.next()){
					HashMap<String,Object> map=new HashMap<String,Object>();
					Map<String,String> data=new HashMap<String,String>();
					//System.out.println(resultset.getString("content"));
					data.put("status_id", resultset.getString("status_id"));
					data.put("status_to", resultset.getString("status_to"));
					data.put("status_from", resultset.getString("status_from"));
					data.put("content", resultset.getString("content"));
					data.put("type", resultset.getString("type"));
					data.put("send_at", resultset.getString("send_at"));
					StatusEntity status=buildStatusEntity(data);
					String sql_user="Select * from profile where user_email=?";
					PreparedStatement preparedStatementUser;
					try {
						preparedStatementUser = this.getConn().prepareStatement(sql_user);
						preparedStatementUser.setString(1, status.getStatus_to());
						ResultSet resultset_user=preparedStatementUser.executeQuery();
						Map<String,String> profile_data=new HashMap<String,String>();
						InputStream inputstream = null;
						OutputStream output=null;
						while(resultset_user.next()) {
							//System.out.println(resultset_user.getString("username"));
						//Map<String,String> profile_data=new HashMap<String,String>();
						profile_data.put("user_email", resultset_user.getString("user_email"));
						profile_data.put("username", resultset_user.getString("username"));
						profile_data.put("full_name", resultset_user.getString("full_name"));
						profile_data.put("hometown", resultset_user.getString("hometown"));
						profile_data.put("current_city", resultset_user.getString("current_city"));
						profile_data.put("occupation", resultset_user.getString("occupation"));
						profile_data.put("dob", resultset_user.getString("dob"));
						Blob image=(Blob) resultset_user.getBlob("picture");
						inputstream=image.getBinaryStream();
						
						}
						
						ProfileEntity entity_user=this.repository.buildProfileEntity(profile_data,inputstream);
						
						map.put("sender",entity_user);
					} catch (SQLException e) {
						e.printStackTrace();
						//return null;
					}

					map.put("status", status);
					list.add(map);
					
				}
				return list;
			}
			


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
