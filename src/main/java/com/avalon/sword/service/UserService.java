/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.service;

import com.avalon.sword.pojo.CustomUserDetails;
import com.avalon.sword.pojo.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author jerryran
 */
@Service
public class UserService {
    Logger logger = Logger.getLogger(UserService.class.getName());
    private static String USER_COLLECTION = "user";
    
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    public void saveUser(CustomUserDetails user){
        CustomUserDetails userDetail = customUserDetailsService.createUser(user.getUsername(), user.getPassword());
        user.setPassword(userDetail.getPassword());
        mongoTemplate.save(user, USER_COLLECTION);
    }
    
    public User findUserByUid(String uid){
        return mongoTemplate.findOne(new Query(Criteria.where("uid").is(uid)), User.class, USER_COLLECTION);
    }
    
    public User findUserByName(String name){
        logger.log(Level.INFO, "UserService findUserByName name = {0}", name);
        try {
            return mongoTemplate.findOne(new Query(Criteria.where("username").is(name)), User.class, USER_COLLECTION);
        } catch (Exception e) {
            logger.info("error");
            return null;
        }
    }
    
    public CustomUserDetails findCustomUserByName(String name){
        try {
            return mongoTemplate.findOne(new Query(Criteria.where("username").is(name)), CustomUserDetails.class, USER_COLLECTION);
        } catch (Exception e) {
            logger.info("error");
            return null;
        }
    }
}
