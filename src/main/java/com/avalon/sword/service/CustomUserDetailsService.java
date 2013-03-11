/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.service;

import com.avalon.sword.pojo.CustomUserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jerryran
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
    private Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());
    
    @Autowired private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private SaltSource saltSource;
    @Autowired private MongoTemplate mongoTemplate;
    
    public CustomUserDetails findUserByName(String name){
        logger.log(Level.INFO, "CustomUserDetailsService findUserByName name = {0}", name);
        return mongoTemplate.findOne(new Query(Criteria.where("username").is(name)), CustomUserDetails.class, "user");
    }
    
    /**
     * Returns a populated {@link UserDetails} object. The username is first
     * retrieved from the database and then mapped to a {@link UserDetails}
     * object.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            logger.log(Level.INFO, "loadUserByUsername username = {0}", username);
            CustomUserDetails user = userService.findCustomUserByName(username);
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            
            if(user == null){
                throw  new UsernameNotFoundException("User Not Found");
            }
            logger.log(Level.INFO, "loadUserByUsername {0}", user);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRole()));

        } catch (Exception e) {
            logger.log(Level.INFO, "loadUserByUsername exception: {0}", e);
            throw new RuntimeException(e);
        }
    }
    
    public CustomUserDetails createUser(String username, String plainPassword){
//        if(passwordEncoder == null){
//            passwordEncoder = new Md5PasswordEncoder();
//        }
        CustomUserDetails user = new CustomUserDetails();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encodePassword(username, username));
        return user;
    }

    /**
     * Retrieves a collection of {@link GrantedAuthority} based on a numerical
     * role
     *
     * @param role the numerical role
     * @return a collection of {@link GrantedAuthority
     */
    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    /**
     * Converts a numerical role to an equivalent list of roles
     *
     * @param role the numerical role
     * @return list of roles as as a list of {@link String}
     */
    public List<String> getRoles(Integer role) {
        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ROLE_USER");
            roles.add("ROLE_ADMIN");

        } else if (role.intValue() == 2) {
            roles.add("ROLE_USER");
        }

        return roles;
    }

    /**
     * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
     *
     * @param roles {@link String} of roles
     * @return list of granted authorities
     */
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
