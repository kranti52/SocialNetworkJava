package com.myapp.entity;

import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;

public class WallEntity {
	public String status_to;
	public String status_from;
	public String content;
	public String status_id;
	public String type;
	public Date send_at;
	public String getStatus_to() {
		return status_to;
	}
	public void setStatus_to(String status_to) {
		this.status_to = status_to;
	}
	public String getStatus_from() {
		return status_from;
	}
	public void setStatus_from(String status_from) {
		this.status_from = status_from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setProperties(Map<String,String> message) {

		for(Entry<String,String> message_entity: message.entrySet()){
			String key=message_entity.getKey();
			try {
				MessageEntity.class.getField(key).set(this, message_entity.getValue());
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}

	}
	public Date getSend_at() {
		return send_at;
	}
	public void setSend_at(Date send_at) {
		this.send_at = send_at;
	}
	
}
