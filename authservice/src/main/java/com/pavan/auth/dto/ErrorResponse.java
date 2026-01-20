package com.pavan.auth.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timeStamp,
        Map<String, String> errors
) {

    public ErrorResponse(int status, String message){
        this(status, message, LocalDateTime.now(), null);
    }
}
