package mini.project.Server.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;


public class SpotifyAlbum {
    private String id;
    private String name;
    private String imageUrl;
    private String uri;
    private String album_type;
    private int total_tracks;
    private Map<String, String> external_urls;
    private String href;
    private List<Image> images;
    private String release_date;
    private String album_group;

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

  

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

   

    public Map<String, String> getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Map<String, String> external_urls) {
        this.external_urls = external_urls;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public String getAlbum_group() {
        return album_group;
    }

    public void setAlbum_group(String album_group) {
        this.album_group = album_group;
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

    //Create JO
    public static SpotifyAlbum create(JsonObject jo){

        SpotifyAlbum spotifyAlbum = new SpotifyAlbum();
        spotifyAlbum.setId(jo.getString("id"));
        spotifyAlbum.setName(jo.getString("name"));
        spotifyAlbum.setUri(jo.getString("uri"));
        spotifyAlbum.setAlbum_type(jo.getString("album_type"));
        spotifyAlbum.setTotal_tracks(jo.getInt("total_tracks"));
        spotifyAlbum.setHref(jo.getString("href"));
        spotifyAlbum.setRelease_date(jo.getString("release_date"));
    
    


//---------------------------------------------------------------------------------------------------------

    JsonObject externalUrls = jo.getJsonObject("external_urls");
    Map<String, String> externalUrlsMap = new HashMap<>();
    externalUrlsMap.put("spotify", externalUrls.getString("spotify"));
    //THIS//
    spotifyAlbum.setExternal_urls(externalUrlsMap);

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
    spotifyAlbum.setImages(imagesList);
//---------------------------------------------------------------------------------------------------------

        return spotifyAlbum;

        
    }

    public JsonObject toJson(){

        JsonObjectBuilder externalUrlsBuilder = Json.createObjectBuilder();
        external_urls.entrySet().forEach(entry -> externalUrlsBuilder.add(entry.getKey(), entry.getValue()));
     
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
                   .add("album_type",album_type)
                   .add("total_tracks", total_tracks)
                //    .add("available_markets", (JsonValue) available_markets)
                   .add("href", href)
                   .add("release_date", release_date)
                //    .add("artists", (JsonValue) artists)
                   .add("external_urls",externalUrlsBuilder)
                   .add("images", imagesBuilder)
                   .build();
                
    }

}
