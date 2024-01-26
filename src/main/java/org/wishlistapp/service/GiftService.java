package org.wishlistapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.client.AiClient;
import org.wishlistapp.mapper.GiftMapper;
import org.wishlistapp.model.Gift;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.repository.WishListRepository;
import org.wishlistapp.sorter.MergeSorter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftService {
    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;
    private final WishListRepository wishListRepository;
    private final AiClient aiClient;
    private final MergeSorter mergeSorter;
    private final ModelMapper modelMapper;

    @Autowired
    public GiftService(GiftRepository giftRepository, GiftMapper giftMapper, WishListRepository wishListRepository, AiClient aiClient, MergeSorter mergeSorter, ModelMapper modelMapper) {
        this.giftRepository = giftRepository;
        this.giftMapper = giftMapper;
        this.wishListRepository = wishListRepository;
        this.aiClient = aiClient;
        this.mergeSorter = mergeSorter;
        this.modelMapper = modelMapper;
    }

    public List<GiftResponseDTO> getGiftsByWishListId(Long wishListId) {
        var gifts = giftRepository.findByOwnerList_WishListId(wishListId);
        return gifts.stream()
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class)) // Use the mapper to convert each Gift to GiftDTO
                .toList();
    }

    public GiftResponseDTO addGift(GiftCreateDTO giftDTO) {
        var gift = modelMapper.map(giftDTO, Gift.class);

        // To set the owner list, we need to get the list from the database, username + title -> wishlist
        var username = giftDTO.getUsername();
        var title = giftDTO.getOwnerListTitle();
        System.out.println("Username: " + username + ", title: " + title);
        var wl = wishListRepository.findByOwner_UsernameAndTitle(username, title);
        if (wl != null) {
            gift.setOwnerList(wl);
        } else {
            System.out.println("Wishlist not found");
            return null;
        }
        // TODO: Summarise the URL as description
        // TODO: Use the summary or comments if no URL to find a suitable title
        var description = aiClient.getResponse(giftDTO.getComments());
        gift.setTitle(description);
        // TODO: If URL, find price

        giftRepository.save(gift);
        //return giftMapper.toDTO(gift);
        return modelMapper.map(gift, GiftResponseDTO.class);
    }

    public List<GiftResponseDTO> findAll() {
        var gifts = giftRepository.findAll();
        return gifts.stream()
                //.map(giftMapper::toDTO) // Use the mapper to convert each Gift to GiftDTO
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class))
                .toList();
    }

    public GiftResponseDTO getGiftById(Long id) {
        var gift = giftRepository.findById(id);
        return modelMapper.map(gift, GiftResponseDTO.class);
        //return gift.map(giftMapper::toDTO).orElse(null);
    }

    public List<GiftResponseDTO> getSortedGiftsByWishListId(Long wishListId) {
        var gifts = giftRepository.findByOwnerList_WishListId(wishListId);
        var sortedGifts = mergeSorter.sort(gifts);
        return sortedGifts.stream()
                //.map(giftMapper::toDTO) // Use the mapper to convert each Gift to GiftDTO
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class))
                .toList();
    }

    public GiftResponseDTO deleteById(Long id) {
        var toRemove = this.getGiftById(id);
        giftRepository.deleteById(id);
        return toRemove;
    }

    public GiftResponseDTO updatePriority(GiftCreateDTO giftCreateDto, Long id) {
        return giftRepository.findById(id)
                .map(gift -> {
                    gift.setPriority(giftCreateDto.getPriority());
                    return giftRepository.save(gift);
                })
                //.map(giftMapper::toDTO).orElse(null);
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class))
                .orElse(null);
    }
}
