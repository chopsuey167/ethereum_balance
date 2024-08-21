package com.rga.ethereum_balance.rest.exception;

import com.rga.ethereum_balance.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.exceptions.ClientConnectionException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MessageDecodingException.class)
  public ResponseEntity<?> handleMessageDecodingException(MessageDecodingException e) {
    ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e.getMessage()).build();

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ClientConnectionException.class)
  public ResponseEntity<?> handleClientConnectionException(ClientConnectionException e) {
    ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e.getMessage()).build();

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
