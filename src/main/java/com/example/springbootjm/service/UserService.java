package com.example.springbootjm.service;

import com.example.springbootjm.model.Role;
import com.example.springbootjm.model.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    void add(User user);
    List<User> listUsers();
    User showUser (int id);
    void delete(int id);
    List<User> findUserByUsername(String username);
    User getUserByName(String name);
    List<Role> allRoles();
    Set<Role> allRolesString();
}
