package mini.project.Server.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Artist {

    private String id;
    private String name;
    private int popularity;
    private String directLink;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getDirectLink() {
        return directLink;
    }

    public void setDirectLink(String directLink) {
        this.directLink = directLink;
    }

    public static Artist createArtist(SqlRowSet rs) {

       
        Artist artist = new Artist();
        artist.setId(rs.getString("spotify_artist_id"));
        artist.setName(rs.getString("artist_name"));
        artist.setPopularity(rs.getInt("popularity"));
        artist.setDirectLink(rs.getString("link"));
        artist.setImage(rs.getString("image"));
    
        return artist;
        
       }


    
}
