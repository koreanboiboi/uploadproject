package mini.project.Server.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import mini.project.Server.services.SpotifyService;

@Controller
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpotifyController {

    @Autowired
    private SpotifyService spotSvc;
    

//------------------------------------------------from SpotifyAPI----------------------------------------------------------------------
//get user profile from spotifyAPI
@GetMapping(path = "/user")
@ResponseBody
public ResponseEntity<String> getUserProfile(@RequestHeader Map<String, String> headers){

    System.out.println("headers>>>>>>>>>>" + headers);
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    spotSvc.getUserProfile(headers).stream().forEach(v->{
        arrayBuilder.add(v.toJson());
        // (v.toJson());
    });

    System.out.println("ARRAYBUILDER>>>>>>>" + arrayBuilder);
    return ResponseEntity.ok(arrayBuilder.build().toString());
}

//get artist from spotifyAPI
@GetMapping(path = "/artists", consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<String> searchArtist(@RequestParam Map<String,String> param, @RequestHeader Map<String,String> headers){

    System.out.println("param: >>>>>"+param);
    System.out.println("headers: >>>>>"+headers);

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

    spotSvc.searchArtist(param,headers).stream().forEach(v -> {
        arrayBuilder.add(v.toJson());
    });
    
    System.out.println("ARRAYBUILDER>>>>>>>>>>>>"+arrayBuilder);
    return ResponseEntity.ok(arrayBuilder.build().toString());


}

//get album from spotifyAPI
@GetMapping(path = "/albums", consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<String> searchAlbum(@RequestParam Map<String,String> param, @RequestHeader Map<String,String> headers){

    System.out.println("param: >>>>>"+param);
    System.out.println("headers: >>>>>"+headers);

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

    spotSvc.searchAlbum(param,headers).stream().forEach(v -> {
        arrayBuilder.add(v.toJson());
    });
    
    System.out.println("ARRAYBUILDER>>>>>>>>>>>>"+arrayBuilder);
    return ResponseEntity.ok(arrayBuilder.build().toString());
}

//need to get quotation from spotify to enable this PUT request directly to spotify server otherwise return 403 unauthorized
@PutMapping(path = "/albums/save", consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<String> saveAlbum(@RequestBody Map<String,String> albumId,@RequestHeader Map<String,String> headers){

    System.out.println("ID >>>>>>>>>>>>>" + albumId );
    System.out.println("HEADERS >>>>>>>>> " +headers);

   
    String albumIds = albumId.get("albumId");

    
     spotSvc.saveAlbum(albumIds,headers);



     return ResponseEntity.ok("Album saved successfully!");
    
}


}
