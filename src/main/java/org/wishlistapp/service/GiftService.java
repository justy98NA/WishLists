package org.wishlistapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.client.AiClient;
import org.wishlistapp.mapper.GiftMapper;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.repository.WishListRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftService {
    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;
    private final WishListRepository wishListRepository;
    private final AiClient aiClient;

    @Autowired
    public GiftService(GiftRepository giftRepository, GiftMapper giftMapper, WishListRepository wishListRepository) {
        this.giftRepository = giftRepository;
        this.giftMapper = giftMapper;
        this.wishListRepository = wishListRepository;
        this.aiClient = new AiClient();
    }

    public List<GiftResponseDTO> getGiftsByWishListId(Long wishListId) {
        var gifts = giftRepository.findByOwnerList_WishListId(wishListId);
        return gifts.stream()
                .map(giftMapper::toDTO) // Use the mapper to convert each Gift to GiftDTO
                .toList();
    }

    public GiftResponseDTO addGift(GiftCreateDTO giftDTO) {
        var gift = giftMapper.toEntity(giftDTO);

        // TODO: To set the owner list, we need to get the list from the database, username + title -> wishlist
        var username = giftDTO.getUsername();
        var title = giftDTO.getOwnerListTitle();
        var wl = wishListRepository.findByOwner_UsernameAndTitle(username, title);
        if (wl != null) {
            gift.setOwnerList(wl);
        }
        // TODO: Summarise the URL as description
        // TODO: Use the summary or comments if no URL to find a suitable title
        var description = aiClient.getResponse(giftDTO.getComments());
        gift.setTitle(description);
        // TODO: If URL, find price

        giftRepository.save(gift);
        return giftMapper.toDTO(gift);
    }
}
