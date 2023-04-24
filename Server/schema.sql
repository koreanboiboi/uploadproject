CREATE TABLE user (
  unique_user_id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (unique_user_id)
);

CREATE TABLE gallery (
  data MEDIUMBLOB
);

CREATE TABLE artist (
    spotify_artist_id   VARCHAR(128)                NOT NULL,
    artist_name         VARCHAR(128)                NOT NULL,
    popularity          TINYINT(100)       					, 
    link                VARCHAR(128)                		,
    image				VARCHAR(128)						,
    user_id             VARCHAR(128)                NOT NULL,

    PRIMARY KEY(spotify_artist_id),
    FOREIGN KEY(user_id) REFERENCES user(unique_user_id)
);

CREATE TABLE album (
    spotify_album_id    VARCHAR(128)                NOT NULL,
    album_name          VARCHAR(128)                NOT NULL,
    release_date        DATE                        		,
    link                VARCHAR(128)                		,
    image				VARCHAR(128)						,
    user_id             VARCHAR(128)                NOT NULL,

    PRIMARY KEY(spotify_album_id),
    FOREIGN KEY(user_id) REFERENCES user(unique_user_id)
);