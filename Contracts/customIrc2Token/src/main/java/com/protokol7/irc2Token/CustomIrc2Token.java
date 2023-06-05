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

package com.protokol7.irc2Token;

import java.math.BigInteger;
import com.iconloop.score.token.irc2.IRC2Mintable;
import score.Address;
import score.Context;
import score.annotation.External;

public class CustomIrc2Token extends IRC2Mintable {

    public CustomIrc2Token(String _name, String _symbol, int _decimals) {
        super(_name, _symbol, _decimals);
    }

    /**
     * Mint new tokens to multiple addresses at once
     * 
     * Access: IRC2Mintable::minter
     * 
     * @param addresses An array of addresses to send tokens to
     * @param amounts An array of token amounts to mint
     */
    @External
    public void mintToMany (
        Address[] addresses, 
        BigInteger[] amounts
    ) {
        // Make sure lengths are the same
        Context.require(addresses.length == amounts.length, 
            "Arrays length mismatch");
        
        for (int i = 0; i < addresses.length; i++) {
            // mintTo handles the minter authentication
            this.mintTo(addresses[i], amounts[i]);
        }
    }
}
