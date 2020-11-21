package com.example.springbootjm.service;

import com.example.springbootjm.dao.UserDao;
import com.example.springbootjm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    //Success +
    @Override
    public List<User> listUsers() {
        return userDao.listAllUsers();
    }

    //Success +
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    //Success +
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    //Success +
    @Override
    public List<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    //Success +
    @Override
    public User showUser(int id) {
        return userDao.showUser(id);
    }


    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

}