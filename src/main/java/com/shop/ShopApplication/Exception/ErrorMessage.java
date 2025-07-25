package com.shop.ShopApplication.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, ?> fieldErrors;
}
