package com.pavan.auth.service;

import com.pavan.auth.dto.request.LoginRequest;
import com.pavan.auth.dto.request.RegisterRequest;
import com.pavan.auth.dto.response.UserResponse;

public interface UserService {

     UserResponse registerUser(RegisterRequest request);

     String verifyUser(LoginRequest loginRequest);
}
