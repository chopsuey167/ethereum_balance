package com.rga.ethereum_balance.service;

import com.rga.ethereum_balance.exception.EthereumClientException;
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
  public BigInteger getBalance(String address) {
    try {
      EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
      log.info("Address: {} , Balance: {}", address, ethGetBalance.getBalance());
      return ethGetBalance.getBalance();
    } catch (IOException e) {
      throw new EthereumClientException("Error during connection with Ethereum client", e);
    }
  }
}
