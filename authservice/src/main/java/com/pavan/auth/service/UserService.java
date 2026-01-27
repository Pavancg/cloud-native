package com.pavan.auth.service;

import com.pavan.auth.dto.request.LoginRequest;
import com.pavan.auth.dto.request.RegisterRequest;
import com.pavan.auth.dto.response.UserResponse;
import com.pavan.auth.enums.UserStatus;

import java.util.UUID;

public interface UserService {

     UserResponse registerUser(RegisterRequest request);

     String verifyUser(LoginRequest loginRequest);

     void blockUser(UUID userId);

     void suspendUser(UUID userId);

     void activateUser(UUID userId);

     UserStatus getStatus(UUID userId);
}
