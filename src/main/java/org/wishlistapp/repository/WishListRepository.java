package org.wishlistapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wishlistapp.model.WishList;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByOwner_UsernameAndTitle(String username, String title);
    List<WishList> findByOwner_Username(String username);
}
