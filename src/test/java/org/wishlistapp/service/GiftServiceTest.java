package org.wishlistapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.DTO.GiftResponseDTO;
import org.wishlistapp.model.Gift;
import org.wishlistapp.model.WishList;
import org.wishlistapp.model.WishListTest;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;

import org.wishlistapp.model.WLUser;

@SpringBootTest
@Transactional
public class GiftServiceTest {

    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private GiftService giftService;
    @Autowired
    private WLUserRepository wlUserRepository;
    @Autowired
    private WishListRepository wishListRepository;

    @Test
    public void testGetGiftsByWishListId() {
        WLUser user = new WLUser("John Doe", "johndoe");
        wlUserRepository.save(user);

        WishList wishList = new WishList("Some data about the purpose", "My WIsh List");
        wishList.setOwner(user);
        wishListRepository.save(wishList);

        Gift gift = new Gift("Some title", "https://www.amazon.com", "https://www.amazon.com/image.jpg");
        gift.setOwnerList(wishList);
        giftRepository.save(gift);

        Long wishListID = wishList.getWishListId();
        // var gifts = giftService.getGiftsByWishListId(wishListID);

        assert giftService.getGiftsByWishListId(wishListID) != null;
        assert giftService.getGiftsByWishListId(wishListID).size() == 1;
        assert giftService.getGiftsByWishListId(wishListID).get(0) instanceof GiftResponseDTO;
    }

    @Test
    public void testAiClient() {
        WLUser wluser = new WLUser("John Doe", "johndoe");
        wlUserRepository.save(wluser);
        WishList wishList = new WishList("Some data about the purpose", "My WIsh List");
        wishList.setOwner(wluser);
        wishListRepository.save(wishList);

        GiftCreateDTO gift = new GiftCreateDTO();
        gift.setComments("I want this book in english, hardcover. Title is 'The Lord of the Rings'. I want the whole set.");
        gift.setOwnerListTitle(wishList.getTitle());
        gift.setUsername(wluser.getUsername());
        giftService.addGift(gift);
        var retrievedGift = giftService.getGiftsByWishListId(wishList.getWishListId());
        System.out.println(retrievedGift.getFirst().getTitle());

        assert retrievedGift.size() == 1;
        assert retrievedGift.getFirst().getComments().equals(gift.getComments());
        assert !retrievedGift.getFirst().getTitle().isEmpty();

    }

}
