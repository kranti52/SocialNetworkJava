package com.myapp.repository;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.*;

import com.myapp.entity.FollowEntity;
import com.myapp.entity.FriendEntity;
import com.myapp.entity.FriendRequestEntity;
import com.myapp.entity.ProfileEntity;
import com.myapp.entity.UserEntity;
import com.mysql.jdbc.Blob;

public class AddFriendFollow {
	private Connection conn;
	private ProfileRepository repository;

	public AddFriendFollow() {
		DatabaseConnection db=new DatabaseConnection();
		Connection connection= db.connect();
		this.setConn(connection);;
		this.repository=new ProfileRepository();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public FriendEntity buildFriendEntity(Map<String,String> friend_data) {
		FriendEntity friend=new FriendEntity();
		friend.setProperties(friend_data);
		return friend;

	}
	public FriendRequestEntity buildFriendRequestEntity(Map<String,String> friend_data) {
		FriendRequestEntity friend=new FriendRequestEntity();
		friend.setProperties(friend_data);
		return friend;

	}
	public FollowEntity buildFollowEntity(Map<String,String> follow_data) {
		FollowEntity follow=new FollowEntity();
		follow.setProperties(follow_data);
		return follow;

	}

	public Boolean sendFriendRequest(Map<String,String> friend_data) {
		FriendRequestEntity entity=this.buildFriendRequestEntity(friend_data);
		entity.setSend_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Insert INTO friend_request "+ "(request_to, request_from, send_at,confirm)VALUES(?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getRequest_to());
			preparedStatement.setString(2, entity.getRequest_from());
			preparedStatement.setString(3, entity.getSend_at());
			preparedStatement.setInt(4, 0);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public Boolean AddFriend(Map<String,String> friend_data) {
		FriendEntity entity=this.buildFriendEntity(friend_data);
		entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Insert INTO friends "+ "(friend1, friend2, created_at)VALUES(?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getFriend1());
			preparedStatement.setString(2, entity.getFriend2());
			preparedStatement.setString(3, entity.getCreated_at());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public Boolean DeleteFriendRequest(Map<String,String> friend_data) {
		FriendRequestEntity entity=this.buildFriendRequestEntity(friend_data);
		//entity.setSend_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Delete From friend_request where `request_to`=? and `request_from`=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getRequest_to());
			preparedStatement.setString(2, entity.getRequest_from());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public Boolean createFollower(Map<String,String> follow_data) {
		FollowEntity entity=this.buildFollowEntity(follow_data);
		entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Insert INTO follow "+ "(follower, following, created_at)VALUES(?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getFollower());
			preparedStatement.setString(2, entity.getFollowing());
			preparedStatement.setString(3, entity.getCreated_at());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public Boolean DeleteFollower(Map<String,String> follow_data) {
		FollowEntity entity=this.buildFollowEntity(follow_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="DELETE From follow where `follower`=? and `following`=?";
		PreparedStatement preparedStatement;
		System.out.println(entity.getFollower()+entity.getFollower());
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getFollower());
			preparedStatement.setString(2, entity.getFollowing());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	public Boolean DeleteFriend(Map<String,String> friend_data) {
		FriendEntity entity=this.buildFriendEntity(friend_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="DELETE From friends where `friend1`=? and `friend2`=?";
		PreparedStatement preparedStatement;
		//System.out.println(entity.getFollower()+entity.getFollower());
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getFriend1());
			preparedStatement.setString(2, entity.getFriend2());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	public Boolean getFriend(Map<String,String> friend_data) {
		//System.out.println("friend_test");
		FriendEntity entity=this.buildFriendEntity(friend_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		String sql="Select * From `friends` where (`friend1`=? and `friend2`=?) or (`friend1`=? and `friend2`=?)";
		PreparedStatement preparedStatement;
		//System.out.println(entity.getFollower()+entity.getFollower());
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, entity.getFriend1());
			preparedStatement.setString(2, entity.getFriend2());
			preparedStatement.setString(4, entity.getFriend1());
			preparedStatement.setString(3, entity.getFriend2());
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.isBeforeFirst()) {
				return true;
			}
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	public FollowEntity getFollowerDetails(String follower,String following) {
		//FollowEntity entity=this.buildFollowEntity(follow_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		Map<String,String> data=new HashMap<String,String>();
		String sql="SELECT * from follow where follower=? and following=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, follower);
			preparedStatement.setString(2, following);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset==null) {
				return null;
			}
			else {
				while(resultset.next()){


					data.put("follower", resultset.getString("follower"));
					data.put("following", resultset.getString("following"));
					data.put("created_at", resultset.getString("created_at"));
				}

			}
			FollowEntity entity=this.buildFollowEntity(data);
			return entity;


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	public FriendRequestEntity getFriendRequestDetails(String from,String to) {
		//FollowEntity entity=this.buildFollowEntity(follow_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		//System.out.println("testing fails"+from);
		//System.out.println("testing fails......"+to);
		Map<String,String> data=new HashMap<String,String>();
		String sql="SELECT * from friend_request where request_to=? and request_from=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, to);
			preparedStatement.setString(2, from);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset==null) {
				//System.out.println("testing fails");
				return null;
			}
			else {
				while(resultset.next()) {
					data.put("request_to", resultset.getString("request_to"));
					System.out.println("testing goin on"+resultset.getString("request_to"));
					data.put("request_from", resultset.getString("request_from"));
					data.put("send_at", resultset.getString("send_at"));
					data.put("confirm", Integer.toString(resultset.getInt("confirm")));
				}
				

			}
			FriendRequestEntity entity=this.buildFriendRequestEntity(data);
			return entity;


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<HashMap<String,Object>> getFriendRequest(String to) {
		//FollowEntity entity=this.buildFollowEntity(follow_data);
		//entity.setCreated_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime()));
		//System.out.println("testing fails"+from);
		//System.out.println("testing fails......"+to);
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		String sql="SELECT * from friend_request where request_to=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, to);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset==null) {
				//System.out.println("testing fails");
				return null;
			}
			else {
				while(resultset.next()) {
					HashMap<String,Object> map=new HashMap<String,Object>();
					Map<String,String> data=new HashMap<String,String>();
					data.put("request_to", resultset.getString("request_to"));
					System.out.println("testing goin on"+resultset.getString("request_to"));
					data.put("request_from", resultset.getString("request_from"));
					data.put("send_at", resultset.getString("send_at"));
					data.put("confirm", Integer.toString(resultset.getInt("confirm")));
					FriendRequestEntity entity=this.buildFriendRequestEntity(data);
					String sql_user="Select * from profile where user_email=?";
					PreparedStatement preparedStatementUser;
					try {
						preparedStatementUser = this.getConn().prepareStatement(sql_user);
						preparedStatementUser.setString(1, entity.getRequest_from());
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

					map.put("request", entity);
					list.add(map);

				}

			}

			

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
