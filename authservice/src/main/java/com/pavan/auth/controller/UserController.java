package com.pavan.auth.controller;


import com.pavan.auth.dto.request.LoginRequest;
import com.pavan.auth.dto.request.RegisterRequest;
import com.pavan.auth.dto.response.UserResponse;
import com.pavan.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        UserResponse userResponse = userService.registerUser(registerRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userResponse.id())
                .toUri();
        return ResponseEntity.created(location).body(userResponse);
    }

    @GetMapping
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest servletRequest){
        return (CsrfToken) servletRequest.getAttribute("_csrf");
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest){
        return userService.verifyUser(loginRequest);
    }
}
