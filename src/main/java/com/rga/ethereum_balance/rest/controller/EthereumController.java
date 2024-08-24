package com.rga.ethereum_balance.rest.controller;

import com.rga.ethereum_balance.rest.dto.WalletBalanceResponseDto;
import com.rga.ethereum_balance.service.EthereumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Get a wallet balance by address")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get a wallet balance successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = WalletBalanceResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid address format",
          content = @Content),
      @ApiResponse(responseCode = "500", description = "Ethereum client error",
          content = @Content)
  })
  @GetMapping("/{address}/balance")
  public WalletBalanceResponseDto getWalletBalance(@PathVariable String address) {
    BigInteger balance = ethereumService.getBalance(address);
    return new WalletBalanceResponseDto(address, balance);
  }
}
