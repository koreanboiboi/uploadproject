import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { SpotifyService } from 'src/app/spotify.service';


@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {



  constructor( private spotSvc: SpotifyService){

    
  }

  ngOnInit(): void {
    this.spotSvc.getBlob()
  }

  getBlob(){
    this.spotSvc.getBlob()
  }

   
}
