package mini.project.Server.repositories;

public interface Queries {
    

    // -------------------------------------ARTIST--------------------------------------------------------------------------------------------------------------------

    public String SQL_GET_ARTIST = "SELECT * FROM artist WHERE user_id = ?";

    public String SQL_INSERT_ARTIST = "INSERT INTO artist(spotify_artist_id, artist_name, popularity, link, image, user_id) values(?, ?, ?, ?, ?, ?)";

    public String SQL_CHECK_ARTIST = "SELECT * FROM artist WHERE spotify_artist_id = ?";

    public String SQL_DELETE_ARTIST = "DELETE FROM artist WHERE spotify_artist_id = ?";

    // --------------------------------------ALBUM-------------------------------------------------------------------------------------------------------------------
    public String SQL_INSERT_ALBUM = "INSERT INTO album(spotify_album_id, album_name, release_date, link, image,user_id) values(?, ?, ?, ?, ?, ?)";

    public String SQL_CHECK_ALBUM = "SELECT * FROM album WHERE spotify_album_id = ?";

    public String SQL_GET_ALBUM = "SELECT * FROM album WHERE user_id = ?";

    public String SQL_DELETE_ALBUM = "DELETE FROM album WHERE spotify_album_id = ?";



    // --------------------------------------USER-------------------------------------------------------------------------------------------------------------------
    public String SQL_INSERT_USER = "INSERT IGNORE INTO user(unique_user_id, name) values(?, ?)";

    public String SQL_CHECK_USER = "SELECT * FROM user WHERE unique_user_id = ? AND name = ?";

    //-------------------------------------BLOB-----------------------------------------------------------------------------------------------------------------------
    public String SQL_INSERT_BLOB = "INSERT INTO gallery (data) values (?)"; 

    public String SQL_GET_BLOB = "SELECT * FROM gallery ORDER BY RAND() LIMIT 1";

}
