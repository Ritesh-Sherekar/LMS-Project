package com.example.LMS_ActionService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponses {
    private String message;
    private String details;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
}
