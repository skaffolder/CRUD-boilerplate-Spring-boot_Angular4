package com.manage_film_example.db.manage_film_example_db.mapper.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import com.manage_film_example.db.manage_film_example_db.mapper.Film;

public class FilmBase implements RowMapper<Film>{
	
	private Long _id;
	
	// Attributes
	private String genre;
	private String title;
	private Number year;
	
	
	// Relations filmMaker
	private String filmMaker;
	
	
	
	// Relations m:m cast
	private ArrayList<Long> cast;
	
	
	@Override
	public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Film obj = new Film();
		try {
			obj.set_id(rs.getLong("id"));
			
			obj.setGenre(rs.getString("genre"));
			obj.setTitle(rs.getString("title"));
			obj.setYear(rs.getBigDecimal("year"));
			
        	
        	// Relations 1:m filmMaker
			obj.setFilmMaker(rs.getString("filmMaker"));
        	
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


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	public Number getYear() {
		return year;
	}


	public void setYear(Number year) {
		this.year = year;
	}

	
    
    // Relations 1:m filmMaker
	public String getFilmMaker() {
		return filmMaker;
	}

	public void setFilmMaker(String filmMaker) {
		this.filmMaker = filmMaker;
	}
    
    
    
    
    // Relations m:m cast
	public ArrayList<Long> getCast() {
		return cast;
	}

	public void setCast(ArrayList<Long> cast) {
		this.cast = cast;
	}
	
	
	public static class Film_cast implements RowMapper<Film_cast>{
		
		private Long _id;
		
		// Relations
	
		private Long id_Film;
		private Long id_Actor;
				
		
		@Override
		public Film_cast mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Film_cast obj = new Film_cast();
			try {
				obj.set_id(rs.getLong("id"));
				obj.setId_Film(rs.getLong("id_Film"));
				obj.setId_Actor(rs.getLong("id_Actor"));
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
	
		public Long getId_Film() {
			return id_Film;
		}
	
	
		public void setId_Film(Long id_Film) {
			this.id_Film = id_Film;
		}
	
	
		public Long getId_Actor() {
			return id_Actor;
		}
	
	
		public void setId_Actor(Long id_Actor) {
			this.id_Actor = id_Actor;
		}
		
		
	}
    
    
}