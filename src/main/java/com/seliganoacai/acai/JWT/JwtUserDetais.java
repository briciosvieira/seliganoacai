package com.seliganoacai.acai.JWT;


import com.seliganoacai.acai.entity.Manager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetais extends User {


    public JwtUserDetais(Manager manager) {
        super(manager.getUsername(), manager.getPassword(), AuthorityUtils.createAuthorityList(manager.getRole().name()));
    }

    private Manager manager;

    public Long id(){
        return this.manager.getId();
    }

    public String role(){
        return this.manager.getRole().name();
    }
}
