package com.myapp.entity;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

public class ProfileEntity {
	public String profile_id;
	public String user_email;
	public String full_name;
	public InputStream picture;
	public String username;
	public String occupation;
	public String hometown;
	public String current_city;
	public String dob;
	public String experience;
	public String interest;
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public InputStream getPicture() {
		return picture;
	}
	public void setPicture(InputStream picture) {
		this.picture = picture;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getCurrent_city() {
		return current_city;
	}
	public void setCurrent_city(String current_city) {
		this.current_city = current_city;
	}
	public void setProperties(Map<String,String> profile,InputStream inputStream) {

		for(Entry<String,String> profile_entity: profile.entrySet()){
			String key=profile_entity.getKey();
			System.out.println(key);
			try {
				ProfileEntity.class.getField(key).set(this, profile_entity.getValue());
				//this.getField(key)=user_entity.getValue();
				if(key=="user_email") {
					System.out.println("random");
				}
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
		this.picture=inputStream;

	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public synchronized String getExperience() {
		return experience;
	}
	public synchronized void setExperience(String experience) {
		this.experience = experience;
	}
	public synchronized String getInterest() {
		return interest;
	}
	public synchronized void setInterest(String interest) {
		this.interest = interest;
	}
}
