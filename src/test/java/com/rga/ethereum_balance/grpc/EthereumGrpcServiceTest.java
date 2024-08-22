package com.rga.ethereum_balance.grpc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.rga.ethereum_balance.grpc.stubs.WalletBalanceRequest;
import com.rga.ethereum_balance.grpc.stubs.WalletBalanceResponse;
import com.rga.ethereum_balance.service.EthereumService;
import io.grpc.internal.testing.StreamRecorder;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.web3j.exceptions.MessageDecodingException;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class EthereumGrpcServiceTest {

  private static final BigInteger BALANCE = new BigInteger("268053760463680861530139");
  private static final String ADDRESS = "0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C";

  @Autowired
  private EthereumGrpcService ethereumGrpcService;
  @MockBean
  private EthereumService ethereumService;

  private static WalletBalanceRequest buildWalletBalanceRequest() {
    return WalletBalanceRequest.newBuilder()
        .setAddress(ADDRESS)
        .build();
  }

  @Test
  void gRpcGetWalletBalance_validAddress_success() {
    //Given
    WalletBalanceRequest request = buildWalletBalanceRequest();

    WalletBalanceResponse expectedResponse = WalletBalanceResponse.newBuilder()
        .setAddress(ADDRESS)
        .setBalance(BALANCE.longValue())
        .build();

    StreamRecorder<WalletBalanceResponse> responseObserver = StreamRecorder.create();

    when(ethereumService.getBalance(ADDRESS)).thenReturn(BALANCE);
    //When

    ethereumGrpcService.getWalletBalance(request, responseObserver);

    //Then
    assertNull(responseObserver.getError());
    List<WalletBalanceResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    WalletBalanceResponse actualResponse = results.get(0);
    assertEquals(expectedResponse, actualResponse);

  }

  @Test
  void gRpcGetWalletBalance_invalidAddress_error() {
    //Given
    WalletBalanceRequest request = buildWalletBalanceRequest();

    StreamRecorder<WalletBalanceResponse> responseObserver = StreamRecorder.create();

    when(ethereumService.getBalance(ADDRESS)).thenThrow(MessageDecodingException.class);
    //When

    ethereumGrpcService.getWalletBalance(request, responseObserver);

    //Then
    assertNotNull(responseObserver.getError());

  }
}