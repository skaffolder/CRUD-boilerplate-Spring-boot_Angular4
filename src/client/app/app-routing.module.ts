// DEPENDENCIES
import { NgModule } from '@angular/core';
import { CanActivate, RouterModule, Routes } from '@angular/router';

/* START MY VIEWS IMPORT */
// Do not edit this comment content, it will be overwritten in next Skaffolder generation
import { HomeComponent} from './pages/home/home.component';
import { ActorEditComponent} from './pages/actor-edit/actor-edit.component';
import { ActorListComponent} from './pages/actor-list/actor-list.component';
import { FilmEditComponent} from './pages/film-edit/film-edit.component';
import { FilmListComponent} from './pages/film-list/film-list.component';
import { FilmMakerEditComponent} from './pages/film-maker-edit/film-maker-edit.component';
import { FilmMakerListComponent} from './pages/film-maker-list/film-maker-list.component';

/* END MY VIEWS IMPORT */

// SECURITY
import { LoginComponent } from './pages/login/login.component';
import { ManageUserEditComponent } from './security/manage-user/edit-user/manage-user-edit.component';
import { ManageUserListComponent } from './security/manage-user/list-user/manage-user-list.component';
import { ProfileComponent } from './security/profile/profile.component';
import { AuthGuard } from "./security/auth.guard";

/**
 * WEB APP ROUTES
 */
const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full'  },

    /* START MY VIEWS */

    { path: 'actors/:id',  component: ActorEditComponent , canActivate: [AuthGuard] },
    { path: 'actors',  component: ActorListComponent , canActivate: [AuthGuard] },
    { path: 'films/:id',  component: FilmEditComponent , canActivate: [AuthGuard] },
    { path: 'films',  component: FilmListComponent , canActivate: [AuthGuard] },
    { path: 'filmmakers/:id',  component: FilmMakerEditComponent , canActivate: [AuthGuard] },
    { path: 'filmmakers',  component: FilmMakerListComponent , canActivate: [AuthGuard] },
    { path: 'home',  component: HomeComponent , canActivate: [AuthGuard] },

 /* END MY VIEWS */
    
    { path: 'home', component: HomeComponent},
    // SECURITY
    { path: 'manage-users',  component: ManageUserListComponent, canActivate: [AuthGuard], data:['ADMIN']},
    { path: 'manage-users/:id',  component: ManageUserEditComponent, canActivate: [AuthGuard], data:['ADMIN']},
    { path: 'profile',  component: ProfileComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent}
];

/**
 * ROUTING MODULE
 */
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})

export class AppRoutingModule {}