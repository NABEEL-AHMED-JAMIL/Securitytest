package com.ballistic.security.Securitytest.service;

import com.ballistic.security.Securitytest.model.User;

/**
 * Created by Ballistic Inc on 6/19/2017.
 */
public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

}
