package org.wishlistapp.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.repository.WLUserRepository;
import org.wishlistapp.repository.WishListRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional // Use transactional to rollback database changes after each test
@ComponentScan(basePackages = "org.wishlistapp") // Scan for components in the org.wishlistapp package
public class DatabaseTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GiftRepository giftRepository; // Replace with your repository classes

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private WLUserRepository userRepository;

    @Test
    public void testDatabaseOperations() {
        // Create and persist entities
        WLUser user = new WLUser("John Doe", "johndoe");
        userRepository.save(user);


        // Retrieve entities from the database
        WLUser retrievedUser = userRepository.findById(user.getUserId()).orElse(null);

        // Assert that retrieved entities match the expected values
        assert retrievedUser != null;


        // Update or delete entities if needed and assert the changes
    }
}
