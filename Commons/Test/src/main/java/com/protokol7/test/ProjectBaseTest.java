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

package com.protokol7.test;

import com.iconloop.score.test.Account;
import com.iconloop.score.test.Score;
import com.iconloop.score.test.ServiceManager;
import com.iconloop.score.test.TestBase;

import static org.mockito.Mockito.spy;

import java.math.BigInteger;

public class ProjectBaseTest extends TestBase {

    protected final static ServiceManager sm = getServiceManager();

    // Roles
    protected final static Account owner = sm.createAccount(1_000_000_000);
    protected final Account minter = sm.createAccount();
    protected final Account burner = sm.createAccount();
    protected final Account admin = sm.createAccount();

    // User accounts
    protected final Account alice = sm.createAccount();
    protected final Account bob = sm.createAccount();
    protected final Account charlie = sm.createAccount();
    protected final Account eve = sm.createAccount();

    // BigInteger utils
    protected final BigInteger EXA = BigInteger.TEN.pow(18);

    // Deployers
    public static <T> ScoreSpy<T> deploy (Class<?> clazz, Object... params) throws Exception {
        Score score = sm.deploy(owner, clazz, params);

        @SuppressWarnings("unchecked")
        var spy = (T) spy(score.getInstance());
        score.setInstance(spy);
        return new ScoreSpy<T>(score, spy);
    }
}
