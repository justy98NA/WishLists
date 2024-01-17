package org.wishlistapp.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class WishListTest {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private WLUserRepository userRepository;

    @Test
    public void testAddWishList() {
        // Create and persist entities
        WishList wishList = new WishList("Some data about the purpose", "My WIsh List");

        WLUser user = new WLUser("John Doe", "johndoe");
        userRepository.save(user);

        wishList.setOwner(user);

        wishListRepository.save(wishList);
        // Retrieve entities from the database
        WishList retrievedWishList = wishListRepository.findById(wishList.getWishListId()).orElse(null);

        // Assert that retrieved entities match the expected values
        assert retrievedWishList != null;
        assert retrievedWishList.getDescription().equals(wishList.getDescription());
        assert retrievedWishList.getOwner().equals(wishList.getOwner());
        // Update or delete entities if needed and assert the changes
    }

    @Test
    public void testUniqueTitleUsername() {
        // Create and persist entities
        WishList wishList = new WishList("Some data about the purpose", "My WIsh List");

        WLUser user = new WLUser("John Doe", "johndoe");
        userRepository.save(user);

        wishList.setOwner(user);

        wishListRepository.save(wishList);

        WishList wishList2 = new WishList("Some other data about the purpose", "My WIsh List");
        wishList2.setOwner(user);

        // get the wishlist from the database
        WishList retrievedWishList = wishListRepository.findById(wishList.getWishListId()).orElse(null);

        assert retrievedWishList.getOwner().getUserId() == user.getUserId();
        assertThrows(DataIntegrityViolationException.class, () -> {
            wishListRepository.save(wishList2);
        });
    }
}
