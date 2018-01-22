// DEPENDENCIES
import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, RequestOptions } from '@angular/http';
import { AppRoutingModule }     from './app-routing.module';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';


// SECURITY
import { SecurityService } from './security/services/security.service';
import { TokenEmitter } from './security/token-emitter';
import { TokenInterceptor } from './security/token-interceptor';
import { AuthenticationService } from './security/authentication.service';
import { AuthGuard } from "./security/auth.guard";
import { UserService } from './security/services/user.service';

// SECURITY VIEWS
import { ManageUserListComponent } from './security/manage-user/list-user/manage-user-list.component';
import { ManageUserEditComponent } from './security/manage-user/edit-user/manage-user-edit.component';
import { ProfileComponent } from './security/profile/profile.component';
import { ModalChangePasswordComponent } from './components/modal-change-password.component';
import { LoginComponent } from './pages/login/login.component';

/* START MY VIEWS IMPORTS*/
// Do not edit this comment content, it will be overwritten in next Skaffolder generation
import { HomeComponent } from './pages/home/home.component';
import { ActorEditComponent } from './pages/actor-edit/actor-edit.component';
import { ActorListComponent } from './pages/actor-list/actor-list.component';
import { FilmEditComponent } from './pages/film-edit/film-edit.component';
import { FilmListComponent } from './pages/film-list/film-list.component';
import { FilmMakerEditComponent } from './pages/film-maker-edit/film-maker-edit.component';
import { FilmMakerListComponent } from './pages/film-maker-list/film-maker-list.component';

/* END MY VIEWS IMPORTS*/

/* START MY SERVICES IMPORTS*/
// Do not edit this comment content, it will be overwritten in next Skaffolder generation
import { ActorService } from './services/actor.service';
import { FilmService } from './services/film.service';
import { FilmMakerService } from './services/film-maker.service';

/* END MY SERVICES IMPORTS*/

// LAYOUT
import { AppComponent }  from './app.component';
import { NavbarComponent } from './components/navbar.component';
import { SearchPipe } from './pipes/search.pipe';
import { MaterialModule } from "./material.module";
import { ModalRemoveComponent } from './components/modal-remove.component';

//DIRECTIVES
import { EqualValidator } from './directives/equal-validate.directive';
import { MailValidator } from './directives/mail-validate.directive';

// DECLARE APPLICATION MODULE
@NgModule({
  bootstrap: [ 
    AppComponent 
  ],
  imports: [  
    AppRoutingModule, // ROUTES
    MaterialModule, // MATERIAL THEME
    BrowserModule,
    BrowserAnimationsModule, 
    FormsModule,
    HttpModule
  ],
  declarations: [
    // LAYOUT
    AppComponent, 
    NavbarComponent,
    ModalRemoveComponent,
    EqualValidator,
    MailValidator,
    
     // SECURITY
    LoginComponent,
    ProfileComponent,
    ManageUserListComponent,
    ManageUserEditComponent,
    ModalChangePasswordComponent,
    
    /* START DECLARATIONS */
// Do not edit this comment content, it will be overwritten in next Skaffolder generation
    HomeComponent,
    ActorEditComponent,
    ActorListComponent,
    FilmEditComponent,
    FilmListComponent,
    FilmMakerEditComponent,
    FilmMakerListComponent,
 /* END DECLARATIONS */
    
    // PIPE
    SearchPipe
  ],
  entryComponents: [
    ModalRemoveComponent,
    ModalChangePasswordComponent
  ],
  providers:    [
    /* START PROVIDERS */
// Do not edit this comment content, it will be overwritten in next Skaffolder generation
    ActorService,
    FilmService,
    FilmMakerService,
 /* END PROVIDERS */
    
    // SECURITY
    AuthGuard,
    AuthenticationService,
    UserService,
    SecurityService,
    TokenEmitter,
    {provide: RequestOptions, useClass: TokenInterceptor}
  ],
})
export class AppModule { }
