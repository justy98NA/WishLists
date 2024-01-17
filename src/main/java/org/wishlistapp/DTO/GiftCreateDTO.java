package org.wishlistapp.DTO;
import org.wishlistapp.model.WishList;

public class GiftCreateDTO {
    private String url;
    // Image URL or an image object that is added to Blob Storage and the URL is stored in the database
    private String imageUrl;
    private String comments;
    private String ownerListTitle;
    private String username;

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getComments() {
        return comments;
    }

    public String getOwnerListTitle() {
        return ownerListTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setOwnerListTitle(String ownerListTitle) {
        this.ownerListTitle = ownerListTitle;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
