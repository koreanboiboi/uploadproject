import { Component, OnInit } from '@angular/core';
import { SpotifyAuthService } from 'src/app/spotify-auth.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  show = true
  display = false;

  centered = false;
  disabled = false;
  unbounded = false;

  radius!: number;
  color!: string;
  
  panelOpenState = false;
 
  constructor (private spotSvc: SpotifyAuthService){}

  ngOnInit(): void {

   
      
  }

  shows(){
    this.show = !this.show
  }

  displayDetail(){
    this.display = !this.display
  }
  

  login(){
    this.spotSvc.spotifyLogin();
    // this.spotSvc.getUserProfile()
  }
 
}


