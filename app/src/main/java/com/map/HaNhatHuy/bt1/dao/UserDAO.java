package com.map.HaNhatHuy.bt1.dao;

import com.map.HaNhatHuy.bt1.model.User;

import java.security.Key;
import java.util.ArrayList;

public interface UserDAO {
    boolean add(User u);
    boolean edit(User u);
    boolean delete(int id);
    ArrayList<User> search(String keyword);
    User getUserById(int id);
}
