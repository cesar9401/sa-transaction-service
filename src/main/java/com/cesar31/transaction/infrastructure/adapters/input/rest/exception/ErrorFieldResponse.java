package com.cesar31.transaction.infrastructure.adapters.input.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorFieldResponse {

    private String message;
    private List<ErrorField> errorFields;
}
