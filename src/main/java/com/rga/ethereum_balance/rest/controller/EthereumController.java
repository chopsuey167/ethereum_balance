package com.rga.ethereum_balance.rest.controller;

import com.rga.ethereum_balance.rest.dto.WalletBalanceResponseDto;
import com.rga.ethereum_balance.service.EthereumService;
import java.math.BigInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wallets")
public class EthereumController {

  private final EthereumService ethereumService;

  public EthereumController(EthereumService ethereumService) {
    this.ethereumService = ethereumService;
  }

  @GetMapping("/{address}/balance")
  public WalletBalanceResponseDto getWalletBalance(@PathVariable String address) {
    BigInteger balance = ethereumService.getBalance(address);

    return new WalletBalanceResponseDto(address, balance);
  }
}
