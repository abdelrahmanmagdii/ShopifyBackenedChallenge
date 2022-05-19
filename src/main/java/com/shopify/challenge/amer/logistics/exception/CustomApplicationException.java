package com.shopify.challenge.amer.logistics.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CustomApplicationException extends RuntimeException{
    private String className;
    private String methodName;


    public CustomApplicationException(String msg) { super(msg);}
    public CustomApplicationException() { super(); }

    public CustomApplicationException(String message, String className, String methodName) {
        this(message);
        this.className = className;
        this.methodName = methodName;
    }
}
