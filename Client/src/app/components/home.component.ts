import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpotifyAuthService } from '../spotify-auth.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';

import {User} from '../models' 
import { Router } from '@angular/router';
import { SpotifyService } from '../spotify.service';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private spotAuthSvc: SpotifyAuthService, private fb: FormBuilder, private spotSvc: SpotifyService, private router: Router){}

  user!: User []
  userForm!: FormGroup
  loggedin = false;

  ngOnInit(): void {
    this.spotAuthSvc.requestAccessToken();

    if (window.location.href.includes('error=access_denied')) {
      window.location.href = 'http://localhost:4200';
    } else {
      setTimeout(() => {
        this.router.navigate(['/user']);
      }, 5000);
    }

  }

  async getUserProfile(){
    try {
      const data = await this.spotAuthSvc.getUserProfile().toPromise();
      this.user = data;
      console.log("DATA",data)
      console.log("ID>>>" , data[0].id)
      const userId = data[0].id
      localStorage.setItem("user_id",userId)

      this.spotSvc.saveUser(data).subscribe(response => {
        alert("Saved Successfully")
      });
  
    } catch (error) {
      console.error(error);
    }
  }
}
