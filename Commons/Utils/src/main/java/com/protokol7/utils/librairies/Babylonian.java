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

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

import java.math.BigInteger;

public class Babylonian {

  public BigInteger sqrt (BigInteger x) {
    if (x.equals(ZERO)) {
      return ZERO;
    }

    BigInteger xx = x;
    BigInteger r = ONE;

    if (xx.compareTo(new BigInteger("100000000000000000000000000000000", 16)) >= 0) {
        xx = xx.shiftRight(128);
        r = r.shiftLeft(64);
    }
    if (xx.compareTo(new BigInteger("10000000000000000", 16)) >= 0) {
        xx = xx.shiftRight(64);
        r = r.shiftLeft(32);
    }
    if (xx.compareTo(new BigInteger("100000000", 16)) >= 0) {
        xx = xx.shiftRight(32);
        r = r.shiftLeft(16);
    }
    if (xx.compareTo(new BigInteger("10000", 16)) >= 0) {
        xx = xx.shiftRight(16);
        r = r.shiftLeft(8);
    }
    if (xx.compareTo(new BigInteger("100", 16)) >= 0) {
        xx = xx.shiftRight(8);
        r = r.shiftLeft(4);
    }
    if (xx.compareTo(new BigInteger("10", 16)) >= 0) {
        xx = xx.shiftRight(4);
        r = r.shiftLeft(2);
    }
    if (xx.compareTo(new BigInteger("8", 16)) >= 0) {
        r = r.shiftLeft(1);
    }

    // Seven iterations should be enough
    r = r.add(x.divide(r)).shiftRight(1);
    r = r.add(x.divide(r)).shiftRight(1);
    r = r.add(x.divide(r)).shiftRight(1);
    r = r.add(x.divide(r)).shiftRight(1);
    r = r.add(x.divide(r)).shiftRight(1);
    r = r.add(x.divide(r)).shiftRight(1);
    r = r.add(x.divide(r)).shiftRight(1);

    BigInteger r1 = x.divide(r);

    return r.compareTo(r1) < 0 
      ? r 
      : r1;
  }
}