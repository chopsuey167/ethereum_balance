package com.rga.ethereum_balance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class EthereumBalanceApplicationTests {

  @Test
  void contextLoads() {
  }

}
