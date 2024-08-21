package com.rga.ethereum_balance.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Data
public class ErrorDto {

  private HttpStatus status;
  private String message;

}
