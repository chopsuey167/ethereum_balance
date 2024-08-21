package com.rga.ethereum_balance.service;

import java.io.IOException;
import java.math.BigInteger;

public interface EthereumService {

  BigInteger getBalance(String address) throws IOException;
}
