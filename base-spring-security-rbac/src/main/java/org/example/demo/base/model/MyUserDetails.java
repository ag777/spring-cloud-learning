package org.example.demo.base.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class MyUserDetails implements UserDetails {

    Integer userId;
    String password; //密码
    String username;  //用户名
//    boolean accountNonExpired;   //是否没过期
//    boolean accountNonLocked;   //是否没被锁定
//    boolean credentialsNonExpired;  //是否没过期
//    boolean enabled;  //账号是否可用
    Collection<? extends GrantedAuthority> authorities;  //用户的权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    //写死，未过期
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    //写死，未被锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    //写死,未过期
    }

    @Override
    public boolean isEnabled() {
        return true;    //写死,可用
    }

}