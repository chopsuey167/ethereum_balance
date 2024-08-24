package com.rga.ethereum_balance.rest.dto;

import java.math.BigInteger;


public record WalletBalanceResponseDto(String address, BigInteger balance) {}
