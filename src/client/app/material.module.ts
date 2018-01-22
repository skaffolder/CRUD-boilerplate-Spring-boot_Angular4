/**
 * Import here all the material modules
 * 
 * Documentation at:
 * https://material.angular.io
 * 
 */
import { 
  MatDialogModule, 
  MatButtonModule, 
  MatCheckboxModule, 
  MatInputModule, 
  MatCardModule, 
  MatSelectModule,
  MatToolbarModule,
  MatMenuModule,
  MatIconModule,
  MatListModule,
  MatChipsModule
} from '@angular/material';
import { NgModule } from "@angular/core";

@NgModule({
  imports: [
    MatDialogModule, 
    MatButtonModule, 
    MatCheckboxModule, 
    MatInputModule, 
    MatCardModule, 
    MatSelectModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatChipsModule],
  exports: [
    MatDialogModule, 
    MatButtonModule, 
    MatCheckboxModule, 
    MatInputModule, 
    MatCardModule, 
    MatSelectModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatChipsModule],
})
export class MaterialModule { }