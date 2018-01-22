package com.manage_film_example.security;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import com.manage_film_example.db.manage_film_example_db.mapper.Roles;
import com.manage_film_example.db.manage_film_example_db.mapper.Users;
import com.manage_film_example.db.manage_film_example_db.service.RolesService;

public class AuthenticationService implements Authentication {

	private static final long serialVersionUID = 1L;
	private Users user;
	private final RolesService roleService = new RolesService();
	private ArrayList<GrantedAuthority> listRoles;

	public AuthenticationService(String token) {
		
		if (token ==null || token.equals(""))
			return;
		
		// Get user from token decode
		try {
			//decode token
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			String userJSON = jwt.getClaims().get("user").asString();

			// Set user in Authentication Service
        	Gson gson = new Gson();
			user = gson.fromJson(userJSON, Users.class);
			
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// Set roles
	    if (user != null){
	    	// Add default role
			listRoles = new ArrayList<GrantedAuthority>();
			GrantedAuthority roleDefault = new SimpleGrantedAuthority("ROLE_PRIVATE_USER");
			listRoles.add(roleDefault);
			
			// Get roles from database
			ArrayList<Roles> listDbRoles = roleService.getRoles(user.get_id());
			
			for (Roles roles : listDbRoles) {
				GrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + roles.getRole());
				listRoles.add(role);
			}
	    }
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return listRoles;
	}

	@Override
	public boolean isAuthenticated() {
		return user != null;
	}
	
	public Users getUser() {
		return user;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


}

