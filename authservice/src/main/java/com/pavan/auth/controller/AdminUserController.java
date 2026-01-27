package com.pavan.auth.controller;

import com.pavan.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @PutMapping("/{userId}/block")
    public ResponseEntity<Void> blockUser(@PathVariable UUID userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/suspend")
    public ResponseEntity<Void> suspendUser(@PathVariable UUID userId) {
        userService.suspendUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable UUID userId) {
        userService.activateUser(userId);
        return ResponseEntity.ok().build();
    }
}
