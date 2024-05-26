import { Component } from '@angular/core';


const RENTIFY = 'RENTIFY';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
    //Variables and childrens
    application_name:string = RENTIFY;


    ngOnInit():void {

    }

    constructor(){}
}
