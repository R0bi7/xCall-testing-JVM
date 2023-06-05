package com.protokol7.irc2Token;

import static java.math.BigInteger.*;
import java.math.BigInteger;

import com.protokol7.test.ProjectBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.iconloop.score.test.Account;
import com.protokol7.clients.IRC2Client;
import com.protokol7.utils.MathUtils;
import score.Address;

public class MintToManyTest extends CustomIrc2TokenTest {
  
  @BeforeEach
  void setup() throws Exception {
    setup_customIrc2Token("customIrc2Token", "customIrc2Token", 18);
  }

  @Test
  void testMintToMany () {
    Account alice = ProjectBaseTest.sm.createAccount();
    Account bob = ProjectBaseTest.sm.createAccount();
    Account charlie = ProjectBaseTest.sm.createAccount();

    Address[] addresses = {
      alice.getAddress(),
      bob.getAddress(),
      charlie.getAddress()
    };

    BigInteger[] amounts = {
      BigInteger.valueOf(100),
      BigInteger.valueOf(200),
      BigInteger.valueOf(300)
    };

    // Check balances
    Assertions.assertEquals(ZERO, IRC2Client.balanceOf(customIrc2Token.score, alice));
    Assertions.assertEquals(ZERO, IRC2Client.balanceOf(customIrc2Token.score, bob));
    Assertions.assertEquals(ZERO, IRC2Client.balanceOf(customIrc2Token.score, charlie));
    Assertions.assertEquals(ZERO, IRC2Client.totalSupply(customIrc2Token.score));

    customIrc2Token.invoke(ProjectBaseTest.owner, "mintToMany", addresses, amounts);

    // Check balances
    Assertions.assertEquals(amounts[0], IRC2Client.balanceOf(customIrc2Token.score, alice));
    Assertions.assertEquals(amounts[1], IRC2Client.balanceOf(customIrc2Token.score, bob));
    Assertions.assertEquals(amounts[2], IRC2Client.balanceOf(customIrc2Token.score, charlie));
    Assertions.assertEquals(MathUtils.sum(amounts), IRC2Client.totalSupply(customIrc2Token.score));
    
    // Call again and make sure new balances are updated
    customIrc2Token.invoke(ProjectBaseTest.owner, "mintToMany", addresses, amounts);
  
    // Check balances again
    Assertions.assertEquals(TWO.multiply(amounts[0]), IRC2Client.balanceOf(customIrc2Token.score, alice));
    Assertions.assertEquals(TWO.multiply(amounts[1]), IRC2Client.balanceOf(customIrc2Token.score, bob));
    Assertions.assertEquals(TWO.multiply(amounts[2]), IRC2Client.balanceOf(customIrc2Token.score, charlie));
    Assertions.assertEquals(TWO.multiply(MathUtils.sum(amounts)), IRC2Client.totalSupply(customIrc2Token.score));
  }

  @Test
  void testMintToManyOnlyMinter () {
    Account alice = ProjectBaseTest.sm.createAccount();
    Account bob = ProjectBaseTest.sm.createAccount();
    Account charlie = ProjectBaseTest.sm.createAccount();
    Account eve = ProjectBaseTest.sm.createAccount();
    Account minter = ProjectBaseTest.sm.createAccount();

    Address[] addresses = {
      alice.getAddress(),
      bob.getAddress(),
      charlie.getAddress()
    };

    BigInteger[] amounts = {
      BigInteger.valueOf(100),
      BigInteger.valueOf(200),
      BigInteger.valueOf(300)
    };

    // Eve shouldn't be able to call
    Assertions.assertThrows(AssertionError.class,
      () -> customIrc2Token.invoke(eve, "mintToMany", addresses, amounts));

    customIrc2Token.invoke(ProjectBaseTest.owner, "setMinter", minter.getAddress());

    // Still shouldn't be able to call it
    Assertions.assertThrows(AssertionError.class,
    () -> customIrc2Token.invoke(eve, "mintToMany", addresses, amounts));
    
    // owner neither
    Assertions.assertThrows(AssertionError.class,
      () -> customIrc2Token.invoke(ProjectBaseTest.owner, "mintToMany", addresses, amounts));

    // Only minter
    customIrc2Token.invoke(minter, "mintToMany", addresses, amounts);
  }
}
