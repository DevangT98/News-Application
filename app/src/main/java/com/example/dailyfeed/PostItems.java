package com.example.dailyfeed;

public class PostItems {
    private String id;
    private String caption;
    private String image;

    public PostItems(String id, String caption, String image) {
        this.id = id;
        this.caption = caption;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
