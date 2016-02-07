package com.myapp.entity;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class FriendEntity {
	public String friend_id;
	public String friend1;
	public String friend2;
	public String created_at;
	public String getFriend1() {
		return friend1;
	}
	public void setFriend1(String friend1) {
		this.friend1 = friend1;
	}
	public String getFriend2() {
		return friend2;
	}
	public void setFriend2(String friend2) {
		this.friend2 = friend2;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String string) {
		this.created_at = string;
	}
	public void setProperties(Map<String,String> friend) {
		for(Entry<String,String> friend_entity: friend.entrySet()){
			String key=friend_entity.getKey();
			try {
				FriendEntity.class.getField(key).set(this, friend_entity.getValue());
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized String getFriend_id() {
		return friend_id;
	}
	public synchronized void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
}
