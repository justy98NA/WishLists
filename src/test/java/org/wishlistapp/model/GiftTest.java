package org.wishlistapp.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;


import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class GiftTest {

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private WLUserRepository userRepository;

    @Test
    public void testUniqeTitleAndWishListId() {
        Gift gift = new Gift("Some title", "https://www.amazon.com", "https://www.amazon.com/image.jpg");
        Gift gift2 = new Gift("Some title", "https://www.amazon.com", "https://www.amazon.com/image.jpg");

        WishList wishList = new WishList("Some data about the purpose", "My WIsh List");

        WLUser user1 = new WLUser("John Doe", "johndoe");

        wishList.setOwner(user1);

        gift.setOwnerList(wishList);

        userRepository.save(user1);
        wishListRepository.save(wishList);
        giftRepository.save(gift);

        gift2.setOwnerList(wishList);

        assertThrows(DataIntegrityViolationException.class, () -> {
            giftRepository.save(gift2);
        });
    }
}
