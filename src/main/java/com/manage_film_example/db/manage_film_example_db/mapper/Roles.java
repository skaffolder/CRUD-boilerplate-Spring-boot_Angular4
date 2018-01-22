package com.manage_film_example.db.manage_film_example_db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Roles implements RowMapper<Roles>{

    private Long _id;
    private Long _user;
	private String role;

	@Override
	public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Roles obj = new Roles();
		try {
			obj.set_id(rs.getLong("id"));
			obj.set_user(rs.getLong("_user"));
			obj.setRole(rs.getString("role"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	} 
    
	
	public Long get_id() {
		return _id;
	}
	public void set_id(Long id) {
		this._id = id;
	}

	public Long get_user() {
		return _user;
	}


	public void set_user(Long _user) {
		this._user = _user;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

}
