package com.ballistic.security.Securitytest.repository;

import com.ballistic.security.Securitytest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ballistic Inc on 6/19/2017.
 */

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
