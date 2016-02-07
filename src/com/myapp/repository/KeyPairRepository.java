package com.myapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class KeyPairRepository {
private Connection conn;
	
	public KeyPairRepository() {
		DatabaseConnection db=new DatabaseConnection();
		Connection connection= db.connect();
		this.setConn(connection);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public void createKeyPair(String encrypted_text,String key)
	{
		//String encodedKey = new String(secretKey.getEncoded());
		String current_date=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date().getTime());
		String sql="Insert INTO keypair "+ "(encrypted_text, secret_key, used,created_at)VALUES(?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setString(1, encrypted_text);
			preparedStatement.setString(2, key);
			preparedStatement.setInt(3, 0);
			preparedStatement.setString(4, current_date);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public String updateUsedSecretKey(String encrypted_text) {
		String sql="Update keypair SET used=? where encrypted_text=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			preparedStatement.setInt(1, 1); 
			preparedStatement.setString(2, encrypted_text);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encrypted_text;
	}
	public String getSecretKey(String encrypted_text) {
		String secret_key=null;
		System.out.println(encrypted_text);
		String sql="SELECT secret_key,used FROM keypair where `encrypted_text`=?";
		//String sql="SELECT secret_key FROM keypair where `id`=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.getConn().prepareStatement(sql);
			//preparedStatement.setInt(1, 4);
			preparedStatement.setString(1, encrypted_text);
			ResultSet resultset=preparedStatement.executeQuery();
			//System.out.println(resultset.getString("secret_key"));
			if(resultset==null) {
				return null;
			}
			while(resultset.next()) {
				secret_key=resultset.getString("secret_key");
				int used=resultset.getInt("used");
				if(used==1) {
					return "Already Used";
				}
				return secret_key;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "not used";
	}
}
