package com.manage_film_example.db.manage_film_example_db.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.manage_film_example.db.manage_film_example_db.mapper.Film;
import com.manage_film_example.db.manage_film_example_db.service.FilmService;

@Service
public class FilmBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


    //CRUD METHODS
    
    
    //CRUD - CREATE
    	
	public Film insert(Film obj) {
		
		long id = jdbcTemplate.queryForObject("SELECT nvl(max(\"id\")+1, 1) FROM Film", new MapSqlParameterSource(), Long.class);
		obj.set_id(id);
		
		String sql = "INSERT INTO Film (\"id\", \"genre\",\"title\",\"year\" , \"filmMaker\" )	VALUES (:id, :genre,:title,:year , :filmMaker  )";

		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("genre", obj.getGenre())
			.addValue("title", obj.getTitle())
			.addValue("year", obj.getYear())
			.addValue("filmMaker", obj.getFilmMaker());

		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
        	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM Film WHERE \"id\"=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
        
    //CRUD - FIND BY cast
    	
	public List<Film> findBycast(Long idcast) {
		
        String sql = "select * from Film WHERE \"id\" IN (SELECT \"id_Film\" FROM Film_cast WHERE \"id_Actor\" = :idcast)";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("idcast", idcast);
	    
	    return jdbcTemplate.query(sql, parameters, new Film());
	}
	
    	
        
    //CRUD - FIND BY filmMaker
    	
	public List<Film> findByfilmMaker(Long idfilmMaker) {
		
		String sql = "select * from Film WHERE \"filmMaker\" = :idfilmMaker";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("idfilmMaker", idfilmMaker);
	    
	    return jdbcTemplate.query(sql, parameters, new Film());
	}
	
    	
        	
    //CRUD - GET ONE
    	
	public Film get(Long id) {
	    
		String sql = "select * from Film where \"id\" = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return jdbcTemplate.queryForObject(sql, parameters, new Film());
	}

    	
        	
    //CRUD - GET LIST
    	
	public List<Film> getList() {
	    
		String sql = "select * from Film";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new Film());
	}

    	
        
    //CRUD - EDIT
    	
	public Film update(Film obj, Long id) {

		String sql = "UPDATE Film SET \"genre\" = :genre,\"title\" = :title,\"year\" = :year , \"filmMaker\" = :filmMaker  WHERE \"id\"=:id";

		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("genre", obj.getGenre())
			.addValue("title", obj.getTitle())
			.addValue("year", obj.getYear())
			.addValue("filmMaker", obj.getFilmMaker());

		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    
    // External relation m:m cast
    public static class Film_castService {

    	public static ArrayList<Long> findBy_Film(Long id_Film) {
    		String sql = "select \"id_Actor\" from Film_cast WHERE \"id_Film\"=:id_Film";
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_Film", id_Film);
    	    
    	    List<Long> listId = jdbcTemplate.queryForList(sql, parameters, Long.class);
    		return (ArrayList<Long>) listId;
    	}
    	

    	public static void updateRelation(Long id_Film, ArrayList<Long> cast) {

    		// Delete not in array
    		String in = " and \"id_Actor\" NOT IN (:cast)";
    		String sql = "DELETE FROM Film_cast WHERE \"id_Film\"=:id_Film ";
    		
    		if (cast != null && cast.size() > 0)
    			sql = sql + in;
    			
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_Film", id_Film)
    			.addValue("cast", cast);
    		
    		jdbcTemplate.update(sql, parameters);
    		
    		// Get actual    		
    	    List<Long> actual = FilmService.Film_castService.findBy_Film(id_Film);
    	    
    		// Insert new
    		for (Long id_Actor : cast) {
    			if (actual.indexOf(id_Actor) == -1){
    				FilmService.Film_castService.insert(id_Film, id_Actor);
    			}
    		}
    		
    	}
    	

    	public static void insert(Long id_Film, Long id_Actor) {
    		Film.Film_cast obj = new Film.Film_cast();
			Long id = jdbcTemplate.queryForObject("SELECT nvl(max(\"id\")+1, 1) FROM Film_cast", new MapSqlParameterSource(), Long.class);
			obj.set_id(id);
			
			String sql = "INSERT INTO Film_cast (\"id\", \"id_Film\", \"id_Actor\" )	VALUES (:id, :id_Film, :id_Actor)";

			MapSqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id)
				.addValue("id_Film", id_Film)
				.addValue("id_Actor", id_Actor);

			jdbcTemplate.update(sql, parameters);
    	}

    }
	    
    
    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in  Custom.js
     */
    


}
