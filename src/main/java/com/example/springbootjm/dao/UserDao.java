package com.example.springbootjm.dao;

import com.example.springbootjm.model.Role;
import com.example.springbootjm.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
   List<User> listAllUsers();
   void add(User user);
   void delete(int id);
   User showUser (int id);
   List<User> findUserByUsername(String username);
   User getUserByName(String name);
   List<Role> allRoles();
   Set<Role> allRolesString();
}
