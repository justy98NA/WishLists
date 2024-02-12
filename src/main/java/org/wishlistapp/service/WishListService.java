package org.wishlistapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wishlistapp.DTO.WishListCreateDTO;
import org.wishlistapp.DTO.WishListResponseDTO;
import org.wishlistapp.exception.EntityNotFoundException;
import org.wishlistapp.exception.WishListTitleExistsException;
import org.wishlistapp.model.WLUser;
import org.wishlistapp.model.WishList;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;

import java.util.List;

@Service
public class WishListService {
    private ModelMapper modelMapper;
    private WishListRepository wishListRepository;
    private WLUserRepository userRepository;

    public WishListService(ModelMapper modelMapper, WishListRepository wishListRepository, WLUserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    public WishListResponseDTO addWishList(WishListCreateDTO wishListDTO) {
        var owner = wishListDTO.getUsername();
        var user = userRepository.findByUsername(owner);
        if (user == null) {
            throw new EntityNotFoundException(WLUser.class.getName(), owner);
        }
        var title = wishListDTO.getTitle();
        var wishListExists = wishListRepository.findByOwner_UsernameAndTitle(owner, title);
        if (wishListExists != null) {
            throw new WishListTitleExistsException(title);
        }
        var wishList = modelMapper.map(wishListDTO, WishList.class);
        wishList.setOwner(user);
        wishListRepository.save(wishList);
        return modelMapper.map(wishList, WishListResponseDTO.class);
    }

    public WishListResponseDTO getWishListByUsernameAndTitle(String username, String title) {
        var wl = wishListRepository.findByOwner_UsernameAndTitle(username, title);
        return modelMapper.map(wl, WishListResponseDTO.class);
    }

    public List<WishListResponseDTO> getWishListsByUsername(String username) {
        var wishLists = wishListRepository.findByOwner_Username(username);
        return wishLists.stream()
                .map(wishList -> modelMapper.map(wishList, WishListResponseDTO.class))
                .toList();
    }

    public List<WishListResponseDTO> getAllWishLists() {
        var wishLists = wishListRepository.findAll();
        return wishLists.stream()
                .map(wishList -> modelMapper.map(wishList, WishListResponseDTO.class))
                .toList();
    }
}

