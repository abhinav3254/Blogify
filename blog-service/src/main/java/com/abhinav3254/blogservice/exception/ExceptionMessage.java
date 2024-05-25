package com.abhinav3254.blogservice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ExceptionMessage {
    private String message;
    private HttpStatus httpStatus;
}
