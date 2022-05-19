package com.shopify.challenge.amer.logistics.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptionMessage {
    private String message;
    private String className;
    private String methodName;
}
