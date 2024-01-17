package org.wishlistapp.configuration;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wishlistapp.DTO.GiftCreateDTO;
import org.wishlistapp.model.Gift;
import org.wishlistapp.model.WLUser;
import org.wishlistapp.model.WishList;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;
import org.wishlistapp.service.GiftService;

@Configuration
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    @Bean
    CommandLineRunner initDatabase(GiftService giftService, WLUserRepository wlUserRepository, WishListRepository wishListRepository) {
        return args -> {
            if (wlUserRepository.count() == 0) {
                WLUser user1 = new WLUser("user1", "password1");
                wlUserRepository.save(user1);
                WishList wishList1 = new WishList("user1's wishlist", "Birthday");
                wishList1.setOwner(user1);
                wishListRepository.save(wishList1);
                GiftCreateDTO gift1 = new GiftCreateDTO();
                gift1.setOwnerListTitle(wishList1.getTitle());
                gift1.setComments("I want this book in english, hardcover. Title is 'The Lord of the Rings'. I want the whole set.");
                gift1.setUsername(user1.getUsername());

                GiftCreateDTO gift2 = new GiftCreateDTO();
                gift2.setOwnerListTitle(wishList1.getTitle());
                gift2.setComments("I want this blouse in pink. Size Medium.");
                gift2.setUsername(user1.getUsername());


                log.info(() -> "Preloading " + giftService.addGift(gift1));
                log.info(() -> "Preloading " + giftService.addGift(gift2));

            }
        };

    }
}
