package com.rga.ethereum_balance.rest.exception;

import com.rga.ethereum_balance.exception.EthereumClientException;
import com.rga.ethereum_balance.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.exceptions.ClientConnectionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static ErrorDto buildErrorDto(String e) {
    return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e);
  }

  @ExceptionHandler(MessageDecodingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> handleMessageDecodingException(MessageDecodingException e) {
    return new ResponseEntity<>(buildErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ClientConnectionException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> handleClientConnectionException(ClientConnectionException e) {

    return new ResponseEntity<>(buildErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EthereumClientException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> handleEthereumClientConnectionException(EthereumClientException e) {
    return new ResponseEntity<>(buildErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
