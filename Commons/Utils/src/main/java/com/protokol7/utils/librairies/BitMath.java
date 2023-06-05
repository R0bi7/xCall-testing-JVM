/*
 * Copyright 2023 Protokol7
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

package com.protokol7.utils.librairies;

import static java.math.BigInteger.ZERO;
import java.math.BigInteger;

import score.Context;

public class BitMath {

  public byte mostSignificantBit (BigInteger x) {
    Context.require(x.compareTo(ZERO) > 0, "BitMath::mostSignificantBit: zero");

    byte r = 0;

    if (x.compareTo(new BigInteger("100000000000000000000000000000000", 16)) >= 0) {
        x = x.shiftRight(128);
        r += 128;
    }
    if (x.compareTo(new BigInteger("10000000000000000", 16)) >= 0) {
        x = x.shiftRight(64);
        r += 64;
    }
    if (x.compareTo(new BigInteger("100000000", 16)) >= 0) {
        x = x.shiftRight(32);
        r += 32;
    }
    if (x.compareTo(new BigInteger("10000", 16)) >= 0) {
        x = x.shiftRight(16);
        r += 16;
    }
    if (x.compareTo(new BigInteger("100", 16)) >= 0) {
        x = x.shiftRight(8);
        r += 8;
    }
    if (x.compareTo(new BigInteger("10", 16)) >= 0) {
        x = x.shiftRight(4);
        r += 4;
    }
    if (x.compareTo(new BigInteger("4", 16)) >= 0) {
        x = x.shiftRight(2);
        r += 2;
    }
    if (x.compareTo(new BigInteger("2", 16)) >= 0) {
      r += 1;
    }

    return r;
  }
}