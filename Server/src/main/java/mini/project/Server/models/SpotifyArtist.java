package mini.project.Server.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;


public class SpotifyArtist {

    private String directLink;

 


    private static Map<String, String> external_urls = new HashMap<>();
    private static Map<String, Integer> followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private int popularity;
    private String type;
    private String uri;


    public String getDirectLink() {
        return directLink;
    }

    public void setDirectLink(String directLink) {
        this.directLink = directLink;
    }

    public SpotifyArtist (){
        this.external_urls = new HashMap<>();
    }

    public Map<String, String> getExternal_urls() {
        return external_urls;
    }
    public void setExternal_urls(Map<String, String> external_urls) {
        this.external_urls = external_urls;
    }
    public Map<String, Integer> getFollowers() {
        return followers;
    }
    public void setFollowers(Map<String, Integer> followers) {
        this.followers = followers;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public int getPopularity() {
        return popularity;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



//------------------------------------IMAGE----------------------------------
    public static class Image {
        private int height;
        private String url;
        private int width;

        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }

        
    }
//-----------------------------------------------------------------------------------
    

    

    public static SpotifyArtist create(JsonObject jo){

        SpotifyArtist spotifyArtist = new SpotifyArtist();
        spotifyArtist.setId(jo.getString("id"));
        spotifyArtist.setName(jo.getString("name"));
        spotifyArtist.setUri(jo.getString("uri"));
        spotifyArtist.setType(jo.getString("type"));
        spotifyArtist.setPopularity(jo.getInt("popularity"));
        spotifyArtist.setHref(jo.getString("href"));



        
//---------------------------------------------------------------------------------------------------------

    JsonObject externalUrls = jo.getJsonObject("external_urls");
    Map<String, String> externalUrlsMap = new HashMap<>();
    externalUrlsMap.put("spotify", externalUrls.getString("spotify"));
    //THIS//
    spotifyArtist.setExternal_urls(externalUrlsMap);
//---------------------------------------------------------------------------------------------------------
    // Set followers
    JsonObject followers = jo.getJsonObject("followers");
    Map<String, Integer> followersMap = new HashMap<>();
    followersMap.put("total", followers.getInt("total"));
    
    //THIS//
    spotifyArtist.setFollowers(followersMap);
//---------------------------------------------------------------------------------------------------------

    // Set genres
    JsonArray genres = jo.getJsonArray("genres");
    List<String> genresList = new ArrayList<>();
    for (int i = 0; i < genres.size(); i++) {
        genresList.add(genres.getString(i));
    }

    //THIS//
    spotifyArtist.setGenres(genresList);
//---------------------------------------------------------------------------------------------------------


    // Set images
    JsonArray images = jo.getJsonArray("images");
    List<Image> imagesList = new ArrayList<>();
    for (int i = 0; i < images.size(); i++) {
        JsonObject image = images.getJsonObject(i);
        Image imageObj = new Image();
        imageObj.setHeight(image.getInt("height"));
        imageObj.setWidth(image.getInt("width"));
        imageObj.setUrl(image.getString("url"));
        imagesList.add(imageObj);
    }

    //THIS//
    spotifyArtist.setImages(imagesList);
//---------------------------------------------------------------------------------------------------------



    return spotifyArtist;
    
}

    public JsonObject toJson(){


        JsonObjectBuilder externalUrlsBuilder = Json.createObjectBuilder();
        external_urls.entrySet().forEach(entry -> externalUrlsBuilder.add(entry.getKey(), entry.getValue()));
        
        JsonObjectBuilder followersBuilder = Json.createObjectBuilder();
        followers.entrySet().forEach(entry -> followersBuilder.add(entry.getKey(), entry.getValue()));
        
        JsonArrayBuilder genresBuilder = Json.createArrayBuilder();
        genres.forEach(genre -> genresBuilder.add(genre));
        
        JsonArrayBuilder imagesBuilder = Json.createArrayBuilder();
        images.forEach(image -> {
            JsonObjectBuilder imageBuilder = Json.createObjectBuilder();
            imageBuilder.add("height", image.getHeight())
                .add("width", image.getWidth())
                .add("url", image.getUrl());
            imagesBuilder.add(imageBuilder);
        });

        return Json.createObjectBuilder()
                   .add("id",id)
                   .add("name",name)
                   .add("uri", uri)
                   .add("type", type)
                   .add("popularity", popularity)
                   .add("href", href)
                   .add("external_urls", externalUrlsBuilder)
                   .add("followers", followersBuilder)
                   .add("genres", genresBuilder)
                   .add("images", imagesBuilder)
                    .build();
                
    }
 

    public static SpotifyArtist createArtist(SqlRowSet rs) {

       
        SpotifyArtist artist = new SpotifyArtist();
        artist.setId(rs.getString("spotify_artist_id"));
        artist.setName(rs.getString("artist_name"));
        artist.setPopularity(rs.getInt("popularity"));
        artist.setDirectLink(rs.getString("link"));
        
    
        return artist;
        
       }
    
}
