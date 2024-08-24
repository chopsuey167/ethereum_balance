package com.rga.ethereum_balance.rest.dto;

import org.springframework.http.HttpStatus;

public record ErrorDto(HttpStatus status, String message) {}
