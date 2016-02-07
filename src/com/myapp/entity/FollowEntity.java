package com.myapp.entity;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class FollowEntity {
	public String follow_id;
	public String follower;
	public String following;
	public String created_at;
	
	public void setProperties(Map<String,String> follow) {
		for(Entry<String,String> follow_entity: follow.entrySet()){
			String key=follow_entity.getKey();
			try {
				FollowEntity.class.getField(key).set(this, follow_entity.getValue());
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public synchronized String getFollow_id() {
		return follow_id;
	}

	public synchronized void setFollow_id(String follow_id) {
		this.follow_id = follow_id;
	}
}
