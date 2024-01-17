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
@Transactional // Use transactional to rollback database changes after each test
public class WLUserTest {

    @Autowired
    private WLUserRepository userRepository;

    @Test
    public void testAddUser() {
        // Create and persist entities
        WLUser user = new WLUser("John Doe", "johndoe");
        userRepository.save(user);
        // Retrieve entities from the database
        WLUser retrievedUser = userRepository.findById(user.getUserId()).orElse(null);
        System.out.println(retrievedUser);
        // Assert that retrieved entities match the expected values
        assert retrievedUser != null;
        assert retrievedUser.getUsername().equals(user.getUsername());
        assert retrievedUser.getName().equals(user.getName());
        // Update or delete entities if needed and assert the changes
    }

    @Test
    public void testUniqueUserNameConstraint() {
        WLUser user = new WLUser("John Doe", "johndoe");
        userRepository.save(user);

        // Attempt to create and save another user with the same username
        WLUser user2 = new WLUser("Jane Smith", "johndoe");

        // Assert that an exception is thrown due to the unique constraint violation
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(user2);
        });
    }


}
