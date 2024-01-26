package org.wishlistapp.DTO;

public class WishListCreateDTO {
    private String username;
    private String description;
    private String title;

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
