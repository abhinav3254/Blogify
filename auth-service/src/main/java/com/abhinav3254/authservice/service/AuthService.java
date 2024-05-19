package com.abhinav3254.authservice.service;


import com.abhinav3254.authservice.dto.*;
import com.abhinav3254.authservice.exception.CustomException;
import com.abhinav3254.authservice.jwt.JwtUtils;
import com.abhinav3254.authservice.jwt.MyUserDetailsService;
import com.abhinav3254.authservice.model.User;
import com.abhinav3254.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    public AuthResponse<?> login(LoginDTO loginDTO) {
        if (validateLogin(loginDTO)) {

            Optional<User> optionalUser = userRepository.findByUsername(loginDTO.getUsername());

            if (optionalUser.isEmpty()) {
                throw new CustomException("User not found", HttpStatus.NOT_FOUND);
            }

            User user = optionalUser.get();

            if (user.getPassword().equals(loginDTO.getPassword())) {

                String token = jwtUtils.generateToken(user.getUsername(), user.getId(), user.getRole());

                return new AuthResponse<>(token,new Date());

            } else throw new CustomException("Wrong Password",HttpStatus.BAD_REQUEST);

        } else throw new CustomException("Invalid Details",HttpStatus.BAD_REQUEST);
    }

    private boolean validateLogin(LoginDTO loginDTO) {
        if (loginDTO.getPassword().isEmpty()) throw new CustomException("Password can't be empty",HttpStatus.BAD_REQUEST);
        if (loginDTO.getUsername().isEmpty()) throw new CustomException("Username can't be empty",HttpStatus.BAD_REQUEST);
        return true;
    }

    public AuthResponse<?> register(SignUpDTO sign) {
        if (validateSignup(sign)) {

            Optional<User> optionalUser = userRepository.findByUsername(sign.getUsername());
            Optional<User> optionalUserByEmail = userRepository.findByEmail(sign.getEmail());
            if (optionalUser.isEmpty()) {

                if (optionalUserByEmail.isEmpty()) {

                    User user = setUpUser(sign);

                    User savedUser = userRepository.save(user);

                    String token = jwtUtils.generateToken(user.getUsername(), user.getId(), user.getRole());
                    return new AuthResponse<>(token,new Date());

                } throw new CustomException("Email already in use",HttpStatus.BAD_REQUEST);

            } throw new CustomException("Username already taken",HttpStatus.BAD_REQUEST);

        } throw new CustomException("Invalid Credentials",HttpStatus.BAD_REQUEST);
    }

    private boolean validateSignup(SignUpDTO sign) {
        if (sign.getUsername().isEmpty()) throw new CustomException("Username can't be empty",HttpStatus.BAD_REQUEST);
        if (sign.getEmail().isEmpty()) throw new CustomException("Email can't be empty",HttpStatus.BAD_REQUEST);
        if (!sign.getEmail().contains("@")) throw new CustomException("Invalid Email",HttpStatus.BAD_REQUEST);
        if (sign.getPassword().isEmpty()) throw new CustomException("Phone can't be empty",HttpStatus.BAD_REQUEST);
        if (sign.getName().isEmpty()) throw new CustomException("Name can't be empty",HttpStatus.BAD_REQUEST);
        return true;
    }

    private User setUpUser(SignUpDTO sign) {
        User user = new User();
        user.setUsername(sign.getUsername());
        user.setPassword(sign.getPassword());
        user.setName(sign.getName());
        user.setEmail(sign.getEmail());
        if (!Objects.isNull(sign.getPhone()))
            user.setPhone(sign.getPhone());
        if (!Objects.isNull(sign.getGender()))
            user.setGender(sign.getGender());
        if (!Objects.isNull(sign.getProfilePicture())) {
            try {
                user.setProfilePicture(sign.getProfilePicture().getBytes());
            } catch (IOException e) {
                throw new CustomException("Error in parsing profile picture",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        user.setRole("USER");
        user.setCreatedDate(new Date());
        return user;
    }

    public AuthResponse<?> updateProfile(SignUpDTO sign) {
        if (validateSignup(sign)) {
            Optional<User> optionalUser = userRepository.findByUsername(sign.getUsername());
            if (optionalUser.isPresent()) {
                User user = getUser(sign, optionalUser.get());

                // Save the updated user back to the database
                userRepository.save(user);

                return new AuthResponse<>("Profile Update Successfully", new Date());
            } else {
                throw new CustomException("User not found", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new CustomException("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
    }

    private static User getUser(SignUpDTO sign, User user) {

        // Update user details
        if (!Objects.isNull(sign.getName())) {
            user.setName(sign.getName());
        }
        if (!Objects.isNull(sign.getEmail())) {
            user.setEmail(sign.getEmail());
        }
        if (!Objects.isNull(sign.getPhone())) {
            user.setPhone(sign.getPhone());
        }
        if (!Objects.isNull(sign.getGender())) {
            user.setGender(sign.getGender());
        }
        if (!Objects.isNull(sign.getProfilePicture())) {
            try {
                user.setProfilePicture(sign.getProfilePicture().getBytes());
            } catch (IOException e) {
                throw new CustomException("Error in parsing profile picture", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return user;
    }

    public OtherProfileDTO getProfile(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new CustomException("User not found",HttpStatus.NOT_FOUND);
        return setUpProfileForOther(userOptional.get());
    }

    private OtherProfileDTO setUpProfileForOther(User user) {
        OtherProfileDTO otherProfileDTO = new OtherProfileDTO();
        otherProfileDTO.setId(user.getId());
        otherProfileDTO.setProfilePicture(encodeImage(user.getProfilePicture()));
        otherProfileDTO.setName(user.getName());
        otherProfileDTO.setUsername(user.getUsername());
        otherProfileDTO.setRegisterDate(user.getCreatedDate());
        otherProfileDTO.setGender(user.getGender());
        return otherProfileDTO;
    }

    private String encodeImage(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private ProfileDTO setUpProfile(User user) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setProfilePicture(encodeImage(user.getProfilePicture()));
        profileDTO.setName(user.getName());
        profileDTO.setUsername(user.getUsername());
        profileDTO.setRegisterDate(user.getCreatedDate());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setPhone(user.getPhone());
        profileDTO.setGender(user.getGender());
        profileDTO.setRole(user.getRole());
        return profileDTO;
    }

    public ProfileDTO getMyProfile() {
        User user = myUserDetailsService.getUserDetails();
        return setUpProfile(user);
    }
}
