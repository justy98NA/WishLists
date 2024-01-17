package org.wishlistapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.model.Gift;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;

@Component
public class GiftMapper {

    public Gift toEntity(GiftCreateDTO giftDTO) {
        Gift gift = new Gift();
        gift.setUrl(giftDTO.getUrl());
        gift.setImageUrl(giftDTO.getImageUrl());
        gift.setComments(giftDTO.getComments());

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
