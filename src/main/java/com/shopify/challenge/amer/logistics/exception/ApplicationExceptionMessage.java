package com.shopify.challenge.amer.logistics.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationExceptionMessage {
    private String message;

    public ApplicationExceptionMessage(String message) {
        this.message = message;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rejectedValue;

}
