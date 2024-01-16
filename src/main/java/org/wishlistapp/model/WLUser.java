package org.wishlistapp.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WLUser")
public class WLUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @Column(unique = true)
    private String username;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Set<WishList> wishLists = new HashSet<>();

    public WLUser() {

    }

    public WLUser(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public Set<WishList> getWishLists() {
        return wishLists;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
