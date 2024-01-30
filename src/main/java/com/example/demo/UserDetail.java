package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetail implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userRepository.findByUsernameOrEmail(username, username);
        if (userDto == null) {
            throw new UsernameNotFoundException("User does not exists by Username");
        }
        Set<GrantedAuthority> authorities = userDto.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
        return new User(username, userDto.getPassword(),authorities);
    }
}
