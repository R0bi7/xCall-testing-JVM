/*
 * Copyright 2020 ICONLOOP Inc.
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

package com.protokol7.example;

import score.Context;
import score.annotation.External;
import score.annotation.Optional;
import score.annotation.Payable;

public class HelloWorld {
    private String name;

    public HelloWorld(String name, @Optional boolean _update) {
        if (_update) {
            // trigger update specific logic in this block
        }

        this.name = name;
    }

    @External()
    public void setName(String name) {
        this.name = name;
    }

    @External(readonly=true)
    public String name() {
        return name;
    }

    @External(readonly=true)
    public String getGreeting() {
        return "Hello " + name + "!";
    }

    @External(readonly=true)
    public String getLongGreeting() {
        return "Hello dear " + name + "!";
    }

    public String test() {
        return "test";
    }

    @Payable
    public void fallback() {
        // just receive incoming funds
    }
}
