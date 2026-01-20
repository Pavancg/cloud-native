package com.pavan.auth.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String fullName,
        String email,
        LocalDateTime createdAt
)
{
}
