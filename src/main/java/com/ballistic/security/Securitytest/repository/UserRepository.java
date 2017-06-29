package com.ballistic.security.Securitytest.repository;

import com.ballistic.security.Securitytest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ballistic Inc on 6/19/2017.
 */

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    // find-by-email
    User findByEmail(String email);
}
