package org.wishlistapp.mapper;

import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.model.Gift;


public class GiftMapper {

    // need wishlist repository and user repository
    public Gift toEntity(GiftCreateDTO giftDTO) {
        Gift gift = new Gift();
        gift.setUrl(giftDTO.getUrl());
        gift.setImageUrl(giftDTO.getImageUrl());
        gift.setComments(giftDTO.getComments());
        // TODO: To set the owner list, we need to get the list from the database, username + title -> wishlist
        // TODO: Summarise the URL as description
        // TODO: Use the summary or comments if no URL to find a suitable title
        // TODO: If URL, find price
        return gift;
    }

    public GiftResponseDTO toDTO(Gift gift) {
        GiftResponseDTO giftDTO = new GiftResponseDTO();
        giftDTO.setTitle(gift.getTitle());
        giftDTO.setDescription(gift.getDescription());
        giftDTO.setUrl(gift.getUrl());
        giftDTO.setImageUrl(gift.getImageUrl());
        giftDTO.setPrice(gift.getPrice());
        giftDTO.setComments(gift.getComments());
        return giftDTO;
    }
}
