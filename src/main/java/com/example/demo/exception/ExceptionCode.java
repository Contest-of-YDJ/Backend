package com.example.demo.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_CONFLICT(409, "Member Data Duplicate"),
    MEMBER_UNAUTHORIZED(401, "Member Password Is Fault");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}