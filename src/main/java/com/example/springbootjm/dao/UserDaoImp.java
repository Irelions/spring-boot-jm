package com.example.springbootjm.dao;

import com.example.springbootjm.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {


   @PersistenceContext
   private EntityManager entityManager;

   //Success +
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listAllUsers() {
      return entityManager.createQuery("FROM User").getResultList();
   }

   //Success +
   @Override
   public void add(User user) {
      if(user.getId() == null) {
         entityManager.persist(user);
      } else entityManager.merge(user);
   }

   //Success +
   @Override
   public void delete(int id) {
      entityManager.remove(entityManager.find(User.class, (long)id));
   }

   //Success +
   @Override
   public User showUser(int id) {
      return entityManager.find(User.class, (long)id);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> findUserByUsername(String username) {
      Query query = entityManager.createQuery("FROM User WHERE username = :username");
      query.setParameter("username", username);
      return query.getResultList();
   }

   @Override
   public User getUserByName(String username) {
      return findUserByUsername(username).get(0);
   }


}
