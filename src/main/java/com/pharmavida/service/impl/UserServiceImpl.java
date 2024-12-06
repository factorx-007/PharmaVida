package com.pharmavida.service.impl;

import com.pharmavida.dao.UserDAO;
import com.pharmavida.model.entity.User;
import com.pharmavida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        userDAO = userDAO;
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
