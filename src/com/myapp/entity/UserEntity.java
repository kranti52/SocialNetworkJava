package com.myapp.entity;

import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;


public class UserEntity {
	public String id;
	public String user_email;
	public String verified;
	public String password;
	public String profile_done="false";
	public String created_at;
	public String getemail() {
		return user_email;
	}
	public void setemail(String user_email) {
		this.user_email = user_email;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setProperties(Map<String,String> user) {

		for(Entry<String,String> user_entity: user.entrySet()){
			String key=user_entity.getKey();
			//System.out.println(key);
			try {
				UserEntity.class.getField(key).set(this, user_entity.getValue());
				//this.getField(key)=user_entity.getValue();
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}

	}
	public synchronized String getProfile_done() {
		return profile_done;
	}
	public synchronized void setProfile_done(String profile_done) {
		this.profile_done = profile_done;
	}
	public synchronized String getCreated_at() {
		return created_at;
	}
	public synchronized void setCreated_at(String string) {
		this.created_at = string;
	}
	public synchronized String getId() {
		return id;
	}
	public synchronized void setId(String id) {
		this.id = id;
	}
}
