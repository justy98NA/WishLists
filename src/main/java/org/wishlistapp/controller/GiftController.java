package org.wishlistapp.controller;

import org.springframework.web.bind.annotation.*;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.model.Gift;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.service.GiftService;

import java.util.List;
import java.util.Optional;

@RestController
public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;

    }


    @GetMapping("/gifts/{id}")
    public GiftResponseDTO getGiftById(@PathVariable Long id) {
        return giftService.getGiftById(id);
    }

    @PostMapping("/gifts")
    public GiftResponseDTO addGift(@RequestBody GiftCreateDTO newGift) {
        return giftService.addGift(newGift);
    }

    @GetMapping("/gifts/wishlist/{wishListId}")
    public List<GiftResponseDTO> getGiftsByWishListId(@PathVariable Long wishListId) {
        return giftService.getGiftsByWishListId(wishListId);
    }

    @GetMapping("/gifts")
    public List<GiftResponseDTO> getAllGifts() {
        return giftService.findAll();
    }


}
