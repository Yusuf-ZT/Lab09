package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.*;

/**
 *
 * @author Alex Tompkins - 821984
 */
public class UserDB {
    
    // get user from email
    public User get(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user  = em.find(User.class, email);
            return user;
        }finally {
            em.close();
        }
    }
    
    //get all users
    
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<User> userList = em.createNamedQuery("User.findAll", User.class).getResultList();
            return userList;
        } finally {
            em.close();
        }
        
    }

    //get user by email
    
    public User getUserByEmail(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    

    //insert user
    
    public void insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        }
        catch(Exception e) {
            transaction.rollback();
        }
        finally {
            em.close();
        }
    }


    //update user
    
    public void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try{
            transaction.begin();
            em.merge(user);
            transaction.commit();
        } 
        catch(Exception e) {
            transaction.rollback();
        } finally{
            em.close();
        }
    }
    


    //delete user
    
    public void delete(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try{
           user.getEmail();
           transaction.begin();
           em.remove(em.merge(user));
           transaction.commit();
           
        } catch(Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }
    

}
