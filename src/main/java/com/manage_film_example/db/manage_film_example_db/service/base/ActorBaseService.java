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

import com.manage_film_example.db.manage_film_example_db.mapper.Actor;
import com.manage_film_example.db.manage_film_example_db.service.ActorService;

@Service
public class ActorBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


    //CRUD METHODS
    
    
    //CRUD - CREATE
    	
	public Actor insert(Actor obj) {
		
		long id = jdbcTemplate.queryForObject("SELECT nvl(max(\"id\")+1, 1) FROM Actor", new MapSqlParameterSource(), Long.class);
		obj.set_id(id);
		
		String sql = "INSERT INTO Actor (\"id\", \"birthDate\",\"name\",\"surname\"  )	VALUES (:id, :birthDate,:name,:surname  )";

		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("birthDate", obj.getBirthDate())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());

		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
        	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM Actor WHERE \"id\"=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
        	
    //CRUD - GET ONE
    	
	public Actor get(Long id) {
	    
		String sql = "select * from Actor where \"id\" = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return jdbcTemplate.queryForObject(sql, parameters, new Actor());
	}

    	
        	
    //CRUD - GET LIST
    	
	public List<Actor> getList() {
	    
		String sql = "select * from Actor";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new Actor());
	}

    	
        
    //CRUD - EDIT
    	
	public Actor update(Actor obj, Long id) {

		String sql = "UPDATE Actor SET \"birthDate\" = :birthDate,\"name\" = :name,\"surname\" = :surname  WHERE \"id\"=:id";

		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("birthDate", obj.getBirthDate())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());

		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    
    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in  Custom.js
     */
    


}
