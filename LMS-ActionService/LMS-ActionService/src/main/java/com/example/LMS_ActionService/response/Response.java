package com.example.LMS_ActionService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String message;
    private LocalDateTime localDateTime;
    private T data;
    private int StatusCode;
}
