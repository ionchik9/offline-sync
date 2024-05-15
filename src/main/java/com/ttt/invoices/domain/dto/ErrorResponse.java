package com.ttt.invoices.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private  String timestamp;
    private  int status;
    private  String error;
    private  String path;
}
