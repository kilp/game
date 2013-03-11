/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avalon.sword.pojo;

import java.util.Collection;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author jerryran
 */
public class CustomUserDetails implements UserDetails{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String uid;
    private String username;
    private String password;
    private int role = 2;   //1:admin 2: normal user

    public String getUid() {
        return uid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String name) {
        this.username = name;
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", name=" + username + ", password=" + password + ", role=" + role + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
