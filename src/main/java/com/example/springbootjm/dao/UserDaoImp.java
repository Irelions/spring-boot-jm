package com.example.springbootjm.dao;

import com.example.springbootjm.model.Role;
import com.example.springbootjm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


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

   @Override
   public List<Role> allRoles() {
      List<Role> roles = entityManager.createQuery("FROM Role", Role.class).getResultList();
      return roles;
   }

   @Override
   public Set<Role> allRolesString() {
      List<Role> roles = entityManager.createQuery("FROM Role", Role.class).getResultList();
      Set<Role> temp = new HashSet<>();
      for(Role role : roles){
         temp.add(role);
      }
      String[] a = {"ADMIN", "USER"};
      return temp;
   }
}
