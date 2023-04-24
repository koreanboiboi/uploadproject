package mini.project.Server.repositories;

import static mini.project.Server.repositories.Queries.SQL_CHECK_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_CHECK_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_CHECK_USER;
import static mini.project.Server.repositories.Queries.SQL_GET_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_INSERT_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_INSERT_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_INSERT_USER;
import static mini.project.Server.repositories.Queries.SQL_DELETE_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_GET_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_DELETE_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_INSERT_BLOB;
import static mini.project.Server.repositories.Queries.SQL_GET_BLOB;






import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mini.project.Server.models.Album;
import mini.project.Server.models.Artist;
import mini.project.Server.models.SpotifyAlbum;
import mini.project.Server.models.SpotifyArtist;
import mini.project.Server.models.User;

@Repository
public class SpotifyRepository {

    @Autowired
    private JdbcTemplate temp;

    private static String currentUserId;

    //save artist to mysql
    public List<SpotifyArtist> saveArtist(Map<String, Object> artistsData) {
        List<SpotifyArtist> savedArtists = new ArrayList<>();
        
        Map<String, Object> artistMap = (Map<String, Object>) artistsData.get("artist");
        System.out.println("ARTISTMAPPPPPPPPP"+artistMap);

        String spotifyArtistId = (String) artistMap.get("spotify_artist_id");
        String artistName = (String) artistMap.get("artist_name");
        int popularity = (int) artistMap.get("popularity");
        String link = (String) artistMap.get("external_urls");
        String image = (String) artistMap.get("image");
        String id = currentUserId;
    
        if (spotifyArtistId != null ) { 
            temp.update(SQL_INSERT_ARTIST, spotifyArtistId, artistName, popularity, link, image,id);
    
          
           
            SpotifyArtist savedArtist = temp.queryForObject(SQL_CHECK_ARTIST, new Object[]{spotifyArtistId}, new BeanPropertyRowMapper<>(SpotifyArtist.class));
          
            savedArtists.add(savedArtist);
        } else {
      
            System.out.println("ERROR");
            System.out.println(spotifyArtistId);
        }
    
        return savedArtists;
    }
 
    //GET ARTIST FROM MYSQL
    public List<Artist> getArtist(String userId){
       
        System.out.println("USER ID STRING >>>>>>>>>>>>>>>>>> " + userId);


        SqlRowSet rs = temp.queryForRowSet(SQL_GET_ARTIST,userId);

        List<Artist> artists = new LinkedList<>();
        while(rs.next())
            artists.add(Artist.createArtist(rs));

            return artists;


    }

    //DELETE ARTIST FROM MYSQL
    public void deleteArtist(String artistId){
        temp.update(SQL_DELETE_ARTIST, artistId);
    }

    //SAVE Album to MYSQL
    public List<SpotifyAlbum> saveAlbum(Map<String, Object> albumData) {
        List<SpotifyAlbum> savedAlbumlist = new ArrayList<>();
        
        Map<String, Object> albumMap = (Map<String, Object>) albumData.get("album");
        System.out.println("ALBUM MAP"+albumMap.toString());

        String spotifyAlbumId = (String) albumMap.get("spotify_album_id");
        String albumName = (String) albumMap.get("album_name");
        String releaseDate = (String) albumMap.get("release_date");
        String link = (String) albumMap.get("external_urls");
        String image = (String) albumMap.get("image");
        String id = currentUserId;
    
        if (spotifyAlbumId != null) { 
            temp.update(SQL_INSERT_ALBUM, spotifyAlbumId, albumName,releaseDate, link, image, id);
    
        
            SpotifyAlbum savedAlbum = temp.queryForObject(SQL_CHECK_ALBUM, new Object[]{spotifyAlbumId}, new BeanPropertyRowMapper<>(SpotifyAlbum.class));
            
            savedAlbumlist.add(savedAlbum);
        } else {
           
            System.out.println("ERROR");
        }
    
        return savedAlbumlist;
    }

    //GET Album FROM MYSQL
    public List<Album> getAlbum(String userId){
       
        System.out.println("USER ID STRING >>>>>>>>>>>>>>>>>> " + userId);

        SqlRowSet rs = temp.queryForRowSet(SQL_GET_ALBUM,userId);

        List<Album> album = new LinkedList<>();
        while(rs.next())
            album.add(Album.createAlbum(rs));

            return album;
  
    }

    //DELETE Album FROM MYSQL
    public void deleteAlbum(String albumId){
        temp.update(SQL_DELETE_ALBUM, albumId);
    }

    //save user to mysql
   public List<User> saveUser(Map<String, Object> userData) {
    List<User> userList = new ArrayList<>();

    List<Map<String, Object>> users = (List<Map<String, Object>>) userData.get("user");
    Map<String, Object> userMap = users.get(0);
    System.out.println("userMap"+userMap.toString());

    String userId = (String) userMap.get("id");
    currentUserId = userId;
    System.out.println("USERID>>>"+currentUserId);

    String userName = (String) userMap.get("display_name");

    if (userId != null) { 
        temp.update(SQL_INSERT_USER, userId, userName);

        User savedUser = temp.queryForObject(SQL_CHECK_USER, new Object[]{userId,userName}, new BeanPropertyRowMapper<>(User.class));
        
        userList.add(savedUser);
    } else {
        System.out.println("ERROR");
    }

    return userList;
}

    public void upload(byte[] bytes) {
        temp.update(SQL_INSERT_BLOB, bytes);
    }

    public byte[] getBlob(){
        
        byte[] blobData = temp.queryForObject(SQL_GET_BLOB,  new Object[]{}, byte[].class);

        return blobData;
    }

   
    

}
