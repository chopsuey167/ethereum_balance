package com.rga.ethereum_balance.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rga.ethereum_balance.rest.dto.WalletBalanceResponseDto;
import com.rga.ethereum_balance.service.EthereumService;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.web3j.exceptions.MessageDecodingException;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
    "grpc.server.port=-1"
})
@AutoConfigureMockMvc
class EthereumControllerTest {

  private static final String BALANCE = "268053760463680861530139";
  private static final String ADDRESS = "0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C";
  private static ObjectMapper mapper = new ObjectMapper();
  @MockBean
  private EthereumService ethereumService;
  @Autowired
  private MockMvc mvc;

  @Test
  void getWalletBalance_validAddress_success() throws Exception {
    //Given
    WalletBalanceResponseDto expectedResponse = new WalletBalanceResponseDto(ADDRESS, new BigInteger(BALANCE));
    when(ethereumService.getBalance(ADDRESS)).thenReturn(new BigInteger(BALANCE));

    //When
    ResultActions response = mvc.perform(get("/api/v1/wallets/" + ADDRESS + "/balance")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    );
    //Then
    response.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
  }

  @Test
  void getWalletBalance_invalidAddress_throwInternalErrorException() throws Exception {
    //Given
    when(ethereumService.getBalance(ADDRESS)).thenThrow(MessageDecodingException.class);

    //When
    ResultActions response = mvc.perform(get("/api/v1/wallets/" + ADDRESS + "/balance")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    );
    //Then
    response.andDo(print())
        .andExpect(status().isBadRequest());
  }
}