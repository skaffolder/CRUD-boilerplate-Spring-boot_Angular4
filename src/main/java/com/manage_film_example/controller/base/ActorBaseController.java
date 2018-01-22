package com.manage_film_example.controller.base;

import org.springframework.security.access.annotation.Secured;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manage_film_example.db.manage_film_example_db.service.ActorService;
import com.manage_film_example.db.manage_film_example_db.mapper.Actor;

public class ActorBaseController {

	ActorService actorService = new ActorService();



//CRUD METHODS


    //CRUD - CREATE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/actors", method = RequestMethod.POST, headers = "Accept=application/json")
	public Actor insert(@RequestBody Actor obj) {
		Actor result = actorService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/actors/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		actorService.delete(id);
	}
	
	
    //CRUD - GET ONE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/actors/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Actor get(@PathVariable Long id) {
		Actor obj = actorService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/actors", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Actor> getList() {
		List<Actor> list = actorService.getList();
		return list;
	}
	
	

    //CRUD - EDIT
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/actors/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Actor update(@RequestBody Actor obj, @PathVariable("id") Long id) {
		Actor result = actorService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
