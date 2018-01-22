
import { Observable } from 'rxjs/Rx';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Injectable } from '@angular/core';
import { config } from "../../../config/properties";
import { User } from '../User';
import 'rxjs/add/operator/map';

/**
 * THIS SERVICE MAKE HTTP REQUEST TO SERVER, FOR MANAGE USERS
 */
@Injectable()
export class UserService {

    contextUrl:string = config.host + "/Users" 
    constructor(private http: Http) {}
    
    /**
     * Get list of users
     */
    list(): Observable<User[]> {
        return this.http
            .get(this.contextUrl)
            .map(response => response.json() as User[]);
    }

    /**
     * Get one user by id
     */
    get(id: string): Observable<User> {
        return this.http
            .get(this.contextUrl + "/" + id)
            .map(response => response.json() as User);
    }

    /**
     * Remove one user by id
     */
    remove(id: string): Observable<void> {
        return this.http
            .delete(this.contextUrl + "/" + id)
            .map(response => null);
    }

    /**
     * Create new user
     */
    create(item: User): Observable<void> {
        return this.http
            .post(this.contextUrl, item)
            .map(response => response.json());
    }

    /**
     * Update user
     */
    update(item: User): Observable<void> {
        return this.http
            .post(this.contextUrl + '/' + item._id, item)
            .map(response => response.json());
    }

    /**
     * Change user password
     */
    changePassword(id: string, passwordNew:string, passwordAdmin:string): Observable<void> {
        return this.http
            .post(this.contextUrl + '/' + id + '/changePassword' , {
                passwordNew: passwordNew, 
                passwordAdmin: passwordAdmin 
            })
            .map(response => response.json());
    }

}