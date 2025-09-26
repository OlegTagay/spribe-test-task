package com.spribe.project.models.common;

import com.spribe.project.models.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorResponse {
    private String errorCode;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
