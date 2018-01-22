package com.manage_film_example.controller.base;

import org.springframework.security.access.annotation.Secured;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manage_film_example.db.manage_film_example_db.service.FilmMakerService;
import com.manage_film_example.db.manage_film_example_db.mapper.FilmMaker;

public class FilmMakerBaseController {

	FilmMakerService filmmakerService = new FilmMakerService();



//CRUD METHODS


    //CRUD - CREATE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/filmmakers", method = RequestMethod.POST, headers = "Accept=application/json")
	public FilmMaker insert(@RequestBody FilmMaker obj) {
		FilmMaker result = filmmakerService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/filmmakers/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		filmmakerService.delete(id);
	}
	
	
    //CRUD - GET ONE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/filmmakers/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public FilmMaker get(@PathVariable Long id) {
		FilmMaker obj = filmmakerService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/filmmakers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FilmMaker> getList() {
		List<FilmMaker> list = filmmakerService.getList();
		return list;
	}
	
	

    //CRUD - EDIT
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/filmmakers/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public FilmMaker update(@RequestBody FilmMaker obj, @PathVariable("id") Long id) {
		FilmMaker result = filmmakerService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
