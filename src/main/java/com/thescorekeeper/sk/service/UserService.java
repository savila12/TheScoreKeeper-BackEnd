package com.thescorekeeper.sk.service;


import com.thescorekeeper.sk.exception.InformationNotFoundException;
import com.thescorekeeper.sk.model.Request.LoginRequest;
import com.thescorekeeper.sk.model.Response.LoginResponse;
import com.thescorekeeper.sk.model.User;
import com.thescorekeeper.sk.repository.UserRepository;
import com.thescorekeeper.sk.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    public void setUserRepo(UserRepository userRepo){
        this.userRepository = userRepo;
    }

    public User createUser(User userObject){
        System.out.println("UserService calling createUser ==>");
        //check if a user already exists in our system, else create the user
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }else{
            throw new InformationNotFoundException("User with email address " +
                    userObject.getEmailAddress() + " all ready exists.");
        }
    }

    public User findUserByEmailAddress(String email){

        return userRepository.findByEmailAddress(email);

    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        System.out.println("service calling loginUser() ==>");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (NullPointerException e) {
            throw new InformationNotFoundException("user with that email address " + loginRequest.getEmail() + "not found");
        }

    }







}
