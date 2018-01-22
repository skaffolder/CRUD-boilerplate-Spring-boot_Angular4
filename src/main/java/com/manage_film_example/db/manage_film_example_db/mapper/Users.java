package com.manage_film_example.db.manage_film_example_db.mapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class Users implements RowMapper<Users>{

	private String username;
    private String password;
    private String mail;
    private String name;
    private String surname;
    private String token;
    private Long _id;
    private ArrayList<String> roles;

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Users obj = new Users();
		try {
			obj.set_id(rs.getLong("id"));
			obj.setUsername(rs.getString("username"));
			obj.setPassword(rs.getString("password"));
			obj.setMail(rs.getString("mail"));
			obj.setName(rs.getString("name"));
			obj.setSurname(rs.getString("surname"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public ArrayList<String> getRoles() {
		return roles;
	}


	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}


	public Long get_id() {
		return _id;
	}


	public void set_id(Long _id) {
		this._id = _id;
	}

	/**
	 * Check is user have role
	 * @param role
	 * @return 
	 */
	public boolean hasRole(String role) {
		return roles.indexOf(role) != -1;
	}

}

