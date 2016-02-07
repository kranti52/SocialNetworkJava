package com.myapp.entity;

import java.io.InputStream;
import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;

public class StatusEntity {
	public String status_to;
	public String status_from;
	public String content;
	public String type;
	public String status_id;
	public String send_at;
	public void setProperties(Map<String,String> data) {

		for(Entry<String,String> status_entity: data.entrySet()){
			String key=status_entity.getKey();
			//System.out.println(key);
			try {
				StatusEntity.class.getField(key).set(this, status_entity.getValue());
				//this.getField(key)=user_entity.getValue();
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}

	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
	public synchronized String getSend_at() {
		return send_at;
	}
	public synchronized void setSend_at(String send_at) {
		this.send_at = send_at;
	}
}
