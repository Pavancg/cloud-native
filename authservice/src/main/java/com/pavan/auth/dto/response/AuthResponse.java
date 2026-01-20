package com.pavan.auth.dto.response;

public record AuthResponse(
        String token,
        String type,
        long expiresInSeconds
)
{
}
