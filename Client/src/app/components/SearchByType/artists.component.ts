import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { min } from 'lodash';
import { SpotifyArtists } from 'src/app/models';
import { SpotifyAuthService } from 'src/app/spotify-auth.service';
import { SpotifyService } from 'src/app/spotify.service';

@Component({
  selector: 'app-artists',
  templateUrl: './artists.component.html',
  styleUrls: ['./artists.component.css']
})
export class ArtistsComponent implements OnInit{

  artistsForm!: FormGroup 
  
  artist!: SpotifyArtists []

  popup: boolean = false;
  selectArtist: any;

  artistControl = new FormControl('', [Validators.required]);
  limitControl = new FormControl(5, [Validators.required,Validators.min(1),Validators.max(15)]);


  constructor(private fb: FormBuilder,private spotAuthSvc: SpotifyAuthService, private spotSvc: SpotifyService){}

  ngOnInit(): void {
    this.artistsForm = this.fb.group({
      q: this.fb.control<string>('',[Validators.required]),
      type: this.fb.control<string>('artist'),
      limit: this.fb.control<number>(5,[Validators.required, Validators.min(1),Validators.max(15)])
  })

  }

   async searchArtist(){

    const param = new HttpParams().set("q",this.artistsForm.value.q).set("type",this.artistsForm.value.type).set("limit",this.artistsForm.value.limit)

    console.log(this.artistsForm.value.q)
    console.log(this.artistsForm.value.type)
    console.log(this.artistsForm.value.limit)

    console.log("param>>>>>>>>>>>",JSON.stringify(param))
    console.log(param)

    try {
      const data = await this.spotAuthSvc.searchArtists(param).toPromise();
      console.log("data string>>>>>>",JSON.stringify(data))
      // console.log("ext url >>>>>>" , data.external_urls)
      this.artist = data;
      
  
    } catch (error) {
      console.error(error);
    }

  }

  saveArtist(selectedArtist: any){

    this.selectArtist = selectedArtist

    const artistData = {
        "spotify_artist_id": selectedArtist.id,
        "artist_name": selectedArtist.name,
        "popularity": selectedArtist.popularity,
        "external_urls": `https://open.spotify.com/artist/${selectedArtist.id}`,
        "image": selectedArtist.images[0].url
    };

    console.log("artistData" , artistData)
    
    this.spotSvc.saveArtist(artistData).subscribe(response => {
      alert("Saved Successfully")
    })

    this.popup = true
  }


  goSpotify(id: any){

    window.open('https://open.spotify.com/artist/'+id,'_blank')

    
  }
}
