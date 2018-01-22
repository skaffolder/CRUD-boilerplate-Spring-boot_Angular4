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
import { ActorBaseService } from "./base/actor.base.service";
import { Actor } from '../domain/manage_film_example_db/actor';

/**
 * YOU CAN OVERRIDE HERE ActorBaseService
 */

@Injectable()
export class ActorService extends ActorBaseService {
    
    // CONSTRUCTOR
    constructor(http: Http, authenticationService: AuthenticationService) {
            super(http, authenticationService);
    }
}