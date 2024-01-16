package org.wishlistapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wishlistapp.model.Gift;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    Gift findByWishListUserUsernameAndWishListTitleAndTitle(String username, String wishlistTitle, String title);
}
