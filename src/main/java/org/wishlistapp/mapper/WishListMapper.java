package org.wishlistapp.mapper;

import org.wishlistapp.DTO.WishListCreateDTO;
import org.wishlistapp.model.WishList;

public class WishListMapper {

    // User repository needed

    public WishList toEntity(WishListCreateDTO wishListCreateDTO) {
        WishList wishList = new WishList();
        wishList.setDescription(wishListCreateDTO.getDescription());
        wishList.setTitle(wishListCreateDTO.getTitle());
        // TODO: Find the user from the database
        return wishList;
    }
}
