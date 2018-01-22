// Import Libraries
import { Observable } from 'rxjs/Rx';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { ModalRemoveComponent } from '../../components/modal-remove.component';


// Import Services
import { FilmService } from '../../services/film.service';

// Import Models
import { Film } from '../../domain/manage_film_example_db/film';
import { Actor } from '../../domain/manage_film_example_db/actor';import { FilmMaker } from '../../domain/manage_film_example_db/film-maker';
// START - USED SERVICES
/*
 *	FilmService.delete
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	FilmService.list
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * FilmService  
 */
// END - REQUIRED RESOURCES

@Component({
    selector: "film-list",
    templateUrl: './film-list.component.html',
    styleUrls: ['./film-list.component.css']
})
export class FilmListComponent implements OnInit {
    
    // Attributes
    list: Film[];
    search: any = {};
    idSelected: string;
    
    // Constructor
    constructor(
        private filmService: FilmService, 
        public dialog: MatDialog) {}

    // Functions
    ngOnInit(): void {
        this.filmService.list().subscribe(list => this.list = list);
    }

    openModal(id: string): void {
        let dialogRef = this.dialog.open(ModalRemoveComponent, {
            width: '250px',
            data: () => {
                // Execute on confirm
                this.filmService.remove(id).subscribe(() => {
                    dialogRef.close();
                });
                this.list = this.list.filter(item => item._id != id);
            }
        });
    }

}