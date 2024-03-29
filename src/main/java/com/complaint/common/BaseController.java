package com.complaint.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {

    protected <T> ResponseEntity<T> handleResponse(T body) {
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    protected ResponseEntity<Void> handleCreatedResponse() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}