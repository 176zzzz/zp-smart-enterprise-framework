package com.zp.sef.common.model.user;

import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * LoginUser 登录用户信息
 *
 * @author ZP
 */
@Data
public class LoginUser implements UserDetails {

    private String id;

    private String userName;

    private String password;

    private Boolean enabled;

    private List<GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public LoginUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginUser() {
    }
}
