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

import com.myapp.entity.MessageEntity;
import com.myapp.entity.ProfileEntity;
import com.myapp.entity.StatusEntity;
import com.mysql.jdbc.Blob;

public class MessageRepository {
	private Connection conn;
	private ProfileRepository repository;

	public MessageRepository() {
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
	
	public MessageEntity buildMessageEntity(Map<String,String> data) {
		MessageEntity message=new MessageEntity();
		message.setProperties(data);
		return message;
		
	}
	public void createMessage(Map<String,String> data) {
		MessageEntity entity=this.buildMessageEntity(data);
		entity.setSend_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Insert INTO message (`content`,`to`,`from`, `send_at`)VALUES(?,?,?,?);";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(3, entity.getFrom());
			preparedStatement.setString(2, entity.getTo());
			preparedStatement.setString(1, entity.getContent());
			preparedStatement.setString(4, entity.getSend_at());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public List<HashMap<String,Object>> getMessages(String to) {
		
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		
		List<MessageEntity> entity=new ArrayList<MessageEntity>();
		List<ProfileEntity> profile_entity=new ArrayList<ProfileEntity>();
		String sql="SELECT * from message where `to`=?";
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
					//System.out.println(resultset.getString("to"));
					data.put("message_id", resultset.getString("message_id"));
					data.put("to", resultset.getString("to"));
					data.put("from", resultset.getString("from"));
					data.put("content", resultset.getString("content"));
					data.put("send_at", resultset.getString("send_at"));
					MessageEntity message=buildMessageEntity(data);
					String sql_user="Select * from profile where `user_email`=?";
					PreparedStatement preparedStatementUser;
					try {
						preparedStatementUser = this.getConn().prepareStatement(sql_user);
						preparedStatementUser.setString(1, message.getTo());
						ResultSet resultset_user=preparedStatementUser.executeQuery();
					
						Map<String,String> profile_data=new HashMap<String,String>();
						InputStream inputstream = null;
						//OutputStream output=null;
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
						preparedStatementUser.close();
					} catch (SQLException e) {
						e.printStackTrace();
						//return null;
					}
					
					map.put("message", message);
					list.add(map);
					
				}
				
				
			}
			

			preparedStatement.close();
			
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
