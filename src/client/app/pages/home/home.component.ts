// Import Libraries
import { Component } from '@angular/core';
import { Location } from '@angular/common';

// Import Services

// START - USED SERVICES		
// END - USED SERVICES

// START - REQUIRED RESOURCES		
// END - REQUIRED RESOURCES

/**
 * Home Component
 */
@Component({
    selector: 'home',
    templateUrl : './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent {
    
    constructor(private location: Location) { 
        
    }
}