package com.rga.ethereum_balance.rest.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WalletBalanceResponseDto {

  String address;
  BigInteger balance;
}
