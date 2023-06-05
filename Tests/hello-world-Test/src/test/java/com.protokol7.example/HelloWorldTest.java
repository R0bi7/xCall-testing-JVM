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

import com.iconloop.score.test.Account;
import com.iconloop.score.test.ServiceManager;
import com.protokol7.test.ProjectBaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelloWorldTest extends ProjectBaseTest {
    private static final ServiceManager sm = getServiceManager();
    private static final Account owner = sm.createAccount();

    @Test
    void appHasAName() {
        final String name = "Alice";
        HelloWorld classUnderTest = new HelloWorld(name, false);
        assertEquals(classUnderTest.name(), name);
    }

    @Test
    void appHasAGreeting() {
        HelloWorld classUnderTest = new HelloWorld("Alice", false);
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test
    void appHasALongGreeting() {
        HelloWorld classUnderTest = new HelloWorld("Alice", false);
        assertNotNull(classUnderTest.getLongGreeting(), "app should have a long greeting");
    }

    @Test
    void appHasATest() {
        HelloWorld classUnderTest = new HelloWorld("Alice", false);
        assertNotNull(classUnderTest.test(), "app should have a long greeting");
    }

    @Test
    void setName() throws Exception {
        final String alice = "Alice";
        var score = sm.deploy(owner, HelloWorld.class, alice, false);
        assertEquals(alice, score.call("name"));

        final String bob = "Bob";
        score.invoke(owner, "setName", bob);
        assertEquals(bob, score.call("name"));
    }
}
