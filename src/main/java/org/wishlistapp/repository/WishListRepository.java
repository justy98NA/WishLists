package org.wishlistapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wishlistapp.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByOwner_UsernameAndTitle(String username, String title);
}
