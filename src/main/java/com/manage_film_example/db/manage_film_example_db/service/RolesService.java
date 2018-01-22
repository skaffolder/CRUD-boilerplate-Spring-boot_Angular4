package com.manage_film_example.db.manage_film_example_db.service;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.manage_film_example.db.manage_film_example_db.mapper.Roles;

@Service
public class RolesService{

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	public ArrayList<Roles> getRoles(Long id_user) {
		
		String sql = "select * from Roles WHERE \"_user\" = :id_user";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("id_user", id_user);
	    
	    return (ArrayList<Roles>) jdbcTemplate.query(sql, parameters, new Roles());
	}
	
	

    public ArrayList<String> getRolesAsStringArray(Long userid) {
        String sql = "select \"role\" from Roles where \"_user\" = :userid";
        SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("userid", userid);
        
        return (ArrayList<String>) jdbcTemplate.queryForList(sql, parameters, String.class);
    }


    public void insert(Long id_user, String role) {
        
        Roles roleObj = new Roles();
        long id = jdbcTemplate.queryForObject("SELECT nvl(max(\"id\")+1, 1) FROM Roles", new MapSqlParameterSource(), Long.class);
        roleObj.set_id(id);
        
        String sql = "INSERT INTO Roles (\"id\", \"role\",\"_user\"  )    VALUES (:id, :role, :_user)";

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("role", role)
            .addValue("_user", id_user);

        jdbcTemplate.update(sql, parameters);
        
    }


    public void deleteNotInArray(Long id_user, ArrayList<String> roles) {
        String in = " and \"role\" NOT IN (:roles)";
        String sql = "DELETE FROM Roles WHERE \"_user\"=:id_user ";
        
        if (roles != null && roles.size() > 0)
            sql = sql + in;
            
        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id_user", id_user)
            .addValue("roles", roles);
        
        jdbcTemplate.update(sql, parameters);
        
    }

}