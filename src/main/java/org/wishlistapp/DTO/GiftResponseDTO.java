package org.wishlistapp.DTO;

public class GiftResponseDTO {
    // Derived from comments, or URL
    private String title;
    // Only applicable if there is an url
    private String description;
    // Optional
    private String url;
    // Optional, but it is an url pointing to the storage with images
    private String imageUrl;
    // Derived from url, optional
    private double price;
    // User comments on the gift
    private String comments;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
