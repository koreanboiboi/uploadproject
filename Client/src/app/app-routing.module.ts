import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { HomeComponent } from './components/home.component';
import { LoginComponent } from './components/Login/login.component';
import { AlbumsComponent } from './components/SearchByType/albums.component';
import { ArtistsComponent } from './components/SearchByType/artists.component';
import { UserComponent } from './components/user/user.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { LogoutComponent } from './components/logout/logout.component';


const routes: Routes = [
  {path: '', component:LoginComponent},
  {path: 'artists', component:ArtistsComponent},
  {path: 'albums', component:AlbumsComponent},
  {path: 'success' , component: HomeComponent},
  {path: 'user' , component: UserComponent},
  {path: 'favorite' , component: FavoriteComponent},
  {path: 'gallery' , component: GalleryComponent},
  {path: 'logout' , component: LogoutComponent},

  { path: '**', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
