import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { SpotifyAlbums } from 'src/app/models';
import { SpotifyAuthService } from 'src/app/spotify-auth.service';
import { SpotifyService } from 'src/app/spotify.service';

@Component({
  selector: 'app-albums',
  templateUrl: './albums.component.html',
  styleUrls: ['./albums.component.css']
})

export class AlbumsComponent implements OnInit {

  albumForm!: FormGroup

  album!: SpotifyAlbums []
  
  selectedAlbum: any
  popup: boolean = false

  constructor(private fb: FormBuilder,private spotAuthSvc: SpotifyAuthService, private spotSvc: SpotifyService){
  }

  ngOnInit(): void {
      this.albumForm = this.fb.group({
        q: this.fb.control<string>('',[Validators.required]),
        type: this.fb.control<string>('album'),
        limit: this.fb.control<number>(5,[Validators.required, Validators.min(5),Validators.max(25)])
    })
  }

  async searchAlbum(){

    const param = new HttpParams().set("q",this.albumForm.value.q).set("type",this.albumForm.value.type).set("limit",this.albumForm.value.limit)

    // console.log(this.artistsForm.value.q)
    // console.log(this.artistsForm.value.type)
    // console.log(this.artistsForm.value.limit)

    console.log("param>>>>>>>>>>>",JSON.stringify(param))
    console.log(param)

    try {
      const data = await this.spotAuthSvc.searchAlbum(param).toPromise();
      console.log("data string>>>>>>",JSON.stringify(data))
      this.album = data;

    } catch (error) {
      console.error(error);
    }
  }

  saveAlbum(alb: any){

    this.selectedAlbum = alb

    const albumData = {
        "spotify_album_id": this.selectedAlbum.id,
        "album_name": this.selectedAlbum.name,
        "release_date": this.selectedAlbum.release_date,
        "external_urls": this.selectedAlbum.external_urls.spotify,
        "image": this.selectedAlbum.images[0].url
    };

    console.log("albumData" , albumData)
    
    this.spotSvc.saveAlbum(albumData).subscribe(response => {
      alert("Saved Successfully")
    })

    this.popup = true
  }



  goSpotify(url: any){
    window.open(url,'_blank')
  }

}

