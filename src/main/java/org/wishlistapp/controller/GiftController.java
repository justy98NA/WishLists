package org.wishlistapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final GiftRepository giftRepository;

    public GiftController(GiftService giftService, GiftRepository giftRepository) {
        this.giftService = giftService;
        this.giftRepository = giftRepository;

    }


//    @GetMapping("/gifts/{id}")
//    public GiftResponseDTO getGiftById(@PathVariable Long id) {
//        return giftService.getGiftsByWishListId(id).getFirst();
//    }

//    @PostMapping("/newgift")
//    public GiftResponseDTO addGift(GiftCreateDTO gift) {
//        return giftService.addGift(gift);
//    }

    @GetMapping("/gifts/{wishListId}")
    public List<GiftResponseDTO> getGiftsByWishListId(@PathVariable Long wishListId) {
        return giftService.getGiftsByWishListId(wishListId);
    }

    @GetMapping("/gifts")
    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }


}
