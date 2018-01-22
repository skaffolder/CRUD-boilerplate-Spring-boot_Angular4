package com.manage_film_example.db.manage_film_example_db.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.manage_film_example.db.manage_film_example_db.mapper.Users;
import com.manage_film_example.security.AuthenticationService;

@Service
public class UsersService{

	private static NamedParameterJdbcTemplate jdbcTemplate;
	private static RolesService rolesService = new RolesService();
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

    	
	public Users login(String username, String password) {
	    
		String sql = "select * from Users where \"username\" = :username AND  \"password\" = :password";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("username", username)
		.addValue("password", password);
	    
	    try {
		    Users user = jdbcTemplate.queryForObject(sql, parameters, new Users());
		    user = this.addRoles(user);
		    return user;
	    } catch(EmptyResultDataAccessException e){
	    	return null;
	    }
	}
	
	//CRUD - CREATE
	public Users insert(Users obj) {
		long id = jdbcTemplate.queryForObject("SELECT nvl(max(\"id\")+1, 1) FROM Users", new MapSqlParameterSource(), Long.class);
		obj.set_id(id);
		String sql = "INSERT INTO Users (\"id\", \"username\", \"password\", \"name\", \"surname\" , \"mail\" )	VALUES (:id, :username , :password, :name, :surname, :mail )";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("username", obj.getUsername())
			.addValue("password", obj.getPassword())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname())
			.addValue("mail", obj.getMail());

		jdbcTemplate.update(sql, parameters);

		// Insert Roles
		this.updateRoles(id, obj.getRoles());
		
	    return obj;
	}
	
    
    //CRUD - GET ONE
	public Users get(Long id) {
		String sql = "select * from Users where \"id\" = :id";
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    Users user = jdbcTemplate.queryForObject(sql, parameters, new Users());
	    user = this.addRoles(user);
	    return user;
	}
   
    
    //CRUD - GET LIST
	public List<Users> getList() {
		String sql = "select * from Users";
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    List<Users> list = jdbcTemplate.query(sql, parameters, new Users());
	    
	    for (Users user : list) {
			user = this.addRoles(user);
		}
	    
	    return list;
	}
        	
        
    //CRUD - EDIT
	public Users update(Users obj, Long id) {
		String sql = "UPDATE Users SET \"name\" = :name, \"surname\" = :surname , \"mail\" = :mail WHERE \"id\"=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname())
			.addValue("mail", obj.getMail());
		jdbcTemplate.update(sql, parameters);
		
		// Update Roles
		this.updateRoles(id, obj.getRoles());
		
	    return obj;
	}
	
	
    //CRUD - REMOVE
	public void delete(Long id) {
		
		// Remove roles
	    rolesService.deleteNotInArray(id, new ArrayList<String>());
	    
	    // Remove User
		String sql = "DELETE FROM Users WHERE \"id\"=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		jdbcTemplate.update(sql, parameters);
	    
	}


	public void changePassword(Long id_user, Map<String, String> params) throws Exception {
		
		AuthenticationService auth =(AuthenticationService) SecurityContextHolder.getContext().getAuthentication();

		String usernameAdmin = auth.getUser().getUsername();
		String passwordAdmin = params.get("passwordAdmin");
		String passwordNew= params.get("passwordNew");

		// Check admin user
		Users admin = this.login(usernameAdmin, passwordAdmin);
		
		if(admin == null)
			throw new Exception("Admin password not valid");
		if (!admin.hasRole("ADMIN"))
			throw new Exception("User is not admin");
		
		this.updatePassword(id_user, passwordNew);
	}
	

	// UTILS FUNCTION

	public void updatePassword(Long id_user, String password) {
		String sql = "UPDATE Users SET \"password\" = :password WHERE \"id\"=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id_user)
			.addValue("password", password);
		jdbcTemplate.update(sql, parameters);
	}
	
    public Users addRoles(Users user) {
	    ArrayList<String> roles = rolesService.getRolesAsStringArray(user.get_id());
	    user.setRoles(roles);
	    return user;
	}

	public void updateRoles(Long id_user, ArrayList<String> roles) {

		// Delete not in array
	    rolesService.deleteNotInArray(id_user, roles);
		
		// Get actual    		
	    List<String> actual = rolesService.getRolesAsStringArray(id_user);
	    
		// Insert new
		for (String role : roles) {
			if (actual.indexOf(role) == -1){
				rolesService.insert(id_user, role);
			}
		}
		
	}
	
}