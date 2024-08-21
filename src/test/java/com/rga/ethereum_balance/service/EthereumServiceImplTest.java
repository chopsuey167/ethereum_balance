package com.rga.ethereum_balance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.rga.ethereum_balance.exception.EthereumClientException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;

@ExtendWith(MockitoExtension.class)
class EthereumServiceImplTest {

  private static final String ADDRESS = "0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C";
  @InjectMocks
  private EthereumServiceImpl ethereumService;
  @Mock
  private Web3j web3j;
  @Mock
  private Web3jService web3jService;


  @Test
  void getBalance_validAddress_returnBalance() throws IOException {

    //given
    EthGetBalance ethGetBalance = buildEthGetBalance();
    Request requestEthBalance = buildEthRequest();

    when(web3j.ethGetBalance(ADDRESS, DefaultBlockParameterName.LATEST)).thenReturn(requestEthBalance);
    when(web3j.ethGetBalance(ADDRESS, DefaultBlockParameterName.LATEST).send()).thenReturn(ethGetBalance);

    //when
    BigInteger balance = ethereumService.getBalance(ADDRESS);

    //then
    assertNotNull(balance);
    assertEquals(ethGetBalance.getBalance(), balance);

  }

  @Test
  void getBalance_validAddress_throwMessageDecodingException() throws IOException {

    //given
    Request requestEthBalance = buildEthRequest();

    when(web3j.ethGetBalance(ADDRESS, DefaultBlockParameterName.LATEST)).thenReturn(requestEthBalance);
    when(web3j.ethGetBalance(ADDRESS, DefaultBlockParameterName.LATEST).send()).thenThrow(
        MessageDecodingException.class);

    //when
    Executable executable = () -> ethereumService.getBalance(ADDRESS);

    //then
    var exception = assertThrows(MessageDecodingException.class, executable);
    assertEquals(MessageDecodingException.class, exception.getClass());

  }

  @Test
  void getBalance_validAddress_throwEthereumClientException() throws IOException {

    //given
    Request requestEthBalance = buildEthRequest();

    when(web3j.ethGetBalance(ADDRESS, DefaultBlockParameterName.LATEST)).thenReturn(requestEthBalance);
    when(web3j.ethGetBalance(ADDRESS, DefaultBlockParameterName.LATEST).send()).thenThrow(
        IOException.class);

    //when
    Executable executable = () -> ethereumService.getBalance(ADDRESS);

    //then
    var exception = assertThrows(EthereumClientException.class, executable);
    assertEquals(EthereumClientException.class, exception.getClass());

  }

  private Request buildEthRequest() {
    Request requestEthBalance = new Request<>("eth_getBalance",
        Arrays.asList(ADDRESS, DefaultBlockParameterName.LATEST),
        this.web3jService,
        EthGetBalance.class);
    return requestEthBalance;
  }

  private EthGetBalance buildEthGetBalance() {
    EthGetBalance ethGetBalance = new EthGetBalance();
    ethGetBalance.setResult("4122328607366998043");
    return ethGetBalance;
  }
}