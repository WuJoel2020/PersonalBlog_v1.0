package org.joel.blog.service;

import org.joel.blog.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
