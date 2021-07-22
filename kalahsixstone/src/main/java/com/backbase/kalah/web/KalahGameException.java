package com.backbase.kalah.web;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KalahGameException extends Exception {
    private Integer errorcode;
    private String errormessage;
    private String source;
}
