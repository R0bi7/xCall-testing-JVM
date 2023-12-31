/*
 * Copyright 2021 ICONation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.protokol7.utils;

import static java.math.BigInteger.ZERO;

import java.math.BigInteger;

public class IntUtils {
  public final static BigInteger MAX_UINT8   = new BigInteger("ff", 16);
  public final static BigInteger MAX_UINT16  = new BigInteger("ffff", 16);
  public final static BigInteger MAX_INT32   = new BigInteger("7fffffff", 16);
  public final static BigInteger MAX_UINT32  = new BigInteger("ffffffff", 16);
  public final static BigInteger MAX_UINT64  = new BigInteger("ffffffffffffffff", 16);
  public static final BigInteger MAX_UINT96  = new BigInteger("ffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_UINT112 = new BigInteger("ffffffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_UINT128 = new BigInteger("ffffffffffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_UINT144 = new BigInteger("ffffffffffffffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_UINT160 = new BigInteger("ffffffffffffffffffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_UINT224 = new BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_INT256  = new BigInteger("7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16);
  public final static BigInteger MAX_UINT256 = new BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16);
  public final static BigInteger TWO_POW_96  = MAX_UINT96.add(BigInteger.ONE);
  public final static BigInteger TWO_POW_112 = MAX_UINT112.add(BigInteger.ONE);
  public final static BigInteger TWO_POW_128 = MAX_UINT128.add(BigInteger.ONE);
  public final static BigInteger TWO_POW_144 = MAX_UINT144.add(BigInteger.ONE);
  public final static BigInteger TWO_POW_160 = MAX_UINT160.add(BigInteger.ONE);
  public final static BigInteger TWO_POW_224 = MAX_UINT224.add(BigInteger.ONE);
  public final static BigInteger TWO_POW_256 = MAX_UINT256.add(BigInteger.ONE);

  public static BigInteger uint128(BigInteger n) {
    if (n.compareTo(ZERO) < 0) {
      return n.add(TWO_POW_128);
    }
    return n.mod(TWO_POW_128);
  }

  public static BigInteger uint256(BigInteger n) {
    if (n.compareTo(ZERO) < 0) {
      return n.add(TWO_POW_256);
    }
    return n.mod(TWO_POW_256);
  }

  public static BigInteger uint96(BigInteger n) {
    if (n.compareTo(ZERO) < 0) {
      return n.add(TWO_POW_96);
    }
    return n.mod(TWO_POW_96);
  }

  public static BigInteger uint144(BigInteger n) {
    if (n.compareTo(ZERO) < 0) {
      return n.add(TWO_POW_144);
    }
    return n.mod(TWO_POW_144);
  }

  public static BigInteger uint224(BigInteger n) {
    if (n.compareTo(ZERO) < 0) {
      return n.add(TWO_POW_224);
    }
    return n.mod(TWO_POW_224);
  }

  public static BigInteger uint112(BigInteger n) {
    if (n.compareTo(ZERO) < 0) {
      return n.add(TWO_POW_112);
    }
    return n.mod(TWO_POW_112);
  }
}
