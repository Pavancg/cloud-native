package com.pavan.auth.service;

import com.pavan.auth.dto.request.LoginRequest;
import com.pavan.auth.dto.request.RegisterRequest;
import com.pavan.auth.dto.response.UserResponse;
import com.pavan.auth.entity.User;
import com.pavan.auth.enums.UserStatus;
import com.pavan.auth.exceptions.UserAlreadyExistsException;
import com.pavan.auth.mapper.UserMapper;
import com.pavan.auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("Email is already registered");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(bCryptPasswordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public String verifyUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        if (authentication.isAuthenticated()) {
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            return jwtService.getToken(user.getUUID(), loginRequest.email());
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }

    }

    @Override
    public void blockUser(UUID userId) {
        updateStatus(userId, UserStatus.BLOCKED);
    }

    @Override
    public void suspendUser(UUID userId) {
        updateStatus(userId, UserStatus.SUSPENDED);
    }

    @Override
    public void activateUser(UUID userId) {
        updateStatus(userId, UserStatus.ACTIVE);
    }

    @Override
    public UserStatus getStatus(UUID userId) {
        return userRepository.findById(userId)
                .map(User::getStatus)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void updateStatus(UUID userId, UserStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(status);
        userRepository.save(user);
    }
}
