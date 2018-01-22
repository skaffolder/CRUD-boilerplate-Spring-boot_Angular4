// DEPENDENCIES
import { Observable } from 'rxjs/Rx';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';

// SECURITY
import { AuthenticationService } from '../security/authentication.service';

// CONFIG
import { config } from "../../config/properties";

// MODEL
import { FilmMakerBaseService } from "./base/film-maker.base.service";
import { FilmMaker } from '../domain/manage_film_example_db/film-maker';

/**
 * YOU CAN OVERRIDE HERE FilmMakerBaseService
 */

@Injectable()
export class FilmMakerService extends FilmMakerBaseService {
    
    // CONSTRUCTOR
    constructor(http: Http, authenticationService: AuthenticationService) {
            super(http, authenticationService);
    }
}