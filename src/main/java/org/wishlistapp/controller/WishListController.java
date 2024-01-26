package org.wishlistapp.controller;

import org.springframework.web.bind.annotation.*;
import org.wishlistapp.DTO.WishListCreateDTO;
import org.wishlistapp.DTO.WishListResponseDTO;
import org.wishlistapp.service.WishListService;

import java.util.List;

@RestController
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/wishlists/{username}")
    public List<WishListResponseDTO> getWishListsByUsername(@PathVariable String username) {
        return wishListService.getWishListsByUsername(username);
    }

    @GetMapping("/wishlists/{username}/{title}")
    public WishListResponseDTO getWishListByUsernameAndTitle(@PathVariable String username, @PathVariable String title) {
        return wishListService.getWishListByUsernameAndTitle(username, title);
    }

    @PostMapping("/wishlists")
    public WishListResponseDTO addWishList(@RequestBody WishListCreateDTO wishList) {
        return wishListService.addWishList(wishList);
    }

    @GetMapping("/wishlists")
    public List<WishListResponseDTO> getAllWishLists() {
        return wishListService.getAllWishLists();
    }
}
