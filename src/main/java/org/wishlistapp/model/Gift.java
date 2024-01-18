package org.wishlistapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Gifts", uniqueConstraints = @UniqueConstraint(columnNames = {"wishListId", "title"}))
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Derived from comments, or URL
    private String title;
    // Only applicable if there is an url
    private String description;
    // Optional
    private String url;
    // Optional, but it is an url pointing to the storage with images
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "wishListId", nullable = false)
    private WishList ownerList;
    // Derived from url, optional
    private double price;
    // User comments on the gift
    private String comments;

    private int priority;

    public Gift() {

    }

    public Gift(String title, String url, String imageUrl) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public String getComments() {
        return comments;
    }

    public void setOwnerList(WishList ownerList) {
        this.ownerList = ownerList;
    }

    public Long getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
