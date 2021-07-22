package com.backbase.kalah.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String errorMessage;
}
