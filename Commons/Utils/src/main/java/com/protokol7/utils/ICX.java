package com.protokol7.utils;

import java.math.BigInteger;

import score.Address;
import score.Context;

public class ICX {
  public static final Address TOKEN_ADDRESS = Address.fromString("cx0000000000000000000000000000000000000000");
  public static final int DECIMALS = 18;
  public static final String SYMBOL = "ICX";
  public static final BigInteger INITIAL_TOTAL_SUPPLY = BigInteger.valueOf(800_000_000);
  // 10**18
  private final static BigInteger EXA = new BigInteger("1000000000000000000");
  // timestamp of block height 1, in seconds
  private final static BigInteger BLOCK_HEIGHT_1_TIMESTAMP = new BigInteger("1516819217");

  public static void transfer (
    Address targetAddress, 
    BigInteger value
  ) {
    Context.transfer(targetAddress, value);
  }

  public static void transfer (
    Address targetAddress, 
    BigInteger value,
    String method,
    Object... params
  ) {
    Context.call(value, targetAddress, method, params);
  }

  public static BigInteger totalSupply() {
    BigInteger start = BLOCK_HEIGHT_1_TIMESTAMP; 
    BigInteger now = TimeUtils.now();
    BigInteger elapsed = now.subtract(start);
    int years = elapsed.divide(TimeUtils.ONE_YEAR).intValue();

    BigInteger result = INITIAL_TOTAL_SUPPLY;
    for (int i = 0; i < years - 1; i++) {
      // Approximate a 5% inflation rate / year
      result = result.add(result.multiply(BigInteger.valueOf(5)).divide(BigInteger.valueOf(100)));
    }

    return result.multiply(EXA);
  }

  public static boolean isICX (Address token) {
    return token.equals(TOKEN_ADDRESS);
  }
}
