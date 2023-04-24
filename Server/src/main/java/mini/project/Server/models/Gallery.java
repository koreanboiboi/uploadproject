package mini.project.Server.models;

public class Gallery {
    private String title;
    private String description;
    private byte[] image;



    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] bytes) {
        this.image = bytes;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
