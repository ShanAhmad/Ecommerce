package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login Successful", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO){
        if(userRepository.existsByusername(signUpDTO.getUsername())){
            return new ResponseEntity<>("Username Already exists",HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByemail((signUpDTO.getEmail()))){
            return new ResponseEntity<>("Email Already exists",HttpStatus.BAD_REQUEST);
        }
        UserDto userDto = new UserDto();
        userDto.setName(signUpDTO.getName());
        userDto.setEmail(signUpDTO.getEmail());
        userDto.setUsername(signUpDTO.getUsername());
        userDto.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        RoleDto roleDto = roleRepository.findByRole("ROLE_ADMIN").get();
        userDto.setRoles(Collections.singleton(roleDto));
        userRepository.save(userDto);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);

    }
}
