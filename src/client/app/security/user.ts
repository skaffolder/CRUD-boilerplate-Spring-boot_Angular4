/**
 * THIS CLASS MAP THE USER PROFILE
 */
export class User {
    
    public _id: string;
    public username: string;
    public password: string;
    public token: string;
    public roles: string[]
    public mail: string;
    public name: string;
    public surname: string;

    constructor(
        _id: string,
        username: string,
        token: string,
        roles: string[]
    ){
        this._id = _id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    // UTILS FUNCTIONS

    /**
     * Check if logged user is admin 
     */
    isAdmin():boolean {
        if (!this.roles)
            return false;
        
        return this.roles.indexOf("ADMIN") == 0;
    }

    /**
     * Check if logged user has one role 
     */
    hasRole(role:string | Array<string>):boolean {
        if (!this.roles) return false;

        if (typeof role == "string") {
            return (this.roles.indexOf(role) != -1);
        } else {
            let found = role.filter(rol => {
                return (this.roles.indexOf(rol) != -1);
            })
            return found.length > 0;
        }
    }
    
}