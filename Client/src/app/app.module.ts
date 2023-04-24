import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlbumsComponent } from './components/SearchByType/albums.component';
import { ArtistsComponent } from './components/SearchByType/artists.component';
import { HomeComponent } from './components/home.component';
import { SpotifyAuthService } from './spotify-auth.service';
import { SpotifyService } from './spotify.service';
import { LoginComponent } from './components/Login/login.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { UserComponent } from './components/user/user.component';
import { GalleryComponent } from './components/gallery/gallery.component';

// --------------------Material Modules-----------------------------------------
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatButtonModule} from '@angular/material/button';
import {MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatChipsModule} from '@angular/material/chips';
import { LogoutComponent } from './components/logout/logout.component';

// ------------------------------------------------------------------------------


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ArtistsComponent,
    AlbumsComponent,
    FavoriteComponent,
    UserComponent,
    GalleryComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatToolbarModule,
    MatIconModule,
    MatDividerModule,
    MatExpansionModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatProgressBarModule,
    MatCardModule,
    MatInputModule,
    MatGridListModule,
    MatChipsModule
    
  ],
  providers: [SpotifyAuthService,SpotifyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
