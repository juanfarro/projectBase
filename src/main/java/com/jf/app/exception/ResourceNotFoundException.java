package com.jf.app.exception;

import lombok.Getter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    @Temporal(TemporalType.DATE)
    private Date timestamp;
    private String message;

    public ResourceNotFoundException(Class c, Long id) {
        this.timestamp = new Date();
        this.message = String.format("Resource %s with id %s not found.", c.getName(), id);
    }
}
