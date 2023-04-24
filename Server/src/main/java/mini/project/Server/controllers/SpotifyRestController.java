package mini.project.Server.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mini.project.Server.models.Album;
import mini.project.Server.models.Artist;
import mini.project.Server.repositories.SpotifyRepository;


@RestController
@RequestMapping("/api")
public class SpotifyRestController {

   

    @Autowired 
    private SpotifyRepository spotRepo;


//SAVE USER
@PostMapping("/saveUser")
@ResponseBody
public ResponseEntity<String> saveUser(@RequestBody Map<String,Object> userData){

System.out.println("userData" + userData);
spotRepo.saveUser(userData);

return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved User to Mysql Database");

}

//SAVE ARTIST
@PostMapping("/saveArtist")
@ResponseBody
public ResponseEntity<String> saveArtist(@RequestBody Map<String,Object> artistsData){

System.out.println("artistData" + artistsData);
spotRepo.saveArtist(artistsData);

return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved Artist to Mysql Database");

}

//SAVE ALBUM
@PostMapping("/saveAlbum")
@ResponseBody
public ResponseEntity<String> saveAlbum(@RequestBody Map<String,Object> albumData){

System.out.println("albumData" + albumData);
spotRepo.saveAlbum(albumData);

return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved Album to Mysql Database");

}
//--------------------------------------------------------MYSQL------------------------------------------------------------
//get list of artist from mysql

@GetMapping("/getArtist/{userId}")
public List<Artist> getArtist(@PathVariable String userId ) {
  
   
  List<Artist> artist =  spotRepo.getArtist(userId);

  return artist;
    

}

@DeleteMapping("/deleteArtist/{artistId}")
public void deleteArtist(@PathVariable String artistId) {

    spotRepo.deleteArtist(artistId);
}

@GetMapping("/getAlbum/{userId}")
public List<Album> getAlbum(@PathVariable String userId) {
    List<Album> album = spotRepo.getAlbum(userId);

    return album;
}

@DeleteMapping("/deleteAlbum/{albumId}")
public void deleteAlbum(@PathVariable String albumId) {

    spotRepo.deleteAlbum(albumId);
}

// -----------------------------------------post blob----------------------------------------------------------------------
@PostMapping("/gallery")
public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){

    try {
        byte[] bytes = file.getBytes();
        spotRepo.upload(bytes);
        return ResponseEntity.ok().body("File uploaded successfully");
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }

}

@GetMapping("/viewGallery")
public byte[] getBlob(){

    byte[] blobData = spotRepo.getBlob();

    return blobData;

}
    
}
    

