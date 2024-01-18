package org.wishlistapp.controller;

import org.springframework.web.bind.annotation.*;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.model.Gift;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.service.GiftService;
import retrofit2.http.Path;

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

    @GetMapping("/gifts/{wishListId}/sorted")
    public List<GiftResponseDTO> getSortedGiftsByWishListId(@PathVariable Long wishListId) {
        return giftService.getSortedGiftsByWishListId(wishListId);
    }

    @DeleteMapping("/gifts/{id}")
    public GiftResponseDTO deleteGiftById(@PathVariable Long id) {
        return giftService.deleteById(id);
    }

    @PutMapping("/gifts/{id}")
    public GiftResponseDTO updatePriority(@RequestBody GiftCreateDTO giftCreateDTO, @PathVariable Long id) {
        return giftService.updatePriority(giftCreateDTO, id);
    }
}
