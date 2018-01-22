package com.manage_film_example.controller.base;

import org.springframework.security.access.annotation.Secured;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manage_film_example.db.manage_film_example_db.service.FilmService;
import com.manage_film_example.db.manage_film_example_db.mapper.Film;

public class FilmBaseController {

	FilmService filmService = new FilmService();



//CRUD METHODS


    //CRUD - CREATE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films", method = RequestMethod.POST, headers = "Accept=application/json")
	public Film insert(@RequestBody Film obj) {
		Film result = filmService.insert(obj);

	    
		//external relation cast
		ArrayList<Long> cast = obj.getCast();
		if (cast != null) {
			FilmService.Film_castService.updateRelation(result.get_id(), cast);
		}
		
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		filmService.delete(id);
	}
	

    //CRUD - FIND BY cast
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films/findBycast/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Film> findBycast(@PathVariable("key") Long idcast) {
		List<Film> list = filmService.findBycast(idcast);
		return list;
	}
	

    //CRUD - FIND BY filmMaker
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films/findByfilmMaker/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Film> findByfilmMaker(@PathVariable("key") Long idfilmMaker) {
		List<Film> list = filmService.findByfilmMaker(idfilmMaker);
		return list;
	}
	
	
    //CRUD - GET ONE
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Film get(@PathVariable Long id) {
		Film obj = filmService.get(id);
		
		
		//external relation cast
		ArrayList<Long> cast = FilmService.Film_castService.findBy_Film(id);
		obj.setCast(cast);
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Film> getList() {
		List<Film> list = filmService.getList();
		return list;
	}
	
	

    //CRUD - EDIT
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/films/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Film update(@RequestBody Film obj, @PathVariable("id") Long id) {
		Film result = filmService.update(obj, id);

	    
		//external relation cast
		ArrayList<Long> cast = obj.getCast();
		if (cast != null) {
			FilmService.Film_castService.updateRelation(id, cast);
		}
		
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
