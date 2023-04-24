import { Component, OnInit } from '@angular/core';
import { async } from 'rxjs';
import { SpotifyAlbums, SpotifyArtists, sqlAlbum, sqlArtist } from 'src/app/models';
import { SpotifyService } from 'src/app/spotify.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit{

  showArtist = false;
  showAlbum = false;

  userId: any
  constructor(private spotSvc: SpotifyService){}

  artist!: sqlArtist []
  album!: sqlAlbum []

  ngOnInit(): void {
      const userID = localStorage.getItem('user_id')
      this.userId = userID
  }


  async getArtist(){
    this.showArtist = true;
    this.showAlbum = false;
    console.log("artist>>>",this.artist)
    try{
    const artistData = await this.spotSvc.getArtist(this.userId).toPromise()

    this.artist = artistData
    console.log("artistdata>>>",artistData)
    console.log("artist>>>",this.artist)
    } catch (error) {
      console.log(error)
    }
  }

  deleteArtist(removeById: any){

    this.spotSvc.deleteArtist(removeById).subscribe(response => {
      console.log("deleted",removeById)
      
      this.getArtist()
    })

  }

  async getAlbum(){
    this.showArtist = false;
    this.showAlbum = true;
    console.log("album USER ID",this.userId)
    try {
    const albumData = await this.spotSvc.getAlbum(this.userId).toPromise()

    this.album = albumData
    console.log("ALBUMDATA",albumData)
      
    } catch (error) {
      console.log(error)
    }
  }
  
  deleteAlbum(removeById: any){

    this.spotSvc.deleteAlbum(removeById).subscribe(response => {
      console.log("deleted",removeById)
      this.getAlbum()
    })

  }


  shareArtist(artistData:any) {
    const artist = artistData
    const imageURL = artistData.image
    const fileContent = JSON.stringify(artist)

    fetch(imageURL)
    .then(response => response.blob())
    .then(blob => {
      const imageFile = new File([blob], 'artist.jpg', { type: 'image/jpeg' });
      
    const formData = new FormData()

    formData.append('file', blob, 'artist.jpg');
      
    fetch('http://localhost:8080/api/gallery', {
      method: 'POST',
      body: formData
    })
    .then(response => response.json())
    .then(() => {
      console.log('Upload success:',JSON.stringify(formData));
    })
    .catch(error => {
      console.error('Upload error:', error);
    });

     

    navigator.share({
      title: 'My awesome APPLICATION',
      text: 'Check out this cool content!\n'
            +`Listen to ${JSON.stringify(artistData.name)} on Spotify \n`
            +'Click link below',
      url: artistData.directLink,
      files: [new File([fileContent], 'Artist.txt', { type: 'text/plain' }),imageFile]
    })
    .then(result => {
      console.info('>>> share result: ', result)
    })
    .catch(err => {
      console.error('>>> share error: ', err)
    })
  })
  }

  shareAlbum (albumData:any) {
    const album = albumData
    const imageURL = albumData.image
    const fileContent = JSON.stringify(album)

    fetch(imageURL)
    .then(response => response.blob())
    .then(blob => {
      const imageFile = new File([blob], 'album.jpg', { type: 'image/jpeg' });
      
    const formData = new FormData()
    formData.append('file', blob, 'album.jpg');
      

    fetch('http://localhost:8080/api/gallery', {
      method: 'POST',
      body: formData
    })
    .then(response => response.json())
    .then(() => {
      console.log('Upload success:',JSON.stringify(formData));
    })
    .catch(error => {
      console.error('Upload error:', error);
    });


    navigator.share({
      title: 'My awesome SINGLE PAGE APP',
      text: 'Check out this cool content!\n'
            +`Listen to ${JSON.stringify(albumData.albumName)} on Spotify \n`
            +'Click link below',
      url: albumData.directLink,
      files: [new File([fileContent], 'Album.txt', { type: 'text/plain' }),imageFile]
    })
    .then(() => {
      console.info('Share success ')
    })
    .catch(err => {
      console.error('Share error: ', err)
    })
  })
  }

}
