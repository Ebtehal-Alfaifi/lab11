package com.example.lab11.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Api {
    private String message;

    public Api(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
