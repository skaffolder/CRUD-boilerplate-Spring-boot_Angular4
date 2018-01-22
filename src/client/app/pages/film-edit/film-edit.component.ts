// Import Libraries
import { ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

// Import Services
import { FilmService } from '../../services/film.service';
import { FilmMakerService } from '../../services/film-maker.service';
import { ActorService } from '../../services/actor.service';

// Import Models
import { Film } from '../../domain/manage_film_example_db/film';
import { Actor } from '../../domain/manage_film_example_db/actor';import { FilmMaker } from '../../domain/manage_film_example_db/film-maker';
// START - USED SERVICES
/*
 *	FilmService.create
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	FilmService.get
 *		PARAMS: 
 *					ObjectId id - Id 
 *		
 *
 *	FilmMakerService.list
 *		PARAMS: 
 *		
 *
 *	ActorService.list
 *		PARAMS: 
 *		
 *
 *	FilmService.update
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * ActorService  
 * FilmService  
 * FilmMakerService  
 */
// END - REQUIRED RESOURCES

/**
 * Edit component for FilmEdit
 */
@Component({
    selector: 'film-edit',
    templateUrl : './film-edit.component.html',
    styleUrls: ['./film-edit.component.css']
})
export class FilmEditComponent implements OnInit {

    item: Film;
    listCast: Actor[];
    listFilmMaker: FilmMaker[];
    model: Film;
    
    constructor(
        private filmService: FilmService,
        private filmmakerService: FilmMakerService,
        private actorService: ActorService,
        private route: ActivatedRoute,
        private location: Location) {
        // Init item
        this.item = new Film();
    }

    ngOnInit(): void {
            this.route.params.subscribe(param => {
                let id: string = param['id'];
                if (id !== 'new') {
                    // Get item from server 
                    this.filmService.get(id).subscribe(item => this.item = item);
                }
                this.actorService.list().subscribe(list => this.listCast = list);
                this.filmmakerService.list().subscribe(list => this.listFilmMaker = list);
            });
    }

    /**
     * Save Item
     */
    save (formValid:boolean, item: Film): void{
        if (formValid) {
            if(item._id){
                this.filmService.update(item).subscribe(data => this.goBack());
            } else {
                this.filmService.create(item).subscribe(data => this.goBack());
            }  
        }
    }

    /**
     * Go Back
     */
    goBack(): void {
        this.location.back();
    }
    
    /**
     * Actor Relations utils functions
     */
    containActor(id: string){
        if(!this.item.cast) return false;
        return this.item.cast.indexOf(id) != -1;
    }
    
    addActor(id: string) {
        if(!this.item.cast)
            this.item.cast = [];
        this.item.cast.push(id);
    }
    
    removeActor(index: number) {
        this.item.cast.splice(index,1);
    }

}