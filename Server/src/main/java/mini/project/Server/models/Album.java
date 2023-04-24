package mini.project.Server.models;

import java.util.Date;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Album {
    
    private String id;
    private String albumName;
    private Date date;
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
    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDirectLink() {
        return directLink;
    }
    public void setDirectLink(String directLink) {
        this.directLink = directLink;
    }
    
public static Album createAlbum(SqlRowSet rs) {

       
        Album album = new Album();
        album.setId(rs.getString("spotify_album_id"));
        album.setAlbumName(rs.getString("album_name"));
        album.setDate(rs.getDate("release_date"));
        album.setDirectLink(rs.getString("link"));
        album.setImage(rs.getString("image"));
    
        return album;
        
       }

    
}
