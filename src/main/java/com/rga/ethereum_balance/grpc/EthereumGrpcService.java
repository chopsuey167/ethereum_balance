package com.rga.ethereum_balance.grpc;

import com.rga.ethereum_balance.grpc.stubs.EthereumServiceGrpc;
import com.rga.ethereum_balance.grpc.stubs.WalletBalanceRequest;
import com.rga.ethereum_balance.grpc.stubs.WalletBalanceResponse;
import com.rga.ethereum_balance.service.EthereumService;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.web3j.exceptions.MessageDecodingException;

@GrpcService
@Slf4j
public class EthereumGrpcService extends EthereumServiceGrpc.EthereumServiceImplBase {

  private final EthereumService ethereumService;

  public EthereumGrpcService(EthereumService ethereumService) {
    this.ethereumService = ethereumService;
  }

  @Override
  public void getWalletBalance(WalletBalanceRequest request, StreamObserver<WalletBalanceResponse> responseObserver) {
    BigInteger balance = null;
    try {
      balance = ethereumService.getBalance(request.getAddress());
      responseObserver.onNext(WalletBalanceResponse.newBuilder()
          .setAddress(request.getAddress())
          .setBalance(balance.longValue()).build());
      responseObserver.onCompleted();
    } catch (MessageDecodingException e) {
      log.error("Error getting balance from address {}", request.getAddress());
      StatusException exception = Status.INTERNAL.withDescription(e.getMessage()).withCause(e).asException();
      responseObserver.onError(exception);
    }
  }

}
