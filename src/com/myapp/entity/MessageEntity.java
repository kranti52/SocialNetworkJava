package com.myapp.entity;

import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;

public class MessageEntity {
	public String message_id;
	public String to;
	public String from;
	public String content;
	public String send_at;
	public void setProperties(Map<String,String> data) {

		for(Entry<String,String> message_entity: data.entrySet()){
			String key=message_entity.getKey();
			//System.out.println(key);
			try {
				MessageEntity.class.getField(key).set(this, message_entity.getValue());
				//this.getField(key)=user_entity.getValue();
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}

	}
	public synchronized String getMessage_id() {
		return message_id;
	}
	public synchronized void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public synchronized String getTo() {
		return to;
	}
	public synchronized void setTo(String to) {
		this.to = to;
	}
	public synchronized String getFrom() {
		return from;
	}
	public synchronized void setFrom(String from) {
		this.from = from;
	}
	public synchronized String getContent() {
		return content;
	}
	public synchronized void setContent(String content) {
		this.content = content;
	}
	public synchronized String getSend_at() {
		return send_at;
	}
	public synchronized void setSend_at(String send_at) {
		this.send_at = send_at;
	}
}
