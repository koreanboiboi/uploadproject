
export interface User{
  email:string
  // link: string;
  display_name: string;
  external_urls: {
    spotify: string;
  };
  followers: {
    href: string | null;
    total: number;
  };
  href: string;
  id: string;
  images: {
    height: number | null;
    url: string;
    width: number | null;
  }[];
  type: string;
  uri: string;
}

export interface Image {
    /** The image height in pixels. If unknown: null or not returned. */
    height: number | null;
    /** The source URL of the image. */
    url: string;
    /** The image width in pixels. If unknown: null or not returned. */
    width: number | null;
}

// -----------------------------------------------------/v1/search--------------------

// export interface SpotifySearchResponse {
//     albums?: SpotifyAlbums;
//     artists?: SpotifyArtists;
//     tracks?: SpotifyTracks;
//   }
  
//   export interface SpotifyAlbums {
//     href: string;
//     items: SpotifyAlbum[];
//     limit: number;
//     next: string | null;
//     offset: number;
//     previous: string | null;
//     total: number;
//   }
  
//   export interface SpotifyAlbum {
//     album_type: string;
//     artists: SpotifyArtist[];
//     available_markets: string[];
//     external_urls: SpotifyExternalUrls;
//     href: string;
//     id: string;
//     images: SpotifyImage[];
//     name: string;
//     release_date: string;
//     release_date_precision: string;
//     total_tracks: number;
//     type: string;
//     uri: string;
//   }

// export interface Artist {
//     artistList: SpotifyArtists []
// }
  
  export interface sqlAlbum{
    directLink: any
    albumName: string
    date: any
    id: string
    image: string
  }

  export interface sqlArtist{
    directLink: any
    name: string
    id: string
    popularity: number
    image: string
  }

  export interface SpotifyArtists{
        link: string
        external_urls: {
          spotify: string;
        };
        followers: {
          href: string | null;
          total: number;
        };
        genres: string[];
        href: string;
        id: string;
        images: {
          height: number;
          url: string;
          width: number;
        }[];
        name: string;
        popularity: number;
        type: string;
        uri: string;
    
  }

  export interface SpotifyAlbums {
    external_urls: {
      spotify: string;
    };
    href: string;
    id: string;
    images: {
      height: number;
      url: string;
      width: number;
    }[];
    name: string;
    release_date: string;
    uri: string;
  }

//   export interface SpotifyArtists {
//     href: string;
//     items: SpotifyArtist[];
//     limit: number;
//     next: string | null;
//     offset: number;
//     previous: string | null;
//     total: number;
//   }
  
//   export interface SpotifyArtist {
//     external_urls: SpotifyExternalUrls;
//     followers: SpotifyFollowers;
//     genres: string[];
//     href: string;
//     id: string;
//     images: SpotifyImage[];
//     name: string;
//     popularity: number;
//     type: string;
//     uri: string;
//   }
  
//   export interface SpotifyTracks {
//     href: string;
//     items: SpotifyTrack[];
//     limit: number;
//     next: string | null;
//     offset: number;
//     previous: string | null;
//     total: number;
//   }
  
//   export interface SpotifyTrack {
//     album: SpotifySimplifiedAlbum;
//     artists: SpotifyArtist[];
//     available_markets: string[];
//     disc_number: number;
//     duration_ms: number;
//     explicit: boolean;
//     external_ids: SpotifyExternalIds;
//     external_urls: SpotifyExternalUrls;
//     href: string;
//     id: string;
//     is_local: boolean;
//     is_playable: boolean;
//     name: string;
//     popularity: number;
//     preview_url: string | null;
//     track_number: number;
//     type: string;
//     uri: string;
//   }
  
//   export interface SpotifySimplifiedAlbum {
//     album_type: string;
//     artists: SpotifyArtist[];
//     available_markets: string[];
//     external_urls: SpotifyExternalUrls;
//     href: string;
//     id: string;
//     images: SpotifyImage[];
//     name: string;
//     release_date: string;
//     release_date_precision: string;
//     total_tracks: number;
//     type: string;
//     uri: string;
//   }
  
//   export interface SpotifyExternalUrls {
//     spotify: string;
//   }
  
//   export interface SpotifyFollowers {
//     href: null;
//     total: number;
//   }
  
//   export interface SpotifyImage {
//     height: number | null;
//     url: string;
//     width: number | null;
//   }
  
//   export interface SpotifyExternalIds {
//     isrc: string;
//   }
