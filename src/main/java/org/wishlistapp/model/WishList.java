package org.wishlistapp.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "wishlist", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "title"}))
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishListId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private WLUser owner;
    private String description;
    @OneToMany(mappedBy = "ownerList", cascade = CascadeType.PERSIST)
    private Set<Gift> gifts;

    private String title;

    public WishList() {

    }

    public WishList(String description, String title) {

        this.description = description;
        this.title = title;
    }

    public void setOwner(WLUser owner) {
        this.owner = owner;
    }

    public void setGifts(Set<Gift> gifts) {
        this.gifts = gifts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
