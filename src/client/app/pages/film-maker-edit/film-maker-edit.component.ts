// Import Libraries
import { ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

// Import Services
import { FilmMakerService } from '../../services/film-maker.service';
import { FilmService } from '../../services/film.service';

// Import Models
import { FilmMaker } from '../../domain/manage_film_example_db/film-maker';

import { Film } from '../../domain/manage_film_example_db/film';

// START - USED SERVICES
/*
 *	FilmMakerService.create
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	FilmService.findByfilmMaker
 *		PARAMS: 
 *					Objectid key - Id della risorsa filmMaker da cercare
 *		
 *
 *	FilmMakerService.get
 *		PARAMS: 
 *					ObjectId id - Id 
 *		
 *
 *	FilmMakerService.update
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * FilmService  
 * FilmMakerService  
 */
// END - REQUIRED RESOURCES

/**
 * Edit component for FilmMakerEdit
 */
@Component({
    selector: 'film-maker-edit',
    templateUrl : './film-maker-edit.component.html',
    styleUrls: ['./film-maker-edit.component.css']
})
export class FilmMakerEditComponent implements OnInit {

    item: FilmMaker;
    listFilmMaker: FilmMaker[];
	externalFilm: Film[];
    model: FilmMaker;
    
    constructor(
        private filmmakerService: FilmMakerService,
        private filmService: FilmService,
        private route: ActivatedRoute,
        private location: Location) {
        // Init item
        this.item = new FilmMaker();
	   this.externalFilm = [];
    }

    ngOnInit(): void {
            this.route.params.subscribe(param => {
                let id: string = param['id'];
                if (id !== 'new') {
                    // Get item from server 
                    this.filmmakerService.get(id).subscribe(item => this.item = item);
                }
                this.filmService.findByFilmMaker(id).subscribe(list => this.externalFilm = list);
            });
    }

    /**
     * Save Item
     */
    save (formValid:boolean, item: FilmMaker): void{
        if (formValid) {
            if(item._id){
                this.filmmakerService.update(item).subscribe(data => this.goBack());
            } else {
                this.filmmakerService.create(item).subscribe(data => this.goBack());
            }  
        }
    }

    /**
     * Go Back
     */
    goBack(): void {
        this.location.back();
    }
    

}