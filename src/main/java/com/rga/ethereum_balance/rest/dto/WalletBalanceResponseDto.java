package com.rga.ethereum_balance.rest.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletBalanceResponseDto {

  String address;
  BigInteger balance;
}
