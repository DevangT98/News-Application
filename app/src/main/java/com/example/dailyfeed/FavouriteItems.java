package com.example.dailyfeed;

public class FavouriteItems {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String detailUrl;
    private String publishedAt;
    private int checked;

    public FavouriteItems(String id, String title, String description, String imageUrl, String detailUrl, String publishedAt, int checked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.detailUrl = detailUrl;
        this.publishedAt = publishedAt;
        this.checked = checked;
    }

    public int getChecked() {
        return checked;
    }

    public String getViewId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
