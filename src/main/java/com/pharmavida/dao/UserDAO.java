package com.pharmavida.dao;

import com.pharmavida.model.entity.User;

public interface UserDAO {
    User findByUsername(String username);
}
