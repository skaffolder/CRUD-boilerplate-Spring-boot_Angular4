package com.manage_film_example.db.manage_film_example_db.mapper.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import com.manage_film_example.db.manage_film_example_db.mapper.FilmMaker;

public class FilmMakerBase implements RowMapper<FilmMaker>{
	
	private Long _id;
	
	// Attributes
	private String name;
	private String surname;
	
	
	
	
	
	@Override
	public FilmMaker mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FilmMaker obj = new FilmMaker();
		try {
			obj.set_id(rs.getLong("id"));
			
			obj.setName(rs.getString("name"));
			obj.setSurname(rs.getString("surname"));
			
        	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
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

	
    
    
    
    
    
}