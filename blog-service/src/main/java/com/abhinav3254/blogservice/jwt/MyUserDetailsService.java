package com.abhinav3254.blogservice.jwt;




import com.abhinav3254.blogservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;




@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    com.abhinav3254.blogservice.model.User user;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.abhinav3254.blogservice.model.User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("user not found by email/phone "+username);

    }



    public com.abhinav3254.blogservice.model.User getUserDetails() {
        return user;
    }

}