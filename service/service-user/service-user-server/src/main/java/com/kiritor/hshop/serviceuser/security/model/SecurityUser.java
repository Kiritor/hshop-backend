/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: SecurityUser
 */
package com.kiritor.hshop.serviceuser.security.model;

import com.kiritor.hshop.serviceuser.model.Role;
import com.kiritor.hshop.serviceuser.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Class SecurityUser
 * @Description
 * @Author 子梁
 * @Date 2020/7/11
 * @Verson 1.0.0
 *
 */

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private User currentUser;
    private List<Role> roleList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
    }

    @Override
    public String getPassword() {
        return currentUser.getPassword();
    }

    public void setPassword(String password) { currentUser.setPassword(password);}

    @Override
    public String getUsername() {
        return currentUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return currentUser.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return currentUser.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return currentUser.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return currentUser.getEnabled();
    }
}
