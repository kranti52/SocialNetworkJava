package com.myapp.entity;

import java.util.Map;
import java.util.Map.Entry;

public class FriendRequestEntity {
	private String id;
	public String request_to;
	public String request_from;
	public String send_at;
	public String confirm;
	
	public void setProperties(Map<String,String> friend) {
		for(Entry<String,String> friend_entity: friend.entrySet()){
			String key=friend_entity.getKey();
			try {
				FriendRequestEntity.class.getField(key).set(this, friend_entity.getValue());
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized String getId() {
		return id;
	}
	public synchronized void setId(String id) {
		this.id = id;
	}
	public synchronized String getRequest_to() {
		return request_to;
	}
	public synchronized void setRequest_to(String request_to) {
		this.request_to = request_to;
	}
	public synchronized String getRequest_from() {
		return request_from;
	}
	public synchronized void setRequest_from(String request_from) {
		this.request_from = request_from;
	}
	public synchronized String getSend_at() {
		return send_at;
	}
	public synchronized void setSend_at(String send_at) {
		this.send_at = send_at;
	}
	public synchronized String getConfirm() {
		return confirm;
	}
	public synchronized void setConfirm(String confirm) {
		this.confirm = confirm;
	}
}
