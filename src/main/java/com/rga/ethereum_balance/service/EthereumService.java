package com.rga.ethereum_balance.service;

import java.math.BigInteger;

public interface EthereumService {

  BigInteger getBalance(String address);
}
