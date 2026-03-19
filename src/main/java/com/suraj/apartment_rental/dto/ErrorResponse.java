package com.suraj.apartment_rental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String code;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    // Constructor for simple errors
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
