package com.rga.ethereum_balance.rest.exception;

import com.rga.ethereum_balance.exception.EthereumClientException;
import com.rga.ethereum_balance.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.exceptions.ClientConnectionException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static ErrorDto buildErrorDto(String e) {
    return ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e).build();
  }

  @ExceptionHandler(MessageDecodingException.class)
  public ResponseEntity<?> handleMessageDecodingException(MessageDecodingException e) {
    return new ResponseEntity<>(buildErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ClientConnectionException.class)
  public ResponseEntity<?> handleClientConnectionException(ClientConnectionException e) {

    return new ResponseEntity<>(buildErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EthereumClientException.class)
  public ResponseEntity<?> handleEthereumClientConnectionException(EthereumClientException e) {
    return new ResponseEntity<>(buildErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
