package com.rga.ethereum_balance.service;

import java.io.IOException;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

@Service
@Slf4j
public class EthereumServiceImpl implements EthereumService {

  private final Web3j web3j;

  public EthereumServiceImpl(Web3j web3j) {
    this.web3j = web3j;
  }

  @Override
  public BigInteger getBalance(String address) throws IOException {
    EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
    log.info("Address: {} , Balance: {}", address, ethGetBalance.getBalance());
    return ethGetBalance.getBalance();
  }
}
