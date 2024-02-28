package org.wishlistapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.client.AiClient;
import org.wishlistapp.exception.EntityNotFoundException;
import org.wishlistapp.mapper.GiftMapper;
import org.wishlistapp.model.Gift;
import org.wishlistapp.model.WishList;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.repository.WishListRepository;
import org.wishlistapp.sorter.MergeSorter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        wishListRepository.findById(wishListId)
                .orElseThrow(() -> new EntityNotFoundException(WishList.class.toString(), wishListId.toString()));

        var gifts = giftRepository.findByOwnerList_WishListId(wishListId);
        return gifts.stream()
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class)) // Use the mapper to convert each Gift to GiftDTO
                .toList();
    }

    public GiftResponseDTO addGift(GiftCreateDTO giftDTO) {
        var username = giftDTO.getUsername();
        var title = giftDTO.getOwnerListTitle();

        var wishlist = wishListRepository.findByOwner_UsernameAndTitle(username, title);
        if (wishlist == null) {
            throw new EntityNotFoundException(WishList.class.toString(), title);
        }

        var gift = modelMapper.map(giftDTO, Gift.class);

        // TODO: Summarise the URL as description
        // TODO: Use the summary or comments if no URL to find a suitable title

        try {
            gift.setTitle(getTitleFromAssistant(giftDTO.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // potentially check if the title in the owner list exists

        //gift.setTitle(aiTitle);
        gift.setOwnerList(wishlist);
        // TODO: If URL, find price

        // Or just save and catch errors?
        giftRepository.save(gift);
        //return giftMapper.toDTO(gift);
        return modelMapper.map(gift, GiftResponseDTO.class);
    }

    private String getTitleFromAssistant(String url) throws Exception {
        URL source = new URL("http://localhost:8082/title");
        HttpURLConnection con = (HttpURLConnection) source.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
        con.setRequestProperty("Accept", "text/plain");

        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = url.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

        }
        con.disconnect();
        return response.toString();
    }
    public List<GiftResponseDTO> findAll() {
        var gifts = giftRepository.findAll();
        return gifts.stream()
                //.map(giftMapper::toDTO) // Use the mapper to convert each Gift to GiftDTO
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class))
                .toList();
    }

    public GiftResponseDTO getGiftById(Long id) {
        return giftRepository.findById(id)
                .map(gift -> modelMapper.map(gift, GiftResponseDTO.class))
                .orElseThrow(() -> new EntityNotFoundException(Gift.class.toString(), id.toString()));
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
