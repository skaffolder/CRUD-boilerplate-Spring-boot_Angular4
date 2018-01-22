// Import Libraries
import { Observable } from 'rxjs/Rx';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { ModalRemoveComponent } from '../../components/modal-remove.component';


// Import Services
import { FilmMakerService } from '../../services/film-maker.service';

// Import Models
import { FilmMaker } from '../../domain/manage_film_example_db/film-maker';

import { Film } from '../../domain/manage_film_example_db/film';

// START - USED SERVICES
/*
 *	FilmMakerService.delete
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	FilmMakerService.list
 *		PARAMS: 
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * FilmMakerService  
 */
// END - REQUIRED RESOURCES

@Component({
    selector: "film-maker-list",
    templateUrl: './film-maker-list.component.html',
    styleUrls: ['./film-maker-list.component.css']
})
export class FilmMakerListComponent implements OnInit {
    
    // Attributes
    list: FilmMaker[];
    search: any = {};
    idSelected: string;
    
    // Constructor
    constructor(
        private filmmakerService: FilmMakerService, 
        public dialog: MatDialog) {}

    // Functions
    ngOnInit(): void {
        this.filmmakerService.list().subscribe(list => this.list = list);
    }

    openModal(id: string): void {
        let dialogRef = this.dialog.open(ModalRemoveComponent, {
            width: '250px',
            data: () => {
                // Execute on confirm
                this.filmmakerService.remove(id).subscribe(() => {
                    dialogRef.close();
                });
                this.list = this.list.filter(item => item._id != id);
            }
        });
    }

}