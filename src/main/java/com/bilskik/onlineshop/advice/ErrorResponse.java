package com.bilskik.onlineshop.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
   private HttpStatus httpStatus;
   @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
   private LocalDateTime timeStamp;
   private String message;
    public ErrorResponse(HttpStatus httpStatus, String message) {
        this();
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public ErrorResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus httpStatus, LocalDateTime timeStamp, String message) {
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
