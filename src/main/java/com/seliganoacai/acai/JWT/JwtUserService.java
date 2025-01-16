package com.seliganoacai.acai.JWT;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.Role;
import com.seliganoacai.acai.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserService implements UserDetailsService {

    @Autowired
    private ManagerService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = service.findByUsername(username);
        return new JwtUserDetais(manager);
    }

    public JwtToken getTokenAutenticated(String username) {
        Role role = service.findRoleByUsername(username);
        return JwtUtils.createJwtToken(username,role.name());
    }
}
