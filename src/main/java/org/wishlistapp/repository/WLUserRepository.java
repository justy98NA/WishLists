package org.wishlistapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wishlistapp.model.WLUser;

public interface WLUserRepository extends JpaRepository<WLUser, Long> {
    WLUser findByUsername(String username);

}
