package com.rga.ethereum_balance.rest.exception;

import com.rga.ethereum_balance.rest.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.web3j.exceptions.MessageDecodingException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MessageDecodingException.class)
  public ResponseEntity<?> handleMessageDecodingException(MessageDecodingException e) {
    ErrorDTO errorDto = ErrorDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e.getMessage()).build();

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
