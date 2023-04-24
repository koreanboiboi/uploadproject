package mini.project.Server.models;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class User {

    
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

    private String display_name;
    private String id;
    private Map<String, String> external_urls;
    private Map<String, Integer> followers;
    private List<Image> images;
    private String href;
    private String uri;
    private String type;

    public String getHref() {
        return href;
    }


    public void setHref(String href) {
        this.href = href;
    }


    public String getUri() {
        return uri;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getDisplay_name() {
        return display_name;
    }


    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
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


    public List<Image> getImages() {
        return images;
    }


    public void setImages(List<Image> images) {
        this.images = images;
    }


    public JsonObject toJson(){

        JsonObjectBuilder externalUrlsBuilder = Json.createObjectBuilder();
        external_urls.entrySet().forEach(entry -> externalUrlsBuilder.add(entry.getKey(), entry.getValue()));
        
        JsonObjectBuilder followersBuilder = Json.createObjectBuilder();
        followers.entrySet().forEach(entry -> followersBuilder.add(entry.getKey(), entry.getValue()));
        
        JsonArrayBuilder imagesBuilder = Json.createArrayBuilder();
        images.forEach(image -> {
            JsonObjectBuilder imageBuilder = Json.createObjectBuilder();
            imageBuilder.add("height", image.getHeight())
                .add("width", image.getWidth())
                .add("url", image.getUrl());
            imagesBuilder.add(imageBuilder);
        });

        return Json.createObjectBuilder()
            .add("display_name",display_name)
            .add("id",id)
            .add("type", type)
            .add("uri", uri)
            .add("href", href)
            .add("images", imagesBuilder)
            .add("followers", followersBuilder)
            .add("external_urls", externalUrlsBuilder)
            .build();

    }

    public static User create(JsonObject jo){
        User user = new User();
        user.setDisplay_name(jo.getString("display_name"));
        user.setId(jo.getString("id"));
        user.setUri(jo.getString("uri"));
        user.setHref(jo.getString("href"));
        user.setType(jo.getString("type"));

//---------------------------------------------------------------------------------------------------------
    JsonObject externalUrls = jo.getJsonObject("external_urls");
    Map<String, String> externalUrlsMap = new HashMap<>();
    externalUrlsMap.put("spotify", externalUrls.getString("spotify"));

    user.setExternal_urls(externalUrlsMap);
//---------------------------------------------------------------------------------------------------------
    JsonObject followers = jo.getJsonObject("followers");
    Map<String, Integer> followersMap = new HashMap<>();
    followersMap.put("total", followers.getInt("total"));

    user.setFollowers(followersMap);

//---------------------------------------------------------------------------------------------------------
    JsonArray images = jo.getJsonArray("images");
    List<Image> imagesList = new ArrayList<>();
    for (int i = 0; i < images.size(); i++) {
        JsonObject image = images.getJsonObject(i);
        Image imageObj = new Image();
        if (!image.isNull("height")) {
            imageObj.setHeight(image.getInt("height"));
        }
        if (!image.isNull("width")) {
            imageObj.setWidth(image.getInt("width"));
        }
        imageObj.setUrl(image.getString("url"));
        imagesList.add(imageObj);
    }

    user.setImages(imagesList);


        return user;
    }


    public static User createUser(SqlRowSet rs) {

        User user = new User();
        user.setDisplay_name(rs.getString("display_name"));
        user.setId(rs.getString("id"));
    
        return user;
        
       }

   public static User postUser(MultiValueMap<String, String> form) {

    User user = new User();
    user.setDisplay_name(form.getFirst("display_name"));
    user.setId(form.getFirst("id"));

    return user;
    
   }
 


}
