package com.pavan.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide proper email")
        String email,

        @NotBlank
        String password
) {
}
